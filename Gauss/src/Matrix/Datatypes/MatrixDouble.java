package Matrix.Datatypes;

public class MatrixDouble extends MatrixDataType<Double> {

    private static final MatrixDouble ONE = new MatrixDouble(1.0);
    private static final MatrixDouble MINUSONE = new MatrixDouble(-1.0);
    private static final MatrixDouble ZERO = new MatrixDouble(0.0);

    private double value;

    public MatrixDouble(int v) {
        setValue(((double) v) / 0x10000); //0x10000 = 2^16
    }

    private MatrixDouble(double v) {
        this.value = v;
    }

    @Override
    public MatrixDataType<Double> getZero() {
        return ZERO;
    }

    @Override
    public MatrixDataType<Double> getMinusOne() {
        return MINUSONE;
    }

    @Override
    public MatrixDataType<Double> getOne() {
        return ONE;
    }

    @Override
    public double evaluate() {
        return this.value;
    }

    @Override
    public Double getValue() {
        return this.value;
    }

    @Override
    public void setValue(Double v) {
        this.value = v;
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }

    @Override
    public MatrixDataType<Double> add(MatrixDataType<Double> number) {
        return new MatrixDouble(this.getValue() + number.getValue());
    }

    @Override
    public MatrixDataType<Double> subtract(MatrixDataType<Double> number) {
        return new MatrixDouble(this.getValue() - number.getValue());
    }

    @Override
    public MatrixDataType<Double> multiply(MatrixDataType<Double> number) {
        return new MatrixDouble(this.getValue() * number.getValue());
    }

    @Override
    public MatrixDataType<Double> divide(MatrixDataType<Double> number) {
        return new MatrixDouble(this.getValue() / number.getValue());
    }

    @Override
    public MatrixDataType<Double> getInverse() {
        return ONE.divide(this);
    }

    @Override
    public int compareTo(MatrixDataType<Double> number) {
        return Double.compare(this.getValue(), number.getValue());
    }

    @Override
    public MatrixDataType<Double> abs() {
        if (this.compareTo(ZERO) > 0)
            return this;
        else
            return this.multiply(MINUSONE);
    }
}
