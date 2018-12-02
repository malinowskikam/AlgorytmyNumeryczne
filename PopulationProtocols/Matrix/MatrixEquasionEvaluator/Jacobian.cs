using System;
using System.Collections.Generic;
using System.Text;

namespace PopulationProtocols
{
    public class Jacobian : IMatrixEquasionEvaluator<Double> {
        readonly int NOfIterations;

        public Jacobian(int i) {
            NOfIterations = i;
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

        public Matrix<Double> Perform(MatrixEquasion<Double> eq) {
            IMatrixDataType<Double>[][] array = new IMatrixDataType<double>[eq.A.RowCount][];
            for (int i = 0; i < eq.A.RowCount; i++) {
                array[i] = new IMatrixDataType<double>[1];
                array[i][0] = new MatrixDouble(0);
            }
            Matrix<Double> oldMatrix = new Matrix<Double>(array);
            Matrix<Double> newMatrix = new Matrix<Double>(eq.B);

            for(int k=0; k<NOfIterations; k++) {
                for(int i=0; i<eq.A.RowCount; i++) 
                    JacobianIteration(eq, oldMatrix, newMatrix, i);
                oldMatrix = new Matrix<Double>(newMatrix);
            }

            return newMatrix;
        }
    }
}
