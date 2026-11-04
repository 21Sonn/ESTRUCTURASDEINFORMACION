// ===================== Nodo =====================
// Cada batería del cañón se guarda dentro de un nodo
class Nodo {
    Object contenido;   // dato que guarda el nodo
    Nodo siguiente;     // nodo que está debajo

    public Nodo(Object contenido) {
        this.contenido = contenido;
    }
}

// ===================== PilaLimitada =====================
// Pila (LIFO) que no deja apilar más de capacidadMaxima elementos
class PilaLimitada {
    Nodo primeroDeLaPila;   // tope de la pila

    int capacidadMaxima;    // límite físico del cañón
    int cantidadActual;     // baterías que hay ahora

    // Constructor: define el límite al crear la pila
    public PilaLimitada(int max) {
        capacidadMaxima = max;
        cantidadActual = 0;
    }

    // Apilar (Push) con validación de capacidad
    public void apilar(Object nuevoObjeto) {
        // Si está llena, mostramos error y no apilamos
        if (cantidadActual >= capacidadMaxima) {
            System.out.println("¡PELIGRO! Cartucho lleno, no se puede apilar: " + nuevoObjeto);
            return;
        }

        // Apilar normal
        Nodo nuevoNodo = new Nodo(nuevoObjeto);
        nuevoNodo.siguiente = primeroDeLaPila;
        primeroDeLaPila = nuevoNodo;

        // Apilamos con éxito: sumamos al contador
        cantidadActual++;
    }

    // Sacar (Pop): devuelve el tope o null si está vacía
    public Object sacar() {
        if (primeroDeLaPila == null) return null;

        Object dato = primeroDeLaPila.contenido;
        primeroDeLaPila = primeroDeLaPila.siguiente;

        // Sacamos un elemento: restamos al contador
        cantidadActual--;

        return dato;
    }
}

// ===================== Main =====================
// Programa de prueba
public class Desafio1 {
    public static void main(String[] args) {
        // Cañón con capacidad para 5 baterías
        PilaLimitada canon = new PilaLimitada(5);

        // Intentamos meter 6 baterías (la sexta debe dar error)
        for (int i = 1; i <= 6; i++) {
            canon.apilar("Batería " + i);
        }

        // Sacamos una y volvemos a intentar
        System.out.println("Sacada: " + canon.sacar());
        canon.apilar("Batería 6");   // ahora sí cabe

        // Vaciamos el cañón disparando
        Object bateria;
        while ((bateria = canon.sacar()) != null) {
            System.out.println("Disparo con: " + bateria);
        }
    }
}