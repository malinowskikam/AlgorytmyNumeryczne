using System;
using System.Collections.Generic;
using System.Text;

namespace PopulationProtocols
{
    class SimulationTests
    {
        public static void Start()
        {
            Random g = new Random();
            SimulationControl s = new SimulationControl(3, g);

            Console.WriteLine(s.GetProbability(1, 1, 1000, 100));
        }
    }
}
