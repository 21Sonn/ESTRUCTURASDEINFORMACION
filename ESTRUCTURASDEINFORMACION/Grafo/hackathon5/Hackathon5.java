import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.alg.shortestpath.FloydWarshallShortestPaths;
import org.jgrapht.alg.shortestpath.YenKShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.jgrapht.traverse.BreadthFirstIterator;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Hackathon 3 horas - Equipo 5: Vuelos Nacionales (Avianca).
 * Backend con JGraphT: Ruta Optima (Dijkstra), Zonas de Influencia (BFS) y servidor HTTP para el frontend.
 */
public class Hackathon5 {

    // ============ DATASET OFICIAL (copiado tal cual, sin modificar tiempos) ============
    static final String[] NODOS = {
        "BOG-Bogota", "MDE-Medellin", "CLO-Cali", "CTG-Cartagena", "BAQ-B/quilla",
        "SMR-S.Marta", "PEI-Pereira", "BGA-B/manga", "CUC-Cucuta", "AXM-Armenia",
        "VUP-V/dupar", "NVA-Neiva", "LET-Leticia", "MZL-Manizales", "EYP-Yopal"
    };

    static final Object[][] RUTAS = {
        {"BOG-Bogota", "MDE-Medellin", 22},
        {"MDE-Medellin", "CLO-Cali", 9},
        {"CLO-Cali", "CTG-Cartagena", 12},
        {"CTG-Cartagena", "BAQ-B/quilla", 20},
        {"BAQ-B/quilla", "SMR-S.Marta", 8},
        {"SMR-S.Marta", "PEI-Pereira", 24},
        {"PEI-Pereira", "BGA-B/manga", 21},
        {"BGA-B/manga", "CUC-Cucuta", 11},
        {"CUC-Cucuta", "AXM-Armenia", 18},
        {"AXM-Armenia", "VUP-V/dupar", 15},
        {"VUP-V/dupar", "NVA-Neiva", 21},
        {"NVA-Neiva", "LET-Leticia", 22},
        {"LET-Leticia", "MZL-Manizales", 15},
        {"MZL-Manizales", "EYP-Yopal", 14},
        {"BOG-Bogota", "BAQ-B/quilla", 30},
        {"CTG-Cartagena", "BGA-B/manga", 22},
        {"PEI-Pereira", "VUP-V/dupar", 16},
        {"AXM-Armenia", "MZL-Manizales", 39},
        {"CLO-Cali", "LET-Leticia", 18},
        {"MDE-Medellin", "EYP-Yopal", 44}
    };

