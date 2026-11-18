/**
 * Misión 6 - Explosión de Sokovia (PostOrden) (Nivel 2)
 * Objetivo: recursividad Izq -> Der -> Raíz (hojas hasta la raíz).
 */
public class Mision06_ExplosionSokovia {

    static class NodoVengador {
        int valor;
        NodoVengador izq;
        NodoVengador der;

        NodoVengador(int valor) {
            this.valor = valor;
        }
    }

    static class ArbolBinario {
        NodoVengador raiz;

        public void insertar(int valor) {
            raiz = insertarRec(raiz, valor);
        }

        private NodoVengador insertarRec(NodoVengador actual, int valor) {
            if (actual == null) return new NodoVengador(valor);
            if (valor < actual.valor) actual.izq = insertarRec(actual.izq, valor);
            else if (valor > actual.valor) actual.der = insertarRec(actual.der, valor);
            return actual;
        }

        public void postOrden() {
            postOrdenRec(raiz);
            System.out.println();
        }

        private void postOrdenRec(NodoVengador nodo) {
            if (nodo == null) return;
            postOrdenRec(nodo.izq);                       // Izquierda
            postOrdenRec(nodo.der);                       // Derecha
            System.out.print(nodo.valor + " ");           // Raíz (aquí se "destruye" el nodo)
        }

        // Destruye la red liberando los nodos desde las hojas hasta la raíz
        public void destruir() {
            raiz = destruirRec(raiz);
        }

        private NodoVengador destruirRec(NodoVengador nodo) {
            if (nodo == null) return null;
            nodo.izq = destruirRec(nodo.izq);
            nodo.der = destruirRec(nodo.der);
            System.out.println("Nodo " + nodo.valor + " destruido");
            return null; // se corta la referencia: queda disponible para el recolector
        }
    }

    public static void main(String[] args) {
        ArbolBinario red = new ArbolBinario();
        int[] nodos = {40, 20, 60, 10, 30, 50, 70};
        for (int n : nodos) {
            red.insertar(n);
        }
        System.out.print("Recorrido PostOrden: ");
        red.postOrden();
        System.out.println("--- Ultron destruye la red ---");
        red.destruir();
        System.out.println("Raíz tras la destrucción: " + red.raiz);
    }
}