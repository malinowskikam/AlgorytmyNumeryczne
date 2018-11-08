package Main;

import GaussElimination.MatrixEquasion;
import Matrix.Matrix;
import Matrix.Generators.MatrixRawTabGenerator;

import java.util.Random;

public class Main {
    public static void main(String[] args) {

        Random gen = new Random();

        //long seed = gen.nextLong();
        long seed = 827346958135L;
        int n_a=3;
        int m_a=3;
        int n_x=3;
        int m_x=1;
        int min = -0x10000;
        int max = 0x10000-1;
        char type = 'b';

        int[][] raw_a = MatrixRawTabGenerator.generateRawTable(n_a, m_a,min,max,seed);
        int[][] raw_x = MatrixRawTabGenerator.generateRawTable(n_x, m_x,min,max,seed);

        Matrix A = new Matrix(raw_a,type);
        Matrix X = new Matrix(raw_x,type);

        MatrixEquasion eq = new MatrixEquasion(A,X,A.multiply(X));
        System.out.println(eq);

        eq.performGaussianEliminationNoPivot(false);

        System.out.println(eq);


    }
}
