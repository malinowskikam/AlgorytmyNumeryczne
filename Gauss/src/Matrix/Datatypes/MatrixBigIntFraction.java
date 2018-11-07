package Matrix.Datatypes;

import java.math.BigInteger;

public class MatrixBigIntFraction extends MatrixDataType<BigIntFraction> {

    private BigIntFraction fraction;

    public MatrixBigIntFraction(int v) {
        setValue(new BigIntFraction(new BigInteger("" + v), new BigInteger("" + 0x10000))); //0x10000 = 2^16
        this.inferedClass = BigIntFraction.class;
    }

    public MatrixBigIntFraction(int n, int d) {
        setValue(new BigIntFraction(new BigInteger("" + n), new BigInteger("" + d)));
        this.inferedClass = BigIntFraction.class;
    }

    public MatrixBigIntFraction(BigIntFraction f) {
        this.setValue(f);
        this.inferedClass = BigIntFraction.class;
    }

    public MatrixBigIntFraction(MatrixBigIntFraction prototype) {
        this.setValue(new BigIntFraction(prototype.getValue()));
        this.inferedClass = BigIntFraction.class;
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
}
