using System;
using System.Numerics;

namespace Approximation.Matrix.Datatypes
{
    public interface IMatrixDataType<T1> {
        
        double Evaluate();
        T1 GetValue();
        void SetValue(T1 v);
        string ToString();

        IMatrixDataType<T1> Add(IMatrixDataType<T1> number);
        IMatrixDataType<T1> Subtract(IMatrixDataType<T1> number);
        IMatrixDataType<T1> Multiply(IMatrixDataType<T1> number);
        IMatrixDataType<T1> Divide(IMatrixDataType<T1> number);
        IMatrixDataType<T1> GetInverse();
        int CompareTo(IMatrixDataType<T1> number);
        IMatrixDataType<T1> Abs();
    }
}