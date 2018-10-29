package Datatypes;

public class MatrixDouble implements MatrixDataType<MatrixDouble,Double> {

    private double value;

    public MatrixDouble(int v) {
        setValue(((double)v)/0x10000); //0x10000 = 2^16
    }

    public MatrixDouble(double v) { this.value=v; }

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
    public MatrixDouble add(MatrixDouble number) {
        return new MatrixDouble(this.getValue() + number.getValue());
    }

    @Override
    public MatrixDouble subtract(MatrixDouble number) {
        return new MatrixDouble(this.getValue() - number.getValue());
    }

    @Override
    public MatrixDouble multiply(MatrixDouble number) {
        return new MatrixDouble(this.getValue() * number.getValue());
    }

    @Override
    public MatrixDouble divide(MatrixDouble number) {
        return new MatrixDouble(this.getValue() / number.getValue());
    }
}
