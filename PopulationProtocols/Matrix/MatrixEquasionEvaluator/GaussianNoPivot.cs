using System;
using System.Collections.Generic;
using System.Text;

namespace PopulationProtocols
{
    class GaussianNoPivot<T> : IMatrixEquasionEvaluator<T>
    {
        public Matrix<T> Perform(MatrixEquasion<T> eq)
        {
            FirstPhaseNoPivot(eq);
            SecondPhase(eq);

            return eq.B;
        }

        private void FirstPhaseNoPivot(MatrixEquasion<T> eq)
        {
            //Pierwsza faza eliminacji Gaussa, bez wyboru elementu podstawowego
            //Tworzy z macierzy A macierz trójkątną górną oraz dzieli wiersze tak, aby uzyskać jedynki wiodące
            //Przenosi operacje na macierz B
            for (int i = 0; i < eq.A.ColCount; i++)
            {
                eq.B.ValueMatrix[i] = Matrix<T>.MultiplyRow(eq.B.ValueMatrix[i], (eq.A.ValueMatrix[i][i].GetInverse()));
                eq.A.ValueMatrix[i] = Matrix<T>.MultiplyRow(eq.A.ValueMatrix[i], (eq.A.ValueMatrix[i][i].GetInverse()));

                for (int j = i + 1; j < eq.A.RowCount; j++)
                {
                    eq.B.ValueMatrix[j] = Matrix<T>.SubtractRows(eq.B.ValueMatrix[j], Matrix<T>.MultiplyRow(eq.B.ValueMatrix[i], eq.A.ValueMatrix[j][i]));
                    eq.A.ValueMatrix[j] = Matrix<T>.SubtractRows(eq.A.ValueMatrix[j], Matrix<T>.MultiplyRow(eq.A.ValueMatrix[i], eq.A.ValueMatrix[j][i]));
                }
            }
        }

        private void SecondPhase(MatrixEquasion<T> eq)
        {
            //Druga faza eliminacji Gaussa
            //Sprowadza macierz A do macierzy jednostkowej
            //Po tej operacji macierz B to wyliczony X, błąd to | ||B|| - ||X|| |
            for (int i = eq.A.ColCount - 1; i >= 0; i--)
            {
                for (int j = i - 1; j >= 0; j--)
                {
                    eq.B.ValueMatrix[j] = Matrix<T>.SubtractRows(eq.B.ValueMatrix[j], Matrix<T>.MultiplyRow(eq.B.ValueMatrix[i], eq.A.ValueMatrix[j][i]));
                    eq.A.ValueMatrix[j] = Matrix<T>.SubtractRows(eq.A.ValueMatrix[j], Matrix<T>.MultiplyRow(eq.A.ValueMatrix[i], eq.A.ValueMatrix[j][i]));
                }
            }
        }
    }
}
