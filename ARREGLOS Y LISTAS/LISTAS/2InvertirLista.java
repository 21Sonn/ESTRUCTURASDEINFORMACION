public class InvertirLista {

    static class Nodo {
        int dato;
        Nodo siguiente;

        Nodo(int dato) {
            this.dato = dato;
        }
    }

    // Voltea las flechas: A->B->null se vuelve B->A->null
    public static Nodo invertir(Nodo cabeza) {
        Nodo prev = null;
        Nodo actual = cabeza;
        while (actual != null) {
            Nodo sig = actual.siguiente;
            actual.siguiente = prev;
            prev = actual;
            actual = sig;
        }
        return prev; // nueva cabeza
    }

    public static void imprimir(Nodo cabeza) {
        Nodo actual = cabeza;
        while (actual != null) {
            System.out.print(actual.dato + " -> ");
            actual = actual.siguiente;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        Nodo cabeza = new Nodo(1);
        cabeza.siguiente = new Nodo(2);
        cabeza.siguiente.siguiente = new Nodo(3);

        System.out.print("Original: ");
        imprimir(cabeza);
        cabeza = invertir(cabeza);
        System.out.print("Invertida: ");
        imprimir(cabeza);
    }
}