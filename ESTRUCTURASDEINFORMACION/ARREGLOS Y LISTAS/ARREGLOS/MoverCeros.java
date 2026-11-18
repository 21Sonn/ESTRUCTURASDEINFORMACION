import java.util.Arrays;

public class MoverCeros {

    public static void moverCeros(int[] arreglo) {
        int idxNoCero = 0;                                   // Paso 1

        for (int i = 0; i < arreglo.length; i++) {           // Paso 2
            if (arreglo[i] != 0) {
                arreglo[idxNoCero] = arreglo[i];
                idxNoCero++;
            }
        }

        for (int i = idxNoCero; i < arreglo.length; i++) {   // Paso 3
            arreglo[i] = 0;
        }
    }

    public static void main(String[] args) {
        int[] numeros = {0, 1, 0, 3, 12};
        moverCeros(numeros);
        System.out.println(Arrays.toString(numeros));
    }
}