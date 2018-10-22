package Datatypes;

import java.math.BigInteger;

public class MatrixBigIntFraction implements MatrixDataType<MatrixBigIntFraction, MatrixBigIntFraction.Fraction> {

    class Fraction {
        public BigInteger numerator; //licznik
        public BigInteger denominator; //mianownik

        Fraction(BigInteger n, BigInteger d) {
            this.numerator = n;
            this.denominator = d;
        }
    }

    Fraction fraction;

    public MatrixBigIntFraction(int v){
        setValue(new Fraction(new BigInteger(""+v),new BigInteger(""+0x10000))); //0x10000 = 2^16
    }

    public MatrixBigIntFraction(int n,int d){
        setValue(new Fraction(new BigInteger(""+n),new BigInteger(""+d)));
    }

    @Override
    public double evaluate() {
        return fraction.numerator.doubleValue()/fraction.denominator.doubleValue();
    }

    @Override
    public Fraction getValue() {
        return this.fraction;
    }

    @Override
    public void setValue(Fraction f) {
        this.fraction = f;
    }

    @Override
    public void add(MatrixBigIntFraction number) {
        //dokończyć
    }

    @Override
    public void subtract(MatrixBigIntFraction number) {
        //dokończyć
    }

    @Override
    public void multiply(MatrixBigIntFraction number) {
        //dokończyć
    }

    @Override
    public void divide(MatrixBigIntFraction number) {
        //dokończyć
    }
}
