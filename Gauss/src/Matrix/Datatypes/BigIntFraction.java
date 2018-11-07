package Matrix.Datatypes;

import java.math.BigInteger;

public class BigIntFraction {
    BigInteger numerator; //licznik
    BigInteger denominator; //mianownik

    public BigIntFraction(BigInteger n, BigInteger d) {
        this.numerator = n.divide(nwd(n, d));
        this.denominator = d.divide(nwd(n, d));
    }

    BigIntFraction(BigIntFraction prototype) {
        this.numerator = prototype.numerator;
        this.denominator = prototype.denominator;
    }

    private static BigInteger nwd(BigInteger a, BigInteger b) {
        BigInteger c;
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
        if (!nwd.equals(BigInteger.ONE)) {
            this.numerator = this.numerator.divide(nwd);
            this.denominator = this.denominator.divide(nwd);
        }
    }

    BigIntFraction add(BigIntFraction number) {
        BigInteger newNumerator,newDenominator;
        if (!this.denominator.equals(number.denominator)) { // gdy różny mianownik
            newDenominator = nww(this.denominator, number.denominator); // wspólny mianownik
            // poniżej wzór na sumę liczników zamienionych na wspólny mianownik
            BigInteger newNumerator1 = this.numerator.multiply(newDenominator.divide(this.denominator));
            BigInteger newNumerator2 = number.numerator.multiply(newDenominator.divide(number.denominator));
            newNumerator = newNumerator1.add(newNumerator2);

        } else { // gdy równy mianownik
            newNumerator = this.numerator.add(number.numerator);
            newDenominator = this.denominator;
        }

        BigIntFraction newFraction = new BigIntFraction(newNumerator,newDenominator);
        newFraction.simplify();
        return  newFraction;
    }

    public BigIntFraction subtract(BigIntFraction number) {
        BigInteger newNumerator,newDenominator;
        if (!this.denominator.equals(number.denominator)) { // gdy różny mianownik
            newDenominator = nww(this.denominator, number.denominator); // wspólny mianownik
            // poniżej wzór na sumę liczników zamienionych na wspólny mianownik
            BigInteger newNumerator1 = this.numerator.multiply(newDenominator.divide(this.denominator));
            BigInteger newNumerator2 = number.numerator.multiply(newDenominator.divide(number.denominator));
            newNumerator = newNumerator1.subtract(newNumerator2);

        } else { // gdy równy mianownik
            newNumerator = this.numerator.subtract(number.numerator);
            newDenominator = this.denominator;
        }

        BigIntFraction newFraction = new BigIntFraction(newNumerator,newDenominator);
        newFraction.simplify();
        return  newFraction;
    }

    BigIntFraction multiply(BigIntFraction number) {

        BigInteger newNumerator, newDenominator;
        newNumerator = this.numerator.multiply(number.numerator);
        newDenominator = this.denominator.multiply(number.denominator);

        BigIntFraction newFraction = new BigIntFraction(newNumerator,newDenominator);
        newFraction.simplify();
        return  newFraction;

    }

    BigIntFraction divide(BigIntFraction number) {

        BigInteger newNumerator, newDenominator;
        newNumerator = this.numerator.multiply(number.denominator);
        newDenominator = this.denominator.multiply(number.numerator);

        BigIntFraction newFraction = new BigIntFraction(newNumerator,newDenominator);
        newFraction.simplify();
        return  newFraction;

    }

    public String toString() {
        return this.numerator.toString() + "/" + this.denominator.toString();
    }
}