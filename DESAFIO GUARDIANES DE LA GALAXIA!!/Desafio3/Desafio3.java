// ===================== Nodo =====================
class Nodo {
    Object contenido;   // dato guardado
    Nodo siguiente;     // nodo que viene después

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

// ===================== Cola (FIFO) =====================
class Cola {
    Nodo primeroEnLaCola;
    Nodo ultimoEnLaCola;

    // Insertar al final (Enfilar)
    public void enfilar(Object nuevoObjeto) {
        Nodo nuevoNodo = new Nodo(nuevoObjeto);

        if (primeroEnLaCola == null) {
            primeroEnLaCola = nuevoNodo;
        } else {
            ultimoEnLaCola.siguiente = nuevoNodo;
        }
        ultimoEnLaCola = nuevoNodo;
    }

    // Eliminar del principio (Desenfilar)
    public Object sacar() {
        if (primeroEnLaCola == null) return null;

        Object dato = primeroEnLaCola.contenido;
        primeroEnLaCola = primeroEnLaCola.siguiente;

        if (primeroEnLaCola == null) ultimoEnLaCola = null; // si quedó vacía
        return dato;
    }

    // NUEVO: indica si la cola no tiene elementos
    public boolean estaVacia() {
        return primeroEnLaCola == null;
    }

    // NUEVO: imprime la cola sin modificarla (solo para la prueba)
    public void mostrar() {
        Nodo actual = primeroEnLaCola;
        System.out.print("Cola (frente -> final): ");
        while (actual != null) {
            System.out.print(actual.contenido);
            if (actual.siguiente != null) System.out.print(" -> ");
            actual = actual.siguiente;
        }
        System.out.println();
    }
}

// ===================== Main =====================
public class Desafio3 {

    // Invierte el orden de los elementos de la cola usando una pila temporal
    public static void invertirCola(Cola cola) {
        // 1. Pila auxiliar: al ser LIFO, invierte el orden naturalmente
        Pila temp = new Pila();

        // 2. Pasamos todo de la cola a la pila (la cola queda vacía)
        while (!cola.estaVacia()) {
            temp.apilar(cola.sacar());
        }

        // 3. Pasamos todo de la pila a la cola (ahora en orden inverso)
        while (!temp.estaVacia()) {
            cola.enfilar(temp.sacar());
        }
    }

    public static void main(String[] args) {
        // Groot introdujo las coordenadas en orden inverso (5 a 1)
        Cola coordenadas = new Cola();
        coordenadas.enfilar("Coordenada 5");
        coordenadas.enfilar("Coordenada 4");
        coordenadas.enfilar("Coordenada 3");
        coordenadas.enfilar("Coordenada 2");
        coordenadas.enfilar("Coordenada 1");

        System.out.println("Antes de invertir:");
        coordenadas.mostrar();

        invertirCola(coordenadas);

        System.out.println("Después de invertir:");
        coordenadas.mostrar();
    }
}