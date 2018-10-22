package Datatypes;

public class MatrixFloat implements MatrixDataType<MatrixFloat,Float> {

    private float value;

    public MatrixFloat(int v){
        setValue(((float)v)/0x10000); //0x10000 = 2^16
    }

    @Override
    public double evaluate() {
        return value;
    }

    @Override
    public Float getValue() {
        return (float) this.value;
    }

    @Override
    public void setValue(Float v) {
        this.value = v;
    }

    @Override
    public void add(MatrixFloat number) {
        this.value += number.getValue();
    }

    @Override
    public void subtract(MatrixFloat number) {
        this.value -= number.getValue();
    }

    @Override
    public void multiply(MatrixFloat number) {
        this.value *= number.getValue();
    }

    @Override
    public void divide(MatrixFloat number) {
        this.value /= number.getValue();
    }
}
