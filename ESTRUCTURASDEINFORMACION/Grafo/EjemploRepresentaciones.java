package ESTRUCTURASDEINFORMACION.Grafo;
import java.util.*;

public class EjemploRepresentaciones {
    public static void main(String[] args) {
        int n = 4;
        int[][] aristas = {{0, 1, 3}, {0, 2, 7}, {1, 2, 2}, {2, 3, 5}};

        List<List<int[]>> lista = new ArrayList<>();
        for (int i = 0; i < n; i++) lista.add(new ArrayList<>());
        double[][] matriz = new double[n][n];

        for (int[] a : aristas) {
            lista.get(a[0]).add(new int[]{a[1], a[2]});
            matriz[a[0]][a[1]] = a[2];
        }

        System.out.println("Lista de adyacencia:");
        for (int u = 0; u < n; u++) {
            StringBuilder sb = new StringBuilder(u + " ->");
            for (int[] v : lista.get(u)) {
                sb.append(" (").append(v[0]).append(", ").append(v[1]).append(")");
            }
            System.out.println(sb);
        }

        System.out.println("\nMatriz de adyacencia:");
        for (int u = 0; u < n; u++) {
            for (int v = 0; v < n; v++) {
                System.out.printf("%5.1f", matriz[u][v]);
            }
            System.out.println();
        }
    }
}