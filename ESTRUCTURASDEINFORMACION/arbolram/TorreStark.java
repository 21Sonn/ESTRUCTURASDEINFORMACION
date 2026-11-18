package ESTRUCTURASDEINFORMACION.arbolram;

public class TorreStark {

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

    public void insertar(int codigo) {
        raiz = insertarRec(raiz, codigo);
    }

    private Nodo insertarRec(Nodo n, int codigo) {
        if (n == null) return new Nodo(codigo);
        if (codigo < n.dato) {
            n.izquierdo = insertarRec(n.izquierdo, codigo);
        } else if (codigo > n.dato) {
            n.derecho = insertarRec(n.derecho, codigo);
        }
        return n;
    }

    public int calcularAltura() {
        return alturaRec(raiz);
    }

    private int alturaRec(Nodo n) {
        if (n == null) return 0;
        return 1 + Math.max(alturaRec(n.izquierdo), alturaRec(n.derecho));
    }

    public static void main(String[] args) {
        TorreStark torre = new TorreStark();

        System.out.println("Torre vacía: " + torre.calcularAltura());

        torre.insertar(50);
        System.out.println("Con 1 nodo: " + torre.calcularAltura());

        int[] codigos = {30, 70, 20, 40, 60, 80};
        for (int c : codigos) {
            torre.insertar(c);
        }
        System.out.println("Con 7 nodos balanceados: " + torre.calcularAltura());

        torre.insertar(99);
        System.out.println("Tras insertar 99: " + torre.calcularAltura());

        TorreStark cadena = new TorreStark();
        int[] ordenados = {10, 20, 30, 40, 50};
        for (int c : ordenados) {
            cadena.insertar(c);
        }
        System.out.println("5 nodos en cadena: " + cadena.calcularAltura());
    }
}