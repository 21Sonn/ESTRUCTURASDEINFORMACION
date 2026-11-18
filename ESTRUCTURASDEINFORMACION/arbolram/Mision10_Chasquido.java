/**
 * Misión 10 - El Chasquido (Eliminar Nodo) (Nivel 3)
 * Objetivo: gestión avanzada de punteros. Cubre los 3 casos:
 *   1) Nodo hoja  2) Nodo con un hijo  3) Nodo con dos hijos (sucesor inorden)
 */
public class Mision10_Chasquido {

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

        public void eliminar(int valor) {
            raiz = eliminarRec(raiz, valor);
        }

        private NodoVengador eliminarRec(NodoVengador nodo, int valor) {
            if (nodo == null) {
                return null; // el valor no existe
            }
            if (valor < nodo.valor) {
                nodo.izq = eliminarRec(nodo.izq, valor);
            } else if (valor > nodo.valor) {
                nodo.der = eliminarRec(nodo.der, valor);
            } else {
                // Caso 1: sin hijos (hoja)
                if (nodo.izq == null && nodo.der == null) {
                    return null;
                }
                // Caso 2: un solo hijo
                if (nodo.izq == null) return nodo.der;
                if (nodo.der == null) return nodo.izq;

                // Caso 3: dos hijos -> sucesor inorden (menor del subárbol derecho)
                NodoVengador sucesor = nodo.der;
                while (sucesor.izq != null) {
                    sucesor = sucesor.izq;
                }
                nodo.valor = sucesor.valor;                    // copiar valor del sucesor
                nodo.der = eliminarRec(nodo.der, sucesor.valor); // eliminar el sucesor original
            }
            return nodo;
        }

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
        int[] datos = {50, 30, 70, 20, 40, 60, 80, 65};
        for (int d : datos) {
            arbol.insertar(d);
        }
        System.out.print("Inicial:                   ");
        arbol.imprimirInorden();

        arbol.eliminar(20);   // hoja
        System.out.print("Tras eliminar 20 (hoja):   ");
        arbol.imprimirInorden();

        arbol.eliminar(60);   // un hijo (65)
        System.out.print("Tras eliminar 60 (1 hijo): ");
        arbol.imprimirInorden();

        arbol.eliminar(50);   // dos hijos (raíz)
        System.out.print("Tras eliminar 50 (2 hijos):");
        arbol.imprimirInorden();
        System.out.println("Nueva raíz: " + arbol.raiz.valor);
    }
}