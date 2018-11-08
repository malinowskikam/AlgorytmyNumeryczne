package Matrix.Datatypes;

public class MatrixFloat extends MatrixDataType<Float> {

    private static final MatrixFloat ONE = new MatrixFloat(1.0f);
    private static final MatrixFloat MINUSONE = new MatrixFloat(-1.0f);
    private static final MatrixFloat ZERO = new MatrixFloat(0.0f);


    private float value;

    public MatrixFloat(int v) {
        setValue(((float) v) / 0x10000); //0x10000 = 2^16
    }

    private MatrixFloat(float v) {
        this.setValue(v);
    }

    @Override
    public MatrixDataType<Float> getZero() {
        return ZERO;
    }

    @Override
    public MatrixDataType<Float> getMinusOne() {
        return MINUSONE;
    }

    @Override
    public MatrixDataType<Float> getOne() {
        return ONE;
    }


    @Override
    public double evaluate() {
        return this.value;
    }

    @Override
    public Float getValue() {
        return this.value;
    }

    @Override
    public void setValue(Float v) {
        this.value = v;
    }

    @Override
    public String toString() {
        return Float.toString(this.value);
    }

    @Override
    public MatrixDataType<Float> add(MatrixDataType<Float> number) {
        return new MatrixFloat(this.getValue() + number.getValue());
    }

    @Override
    public MatrixDataType<Float> subtract(MatrixDataType<Float> number) {
        return new MatrixFloat(this.getValue() - number.getValue());
    }

    @Override
    public MatrixDataType<Float> multiply(MatrixDataType<Float> number) {
        return new MatrixFloat(this.getValue() * number.getValue());
    }

    @Override
    public MatrixDataType<Float> divide(MatrixDataType<Float> number) {
        return new MatrixFloat(this.getValue() / number.getValue());
    }

    @Override
    public MatrixDataType<Float> getInverse() {
        return ONE.divide(this);
    }

    @Override
    public int compareTo(MatrixDataType<Float> number) {
        return Float.compare(this.getValue(), number.getValue());
    }

    @Override
    public MatrixDataType<Float> abs() {
        if (this.compareTo(ZERO) > 0)
            return this;
        else
            return this.multiply(MINUSONE);
    }
}