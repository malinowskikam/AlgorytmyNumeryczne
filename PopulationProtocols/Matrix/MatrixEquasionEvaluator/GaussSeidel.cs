using System;
using System.Collections.Generic;
using System.Text;

namespace PopulationProtocols {
    class GaussSeidel : IMatrixEquasionEvaluator<Double> {
        readonly int NOfIterations;

        GaussSeidel(int i) {
            NOfIterations = i;
        }

        public Matrix<Double> Perform(MatrixEquasion<Double> eq) {
            IMatrixDataType<Double>[][] array = new IMatrixDataType<double>[eq.A.RowCount][];
            for (int i = 0; i < eq.A.RowCount; i++) {
                array[i] = new IMatrixDataType<double>[1];
                array[i][0] = new MatrixDouble(0);
            }
            Matrix<Double> result = new Matrix<Double>(array);


            return result;
        }
    }
}
