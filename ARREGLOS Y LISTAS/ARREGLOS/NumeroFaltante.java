public class NumeroFaltante {

    public static int encontrarFaltante(int[] arreglo) {
        int n = arreglo.length + 1;                  // Paso 0
        int sumaIdeal = n * (n + 1) / 2;             // Paso 1

        int sumaReal = 0;                            // Paso 2
        for (int i = 0; i < arreglo.length; i++) {
            sumaReal += arreglo[i];
        }

        return sumaIdeal - sumaReal;                 // Paso 3
    }

    public static void main(String[] args) {
        int[] numeros = {1, 2, 4, 5};
        System.out.println("El número faltante es: " + encontrarFaltante(numeros));
    }
}