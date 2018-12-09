using System;
using System.Collections.Generic;
using System.Text;

namespace PopulationProtocols
{
    public interface IMatrixEquasionEvaluator<T>
    {
        Result Perform(MatrixEquasion<T> eq);    
    }
}
