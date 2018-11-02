package Matrix.Datatypes;

public class MatrixDouble extends MatrixDataType<Double> {

    private double value;

    public MatrixDouble(int v) {
        setValue(((double)v)/0x10000); //0x10000 = 2^16
    }

    public MatrixDouble(double v) {
        this.value=v;
        this.inferedClass=Double.class;
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
    public String toString() { return ""+value;}

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
}
