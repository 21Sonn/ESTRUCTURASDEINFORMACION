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
public class Desafio4 {

    // Busca un arma, la extrae y deja el resto de la pila en el mismo orden
    public static String buscarYExtraer(Pila armas, String objetivo) {
        // 1. Pila auxiliar para guardar temporalmente las armas que estorban
        Pila auxiliar = new Pila();
        String encontrada = null;   // si no se encuentra, se devuelve null

        // 2. Sacamos armas de la pila original hasta dar con el objetivo
        while (!armas.estaVacia()) {
            String arma = (String) armas.sacar();

            if (arma.equals(objetivo)) {
                encontrada = arma;      // la guardamos
                break;                  // detenemos la búsqueda
            } else {
                auxiliar.apilar(arma);  // la apartamos en la auxiliar
            }
        }

        // 3. Devolvemos las armas apartadas a la pila original
        while (!auxiliar.estaVacia()) {
            armas.apilar(auxiliar.sacar());
        }

        // 4. Retornamos el arma buscada
        return encontrada;
    }

    public static void main(String[] args) {
        // Armas de Drax (la última que se apila queda arriba)
        Pila armas = new Pila();
        armas.apilar("Espada");
        armas.apilar("Hacha");
        armas.apilar("Dagas de Vibranium");
        armas.apilar("Escudo");
        armas.apilar("Martillo");

        System.out.println("Antes de buscar:");
        armas.mostrar();

        // Búsqueda exitosa
        String arma = buscarYExtraer(armas, "Dagas de Vibranium");
        System.out.println("Arma extraída: " + arma);
        System.out.println("Después de buscar:");
        armas.mostrar();

        // Búsqueda de un arma que no existe
        String otra = buscarYExtraer(armas, "Lanza");
        if (otra == null) {
            System.out.println("La Lanza no está en la pila.");
        }
        armas.mostrar();
    }
}