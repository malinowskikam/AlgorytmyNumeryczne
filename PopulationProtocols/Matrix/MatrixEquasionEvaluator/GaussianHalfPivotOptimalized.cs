﻿using System;
using System.Collections.Generic;
using System.Text;
using System.Diagnostics;

namespace PopulationProtocols
{
    class GaussianHalfPivotOptimalized : IMatrixEquasionEvaluator<double>
    {
        public Result Perform(MatrixEquasion<double> eq)
        {
            Stopwatch st = new Stopwatch();
            MatrixEquasion<double> temp = new MatrixEquasion<double>(eq);

            st.Start();
            FirstPhaseHalfPivot(temp);
            SecondPhase(temp);
            st.Stop();

            double error = Matrix<double>.GetNormOfDiffrence(eq.A.Multiply(temp.B), eq.B);

            return new Result("GaussianHalfPivotOptimalized", error, st.ElapsedMilliseconds, 1, temp.B);
        }

        private void FirstPhaseHalfPivot(MatrixEquasion<double> eq)
        {
            //Pierwsza faza eliminacji Gaussa, z częściowym wyborem elementu podstawowego
            //Tworzy z macierzy A macierz trójkątną górną oraz dzieli wiersze tak, aby uzyskać jedynki wiodące
            //Przenosi operacje na macierz B
            for (int i = 0; i < eq.A.ColCount; i++)
            {
                //Wybór wiodącego elementu
                int max = FindMaxInColumn(eq.A, i, i, eq.A.RowCount);

                eq.A.SwapRows(i, max);
                eq.B.SwapRows(i, max);

                if (eq.A.ValueMatrix[i][i].CompareTo(MatrixDouble.ONE) != 0) //Pierwsza optymalizacja - jeśli wiodąca liczba jest jedynką, to wiersz nie jest już mnożony
                {
                    eq.B.ValueMatrix[i] = Matrix<double>.MultiplyRow(eq.B.ValueMatrix[i], (eq.A.ValueMatrix[i][i].GetInverse()));
                    eq.A.ValueMatrix[i] = Matrix<double>.MultiplyRow(eq.A.ValueMatrix[i], (eq.A.ValueMatrix[i][i].GetInverse()));
                }

                for (int j = i + 1; j < eq.A.RowCount; j++)
                {
                    if (eq.A.ValueMatrix[j][i].CompareTo(MatrixDouble.ZERO) != 0)
                    {
                        eq.B.ValueMatrix[j] = Matrix<double>.SubtractRows(eq.B.ValueMatrix[j], Matrix<double>.MultiplyRow(eq.B.ValueMatrix[i], eq.A.ValueMatrix[j][i]));
                        eq.A.ValueMatrix[j] = Matrix<double>.SubtractRows(eq.A.ValueMatrix[j], Matrix<double>.MultiplyRow(eq.A.ValueMatrix[i], eq.A.ValueMatrix[j][i]));
                    }
                }
            }
        }

        private int FindMaxInColumn(Matrix<double> A, int col, int scope_min, int scope_max)
        {
            int max = scope_min;
            for (int i = scope_min + 1; i < scope_max; i++)
                if ((A.ValueMatrix[i][col].Abs().CompareTo(A.ValueMatrix[max][col].Abs())) > 0)
                    max = i;
            return max;
        }

        private void SecondPhase(MatrixEquasion<double> eq)
        {
            //Druga faza eliminacji Gaussa
            //Sprowadza macierz A do macierzy jednostkowej
            for (int i = eq.A.ColCount - 1; i >= 0; i--)
            {
                for (int j = i - 1; j >= 0; j--)
                {
                    if (eq.A.ValueMatrix[j][i].CompareTo(MatrixDouble.ZERO) != 0)
                    {
                        eq.B.ValueMatrix[j] = Matrix<double>.SubtractRows(eq.B.ValueMatrix[j], Matrix<double>.MultiplyRow(eq.B.ValueMatrix[i], eq.A.ValueMatrix[j][i]));
                        eq.A.ValueMatrix[j] = Matrix<double>.SubtractRows(eq.A.ValueMatrix[j], Matrix<double>.MultiplyRow(eq.A.ValueMatrix[i], eq.A.ValueMatrix[j][i]));
                    }
                }
            }
        }
    }
}
