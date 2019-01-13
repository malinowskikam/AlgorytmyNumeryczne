using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Diagnostics;
using Approximation.Matrix.MatrixGeneration;
using Approximation.Matrix;
using Approximation.Matrix.MatrixEquasionEvaluator;
using Approximation.LeastSquares;

namespace Approximation
{
    class GenerationTest
    {
        public static void Start(int[] agentCounts)
        {
            Console.WriteLine("matrix generation");
            Console.WriteLine();
            Console.WriteLine("pomiary");
            Console.WriteLine("agenci,rownania,sekundy");
            double[] equasionCount = new double[agentCounts.Length];
            double[] executionTimes = new double[agentCounts.Length];

            for (int i = 0; i < agentCounts.Length; i++)
            {
                MatrixGenerator eqg = new MatrixGenerator(agentCounts[i]);
                
                Stopwatch st = new Stopwatch();

                st.Start();
                MatrixEquation<double> eq = eqg.GenerateEquation();
                st.Stop();

                equasionCount[i] = eq.A.RowCount;
                executionTimes[i] = st.Elapsed.TotalSeconds;
                Console.WriteLine($"{agentCounts[i]}, {equasionCount[i]}, {executionTimes[i]}");
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