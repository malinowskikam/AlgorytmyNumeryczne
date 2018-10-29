package Datatypes;

public class MatrixFloat implements MatrixDataType<MatrixFloat,Float> {

    private float value;

    public MatrixFloat(int v){
        setValue(((float)v)/0x10000); //0x10000 = 2^16
    }

    public MatrixFloat(float v) { setValue(v);}

    @Override
    public double evaluate() {
        return (double)value;
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
    public String toString() { return ""+value;}

    @Override
    public MatrixFloat add(MatrixFloat number) {
        return new MatrixFloat(this.getValue() + number.getValue());
    }

    @Override
    public MatrixFloat subtract(MatrixFloat number) {
        return new MatrixFloat(this.getValue() - number.getValue());

    }

    @Override
    public MatrixFloat multiply(MatrixFloat number) {
        return new MatrixFloat(this.getValue() * number.getValue());
    }

    @Override
    public MatrixFloat divide(MatrixFloat number) {
        return new MatrixFloat(this.getValue() / number.getValue());
    }
}