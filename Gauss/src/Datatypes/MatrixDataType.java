package Datatypes;

public interface MatrixDataType<T1,T2> {
    public abstract double evaluate();
    public T2 getValue();
    public void setValue(T2 v);
    public abstract String toString();
    public abstract T1 add(T1 number);
    public abstract T1 subtract(T1 number);
    public abstract T1 multiply(T1 number);
    public abstract T1 divide(T1 number);
}
