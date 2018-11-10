package GaussianElimination;

import Matrix.Matrix;

import java.lang.reflect.Array;
import java.util.*;

public class MatrixEquasion {
    Matrix A;
    Matrix B;
    Matrix X;
    char type;

    List<int[]> temp = new ArrayList<>();


    public MatrixEquasion(Matrix A, Matrix X, Matrix B, char type)
    {
        this.A=A;
        this.B=B;
        this.X=X;
        this.type=type;
    }

    public TestResult performGaussianEliminationNoPivot()
    {
        long t1 = System.nanoTime();
        firstPhaseNoPivot();
        long t2 = System.nanoTime();
        secondPhase();
        long t3 = System.nanoTime();
        return (new TestResult(getError(),t2-t1,t3-t2,1,type,A.colCount));
    }

    public TestResult performGaussianEliminationHalfPivot()
    {
        long t1 = System.nanoTime();
        firstPhaseHalfPivot();
        long t2 = System.nanoTime();
        secondPhase();
        long t3 = System.nanoTime();
        return (new TestResult(getError(),t2-t1,t3-t2,2,type,A.colCount));
    }

    public TestResult performGaussianEliminationFullPivot()
    {
        long t1 = System.nanoTime();
        firstPhaseFullPivot();
        long t2 = System.nanoTime();
        secondPhase();
        long t3 = System.nanoTime();

        for ( int i=temp.size()-1; i>=0;i-- )
            B.swapRows(temp.get(i)[0],temp.get(i)[1]);

        return (new TestResult(getError(),t2-t1,t3-t2,3,type,A.colCount));

    }


    public double getError()
    {
        return Matrix.getNormOfDiffrence(B,X);
    }

    private void firstPhaseNoPivot()
    {
        //Pierwsza faza eliminacji Gaussa, bez wyboru elementu podstawowego
        //Tworzy z macierzy A macierz trójkątną górną oraz dzieli wiersze tak, aby uzyskać jedynki wiodące
        //Przenosi operacje na macierz B
        for(int i=0;i<A.colCount;i++)
        {
            B.matrix[i] = Matrix.multiplyRow(B.matrix[i],(A.matrix[i][i].getInverse()));
            A.matrix[i] = Matrix.multiplyRow(A.matrix[i],(A.matrix[i][i].getInverse()));

            for(int j=i+1;j<A.rowCount;j++)
            {
                B.matrix[j] = Matrix.subtractRows(B.matrix[j],Matrix.multiplyRow(B.matrix[i],A.matrix[j][i]));
                A.matrix[j] = Matrix.subtractRows(A.matrix[j],Matrix.multiplyRow(A.matrix[i],A.matrix[j][i]));
            }
        }
    }

    private void firstPhaseHalfPivot()
    {
        //Pierwsza faza eliminacji Gaussa, z częściowym wyborem elementu podstawowego
        //Tworzy z macierzy A macierz trójkątną górną oraz dzieli wiersze tak, aby uzyskać jedynki wiodące
        //Przenosi operacje na macierz B
        for(int i=0;i<A.colCount;i++)
        {
            //Wybór wiodącego elementu
            int max = findMaxInColumn(A,i,i,A.rowCount);

            A.swapRows(i,max);
            B.swapRows(i,max);

            //Dzielenie wiersza przez wiodący element
            B.matrix[i] = Matrix.multiplyRow(B.matrix[i],(A.matrix[i][i].getInverse()));
            A.matrix[i] = Matrix.multiplyRow(A.matrix[i],(A.matrix[i][i].getInverse()));

            for(int j=i+1;j<A.rowCount;j++)
            {
                B.matrix[j] = Matrix.subtractRows(B.matrix[j],Matrix.multiplyRow(B.matrix[i],A.matrix[j][i]));
                A.matrix[j] = Matrix.subtractRows(A.matrix[j],Matrix.multiplyRow(A.matrix[i],A.matrix[j][i]));
            }
        }
    }

    private void firstPhaseFullPivot() {
        //Pierwsza faza eliminacji Gaussa, z pełnym wyborem elementu podstawowego
        //Tworzy z macierzy A macierz trójkątną górną oraz dzieli wiersze tak, aby uzyskać jedynki wiodące
        //Przenosi operacje na macierz B

        for (int i = 0; i < A.colCount; i++) {
            //Wybór wiodącego elementu
            int[] pos = findMaxInSubmatrix(A, i, i);


            A.swapRows(i, pos[0]);
            B.swapRows(i, pos[0]);

            A.swapCols(i, pos[1]);

            //zapisanie zmiany na później
            pos[0] = i;
            temp.add(pos);


            //Dzielenie wiersza przez wiodący element
            B.matrix[i] = Matrix.multiplyRow(B.matrix[i], (A.matrix[i][i].getInverse()));
            A.matrix[i] = Matrix.multiplyRow(A.matrix[i], (A.matrix[i][i].getInverse()));

            for (int j = i + 1; j < A.rowCount; j++) {
                B.matrix[j] = Matrix.subtractRows(B.matrix[j], Matrix.multiplyRow(B.matrix[i], A.matrix[j][i]));
                A.matrix[j] = Matrix.subtractRows(A.matrix[j], Matrix.multiplyRow(A.matrix[i], A.matrix[j][i]));
            }
        }
    }

    private void secondPhase()
    {
        //Druga faza eliminacji Gaussa
        //Sprowadza macierz A do macierzy jednostkowej
        //Po tej operacji macierz B to wyliczony X, błąd to | ||B|| - ||X|| |
        for(int i=A.colCount-1;i>=0;i--)
        {
            for(int j=i-1;j>=0;j--)
            {
                B.matrix[j] = Matrix.subtractRows(B.matrix[j],Matrix.multiplyRow(B.matrix[i],A.matrix[j][i]));
                A.matrix[j] = Matrix.subtractRows(A.matrix[j],Matrix.multiplyRow(A.matrix[i],A.matrix[j][i]));
            }
        }
    }

    public int findMaxInColumn(Matrix A, int col, int scope_min, int scope_max)
    {

        int max = scope_min;
        for(int i = scope_min+1;i<scope_max;i++ )
            if ((A.matrix[i][col].abs().compareTo(A.matrix[max][col].abs()))> 0)
                max = i;
        return max;
    }

    public int[] findMaxInSubmatrix(Matrix A, int x, int y)
    {
        int[] pos = new int[2];
        pos[0]=x;
        pos[1]=y;
        for(int i = x;i<A.rowCount;i++ )
            for (int j=y;j<A.colCount;j++)
                if ((A.matrix[i][j].abs().compareTo(A.matrix[pos[0]][pos[1]].abs()))> 0)
                {
                    pos[0]=i;
                    pos[1]=j;
                }

        return pos;
    }

    @Override
    public String toString() {
        return "--A:--\n" + A.toString() + "--X:--\n" + X.toString() + "--B:--\n" + B.toString();
    }
}
