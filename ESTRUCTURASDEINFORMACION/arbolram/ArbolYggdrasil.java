package ESTRUCTURASDEINFORMACION.arbolram;

public class ArbolYggdrasil {
    static class Nodo {
        String dato;
        Nodo izquierdo;
        Nodo derecho;

        public Nodo(String dato) {
            this.dato = dato;
            this.izquierdo = null;
            this.derecho = null;
        }
    }
    public void preOrden(Nodo n) {
        if (n == null) return;
        System.out.print(n.dato + " ");
        preOrden(n.izquierdo);
        preOrden(n.derecho);
    }    
    public static void main(String[] args) {
        Nodo raiz = new Nodo("asgard");
        raiz.izquierdo = new Nodo("midgar");
        raiz.derecho = new Nodo("Vanaheim");

        ArbolYggdrasil arbol = new ArbolYggdrasil();

        System.out.print("PreOrden:  ");
        arbol.preOrden(raiz);
        System.out.println();
    }

    
}
