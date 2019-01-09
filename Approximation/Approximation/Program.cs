using Approximation.LeastSquares;
using System;

namespace Approximation
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("");

            double[] arguments = { 0.0,0.25,0.5,0.75,1.0 };
            double[] values = { 1.0,1.284,1.6487,2.117,2.7183 };

            LinearFunction l = new LinearFunction(arguments, values);
            Console.WriteLine(l);
            SquareFunction s = new SquareFunction(arguments,values);
            Console.WriteLine(s);
            CubicFunction c = new CubicFunction(arguments, values);
            Console.WriteLine(c);
        }
    }
}
