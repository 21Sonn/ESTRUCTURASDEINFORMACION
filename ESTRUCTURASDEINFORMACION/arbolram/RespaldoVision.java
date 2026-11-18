package ESTRUCTURASDEINFORMACION.arbolram;

public class RespaldoVision {

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

    public void insertar(int valor) {
        raiz = insertarRec(raiz, valor);
    }

    private Nodo insertarRec(Nodo n, int valor) {
        if (n == null) return new Nodo(valor);
        if (valor < n.dato) {
            n.izquierdo = insertarRec(n.izquierdo, valor);
        } else if (valor > n.dato) {
            n.derecho = insertarRec(n.derecho, valor);
        }
        return n;
    }

    // Recorrido PreOrden: Raíz -> Izq -> Der
    public void preOrden() {
        preOrdenRec(raiz);
        System.out.println();
    }

    private void preOrdenRec(Nodo n) {
        if (n == null) return;
        System.out.print(n.dato + " ");
        preOrdenRec(n.izquierdo);
        preOrdenRec(n.derecho);
    }

    // Clonación basada en PreOrden: primero la raíz, luego los hijos
    public RespaldoVision clonar() {
        RespaldoVision respaldo = new RespaldoVision();
        respaldo.raiz = clonarRec(raiz);
        return respaldo;
    }

    private Nodo clonarRec(Nodo n) {
        if (n == null) return null;
        Nodo copia = new Nodo(n.dato);
        copia.izquierdo = clonarRec(n.izquierdo);
        copia.derecho = clonarRec(n.derecho);
        return copia;
    }

    public static void main(String[] args) {
        RespaldoVision vision = new RespaldoVision();
        int[] neuronas = {50, 30, 70, 20, 40, 60, 80};
        for (int n : neuronas) {
            vision.insertar(n);
        }

        RespaldoVision respaldo = vision.clonar();

        System.out.print("Original PreOrden: ");
        vision.preOrden();
        System.out.print("Respaldo PreOrden: ");
        respaldo.preOrden();

        System.out.println("¿Misma raíz (referencia)? " + (vision.raiz == respaldo.raiz));

        vision.insertar(99);
        System.out.println("Tras insertar 99 en el original:");
        System.out.print("Original PreOrden: ");
        vision.preOrden();
        System.out.print("Respaldo PreOrden: ");
        respaldo.preOrden();
    }
}