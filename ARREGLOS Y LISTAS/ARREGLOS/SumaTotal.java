public class SumaTotal {

    // Método que suma todos los elementos del arreglo
    public static int sumaTotal(int[] arreglo) {
        int suma = 0;                           // Paso 1

        for (int i = 0; i < arreglo.length; i++) {
            suma += arreglo[i];                 // Paso 2
        }

        return suma;                            // Paso 3
    }

    public static void main(String[] args) {
        int[] numeros = {4, 7, 2, 10};
        int resultado = sumaTotal(numeros);
        System.out.println("La suma total es: " + resultado);
    }
}