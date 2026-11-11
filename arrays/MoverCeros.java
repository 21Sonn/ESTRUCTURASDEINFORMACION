import java.util.Arrays;

public class MoverCeros {

    public static void moverCeros(int[] arreglo) {
        int idxNoCero = 0;                                   

        for (int i = 0; i < arreglo.length; i++) {           
            if (arreglo[i] != 0) {
                arreglo[idxNoCero] = arreglo[i];
                idxNoCero++;
            }
        }

        for (int i = idxNoCero; i < arreglo.length; i++) {   
            arreglo[i] = 0;
        }
    }

    public static void main(String[] args) {
        int[] numeros = {0, 1, 0, 3, 12};
        moverCeros(numeros);
        System.out.println(Arrays.toString(numeros));
    }
}