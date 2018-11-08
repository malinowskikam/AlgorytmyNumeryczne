package GaussElimination;

import Matrix.Matrix;

public class MatrixEquasion {
    Matrix A;
    Matrix B;
    Matrix X;


    public MatrixEquasion(Matrix A, Matrix X, Matrix B)
    {
        this.A=A;
        this.B=B;
        this.X=X;
    }

    public void performGaussianEliminationNoPivot(boolean outputLogs)
    {
        firstPhaseNoPivot(outputLogs);
        secondPhaseNoPivot(outputLogs);
    }

    public double getError()
    {
        return 0.0;
    }

    private void firstPhaseNoPivot(boolean outputLogs)
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

            //z powodu długiego czasu operacji log na konsole
            if(outputLogs) System.out.println("1st phase, " + i + " row");
        }
    }

    private void secondPhaseNoPivot(boolean outputLogs)
    {
        //Druga faza eliminacji Gaussa
        //Sprowadza macierz A do macierzy jednostkowej
        //

        for(int i=A.colCount-1;i>=0;i--)
        {
            for(int j=i-1;j>=0;j--)
            {
                B.matrix[j] = Matrix.subtractRows(B.matrix[j],Matrix.multiplyRow(B.matrix[i],A.matrix[j][i]));
                A.matrix[j] = Matrix.subtractRows(A.matrix[j],Matrix.multiplyRow(A.matrix[i],A.matrix[j][i]));
            }

            //z powodu długiego czasu operacji log na konsole
            if (outputLogs) System.out.println("1st phase, " + i + " row");

        }
    }

    @Override
    public String toString() {
        return "--A:--\n" + A.toString() + "--X:--\n" + X.toString() + "--B:--\n" + B.toString();
    }
}
