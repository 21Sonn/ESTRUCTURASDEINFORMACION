package ESTRUCTURASDEINFORMACION.arbolram;

public class RadarHeimdall {

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

    public void insertar(int amenaza) {
        raiz = insertarRec(raiz, amenaza);
    }

    private Nodo insertarRec(Nodo n, int amenaza) {
        if (n == null) return new Nodo(amenaza);
        if (amenaza < n.dato) {
            n.izquierdo = insertarRec(n.izquierdo, amenaza);
        } else if (amenaza > n.dato) {
            n.derecho = insertarRec(n.derecho, amenaza);
        }
        return n;
    }

    public boolean buscar(int amenaza) {
        Nodo actual = raiz;
        while (actual != null) {
            if (amenaza == actual.dato) {
                return true;
            }
            if (amenaza < actual.dato) {
                actual = actual.izquierdo;
            } else {
                actual = actual.derecho;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        RadarHeimdall radar = new RadarHeimdall();

        System.out.println("Radar vacío, buscar 10: " + radar.buscar(10));

        int[] amenazas = {50, 30, 70, 20, 40, 60, 80};
        for (int a : amenazas) {
            radar.insertar(a);
        }

        System.out.println("Buscar 40: " + radar.buscar(40));
        System.out.println("Buscar 60: " + radar.buscar(60));
        System.out.println("Buscar 50: " + radar.buscar(50));
        System.out.println("Buscar 25: " + radar.buscar(25));
        System.out.println("Buscar 90: " + radar.buscar(90));
    }
}