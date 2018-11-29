﻿using System;
using System.Collections.Generic;
using System.Text;

namespace PopulationProtocols
{
    class MatrixEquasionTests
    {
        public static void Start()
        {
            // początek danych testowych

            IMatrixDataType<Double>[][] raw1 = new IMatrixDataType<Double>[2][];
            raw1[0] = new IMatrixDataType<Double>[2];
            raw1[1] = new IMatrixDataType<Double>[2];

            raw1[0][0] = new MatrixDouble(1.0 / 3.0);
            raw1[1][0] = new MatrixDouble(2.0 / 3.0);
            raw1[0][1] = new MatrixDouble(2.0 / 3.0);
            raw1[1][1] = new MatrixDouble(1.0 / 3.0);

            Matrix<Double> A = new Matrix<Double>(raw1);

            IMatrixDataType<Double>[][] raw2 = new IMatrixDataType<Double>[2][];
            raw2[0] = new IMatrixDataType<Double>[1];
            raw2[1] = new IMatrixDataType<Double>[1];

            raw2[0][0] = new MatrixDouble(1.0 / 2.0);
            raw2[1][0] = new MatrixDouble(1.0 / 4.0);

            Matrix<Double> X = new Matrix<Double>(raw2);

            //koniec danych testowych

            //wywołanie eliminacji

            MatrixEquasion<Double> eq = new MatrixEquasion<Double>(A, X, A.Multiply(X));


            Console.WriteLine(eq.Evaluate(new GaussianHalfPivot<Double>()));
            Console.WriteLine();
            //Console.WriteLine(eq.Evaluate(new Jacobian<Double>(10)));
            //Console.WriteLine(eq.Evaluate(new GaussSeidel<Double>(10)));
            Console.WriteLine();
            Console.WriteLine(eq);
        }
    }
}
