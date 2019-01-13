using System;
using System.Collections.Generic;
using System.Text;
using System.Diagnostics;
using Approximation.Matrix.Datatypes;

namespace Approximation.Matrix.MatrixEquasionEvaluator
{
    public class GaussSeidel : IMatrixEquasionEvaluator<double> {
        readonly double precision;

        public GaussSeidel(double i) {
            this.precision = i;
        }

        private void GaussSeidelIteration(MatrixEquation<Double> eq, Matrix<Double> newVector, int i) {
            IMatrixDataType<double> x = new MatrixDouble(0);
            for (int j = 0; j < eq.A.ColCount; j++)
                if (i != j)
                    x = (x.Add(eq.A.ValueMatrix[i][j].Multiply(newVector.ValueMatrix[j][0])));
            x = x.Multiply(MatrixDouble.MINUSONE);
            x = x.Add(eq.B.ValueMatrix[i][0]);
            x = x.Divide(eq.A.ValueMatrix[i][i]);

            newVector.ValueMatrix[i][0] = x;
        }

        public void Perform(MatrixEquation<double> eq) {
            Matrix<double> oldVector = new Matrix<double>(eq.B);

            for(int i=0;i<oldVector.RowCount;i++)
                oldVector.ValueMatrix[i][0] = MatrixDouble.ZERO;

            Matrix<double> newVector = new Matrix<double>(eq.B);

            int iterations = 0;

            while (Matrix<double>.GetNormOfDiffrence(oldVector, newVector) > this.precision)
            {
                oldVector = new Matrix<double>(newVector);

                iterations++;

                for (int i = 0; i < eq.A.RowCount; i++)
                    GaussSeidelIteration(eq, newVector, i);
            }

            eq.X = newVector;

            return;
        }
    }
}
