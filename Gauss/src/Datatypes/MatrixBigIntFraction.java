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

    public MatrixBigIntFraction(Fraction f) { this.setValue(f); }

    private static BigInteger nwd(BigInteger a, BigInteger b){
        BigInteger c;
        while(b.doubleValue()>0){
            c=a.mod(b);
            a=b;
            b=c;
        }
        return a;
    }

    public static BigInteger nww(BigInteger a, BigInteger b){
        return a.multiply(b).divide(nwd(a,b));
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
    public String toString(){
        return fraction.numerator.toString() + "/" + fraction.denominator.toString();
    }

    @Override
    public MatrixBigIntFraction add(MatrixBigIntFraction number) {
        BigInteger newNumerator;
        if (!this.getValue().denominator.equals(number.getValue().denominator)) { // gdy różny mianownik
            BigInteger nww = nww(this.fraction.denominator, number.getValue().denominator); // wspólny mianownik
            // poniżej wzór na sumę liczników zamienionych na wspólny mianownik
            BigInteger newNumerator1 = this.getValue().numerator.multiply(nww.divide(this.getValue().denominator));
            BigInteger newNumerator2 = number.getValue().numerator.multiply(nww.divide(number.getValue().denominator));
            newNumerator = newNumerator1.add(newNumerator2);
            BigInteger nwd = nwd(newNumerator, nww); // nwd do skrócenia ułamka

            if (!nwd.equals(BigInteger.ONE))
                return new MatrixBigIntFraction(new Fraction(newNumerator.divide(nwd), nww.divide(nwd)));
            else
                return new MatrixBigIntFraction(new Fraction(newNumerator, nww));
        }
        else { // gdy równy mianownik
            newNumerator = this.getValue().numerator.add(number.getValue().numerator);
            BigInteger nwd = nwd(newNumerator,this.getValue().denominator);
            if (!nwd.equals(BigInteger.ONE))
                return new MatrixBigIntFraction(new Fraction(newNumerator.divide(nwd), this.getValue().denominator.divide(nwd)));
            else
                return new MatrixBigIntFraction(new Fraction(newNumerator, this.getValue().denominator));
        }
    }

    @Override
    public MatrixBigIntFraction subtract(MatrixBigIntFraction number) {
        BigInteger newNumerator;
        if (!this.getValue().denominator.equals(number.getValue().denominator)) { // gdy różny mianownik
            BigInteger nww = nww(this.fraction.denominator, number.getValue().denominator); // wspólny mianownik
            // poniżej wzór na sumę liczników zamienionych na wspólny mianownik
            BigInteger newNumerator1 = this.getValue().numerator.multiply(nww.divide(this.getValue().denominator));
            BigInteger newNumerator2 = number.getValue().numerator.multiply(nww.divide(number.getValue().denominator));
            newNumerator = newNumerator1.subtract(newNumerator2);
            BigInteger nwd = nwd(newNumerator, nww); // nwd do skrócenia ułamka

            if (!nwd.equals(BigInteger.ONE))
                return new MatrixBigIntFraction(new Fraction(newNumerator.divide(nwd), nww.divide(nwd)));
            else
                return new MatrixBigIntFraction(new Fraction(newNumerator, nww));
        }
        else { // gdy równy mianownik
            newNumerator = this.getValue().numerator.subtract(number.getValue().numerator);
            BigInteger nwd = nwd(newNumerator,this.getValue().denominator);
            if (!nwd.equals(BigInteger.ONE))
                return new MatrixBigIntFraction(new Fraction(newNumerator.divide(nwd), this.getValue().denominator.divide(nwd)));
            else
                return new MatrixBigIntFraction(new Fraction(newNumerator, this.getValue().denominator));
        }
    }

    @Override
    public MatrixBigIntFraction multiply(MatrixBigIntFraction number) {
        //dokończyć
        return this;
    }

    @Override
    public MatrixBigIntFraction divide(MatrixBigIntFraction number) {
        //dokończyć
        return this;
    }
}
