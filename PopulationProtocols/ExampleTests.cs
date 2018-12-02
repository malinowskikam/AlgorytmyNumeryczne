using System;
using System.Collections.Generic;
using System.Text;

namespace PopulationProtocols
{
    class ExampleTests
    {
        public static void Start()
        {
            int size = 3;
            Random gen = new Random();
            SimulationControl s = new SimulationControl(size, gen);
            MatrixGenerator g = new MatrixGenerator(size);
            Matrix<Double> m = g.GenerateEquasion().Evaluate(new GaussianHalfPivot<Double>());
            Matrix<Double> m2 = g.GenerateEquasion().Evaluate(new Jacobian(10));

            Console.WriteLine("Eliminacja Gaussa, bez optymalizacji:");
            for (int i = 0; i < g.Keys.Count; i++)
                Console.WriteLine($"{g.Keys[i]} - {m.ValueMatrix[i][0].Evaluate() * 100} %");

            Console.WriteLine("Metoda Monte Carlo:");
            for (int i = 0; i <= size; i++)
            {
                for (int j = 0; j <= size - i; j++)
                    Console.WriteLine($"{i},{j} - {s.GetProbability(i, j, 100000, 1000)*100} %");
            }

            Console.WriteLine("Metoda Jacobiego:");
            for (int i = 0; i < g.Keys.Count; i++)
                Console.WriteLine($"{g.Keys[i]} - {m2.ValueMatrix[i][0].Evaluate() * 100} %");
        }
    }
}