    // ============ GRAFO ============
    private final Graph<String, DefaultWeightedEdge> grafo =
            new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);

    public Hackathon5() {
        for (String nodo : NODOS) grafo.addVertex(nodo);
        for (Object[] r : RUTAS) {
            DefaultWeightedEdge e = grafo.addEdge((String) r[0], (String) r[1]);
            if (e == null) throw new IllegalStateException("Ruta duplicada en el dataset: " + r[0] + " -> " + r[1]);
            grafo.setEdgeWeight(e, ((Number) r[2]).doubleValue());
        }
    }

    // ============ LOGICA REQUERIDA ============

    /** Ruta Optima (Dijkstra). Devuelve null si no hay ruta. */
    public GraphPath<String, DefaultWeightedEdge> rutaOptima(String origen, String destino) {
        return new DijkstraShortestPath<>(grafo).getPath(origen, destino);
    }

    /** Zonas de Influencia (BFS): niveles.get(0) = estaciones a 1 salto, niveles.get(1) = a 2 saltos, etc. */
    public List<List<String>> zonaInfluencia(String inicio, int maxSaltos) {
        List<List<String>> niveles = new ArrayList<>();
        BreadthFirstIterator<String, DefaultWeightedEdge> bfs = new BreadthFirstIterator<>(grafo, inicio);
        while (bfs.hasNext()) {
            String v = bfs.next();
            int nivel = bfs.getDepth(v);
            if (nivel > maxSaltos) break;
            if (nivel == 0) continue;
            while (niveles.size() < nivel) niveles.add(new ArrayList<>());
            niveles.get(nivel - 1).add(v);
        }
        return niveles;
    }

    /** Extra: k rutas mas cortas sin repetir estaciones (Yen). */
    public List<GraphPath<String, DefaultWeightedEdge>> rutasAlternativas(String origen, String destino, int k) {
        return new YenKShortestPath<>(grafo).getPaths(origen, destino, k);
    }

    public boolean existe(String estacion) {
        return estacion != null && grafo.containsVertex(estacion);
    }

    // ============ VERIFICACION ============
    /** Compara Dijkstra contra Floyd-Warshall en todos los pares. Devuelve el numero de diferencias. */
    public int verificarContraFloyd() {
        DijkstraShortestPath<String, DefaultWeightedEdge> dijkstra = new DijkstraShortestPath<>(grafo);
        FloydWarshallShortestPaths<String, DefaultWeightedEdge> floyd = new FloydWarshallShortestPaths<>(grafo);
        int diferencias = 0;
        for (String s : grafo.vertexSet())
            for (String t : grafo.vertexSet())
                if (dijkstra.getPathWeight(s, t) != floyd.getPathWeight(s, t)) diferencias++;
        return diferencias;
    }

    // ============ PRESENTACION EN CONSOLA ============
    static String texto(GraphPath<String, DefaultWeightedEdge> p) {
        return String.join(" -> ", p.getVertexList());
    }

    static String describir(GraphPath<String, DefaultWeightedEdge> p) {
        return texto(p) + " (" + (int) p.getWeight() + " min, " + p.getLength() + " tramos)";
    }

    // ============ SERVIDOR HTTP PARA EL FRONTEND ============
    static String esc(String s) {
        return s.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    static String jsonLista(List<String> lista) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < lista.size(); i++) sb.append(i > 0 ? "," : "").append("\"").append(esc(lista.get(i))).append("\"");
        return sb.append("]").toString();
    }

    static String jsonRuta(GraphPath<String, DefaultWeightedEdge> p) {
        return "{\"estaciones\":" + jsonLista(p.getVertexList()) + ",\"minutos\":" + (int) p.getWeight()
                + ",\"tramos\":" + p.getLength() + "}";
    }

    static Map<String, String> consulta(HttpExchange ex) throws IOException {
        Map<String, String> q = new HashMap<>();
        String raw = ex.getRequestURI().getRawQuery();
        if (raw == null) return q;
        for (String par : raw.split("&")) {
            String[] kv = par.split("=", 2);
            q.put(URLDecoder.decode(kv[0], "UTF-8"), kv.length > 1 ? URLDecoder.decode(kv[1], "UTF-8") : "");
        }
        return q;
    }

    static void responder(HttpExchange ex, int codigo, String tipo, byte[] cuerpo) throws IOException {
        ex.getResponseHeaders().add("Content-Type", tipo);
        ex.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        ex.sendResponseHeaders(codigo, cuerpo.length);
        ex.getResponseBody().write(cuerpo);
        ex.close();
    }

    static void json(HttpExchange ex, int codigo, String cuerpo) throws IOException {
        responder(ex, codigo, "application/json; charset=utf-8", cuerpo.getBytes(StandardCharsets.UTF_8));
    }

    void servir(int puerto) throws IOException {
        HttpServer s = HttpServer.create(new InetSocketAddress(puerto), 0);

        s.createContext("/", ex -> {
            Path pagina = Paths.get("simulador.html");
            if (ex.getRequestURI().getPath().equals("/") && Files.exists(pagina)) {
                responder(ex, 200, "text/html; charset=utf-8", Files.readAllBytes(pagina));
            } else {
                json(ex, 404, "{\"error\":\"no encontrado\"}");
            }
        });

        s.createContext("/api/red", ex -> {
            StringBuilder sb = new StringBuilder("{\"nodos\":").append(jsonLista(new ArrayList<>(grafo.vertexSet()))).append(",\"rutas\":[");
            boolean primera = true;
            for (DefaultWeightedEdge e : grafo.edgeSet()) {
                sb.append(primera ? "" : ",").append("{\"origen\":\"").append(esc(grafo.getEdgeSource(e)))
                  .append("\",\"destino\":\"").append(esc(grafo.getEdgeTarget(e)))
                  .append("\",\"minutos\":").append((int) grafo.getEdgeWeight(e)).append("}");
                primera = false;
            }
            json(ex, 200, sb.append("]}").toString());
        });

        s.createContext("/api/ruta", ex -> {
            try {
                Map<String, String> q = consulta(ex);
                String o = q.get("origen"), d = q.get("destino");
                if (!existe(o) || !existe(d)) { json(ex, 400, "{\"error\":\"estacion desconocida\"}"); return; }
                int k = Math.max(1, Math.min(10, Integer.parseInt(q.getOrDefault("k", "1"))));
                GraphPath<String, DefaultWeightedEdge> optima = rutaOptima(o, d);
                StringBuilder sb = new StringBuilder("{\"optima\":");
                sb.append(optima == null ? "null" : jsonRuta(optima)).append(",\"alternativas\":[");
                if (optima != null) {
                    List<GraphPath<String, DefaultWeightedEdge>> todas = rutasAlternativas(o, d, k);
                    for (int i = 1; i < todas.size(); i++) sb.append(i > 1 ? "," : "").append(jsonRuta(todas.get(i)));
                }
                json(ex, 200, sb.append("]}").toString());
            } catch (RuntimeException e) {
                json(ex, 400, "{\"error\":\"parametros invalidos\"}");
            }
        });

        s.createContext("/api/zona", ex -> {
            try {
                Map<String, String> q = consulta(ex);
                String inicio = q.get("inicio");
                if (!existe(inicio)) { json(ex, 400, "{\"error\":\"estacion desconocida\"}"); return; }
                int saltos = Math.max(1, Math.min(14, Integer.parseInt(q.getOrDefault("saltos", "2"))));
                List<List<String>> niveles = zonaInfluencia(inicio, saltos);
                StringBuilder sb = new StringBuilder("{\"inicio\":\"" + esc(inicio) + "\",\"niveles\":[");
                for (int i = 0; i < niveles.size(); i++) sb.append(i > 0 ? "," : "").append(jsonLista(niveles.get(i)));
                json(ex, 200, sb.append("]}").toString());
            } catch (RuntimeException e) {
                json(ex, 400, "{\"error\":\"parametros invalidos\"}");
            }
        });

        s.start();
        System.out.println("\nServidor listo: abre http://localhost:" + puerto + "  (Ctrl+C para detener)");
    }

    // ============ MAIN ============
    public static void main(String[] args) throws IOException {
        Hackathon5 app = new Hackathon5();

        System.out.println("== 1. Dataset cargado ==");
        System.out.println("Aeropuertos: " + app.grafo.vertexSet().size() + ", rutas: " + app.grafo.edgeSet().size());

        System.out.println("\n== 2. Ruta optima (Dijkstra) ==");
        String[][] consultas = {
            {"BOG-Bogota", "EYP-Yopal"},
            {"BOG-Bogota", "CUC-Cucuta"},
            {"PEI-Pereira", "MZL-Manizales"},
            {"EYP-Yopal", "BOG-Bogota"}
        };
        for (String[] c : consultas) {
            GraphPath<String, DefaultWeightedEdge> p = app.rutaOptima(c[0], c[1]);
            System.out.println(c[0] + " => " + c[1] + ": " + (p == null ? "sin ruta (el dataset es dirigido)" : describir(p)));
        }

        System.out.println("\n== 3. Zonas de influencia (BFS) desde BOG-Bogota, hasta 2 saltos ==");
        List<List<String>> zona = app.zonaInfluencia("BOG-Bogota", 2);
        for (int i = 0; i < zona.size(); i++) System.out.println("Nivel " + (i + 1) + ": " + zona.get(i));

        System.out.println("\n== 4. Tres mejores rutas PEI-Pereira => MZL-Manizales (Yen) ==");
        List<GraphPath<String, DefaultWeightedEdge>> alternativas = app.rutasAlternativas("PEI-Pereira", "MZL-Manizales", 3);
        for (int i = 0; i < alternativas.size(); i++) System.out.println((i + 1) + ". " + describir(alternativas.get(i)));

        System.out.println("\n== 5. Verificacion Dijkstra frente a Floyd-Warshall (225 pares) ==");
        int dif = app.verificarContraFloyd();
        System.out.println(dif == 0 ? "OK, sin diferencias" : "FALLO: " + dif + " diferencias");

        if (args.length > 0 && args[0].equals("serve")) app.servir(8080);
    }
}
