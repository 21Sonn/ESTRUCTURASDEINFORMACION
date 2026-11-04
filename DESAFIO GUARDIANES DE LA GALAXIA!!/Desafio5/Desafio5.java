// ===================== Nodo =====================
// Solo lo usa la clase Pila internamente
class Nodo {
    Object contenido;
    Nodo siguiente;

    public Nodo(Object contenido) {
        this.contenido = contenido;
    }
}

// ===================== Pila (LIFO) =====================
class Pila {
    Nodo primeroDeLaPila;

    // Apilar (Push)
    public void apilar(Object nuevoObjeto) {
        Nodo nuevoNodo = new Nodo(nuevoObjeto);
        nuevoNodo.siguiente = primeroDeLaPila;
        primeroDeLaPila = nuevoNodo;
    }

    // Sacar (Pop)
    public Object sacar() {
        if (primeroDeLaPila == null) return null;
        Object dato = primeroDeLaPila.contenido;
        primeroDeLaPila = primeroDeLaPila.siguiente;
        return dato;
    }

    // NUEVO: indica si la pila no tiene elementos
    public boolean estaVacia() {
        return primeroDeLaPila == null;
    }
}

// ===================== ColaNebula =====================
// Cola (FIFO) construida usando únicamente dos pilas
class ColaNebula {
    Pila pilaEntrada;   // aquí se guardan los elementos que llegan
    Pila pilaSalida;    // de aquí se sacan, ya en orden FIFO

    // Constructor: creamos las dos pilas vacías
    public ColaNebula() {
        pilaEntrada = new Pila();
        pilaSalida = new Pila();
    }

    // Enfilar: siempre se apila en la pila de entrada
    public void enfilar(Object obj) {
        pilaEntrada.apilar(obj);
    }

    // Sacar: el primero que entró debe ser el primero en salir
    public Object sacar() {
        // Solo si la pila de salida está vacía, traspasamos TODO
        // de la entrada a la salida (esto invierte el orden)
        if (pilaSalida.estaVacia()) {
            while (!pilaEntrada.estaVacia()) {
                pilaSalida.apilar(pilaEntrada.sacar());
            }
        }
        // Ahora el tope de pilaSalida es el elemento más antiguo.
        // Si ambas pilas estaban vacías, sacar() devuelve null.
        return pilaSalida.sacar();
    }

    // NUEVO: la cola está vacía si ambas pilas lo están
    public boolean estaVacia() {
        return pilaEntrada.estaVacia() && pilaSalida.estaVacia();
    }
}

// ===================== Main =====================
public class Desafio5 {
    public static void main(String[] args) {
        ColaNebula cola = new ColaNebula();

        // Llegan tres comandos
        cola.enfilar("Comando 1");
        cola.enfilar("Comando 2");
        cola.enfilar("Comando 3");

        System.out.println("Sacado: " + cola.sacar());   // Comando 1

        // Llega otro comando mientras la salida aún tiene elementos
        cola.enfilar("Comando 4");

        // Vaciamos la cola
        while (!cola.estaVacia()) {
            System.out.println("Sacado: " + cola.sacar());
        }

        // Intentamos sacar de una cola vacía
        if (cola.sacar() == null) {
            System.out.println("La cola está vacía.");
        }
    }
}