public class BuscarIndice {

    // Devuelve el índice donde está el valor, o -1 si no existe
    public static int buscar(int[] arreglo, int valor) {
        for (int i = 0; i < arreglo.length; i++) {   // Paso 1
            if (arreglo[i] == valor) {               // Paso 2
                return i;
            }
        }
        return -1;                                   // Paso 3
    }

    public static void main(String[] args) {
        int[] numeros = {4, 7, 9, 2};

        int indice1 = buscar(numeros, 9);
        int indice2 = buscar(numeros, 15);

        System.out.println("Buscando 9: " + indice1);
        System.out.println("Buscando 15: " + indice2);
    }
}