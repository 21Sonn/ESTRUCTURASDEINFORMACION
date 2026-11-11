import java.util.Arrays;

public class InvertirArreglo {

    public static void invertir(int[] arreglo) {
        int inicio = 0;                          
        int fin = arreglo.length - 1;

        while (inicio < fin) {                   
            int temp = arreglo[inicio];
            arreglo[inicio] = arreglo[fin];
            arreglo[fin] = temp;

            inicio++;                            
            fin--;
        }
    }

    public static void main(String[] args) {
        int[] numeros = {1, 2, 3, 4, 5};
        invertir(numeros);
        System.out.println(Arrays.toString(numeros));
    }
}