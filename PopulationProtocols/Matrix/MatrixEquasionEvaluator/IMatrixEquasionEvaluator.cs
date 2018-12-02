using System;
using System.Collections.Generic;
using System.Text;

namespace PopulationProtocols
{
    public interface IMatrixEquasionEvaluator<T>
    {
        Matrix<T> Perform(MatrixEquasion<T> eq);    
    }
}
