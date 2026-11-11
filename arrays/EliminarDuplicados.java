public class EliminarDuplicados {

    static class Nodo {
        int dato;
        Nodo siguiente;

        Nodo(int dato) {
            this.dato = dato;
        }
    }

    // Funciona porque la lista está ORDENADA: los duplicados quedan juntos
    public static void eliminarDuplicados(Nodo cabeza) {
        // Empezamos en la cabeza
        Nodo actual = cabeza;
        // Necesitamos que existan el actual y su siguiente para poder compararlos
        while (actual != null && actual.siguiente != null) {
            // Si el dato es igual al del siguiente, el siguiente sobra
            if (actual.dato == actual.siguiente.dato) {
                // Lo saltamos: actual apunta al nodo que viene después del duplicado
                // NO avanzamos, porque el nuevo siguiente también podría ser igual
                actual.siguiente = actual.siguiente.siguiente;
            } else {
                // Son distintos: avanzamos al siguiente nodo
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
        // Construimos 1 -> 2 -> 2 -> 3 -> 3 -> null a partir de un arreglo
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
        // Imprime: 1 -> 2 -> 3 -> null
        System.out.print("Sin duplicados: ");
        imprimir(cabeza);
    }
}