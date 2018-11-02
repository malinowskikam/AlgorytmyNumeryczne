package Main;

import Matrix.Datatypes.MatrixDataType;
import Matrix.Datatypes.MatrixDouble;
import Matrix.Datatypes.MatrixFloat;
import Matrix.Matrix;

public class Main {
    public static void main(String[] args) {

        MatrixDataType t1 = new MatrixDouble(5.6);

        MatrixDataType[][] raw1 = new MatrixDouble[2][3];
        for(int i=0;i<2;i++)
            for(int j=0;j<3;j++)
                raw1[i][j] = new MatrixDouble(2.0);


        MatrixDataType[][] raw2 = new MatrixDouble[3][2];
        for(int i=0;i<3;i++)
            for(int j=0;j<2;j++)
                raw2[i][j] = new MatrixDouble(1.0);

        Matrix m1 = new Matrix(raw1);
        Matrix m2 = new Matrix(raw2);

        System.out.println(m1.multiply(m2));


    }
}
