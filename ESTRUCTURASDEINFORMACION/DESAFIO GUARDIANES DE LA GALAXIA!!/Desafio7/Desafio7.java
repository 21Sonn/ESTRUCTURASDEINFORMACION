// ===================== Nodo =====================
class Nodo {
    Object contenido;   // dato guardado
    Nodo siguiente;     // nodo que viene después

    public Nodo(Object contenido) {
        this.contenido = contenido;
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
}

// ===================== Sector =====================
// Cada sector de escudo con la energía que le falta para llegar al 100%
class Sector {
    String nombre;
    int energiaFaltante;

    public Sector(String nombre, int energiaFaltante) {
        this.nombre = nombre;
        this.energiaFaltante = energiaFaltante;
    }
}

// ===================== Main =====================
public class Desafio7 {

    // Energía que el núcleo entrega por turno
    static final int ENERGIA_POR_CICLO = 10;

    public static void main(String[] args) {
        // 1. Creamos la cola y enfilamos los 4 sectores
        Cola cola = new Cola();
        cola.enfilar(new Sector("Frontal", 25));
        cola.enfilar(new Sector("Trasero", 10));
        cola.enfilar(new Sector("Izquierdo", 30));
        cola.enfilar(new Sector("Derecho", 15));

        // 2. Simulación Round-Robin: se repite hasta que la cola quede vacía
        while (!cola.estaVacia()) {
            // Atendemos al sector que está al frente
            Sector sector = (Sector) cola.sacar();

            // Le entregamos energía
            sector.energiaFaltante = sector.energiaFaltante - ENERGIA_POR_CICLO;

            if (sector.energiaFaltante > 0) {
                // Aún le falta: informamos y vuelve al final de la cola
                System.out.println("Cargando " + sector.nombre + "... Restan "
                        + sector.energiaFaltante + " para el 100%");
                cola.enfilar(sector);
            } else {
                // Ya está completo: no vuelve a la cola
                System.out.println("Cargando " + sector.nombre + "... ¡"
                        + sector.nombre + " al 100%! Sale de la cola.");
            }
        }

        System.out.println("Todos los escudos están al 100%.");
    }
}