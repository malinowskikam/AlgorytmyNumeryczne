using System;
using System.Collections.Generic;
using System.Text;
using System.Diagnostics;

namespace PopulationProtocols
{
    public class Jacobian : IMatrixEquasionEvaluator<double> {
        readonly double precision;

        public Jacobian(double i) {
            this.precision = i;
        }

        private void JacobianIteration(MatrixEquasion<Double> eq, Matrix<Double> oldMatrix, Matrix<Double> newMatrix, int i) {
            IMatrixDataType<Double> x = new MatrixDouble(0);
            for(int j=0; j<eq.A.ColCount; j++) 
                if (i != j) 
                    x = (x.Add(eq.A.ValueMatrix[i][j].Multiply(oldMatrix.ValueMatrix[j][0])));
            x = x.Multiply(MatrixDouble.MINUSONE);
            x = x.Add(eq.B.ValueMatrix[i][0]);
            x = x.Divide(eq.A.ValueMatrix[i][i]);

            newMatrix.ValueMatrix[i][0] = x;
        }

        public Result Perform(MatrixEquasion<Double> eq) {
            MatrixEquasion<double> temp = new MatrixEquasion<double>(eq);
            Matrix<double> oldVector = new Matrix<double>(eq.B);

            for (int i = 0; i < oldVector.RowCount; i++)
                oldVector.ValueMatrix[i][0] = MatrixDouble.ZERO;

            Matrix<double> newVector = new Matrix<double>(eq.B);

            int iterations = 0;
            Matrix<double> old = new Matrix<double>(newVector); ;

            Stopwatch st = new Stopwatch();
            st.Start();

            while (Matrix<double>.GetNormOfDiffrence(oldVector, newVector) > this.precision)
            {
                oldVector = new Matrix<double>(newVector);

                iterations++;

                for (int i=0; i<eq.A.RowCount; i++) 
                    JacobianIteration(eq, oldVector, newVector, i);
            }

            st.Stop();

            double error = Matrix<double>.GetNormOfDiffrence(eq.A.Multiply(newVector), eq.B);

            return new Result($"Jacobian (p:{this.precision})", error, st.ElapsedMilliseconds, iterations, newVector);
        }
    }
}
