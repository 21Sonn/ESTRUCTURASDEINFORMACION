package ESTRUCTURASDEINFORMACION.arbolram;

public class Multiverso {

    static class Nodo {
        int dato;
        Nodo izquierdo;
        Nodo derecho;

        public Nodo(int dato) {
            this.dato = dato;
            this.izquierdo = null;
            this.derecho = null;
        }
    }

    private Nodo raiz;

    public void insertar(int linea) {
        raiz = insertarRec(raiz, linea);
    }

    private Nodo insertarRec(Nodo n, int linea) {
        if (n == null) return new Nodo(linea);
        if (linea < n.dato) {
            n.izquierdo = insertarRec(n.izquierdo, linea);
        } else if (linea > n.dato) {
            n.derecho = insertarRec(n.derecho, linea);
        }
        return n;
    }

    public int contarLineas() {
        return contarRec(raiz);
    }

    private int contarRec(Nodo n) {
        if (n == null) return 0;
        return 1 + contarRec(n.izquierdo) + contarRec(n.derecho);
    }

    public static void main(String[] args) {
        Multiverso multiverso = new Multiverso();

        System.out.println("Multiverso vacío: " + multiverso.contarLineas());

        multiverso.insertar(50);
        System.out.println("Con 1 línea temporal: " + multiverso.contarLineas());

        int[] lineas = {30, 70, 20, 40, 60, 80};
        for (int l : lineas) {
            multiverso.insertar(l);
        }
        System.out.println("Con 7 líneas temporales: " + multiverso.contarLineas());

        multiverso.insertar(40); // duplicado, no se agrega
        System.out.println("Tras insertar un duplicado: " + multiverso.contarLineas());

        multiverso.insertar(99);
        System.out.println("Tras insertar 99: " + multiverso.contarLineas());
    }
}