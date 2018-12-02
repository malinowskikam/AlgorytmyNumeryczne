using System;
using System.Collections.Generic;
using System.Text;

namespace PopulationProtocols
{
    class MatrixEquasion<T>
    {
        public Matrix<T> A { set; get; }
        public Matrix<T> X { set; get; }
        public Matrix<T> B{ set; get; }

        public MatrixEquasion(MatrixEquasion<T> prototype) : this(new Matrix<T>(prototype.A), prototype.X!=null?new Matrix<T>(prototype.X):null, new Matrix<T>(prototype.B)) { }

        public MatrixEquasion(Matrix<T> A, Matrix<T> X, Matrix<T> B)
        {
            this.A = A;
            this.B = B;
            this.X = X;
        }

        public Matrix<T> Evaluate(IMatrixEquasionEvaluator<T> eval)
        {
            return eval.Perform(new MatrixEquasion<T>(this));
        }

       
        public override String ToString()
        {
            return $"--A:--\n{A.ToString()}--X:--\n{X?.ToString()}--B:--\n{B.ToString()}";
        }
    
}
}
