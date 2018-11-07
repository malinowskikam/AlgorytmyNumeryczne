package Matrix.Datatypes;

import Matrix.Matrix;

public class MatrixFloat extends MatrixDataType<Float> {

    private float value;

    public MatrixFloat(int v) {
        setValue(((float) v) / 0x10000); //0x10000 = 2^16
    }

    public MatrixFloat(float v) {
        this.setValue(v);
        this.inferedClass = Float.class;
    }

    public MatrixFloat(MatrixFloat prototype) {
        this.setValue(prototype.getValue());
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
        return "" + value;
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
}