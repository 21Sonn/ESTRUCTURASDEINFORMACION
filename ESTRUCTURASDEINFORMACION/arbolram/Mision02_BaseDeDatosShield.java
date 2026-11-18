/**
 * Misión 2 - Base de Datos de S.H.I.E.L.D. (Nivel 1)
 * Objetivo: algoritmo de inserción BST (recursivo).
 */
public class Mision02_BaseDeDatosShield {

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

        public void insertar(int nivelSeguridad) {
            raiz = insertarRec(raiz, nivelSeguridad);
        }

        private NodoVengador insertarRec(NodoVengador actual, int nivelSeguridad) {
            if (actual == null) {
                return new NodoVengador(nivelSeguridad);
            }
            if (nivelSeguridad < actual.valor) {
                actual.izq = insertarRec(actual.izq, nivelSeguridad);
            } else if (nivelSeguridad > actual.valor) {
                actual.der = insertarRec(actual.der, nivelSeguridad);
            }
            // Si es igual, ya existe: no se inserta duplicado.
            return actual;
        }

        // Auxiliar para verificar visualmente el resultado
        public void imprimirInorden() {
            inorden(raiz);
            System.out.println();
        }

        private void inorden(NodoVengador n) {
            if (n != null) {
                inorden(n.izq);
                System.out.print(n.valor + " ");
                inorden(n.der);
            }
        }
    }

    public static void main(String[] args) {
        ArbolBinario arbol = new ArbolBinario();
        int[] niveles = {50, 30, 70, 20, 40, 60, 80, 30};
        for (int n : niveles) {
            arbol.insertar(n);
        }
        System.out.print("Niveles de seguridad: ");
        arbol.imprimirInorden();
    }
}