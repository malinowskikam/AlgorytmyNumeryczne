package Matrix.Datatypes;

public abstract class MatrixDataType<T1> {

    public abstract MatrixDataType<T1> getZero();
    public abstract MatrixDataType<T1> getMinusOne();
    public abstract MatrixDataType<T1> getOne();

    public abstract double evaluate();
    public abstract T1 getValue();
    public abstract void setValue(T1 v);
    public abstract String toString();
    public abstract MatrixDataType<T1> add(MatrixDataType<T1> number);
    public abstract MatrixDataType<T1> subtract(MatrixDataType<T1> number);
    public abstract MatrixDataType<T1> multiply(MatrixDataType<T1> number);
    public abstract MatrixDataType<T1> divide(MatrixDataType<T1> number);
    public abstract MatrixDataType<T1> getInverse();
    public abstract int compareTo(MatrixDataType<T1> number);
    public abstract MatrixDataType<T1> abs();

}
