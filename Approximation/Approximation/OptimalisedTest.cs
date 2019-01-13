using System;
using System.Collections.Generic;
using System.Text;
using System.Diagnostics;
using Approximation.Matrix.MatrixGeneration;
using Approximation.Matrix;
using Approximation.Matrix.MatrixEquasionEvaluator;
using Approximation.LeastSquares;

namespace Approximation
{
    class OptimalisedTest
    {
        public static void Start(int[] agentCounts)
        {
            Console.WriteLine("gaussian half-pivot optimalized");
            Console.WriteLine();
            double[] equasionCount = new double[agentCounts.Length];
            double[] executionTimes = new double[agentCounts.Length];
            Console.WriteLine("pomiary");
            Console.WriteLine("rownania,sekundy");
            for (int i = 0; i < agentCounts.Length; i++)
            {
                MatrixGenerator eqg = new MatrixGenerator(agentCounts[i]);
                MatrixEquation<double> eq = eqg.GenerateEquation();

                equasionCount[i] = eq.A.RowCount;

                Stopwatch st = new Stopwatch();
                IMatrixEquasionEvaluator<double> mee = new GaussianHalfPivotOptimalized();

                st.Start();
                eq.Evaluate(mee);
                st.Stop();

                executionTimes[i] = st.Elapsed.TotalSeconds;
                Console.WriteLine($"{equasionCount[i]}, {executionTimes[i]}");
            }
            Console.WriteLine();
            SquareFunction c = new SquareFunction(equasionCount, executionTimes);
            Console.WriteLine("funkcja");
            Console.WriteLine(c.ToString());
            Console.WriteLine("argumenty,wartosci");
            foreach (double x in equasionCount)
                Console.WriteLine($"{x}, {c.GetValue(x)}");

            Console.WriteLine($"{5000}, {c.GetValue(5000)}");
            Console.WriteLine($"{10000}, {c.GetValue(10000)}");
            Console.WriteLine($"{25000}, {c.GetValue(25000)}");
            Console.WriteLine($"{50000}, {c.GetValue(50000)}");
            Console.WriteLine($"{100000}, {c.GetValue(100000)}");
            Console.WriteLine();
        } 
    }
}
