using System;
using System.Collections.Generic;
using System.Text;
using Approximation.Matrix.Datatypes;
using Approximation.Matrix.MatrixEquasionEvaluator;

namespace Approximation.Matrix
{
    public class MatrixEquation<T>
    {
        public Matrix<T> A { set; get; }
        public Matrix<T> X { set; get; }
        public Matrix<T> B { set; get; }

        public MatrixEquation(MatrixEquation<T> prototype) : this(new Matrix<T>(prototype.A), prototype.X!=null?new Matrix<T>(prototype.X):null, new Matrix<T>(prototype.B)) { }

        public MatrixEquation(Matrix<T> A, Matrix<T> X, Matrix<T> B)
        {
            this.A = A;
            this.B = B;
            this.X = X;
        }

        public void Evaluate(IMatrixEquasionEvaluator<T> eval)
        {
            eval.Perform(this);
        }

       
        public override String ToString()
        {
            string s="";
            if (A != null)
                s += $"--A:--\n{A.ToString()}";
            if (X != null)
                s += $"--X:--\n{X.ToString()}";
            if (B != null)
                s += $"--B:--\n{B.ToString()}";

            return s;
        }
    
}
}
