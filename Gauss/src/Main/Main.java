package Main;

import Gauss.MatrixEquasion;
import Matrix.Datatypes.BigIntFraction;
import Matrix.Matrix;

import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {

        Matrix A = new Matrix(3,3,'b',-0x10000,0x10000-1);
        Matrix X = new Matrix(3,1,'b',-0x10000,0x10000-1);

        MatrixEquasion eq = new MatrixEquasion(A,X,A.multiply(X));
        System.out.println(eq);



    }
}
