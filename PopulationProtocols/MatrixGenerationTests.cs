using System;
using System.Collections.Generic;
using System.Text;

namespace PopulationProtocols
{
    class MatrixGenerationTests
    {
        public static void Start()
        {
            MatrixGenerator g = new MatrixGenerator(3);

            Console.WriteLine(g.GenerateEquasion());
        }
    }
}
