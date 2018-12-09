using System;
using System.Collections.Generic;
using System.Text;

namespace PopulationProtocols
{
    class PerformanceTest
    {
        public static void Run()
        {
            int[] NOfAgents = { 5,10,15,20,25,30,35,40,45,50 };
            int[] iterations = { 50,20,10,7,5,3,2,2,1,1 };

            Console.WriteLine("Method;n of agents;avg time;error;n of iterations");

            for(int i=0;i<NOfAgents.Length;i++)
            {
                MatrixGenerator Gen = new MatrixGenerator(NOfAgents[i]);
                MatrixEquasion<double> eq = Gen.GenerateEquasion();
                List<Result> res;
                Result avg;

                /*
                res = new List<Result>();
                for(int j=0;j<iterations[i];j++)
                {
                    res.Add(eq.Evaluate(new GaussianHalfPivot()));
                }
                avg = new Result(res);
                Console.WriteLine($"{avg.Method};{NOfAgents[i]};{avg.TimeElapsed};{avg.Error};{avg.NOfIterations}");

                res = new List<Result>();
                for (int j = 0; j < iterations[i]; j++)
                {
                    res.Add(eq.Evaluate(new GaussianHalfPivotOptimalized()));
                }
                avg = new Result(res);
                Console.WriteLine($"{avg.Method};{NOfAgents[i]};{avg.TimeElapsed};{avg.Error};{avg.NOfIterations}");

                res = new List<Result>();
                for (int j = 0; j < iterations[i]; j++)
                {
                    res.Add(eq.Evaluate(new Jacobian(1e-4)));
                }
                avg = new Result(res);
                Console.WriteLine($"{avg.Method};{NOfAgents[i]};{avg.TimeElapsed};{avg.Error};{avg.NOfIterations}");

                res = new List<Result>();
                for (int j = 0; j < iterations[i]; j++)
                {
                    res.Add(eq.Evaluate(new Jacobian(1e-10)));
                }
                avg = new Result(res);
                Console.WriteLine($"{avg.Method};{NOfAgents[i]};{avg.TimeElapsed};{avg.Error};{avg.NOfIterations}");

                res = new List<Result>();
                for (int j = 0; j < iterations[i]; j++)
                {
                    res.Add(eq.Evaluate(new Jacobian(1e-14)));
                }
                avg = new Result(res);
                Console.WriteLine($"{avg.Method};{NOfAgents[i]};{avg.TimeElapsed};{avg.Error};{avg.NOfIterations}");

                res = new List<Result>();
                for (int j = 0; j < iterations[i]; j++)
                {
                    res.Add(eq.Evaluate(new GaussSeidel(1e-4)));
                }
                avg = new Result(res);
                Console.WriteLine($"{avg.Method};{NOfAgents[i]};{avg.TimeElapsed};{avg.Error};{avg.NOfIterations}");

                res = new List<Result>();
                for (int j = 0; j < iterations[i]; j++)
                {
                    res.Add(eq.Evaluate(new GaussSeidel(1e-10)));
                }
                avg = new Result(res);
                Console.WriteLine($"{avg.Method};{NOfAgents[i]};{avg.TimeElapsed};{avg.Error};{avg.NOfIterations}");

                res = new List<Result>();
                for (int j = 0; j < iterations[i]; j++)
                {
                    res.Add(eq.Evaluate(new GaussSeidel(1e-14)));
                }
                avg = new Result(res);
                Console.WriteLine($"{avg.Method};{NOfAgents[i]};{avg.TimeElapsed};{avg.Error};{avg.NOfIterations}");
                */

                res = new List<Result>();
                for (int j = 0; j < iterations[i]; j++)
                {
                    res.Add(eq.Evaluate(new GaussSeidel(1e-6)));
                }
                avg = new Result(res);
                Console.WriteLine($"{avg.Method};{NOfAgents[i]};{avg.TimeElapsed};{avg.Error};{avg.NOfIterations}");

                res = new List<Result>();
                for (int j = 0; j < iterations[i]; j++)
                {
                    res.Add(eq.Evaluate(new Jacobian(1e-6)));
                }
                avg = new Result(res);
				Console.WriteLine($"{avg.Method};{NOfAgents[i]};{avg.TimeElapsed};{avg.Error};{avg.NOfIterations}");
            }

        }
    }
}
