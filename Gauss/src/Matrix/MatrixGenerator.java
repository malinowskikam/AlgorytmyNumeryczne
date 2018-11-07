package Matrix;

import java.util.Random;

class MatrixGenerator {
     static int[][] genetareRawTable(int n, int m, int min, int max) {
        //dla zadania z algorytmu gaussa:
        //max = 0x10000 - 1;
        //min = -0x10000;

        int[][] tab = new int[n][m];
        Random generator = new Random();

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                tab[i][j] = generator.nextInt(max - min + 1) + min;

        return tab;
    }
}
