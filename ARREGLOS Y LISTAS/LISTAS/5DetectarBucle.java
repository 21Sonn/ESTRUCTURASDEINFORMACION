public class DetectarBucle {

    static class Nodo {
        int dato;
        Nodo siguiente;

        Nodo(int dato) {
            this.dato = dato;
        }
    }

    // Liebre y Tortuga: ¿la lista es infinita?
    public static boolean tieneBucle(Nodo cabeza) {
        Nodo lento = cabeza;
        Nodo rapido = cabeza;
        while (rapido != null && rapido.siguiente != null) {
            lento = lento.siguiente;                // 1 paso
            rapido = rapido.siguiente.siguiente;    // 2 pasos
            if (lento == rapido) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Nodo a = new Nodo(1);
        Nodo b = new Nodo(2);
        Nodo c = new Nodo(3);
        Nodo d = new Nodo(4);
        a.siguiente = b;
        b.siguiente = c;
        c.siguiente = d;

        System.out.println("¿Lista normal tiene bucle? " + tieneBucle(a));

        d.siguiente = b; // crea un ciclo
        System.out.println("¿Lista con ciclo tiene bucle? " + tieneBucle(a));
    }
}