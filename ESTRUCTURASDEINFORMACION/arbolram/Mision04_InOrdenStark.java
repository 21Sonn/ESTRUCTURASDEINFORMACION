/**
 * Misión 4 - Protocolo InOrden de Stark (Nivel 2)
 * Objetivo: recursividad Izq -> Raíz -> Der (imprime de menor a mayor).
 */
public class Mision04_InOrdenStark {

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

        public void inOrden() {
            inOrdenRec(raiz);
            System.out.println();
        }

        private void inOrdenRec(NodoVengador nodo) {
            if (nodo == null) return;
            inOrdenRec(nodo.izq);                 // Izquierda
            System.out.print(nodo.valor + " ");   // Raíz
            inOrdenRec(nodo.der);                 // Derecha
        }
    }

    public static void main(String[] args) {
        ArbolBinario inventario = new ArbolBinario();
        int[] armaduras = {35, 12, 50, 7, 20, 42, 60, 3};
        for (int a : armaduras) {
            inventario.insertar(a);
        }
        System.out.print("Inventario de armaduras (antigua -> nueva): ");
        inventario.inOrden();
    }
}