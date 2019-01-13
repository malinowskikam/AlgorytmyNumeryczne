using Approximation.LeastSquares;
using System;
using Approximation.Matrix.MatrixGeneration;

namespace Approximation
{
    class Program
    {
        static void Main(string[] args)
        {
            int[] agentCounts = { 5,140 };

            //GenerationTest.Start(agentCounts);
            //HalfPivotTest.Start(agentCounts);
            OptimalisedTest.Start(agentCounts);
            //GaussSeidelTest.Start(agentCounts);
        }
    }
}

