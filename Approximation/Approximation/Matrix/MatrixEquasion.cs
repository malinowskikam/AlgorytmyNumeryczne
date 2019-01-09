using System;
using System.Collections.Generic;
using System.Text;
using Approximation.Matrix.Datatypes;
using Approximation.Matrix.MatrixEquasionEvaluator;
using Approximation.Matrix.MatrixEquasionEvaluator.EvaluationResult;

namespace Approximation.Matrix
{
    public class MatrixEquasion<T>
    {
        public Matrix<T> A { set; get; }
        public Matrix<T> X { set; get; }
        public Matrix<T> B { set; get; }

        public MatrixEquasion(MatrixEquasion<T> prototype) : this(new Matrix<T>(prototype.A), prototype.X!=null?new Matrix<T>(prototype.X):null, new Matrix<T>(prototype.B)) { }

        public MatrixEquasion(Matrix<T> A, Matrix<T> X, Matrix<T> B)
        {
            this.A = A;
            this.B = B;
            this.X = X;
        }

        public EvaluationResult Evaluate(IMatrixEquasionEvaluator<T> eval)
        {
            return eval.Perform(this);
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
