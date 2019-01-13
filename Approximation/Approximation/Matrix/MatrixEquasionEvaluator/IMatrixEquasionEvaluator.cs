using System;
using System.Collections.Generic;
using System.Text;
using Approximation.Matrix.MatrixEquasionEvaluator;

namespace Approximation.Matrix.MatrixEquasionEvaluator
{
    public interface IMatrixEquasionEvaluator<T>
    {
        void Perform(MatrixEquation<T> eq);    
    }
}
