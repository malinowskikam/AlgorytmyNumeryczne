package Matrix.Datatypes;

import java.math.BigInteger;

public class BigIntFraction {

    private static final BigInteger BI_MINUSONE = new BigInteger("-1");
    private static final BigIntFraction ONE = new BigIntFraction(BigInteger.ONE,BigInteger.ONE);
    private static final BigIntFraction MINUSONE = new BigIntFraction(BI_MINUSONE,BigInteger.ONE);
    private static final BigIntFraction ZERO = new BigIntFraction(BigInteger.ONE,BigInteger.ZERO);

    BigInteger numerator; //licznik
    BigInteger denominator; //mianownik

    public BigIntFraction(BigInteger n, BigInteger d) {
        this.numerator = n;
        this.denominator = d;
        this.simplify();
    }

    BigIntFraction(BigIntFraction prototype) {
        this.numerator = prototype.numerator;
        this.denominator = prototype.denominator;
        this.simplify();
    }

    private static BigInteger nwd(BigInteger n1, BigInteger n2) {
        BigInteger a=n1.abs(),b=n2.abs(),c;

        while (b.doubleValue() > 0) {
            c = a.mod(b);
            a = b;
            b = c;
        }
        return a;
    }

    private static BigInteger nww(BigInteger a, BigInteger b) {
        return a.multiply(b).divide(nwd(a, b));
    }

    private void simplify() {
        BigInteger nwd = nwd(this.numerator, this.denominator);

        //skracanie ułamka
        if (!nwd.equals(BigInteger.ONE)) {
            this.numerator = this.numerator.divide(nwd);
            this.denominator = this.denominator.divide(nwd);
        }

        //przenoszenie minusa do licznika
        if(this.denominator.compareTo(BigInteger.ZERO)<0)
        {
            this.numerator = this.numerator.multiply(BI_MINUSONE);
            this.denominator = denominator.multiply(BI_MINUSONE);
        }
    }

    public BigIntFraction add(BigIntFraction number) {
        BigInteger newNumerator,newDenominator;

        //
        if (!this.denominator.equals(number.denominator)) {
            // gdy różny mianownik

            newDenominator = nww(this.denominator, number.denominator);

            // poniżej wzór na sumę liczników zamienionych na wspólny mianownik
            BigInteger newNumerator1 = this.numerator.multiply(newDenominator.divide(this.denominator));
            BigInteger newNumerator2 = number.numerator.multiply(newDenominator.divide(number.denominator));
            newNumerator = newNumerator1.add(newNumerator2);

        } else {
            // gdy równy mianownik

            newNumerator = this.numerator.add(number.numerator);
            newDenominator = this.denominator;
        }

        BigIntFraction newFraction = new BigIntFraction(newNumerator,newDenominator);
        return  newFraction;
    }

    public BigIntFraction subtract(BigIntFraction number) {
        BigInteger newNumerator,newDenominator;
        if (!this.denominator.equals(number.denominator)) {
            // gdy różny mianownik

            newDenominator = nww(this.denominator, number.denominator);

            // poniżej wzór na różnicę liczników zamienionych na wspólny mianownik
            BigInteger newNumerator1 = this.numerator.multiply(newDenominator.divide(this.denominator));
            BigInteger newNumerator2 = number.numerator.multiply(newDenominator.divide(number.denominator));
            newNumerator = newNumerator1.subtract(newNumerator2);

        } else {
            // gdy równy mianownik

            newNumerator = this.numerator.subtract(number.numerator);
            newDenominator = this.denominator;
        }

        return new BigIntFraction(newNumerator,newDenominator);

    }

    public BigIntFraction multiply(BigIntFraction number) {

        BigInteger newNumerator, newDenominator;
        newNumerator = this.numerator.multiply(number.numerator);
        newDenominator = this.denominator.multiply(number.denominator);

        BigIntFraction newFraction = new BigIntFraction(newNumerator,newDenominator);
        return  newFraction;

    }

    public BigIntFraction divide(BigIntFraction number) {

        BigInteger newNumerator, newDenominator;
        newNumerator = this.numerator.multiply(number.denominator);
        newDenominator = this.denominator.multiply(number.numerator);

        BigIntFraction newFraction = new BigIntFraction(newNumerator,newDenominator);
        return newFraction;

    }

    public static int compare(BigIntFraction f1, BigIntFraction f2){
        BigInteger nwd = nwd(f1.denominator,f2.denominator);

        BigInteger newNumerator1 = f1.numerator.multiply(nwd.divide(f1.denominator));
        BigInteger newNumerator2 = f2.numerator.multiply(nwd.divide(f2.denominator));

        return newNumerator1.compareTo(newNumerator2);
    }

    public String toString() {
        return this.numerator.toString() + "/" + this.denominator.toString();
    }
}