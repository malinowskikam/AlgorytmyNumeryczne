package Main;

import Datatypes.MatrixBigIntFraction;

public class Main {
    public static void main(String[] args) {
        System.out.println("Lecimy");

        MatrixBigIntFraction m = new MatrixBigIntFraction(1,2);
        System.out.println(m.evaluate());
    }
}
