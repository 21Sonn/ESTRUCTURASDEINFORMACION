/**
 * Misión 8 - Búsqueda de las Gemas (Min/Max) (Nivel 3)
 * Objetivo: navegación lineal en un BST.
 * Menor = extremo izquierdo, Mayor = extremo derecho.
 */
public class Mision08_BusquedaGemas {

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

        /** Devuelve el menor valor, o -1 si el árbol está vacío. */
        public int encontrarMenor() {
            if (raiz == null) {
                System.out.println("El árbol está vacío.");
                return -1;
            }
            NodoVengador actual = raiz;
            while (actual.izq != null) {
                actual = actual.izq;
            }
            return actual.valor;
        }

        /** Devuelve el mayor valor, o -1 si el árbol está vacío. */
        public int encontrarMayor() {
            if (raiz == null) {
                System.out.println("El árbol está vacío.");
                return -1;
            }
            NodoVengador actual = raiz;
            while (actual.der != null) {
                actual = actual.der;
            }
            return actual.valor;
        }
    }

    public static void main(String[] args) {
        ArbolBinario gemas = new ArbolBinario();
        int[] poderes = {55, 30, 80, 10, 45, 70, 95, 5};
        for (int p : poderes) {
            gemas.insertar(p);
        }
        System.out.println("Gema de menor poder: " + gemas.encontrarMenor());
        System.out.println("Gema de mayor poder: " + gemas.encontrarMayor());
    }
}