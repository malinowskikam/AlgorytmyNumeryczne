using System;
using System.Collections.Generic;
using System.Text;

namespace PopulationProtocols.Matrix.MatrixEquasionEvaluator
{
    class GaussSeidel<T> : IMatrixEquasionEvaluator<T>
    {
        readonly int NOfIterations;

        GaussSeidel(int i)
        {
            NOfIterations = i;
        }

        public Matrix<T> Perform(MatrixEquasion<T> eq)
        {
            return null;
        }
    }
}
