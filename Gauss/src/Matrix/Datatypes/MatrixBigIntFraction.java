package Matrix.Datatypes;

import java.math.BigInteger;

public class MatrixBigIntFraction extends MatrixDataType<BigIntFraction> {

    private static final MatrixBigIntFraction ONE = new MatrixBigIntFraction(new BigIntFraction(BigInteger.ONE,BigInteger.ONE));
    private static final MatrixBigIntFraction MINUSONE = new MatrixBigIntFraction(new BigIntFraction(new BigInteger("-1"),BigInteger.ONE));
    private static final MatrixBigIntFraction ZERO = new MatrixBigIntFraction(new BigIntFraction(BigInteger.ZERO,BigInteger.ONE));

    private BigIntFraction fraction;

    public MatrixBigIntFraction(int v) {
        //tworzenie ułamka z inta, mianownik to 2^16
        setValue(new BigIntFraction(new BigInteger("" + v), new BigInteger("" + 0x10000))); //0x10000 = 2^16
    }

    private MatrixBigIntFraction(BigIntFraction f) {
        this.setValue(f);
    }

    @Override
    public MatrixDataType<BigIntFraction> getZero() {
        return ZERO;
    }

    @Override
    public MatrixDataType<BigIntFraction> getMinusOne() {
        return MINUSONE;
    }

    @Override
    public MatrixDataType<BigIntFraction> getOne() {
        return ONE;
    }

    @Override
    public double evaluate() {
        return fraction.numerator.doubleValue() / fraction.denominator.doubleValue();
    }

    @Override
    public BigIntFraction getValue() {
        return this.fraction;
    }

    @Override
    public void setValue(BigIntFraction f) {
        this.fraction = f;
    }

    @Override
    public String toString() {
        return fraction.numerator.toString() + "/" + fraction.denominator.toString();
    }

    @Override
    public MatrixDataType<BigIntFraction> add(MatrixDataType<BigIntFraction> number) {
        return new MatrixBigIntFraction(this.getValue().add(number.getValue()));
    }

    @Override
    public MatrixDataType<BigIntFraction> subtract(MatrixDataType<BigIntFraction> number) {
        return new MatrixBigIntFraction(this.getValue().subtract(number.getValue()));
    }

    @Override
    public MatrixDataType<BigIntFraction> multiply(MatrixDataType<BigIntFraction> number) {
        return new MatrixBigIntFraction(this.getValue().multiply(number.getValue()));
    }

    @Override
    public MatrixDataType<BigIntFraction> divide(MatrixDataType<BigIntFraction> number) {
        return new MatrixBigIntFraction(this.getValue().divide(number.getValue()));
    }

    @Override
    public MatrixDataType<BigIntFraction> getInverse() {
        return ONE.divide(this);
    }

    @Override
    public int compareTo(MatrixDataType<BigIntFraction> number) {
        return BigIntFraction.compare(this.getValue(), number.getValue());
    }

    @Override
    public MatrixDataType<BigIntFraction> abs() {
        if (this.compareTo(ZERO) > 0)
            return this;
        else
            return this.multiply(MINUSONE);
    }
}
