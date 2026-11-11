// ===================== Nodo =====================
class Nodo {
    Object contenido;   // dato guardado
    Nodo siguiente;     // nodo que está debajo

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

    // NUEVO: mira la cima sin sacarla
    public Object verTope() {
        if (primeroDeLaPila == null) return null;
        return primeroDeLaPila.contenido;
    }

    // NUEVO: imprime la pila sin modificarla (solo para la prueba)
    public void mostrar() {
        Nodo actual = primeroDeLaPila;
        System.out.print("Pila (tope -> base): ");
        while (actual != null) {
            System.out.print(actual.contenido);
            if (actual.siguiente != null) System.out.print(" -> ");
            actual = actual.siguiente;
        }
        System.out.println();
    }
}

// ===================== Main =====================
public class Desafio6 {

    // Ordena la pila dejando la gema de mayor poder en la cima
    public static void ordenarPila(Pila gemas) {
        // Única estructura auxiliar permitida
        Pila auxiliar = new Pila();

        // Ciclo maestro: procesamos todas las gemas de la pila original
        while (!gemas.estaVacia()) {
            // 1. Sacamos una gema y la guardamos en una variable simple
            int temp = (Integer) gemas.sacar();

            // 2. Devolvemos a la original las gemas de la auxiliar
            //    que sean menores que temp, para que temp quede sobre las mayores
            while (!auxiliar.estaVacia() && (Integer) auxiliar.verTope() < temp) {
                gemas.apilar(auxiliar.sacar());
            }

            // 3. Ya encontramos su lugar: apilamos temp en la auxiliar
            auxiliar.apilar(temp);
        }

        // La auxiliar tiene la menor arriba; al pasarla de vuelta
        // se invierte y la mayor queda en la cima de la original
        while (!auxiliar.estaVacia()) {
            gemas.apilar(auxiliar.sacar());
        }
    }

    public static void main(String[] args) {
        // Gemas con su nivel de poder (la última en apilar queda arriba)
        Pila gemas = new Pila();
        gemas.apilar(30);
        gemas.apilar(10);
        gemas.apilar(50);
        gemas.apilar(20);
        gemas.apilar(40);

        System.out.println("Antes de ordenar:");
        gemas.mostrar();

        ordenarPila(gemas);

        System.out.println("Después de ordenar:");
        gemas.mostrar();
    }
}