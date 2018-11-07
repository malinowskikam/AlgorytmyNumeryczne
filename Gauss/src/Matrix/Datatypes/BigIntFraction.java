package Matrix.Datatypes;

import java.math.BigInteger;

public class BigIntFraction {
    BigInteger numerator; //licznik
    BigInteger denominator; //mianownik

    BigIntFraction(BigInteger n, BigInteger d) {
        this.numerator = n.divide(nwd(n, d));
        this.denominator = d.divide(nwd(n, d));
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

    BigIntFraction add(BigIntFraction number) {
        BigInteger newNumerator;
        if (!this.denominator.equals(number.denominator)) { // gdy różny mianownik
            BigInteger nww = nww(this.denominator, number.denominator); // wspólny mianownik
            // poniżej wzór na sumę liczników zamienionych na wspólny mianownik
            BigInteger newNumerator1 = this.numerator.multiply(nww.divide(this.denominator));
            BigInteger newNumerator2 = number.numerator.multiply(nww.divide(number.denominator));
            newNumerator = newNumerator1.add(newNumerator2);
            BigInteger nwd = nwd(newNumerator, nww); // nwd do skrócenia ułamka

            if (!nwd.equals(BigInteger.ONE))
                return new BigIntFraction(newNumerator.divide(nwd), nww.divide(nwd));
            else
                return new BigIntFraction(newNumerator, nww);
        } else { // gdy równy mianownik
            newNumerator = this.numerator.add(number.numerator);
            BigInteger nwd = nwd(newNumerator, this.denominator);
            if (!nwd.equals(BigInteger.ONE))
                return new BigIntFraction(newNumerator.divide(nwd), this.denominator.divide(nwd));
            else
                return new BigIntFraction(newNumerator, this.denominator);
        }
    }

    BigIntFraction subtract(BigIntFraction number) {
        BigInteger newNumerator;
        if (!this.denominator.equals(number.denominator)) { // gdy różny mianownik
            BigInteger nww = nww(this.denominator, number.denominator); // wspólny mianownik
            // poniżej wzór na sumę liczników zamienionych na wspólny mianownik
            BigInteger newNumerator1 = this.numerator.multiply(nww.divide(this.denominator));
            BigInteger newNumerator2 = number.numerator.multiply(nww.divide(number.denominator));
            newNumerator = newNumerator1.subtract(newNumerator2);
            BigInteger nwd = nwd(newNumerator, nww); // nwd do skrócenia ułamka

            if (!nwd.equals(BigInteger.ONE))
                return new BigIntFraction(newNumerator.divide(nwd), nww.divide(nwd));
            else
                return new BigIntFraction(newNumerator, nww);
        } else { // gdy równy mianownik
            newNumerator = this.numerator.subtract(number.numerator);
            BigInteger nwd = nwd(newNumerator, this.denominator);
            if (!nwd.equals(BigInteger.ONE))
                return new BigIntFraction(newNumerator.divide(nwd), this.denominator.divide(nwd));
            else
                return new BigIntFraction(newNumerator, this.denominator);
        }
    }

    BigIntFraction multiply(BigIntFraction number) {
        BigInteger nwd = nwd(this.numerator, number.denominator);
        if (!nwd.equals(BigInteger.ONE)) {
            this.numerator = this.numerator.divide(nwd);
            number.denominator = number.denominator.divide(nwd);
        }
        nwd = nwd(this.denominator, number.numerator);
        if (!nwd.equals(BigInteger.ONE)) {
            this.denominator = this.denominator.divide(nwd);
            number.numerator = number.numerator.divide(nwd);
        }

        BigInteger newNumerator, newDenominator;
        newNumerator = this.numerator.multiply(number.numerator);
        newDenominator = this.denominator.multiply(number.denominator);

        nwd = nwd(newNumerator, newDenominator);
        if (!nwd.equals(BigInteger.ONE))
            return new BigIntFraction(newNumerator.divide(nwd), newDenominator.divide(nwd));
        else
            return new BigIntFraction(newNumerator, newDenominator);

    }

    BigIntFraction divide(BigIntFraction number) {
        BigInteger nwd = nwd(this.numerator, number.numerator);
        if (!nwd.equals(BigInteger.ONE)) {
            this.numerator = this.numerator.divide(nwd);
            number.numerator = number.numerator.divide(nwd);
        }
        nwd = nwd(this.denominator, number.denominator);
        if (!nwd.equals(BigInteger.ONE)) {
            this.denominator = this.denominator.divide(nwd);
            number.denominator = number.denominator.divide(nwd);
        }

        BigInteger newNumerator, newDenominator;
        newNumerator = this.numerator.multiply(number.denominator);
        newDenominator = this.denominator.multiply(number.numerator);

        nwd = nwd(newNumerator, newDenominator);
        if (!nwd.equals(BigInteger.ONE))
            return new BigIntFraction(newNumerator.divide(nwd), newDenominator.divide(nwd));
        else
            return new BigIntFraction(newNumerator, newDenominator);

    }

    public String toString() {
        return this.numerator.toString() + "/" + this.denominator.toString();
    }
}
