using System;
using System.Collections.Generic;
using System.Text;

namespace PopulationProtocols
{
    class MatrixGenerationTest
    {
        public static void Run()
        {
            MatrixGenerator Gen = new MatrixGenerator(3);

            MatrixEquasion<Double> Eq = Gen.GenerateEquasion();

            Console.WriteLine("Matrix generation");
            Console.WriteLine("A:");
            Console.WriteLine(Eq.A);
            Console.WriteLine("B:");
            Console.WriteLine(Eq.B);
        }
    }
}
