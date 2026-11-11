// ===================== Nodo =====================
// Cada canción de la playlist se guarda dentro de un nodo
class Nodo {
    Object contenido;   // la canción
    Nodo siguiente;     // nodo que viene después

    public Nodo(Object contenido) {
        this.contenido = contenido;
    }
}

// ===================== Cola =====================
// Cola (FIFO): el primero en entrar es el primero en salir
class Cola {
    Nodo primeroEnLaCola;
    Nodo ultimoEnLaCola;

    // Insertar al final (Enfilar)
    public void enfilar(Object nuevoObjeto) {
        Nodo nuevoNodo = new Nodo(nuevoObjeto);

        if (primeroEnLaCola == null) {
            primeroEnLaCola = nuevoNodo;            // cola vacía: es el primero
        } else {
            ultimoEnLaCola.siguiente = nuevoNodo;   // se engancha al final
        }
        ultimoEnLaCola = nuevoNodo;
    }

    // Atender / Eliminar del principio (Desenfilar)
    public Object sacar() {
        if (primeroEnLaCola == null) return null;

        Object dato = primeroEnLaCola.contenido;
        primeroEnLaCola = primeroEnLaCola.siguiente;

        if (primeroEnLaCola == null) ultimoEnLaCola = null;  // si quedó vacía

        return dato;
    }
}

// ===================== Main =====================
public class Desafio2 {

    // Reproduce la siguiente canción y la manda al final para crear el loop
    public static void reproducirSiguiente(Cola playlist) {
        // 1. Sacamos la canción que está al frente
        Object cancion = playlist.sacar();

        // 2. Si la playlist estaba vacía, no hay nada que reproducir
        if (cancion == null) {
            System.out.println("La playlist está vacía.");
            return;
        }

        // 3. Reproducimos la canción
        System.out.println("Reproduciendo: " + cancion);

        // 4. La enviamos al final para que no se pierda (loop)
        playlist.enfilar(cancion);
    }

    public static void main(String[] args) {
        // Creamos la playlist "Awesome Mix"
        Cola playlist = new Cola();
        playlist.enfilar("Hooked on a Feeling");
        playlist.enfilar("Come and Get Your Love");
        playlist.enfilar("Spirit in the Sky");
        playlist.enfilar("Escape (The Piña Colada Song)");

        // Para la prueba la reproducimos 10 veces seguidas.
        // Un bucle verdaderamente infinito sería: while (true) { ... }
        for (int i = 1; i <= 10; i++) {
            reproducirSiguiente(playlist);
        }
    }
}