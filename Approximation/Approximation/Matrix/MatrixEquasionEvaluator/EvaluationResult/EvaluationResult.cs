using System;
using System.Collections.Generic;
using System.Text;

namespace Approximation.Matrix.MatrixEquasionEvaluator.EvaluationResult
{
    public class EvaluationResult
    {
        EvaluationMethod method;
        long EvaluationTime;
        long EquasionCount;

    }

    public enum EvaluationMethod {
        GAUSSIAN,GAUSSIAN_OPT,GAUSS_SEIDEL
    }
}
