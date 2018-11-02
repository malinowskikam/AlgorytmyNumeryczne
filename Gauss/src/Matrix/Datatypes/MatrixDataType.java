package Matrix.Datatypes;

public abstract class MatrixDataType<T1> {

    public Class inferedClass;

    public abstract double evaluate();
    public abstract T1 getValue();
    public abstract void setValue(T1 v);
    public abstract String toString();
    public abstract MatrixDataType<T1> add(MatrixDataType<T1> number);
    public abstract MatrixDataType<T1> subtract(MatrixDataType<T1> number);
    public abstract MatrixDataType<T1> multiply(MatrixDataType<T1> number);
    public abstract MatrixDataType<T1> divide(MatrixDataType<T1> number);
}
