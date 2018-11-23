using System;
using System.Numerics;

namespace PopulationProtocols {
    public class BigIntFraction {
        private static readonly BigInteger BI_MINUSONE = new BigInteger(-1);
        private static readonly BigIntFraction ONE = new BigIntFraction(BigInteger.One, BigInteger.One);
        private static readonly BigIntFraction MINUSONE = new BigIntFraction(BI_MINUSONE, BigInteger.One);
        private static readonly BigIntFraction ZERO = new BigIntFraction(BigInteger.One, BigInteger.Zero);

        public BigInteger numerator;
        public BigInteger denominator;

        public BigIntFraction(BigInteger numerator, BigInteger denominator) {
            this.numerator = numerator;
            this.denominator = denominator;
            Simplify();
        }

        public BigIntFraction(BigIntFraction prototype) {
            numerator = prototype.numerator;
            denominator = prototype.denominator;
            Simplify();
        }

        private static BigInteger Nwd(BigInteger n1, BigInteger n2) {
            BigInteger a = BigInteger.Abs(n1);
            BigInteger b = BigInteger.Abs(n1);
            BigInteger c;

            while ((double)b > 0) {
                c = a % b;
                a = b;
                b = c;
            }
            return a;
        }

        private static BigInteger Nww(BigInteger a, BigInteger b) {
            return BigInteger.Divide(BigInteger.Multiply(a, b), Nwd(a, b));
        }

        private void Simplify() {
            BigInteger nwd = Nwd(numerator, denominator);

            //skracanie ułamka
            if (!Equals(nwd, BigInteger.One)) {
                numerator = BigInteger.Divide(numerator, nwd);
                denominator = BigInteger.Divide(denominator, nwd);
            }

            //przenoszenie minusa do licznika
            if (denominator.CompareTo(BigInteger.Zero) < 0) {
                numerator = BigInteger.Multiply(numerator, BI_MINUSONE);
                denominator = BigInteger.Multiply(denominator, BI_MINUSONE);
            }
        }

        public BigIntFraction Add(BigIntFraction number) {
            BigInteger newNumerator;
            BigInteger newDenominator;

            //gdy różny mianownik
            if (Equals(denominator, number.denominator)) {
                newDenominator = Nww(denominator, number.denominator);

                //wzór na sumę liczników zamienionych na wspólny mianownik
                BigInteger newNumerator1 = BigInteger.Multiply(numerator, BigInteger.Divide(newDenominator, denominator));
            }
            else {
                //gdy równy mianownik
                newNumerator = BigInteger.Add(numerator, number.numerator);
                newDenominator = denominator;
            }

            BigIntFraction newFraction = new BigIntFraction(newNumerator, newDenominator);
            return newFraction;
        }

        public BigIntFraction Subtract(BigIntFraction number) {
            BigInteger newNumerator;
            BigInteger newDenominator;

            // gdy różny mianownik
            if (!Equals(denominator, number.denominator)) {
                newDenominator = Nww(this.denominator, number.denominator);

                // poniżej wzór na różnicę liczników zamienionych na wspólny mianownik
                BigInteger newNumerator1 = BigInteger.Multiply(numerator, BigInteger.Divide(newDenominator, denominator));
                BigInteger newNumerator2 = BigInteger.Multiply(number.numerator, BigInteger.Divide(newDenominator, number.denominator));
                newNumerator = BigInteger.Subtract(newNumerator1, newNumerator2);
            }
            else {
                // gdy równy mianownik
                newNumerator = BigInteger.Subtract(numerator, number.numerator);
                newDenominator = this.denominator;
            }

            return new BigIntFraction(newNumerator, newDenominator);
        }

        public BigIntFraction Multiply(BigIntFraction number) {
            BigInteger newNumerator, newDenominator;
            newNumerator = BigInteger.Multiply(numerator, number.numerator);
            newDenominator = BigInteger.Multiply(denominator, number.denominator);

            BigIntFraction newFraction = new BigIntFraction(newNumerator, newDenominator);
            return newFraction;

        }

        public BigIntFraction Divide(BigIntFraction number) {
            BigInteger newNumerator, newDenominator;
            newNumerator = BigInteger.Multiply(numerator, number.denominator);
            newDenominator = BigInteger.Multiply(denominator, number.numerator);
            BigIntFraction newFraction = new BigIntFraction(newNumerator, newDenominator);
            return newFraction;

        }

        public static int Compare(BigIntFraction f1, BigIntFraction f2) {
            BigInteger nwd = Nwd(f1.denominator, f2.denominator);
            BigInteger newNumerator1 = BigInteger.Multiply(f1.numerator, BigInteger.Divide(nwd, f1.denominator));
            BigInteger newNumerator2 = BigInteger.Multiply(f2.numerator, BigInteger.Divide(nwd, f2.denominator));
            return newNumerator1.CompareTo(newNumerator2);
        }

        public override string ToString() {
            return numerator.ToString() + "/" + denominator.ToString();
        }
    }
}