using Approximation.Matrix;
using System;
using System.Collections.Generic;
using System.Text;
using Approximation.Matrix.Datatypes;

namespace Approximation.LeastSquares
{
    class CubicFunction
    {
        private double a;
        private double b;
        private double c;
        private double d;


        public CubicFunction(double[] arguments, double[] values)
        {
            Matrix<double> A = GetAMatrix(arguments);
            Matrix<double> B = GetBMatrix(arguments, values);

            MatrixEquasion<double> Eq = new MatrixEquasion<double>(A, null, B);

            Eq.Evaluate(new Matrix.MatrixEquasionEvaluator.GaussianHalfPivot());
           
            d = Eq.X.ValueMatrix[0][0].Evaluate();
            c = Eq.X.ValueMatrix[1][0].Evaluate();
            b = Eq.X.ValueMatrix[2][0].Evaluate();
            a = Eq.X.ValueMatrix[3][0].Evaluate();
        }

        public double GetValue(double argument)
        {
            return a * argument * argument * argument + b * argument * argument + c * argument + d;
        }

        private Matrix<double> GetAMatrix(double[] arguments)
        {
            Matrix<double> A;
            double[] s = new double[7]; // 0..2m

            MatrixDouble[][] m = new MatrixDouble[4][];
            for (int i = 0; i < 4; i++)
                m[i] = new MatrixDouble[4];

            for (int i = 0; i < 7; i++)
            {
                double sum = 0.0;
                for (int j = 0; j < arguments.Length; j++)
                {
                    sum += IntPow(arguments[j], i);
                }
                s[i] = sum;
            }

            for (int i = 0; i < 4; i++)
                for (int j = 0; j < 4; j++)
                    m[i][j] = new MatrixDouble(s[i + j]);

            A = new Matrix<double>(m);
            return A;
        }

        private Matrix<double> GetBMatrix(double[] arguments, double[] values)
        {
            Matrix<double> B;

            MatrixDouble[][] m = new MatrixDouble[4][];
            for (int i = 0; i < 4; i++)
                m[i] = new MatrixDouble[1];

            for (int i = 0; i < 4; i++) //i to k z prezentacji, j to i
            {
                double sum = 0.0;
                for (int j = 0; j < arguments.Length; j++)
                {
                    sum += values[j] * IntPow(arguments[j], i);
                }
                m[i][0] = new MatrixDouble(sum);
            }

            B = new Matrix<double>(m);

            return B;
        }

        private double IntPow(double number, int power)
        {
            double result = 1.0;
            for (int i = 0; i < power; i++)
            {
                result *= number;
            }
            return result;
        }

        public override string ToString()
        {
            return $"{a}x^3 + {b}x^2 + {c}x + {d}";
        }
    }
}
