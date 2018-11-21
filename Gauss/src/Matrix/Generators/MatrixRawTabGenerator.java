package Matrix.Generators;

import Matrix.Matrix;

import java.util.Random;

public class MatrixRawTabGenerator {
     public static int[][] generateRawTable(int n, int m, int min, int max, long seed) {
             //generuje tablicę intów o podanej wielkości z podanego zakresu z podanym ziarnem

             int[][] tab = new int[n][m];
             Random generator = new Random(seed);

             for (int i = 0; i < n; i++)
                 for (int j = 0; j < m; j++)
                     tab[i][j] = generator.nextInt(max - min + 1) + min;

             return tab;
    }
}
