using System;
using System.Collections.Generic;
using System.Text;

namespace PopulationProtocols {
    public class GaussSeidel : IMatrixEquasionEvaluator<Double> {
        readonly int NOfIterations;

        public GaussSeidel(int i) {
            NOfIterations = i;
        }

        private void GaussSeidelIteration(MatrixEquasion<Double> eq, Matrix<Double> newMatrix, int i) {
            IMatrixDataType<Double> x = new MatrixDouble(0);
            for (int j = 0; j < eq.A.ColCount; j++)
                if (i != j)
                    x = (x.Add(eq.A.ValueMatrix[i][j].Multiply(newMatrix.ValueMatrix[j][0])));
            x = x.Multiply(MatrixDouble.MINUSONE);
            x = x.Add(eq.B.ValueMatrix[i][0]);
            x = x.Divide(eq.A.ValueMatrix[i][i]);

            newMatrix.ValueMatrix[i][0] = x;
        }

        public Matrix<Double> Perform(MatrixEquasion<Double> eq) {
            Matrix<Double> newMatrix = new Matrix<Double>(eq.B);

            for (int k = 0; k < NOfIterations; k++) 
                for (int i = 0; i < eq.A.RowCount; i++)
                    GaussSeidelIteration(eq, newMatrix, i);

            return newMatrix;
        }
    }
}
