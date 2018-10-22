package Datatypes;

public class MatrixDouble implements MatrixDataType<MatrixDouble,Double> {

    private double value;

    public MatrixDouble(int v) {
        setValue(((double)v)/0x10000); //0x10000 = 2^16
    }

    @Override
    public double evaluate() {
        return value;
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
    public void add(MatrixDouble number) {
        this.value += number.getValue();
    }

    @Override
    public void subtract(MatrixDouble number) {
        this.value -= number.getValue();
    }

    @Override
    public void multiply(MatrixDouble number) {
        this.value *= number.getValue();
    }

    @Override
    public void divide(MatrixDouble number) {
        this.value /= number.getValue();
    }
}
