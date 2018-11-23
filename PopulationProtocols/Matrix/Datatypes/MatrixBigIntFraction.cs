using System;
using System.Numerics;

namespace PopulationProtocols {
    public class MatrixBigIntFraction : IMatrixDataType<BigIntFraction> {
        private static readonly MatrixBigIntFraction ONE = new MatrixBigIntFraction(new BigIntFraction(BigInteger.One, BigInteger.One));
        private static readonly MatrixBigIntFraction MINUSONE = new MatrixBigIntFraction(new BigIntFraction(new BigInteger(-1), BigInteger.One));
        private static readonly MatrixBigIntFraction ZERO = new MatrixBigIntFraction(new BigIntFraction(BigInteger.Zero, BigInteger.One));
        private BigIntFraction Fraction {
            get { return Fraction; }
            set { value = Fraction; }
        }

        public MatrixBigIntFraction(int v) {
            //tworzenie ułamka z inta, mianownik to 2^16
            SetValue(new BigIntFraction(new BigInteger(v), new BigInteger(0x10000))); //0x10000 = 2^16
        }

        private MatrixBigIntFraction(BigIntFraction f) {
            this.SetValue(f);
        }

        public IMatrixDataType<BigIntFraction> GetZero() {
            return ZERO;
        }

        public IMatrixDataType<BigIntFraction> GetMinusOne() {
            return MINUSONE;
        }

        public IMatrixDataType<BigIntFraction> GetOne() {
            return ONE;
        }

        public double Evaluate() {
            return (double)Fraction.numerator / (double)Fraction.denominator;
        }

        public BigIntFraction GetValue() {
            return Fraction;
        }

        public void SetValue(BigIntFraction f) {
            Fraction = f;
        }

        public override string ToString() {
            return Fraction.numerator.ToString() + "/" + Fraction.denominator.ToString();
        }

        public IMatrixDataType<BigIntFraction> Add(IMatrixDataType<BigIntFraction> number) {
            return new MatrixBigIntFraction(GetValue().Add(number.GetValue()));
        }

        public IMatrixDataType<BigIntFraction> Subtract(IMatrixDataType<BigIntFraction> number) {
            return new MatrixBigIntFraction(GetValue().Subtract(number.GetValue()));
        }

        public IMatrixDataType<BigIntFraction> Multiply(IMatrixDataType<BigIntFraction> number) {
            return new MatrixBigIntFraction(GetValue().Multiply(number.GetValue()));
        }

        public IMatrixDataType<BigIntFraction> Divide(IMatrixDataType<BigIntFraction> number) {
            return new MatrixBigIntFraction(GetValue().Divide(number.GetValue()));
        }

        public IMatrixDataType<BigIntFraction> GetInverse() {
            return ONE.Divide(this);
        }

        public int CompareTo(IMatrixDataType<BigIntFraction> number) {
            return BigIntFraction.Compare(GetValue(), number.GetValue());
        }

        public IMatrixDataType<BigIntFraction> Abs() {
            if (CompareTo(ZERO) > 0)
                return this;
            else
                return Multiply(MINUSONE);
        }
    }
}