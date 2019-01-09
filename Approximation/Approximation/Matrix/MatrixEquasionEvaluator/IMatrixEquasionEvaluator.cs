using System;
using System.Collections.Generic;
using System.Text;
using Approximation.Matrix.MatrixEquasionEvaluator.EvaluationResult;

namespace Approximation.Matrix.MatrixEquasionEvaluator
{
    public interface IMatrixEquasionEvaluator<T>
    {
        EvaluationResult.EvaluationResult Perform(MatrixEquasion<T> eq);    
    }
}
