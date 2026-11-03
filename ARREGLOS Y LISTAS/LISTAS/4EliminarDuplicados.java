public class EliminarDuplicados {

    static class Nodo {
        int dato;
        Nodo siguiente;

        Nodo(int dato) {
            this.dato = dato;
        }
    }

    // Lista ordenada: 1->2->2->3->3 se vuelve 1->2->3
    public static void eliminarDuplicados(Nodo cabeza) {
        Nodo actual = cabeza;
        while (actual != null && actual.siguiente != null) {
            if (actual.dato == actual.siguiente.dato) {
                actual.siguiente = actual.siguiente.siguiente; // lo salta
            } else {
                actual = actual.siguiente;
            }
        }
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
        int[] datos = {1, 2, 2, 3, 3};
        Nodo cabeza = new Nodo(datos[0]);
        Nodo cola = cabeza;
        for (int i = 1; i < datos.length; i++) {
            cola.siguiente = new Nodo(datos[i]);
            cola = cola.siguiente;
        }

        System.out.print("Original: ");
        imprimir(cabeza);
        eliminarDuplicados(cabeza);
        System.out.print("Sin duplicados: ");
        imprimir(cabeza);
    }
}