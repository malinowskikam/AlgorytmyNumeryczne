package Datatypes;

public interface MatrixDataType<T1,T2> {
    public abstract double evaluate();
    public T2 getValue();
    public void setValue(T2 v);
    public abstract void add(T1 number);
    public abstract void subtract(T1 number);
    public abstract void multiply(T1 number);
    public abstract void divide(T1 number);
}
