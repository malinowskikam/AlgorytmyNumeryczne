package Main;

import GaussianElimination.MatrixEquasion;
import GaussianElimination.TestResult;
import Matrix.Matrix;
import Matrix.Generators.MatrixRawTabGenerator;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Main {

    private static PrintStream out = System.out;

    private static final PrintStream out_BACKUP = System.out;

    public static void main2(String[] args) throws Exception {

        Main.setOutput("result.csv");

        int matrixSize[] = {5,10,15,20};
        int testCounts[] = {100,50,20,10};

        performTests(matrixSize,testCounts);

        Main.flush();
    }
    private static void setOutput(String file) throws Exception
    {
        Main.out = new PrintStream(new BufferedOutputStream(new FileOutputStream(file)));
        System.setOut(Main.out);
    }

    private static void setOutput()
    {
        Main.out = Main.out_BACKUP;
        System.setOut(Main.out);
    }

    private static void flush()
    {
        out.flush();
    }

    private static void performTests(int[] matrixSize,int[] testCounts)
    {
        final int min = -0x10000;
        final int max = 0x10000-1;

        System.out.println(TestResult.getHeader());

        for(int i=0;i<matrixSize.length;i++)
        //zmiana wielkości macierzy
        {
            char[] type = {'f','d','b'};
            for(char m : type)
            //zmiana typu danych
            {
                ArrayList<TestResult> list = new ArrayList<>();

                for(int j=0;j<testCounts[i];j++)
                //iteracje testów (no pivot)
                {
                    Random seedGenerator = new Random(96639646524L);

                    int[][] rawA = MatrixRawTabGenerator.generateRawTable(matrixSize[i],matrixSize[i],min,max,seedGenerator.nextLong());
                    int[][] rawX = MatrixRawTabGenerator.generateRawTable(matrixSize[i],1,min,max,seedGenerator.nextLong());

                    Matrix mxA = new Matrix(rawA,m);
                    Matrix mxX = new Matrix(rawX,m);

                    MatrixEquasion eq = new MatrixEquasion(mxA,mxX,mxA.multiply(mxX),m);
                    list.add(eq.performGaussianEliminationNoPivot());
                }
                System.err.println("Wykonano testy No Pivot");
                System.out.println(TestResult.getAverage(list));
                list.clear();


                for(int j=0;j<testCounts[i];j++)
                //iteracje testów (no pivot)
                {
                    Random seedGenerator = new Random(8753896639646524L);

                    int[][] rawA = MatrixRawTabGenerator.generateRawTable(matrixSize[i],matrixSize[i],min,max,seedGenerator.nextLong());
                    int[][] rawX = MatrixRawTabGenerator.generateRawTable(matrixSize[i],1,min,max,seedGenerator.nextLong());

                    Matrix mxA = new Matrix(rawA,m);
                    Matrix mxX = new Matrix(rawX,m);

                    MatrixEquasion eq = new MatrixEquasion(mxA,mxX,mxA.multiply(mxX),m);
                    list.add(eq.performGaussianEliminationHalfPivot());
                }
                System.err.println("Wykonano testy Half Pivot");
                System.out.println(TestResult.getAverage(list));
                list.clear();


                for(int j=0;j<testCounts[i];j++)
                //iteracje testów (no pivot)
                {
                    Random seedGenerator = new Random(8753896639646524L);

                    int[][] rawA = MatrixRawTabGenerator.generateRawTable(matrixSize[i],matrixSize[i],min,max,seedGenerator.nextLong());
                    int[][] rawX = MatrixRawTabGenerator.generateRawTable(matrixSize[i],1,min,max,seedGenerator.nextLong());

                    Matrix mxA = new Matrix(rawA,m);
                    Matrix mxX = new Matrix(rawX,m);

                    MatrixEquasion eq = new MatrixEquasion(mxA,mxX,mxA.multiply(mxX),m);
                    list.add(eq.performGaussianEliminationFullPivot());
                }
                System.err.println("Wykonano testy Full Pivot");
                System.out.println(TestResult.getAverage(list));
                System.err.println("Wykonano testy dla macierzy typu " + m);
                list.clear();
            }
            System.err.println("Wykonano testy dla macierzy wielkości " + matrixSize[i]);
        }
    }
}
