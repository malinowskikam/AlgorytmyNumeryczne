package Main;

import Datatypes.MatrixBigIntFraction;

import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        System.out.println("Lecimy");

        MatrixBigIntFraction m1 = new MatrixBigIntFraction(1,2);
        MatrixBigIntFraction m2 = new MatrixBigIntFraction(5,6);
        MatrixBigIntFraction m3 = new MatrixBigIntFraction(1,6);
        MatrixBigIntFraction m4 = new MatrixBigIntFraction(1,3);

        System.out.println(m1.subtract(m2));
        System.out.println(m2.subtract(m3));
        System.out.println(m3.subtract(m1));
        System.out.println(m1.subtract(m4));
    }
}
