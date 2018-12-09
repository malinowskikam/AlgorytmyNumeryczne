using System;
using System.Collections.Generic;
using System.Text;

namespace PopulationProtocols
{
    class MonteCarloTest
    {
        public static void Run()
        {
            int NOfAgents = 3;

            SimulationControl Sim = new SimulationControl(NOfAgents, new Random());
            MatrixGenerator Gen = new MatrixGenerator(NOfAgents);
            MatrixEquasion<double> eq = Gen.GenerateEquasion();

            Console.WriteLine(Sim.Perform(eq));
            Console.WriteLine(eq.Evaluate(new GaussianHalfPivot()));
            Console.WriteLine(eq.Evaluate(new GaussianHalfPivotOptimalized()));
            Console.WriteLine(eq.Evaluate(new Jacobian(1e-4)));
            Console.WriteLine(eq.Evaluate(new Jacobian(1e-10)));
            Console.WriteLine(eq.Evaluate(new Jacobian(1e-14)));
            Console.WriteLine(eq.Evaluate(new GaussSeidel(1e-4)));
            Console.WriteLine(eq.Evaluate(new GaussSeidel(1e-10)));
            Console.WriteLine(eq.Evaluate(new GaussSeidel(1e-14)));

        }
    }
}
