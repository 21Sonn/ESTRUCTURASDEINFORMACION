public class NodoDesdeElFinal {

    static class Nodo {
        int dato;
        Nodo siguiente;

        Nodo(int dato) {
            this.dato = dato;
        }
    }

    // Devuelve el nodo N desde el final (N = 1 es el último) sin saber el tamaño
    public static Nodo nodoDesdeElFinal(Nodo cabeza, int n) {
        Nodo rapido = cabeza;
        Nodo lento = cabeza;

        // Avanza el Rápido N pasos primero
        for (int i = 0; i < n; i++) {
            if (rapido == null) {
                return null; // N es mayor que el tamaño de la lista
            }
            rapido = rapido.siguiente;
        }

        // Mueve ambos hasta que Rápido sea null
        while (rapido != null) {
            rapido = rapido.siguiente;
            lento = lento.siguiente;
        }
        return lento;
    }

    public static void main(String[] args) {
        Nodo cabeza = new Nodo(1);
        cabeza.siguiente = new Nodo(2);
        cabeza.siguiente.siguiente = new Nodo(3);
        cabeza.siguiente.siguiente.siguiente = new Nodo(4);
        cabeza.siguiente.siguiente.siguiente.siguiente = new Nodo(5);
        cabeza.siguiente.siguiente.siguiente.siguiente.siguiente = new Nodo(6);

        Nodo penultimo = nodoDesdeElFinal(cabeza, 2);
        System.out.println("Penúltimo nodo: " + (penultimo != null ? penultimo.dato : "no existe"));
    }
}