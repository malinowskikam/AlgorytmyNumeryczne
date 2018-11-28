using System;
using System.Collections.Generic;
using System.Text;

namespace PopulationProtocols
{
    interface IMatrixEquasionEvaluator<T>
    {
        Matrix<T> Perform(MatrixEquasion<T> eq);    
    }
}
