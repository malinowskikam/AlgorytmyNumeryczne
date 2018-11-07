package Gauss;

import Matrix.Matrix;

public class MatrixEquasion {
    Matrix A; // 3x3
    Matrix B; // 3x1
    Matrix X; // 3x1


    public MatrixEquasion(Matrix A, Matrix X, Matrix B)
    {
        try{
            if(A.rowCount!=B.rowCount||X.colCount!=B.colCount||A.colCount!=X.rowCount) throw new Exception("row/col mismatch");
        } catch (Exception e) {
            System.err.println("Matrix equasion initialization failed:");
            System.err.println(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }

        this.A=A;
        this.B=B;
        this.X=X;
    }

    @Override
    public String toString() {
        return A.toString() + "*\n" + X.toString() + "=\n" + B.toString();
    }
}
