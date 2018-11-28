using System;
using System.Collections.Generic;
using System.Text;

namespace PopulationProtocols
{
    class GaussianFullPivot<T> : IMatrixEquasionEvaluator<T>
    {
        private List<int[]> temp = new List<int[]>();

        public Matrix<T> Perform(MatrixEquasion<T> eq)
        {
            FirstPhaseFullPivot(eq);
            SecondPhase(eq);

            //Przywrócenie kolejności współrzędnych w wynikowym wektorze
            foreach(int[] pos in temp)
                eq.B.SwapRows(pos[0], pos[1]);

            return eq.B;
        }

        private void FirstPhaseFullPivot(MatrixEquasion<T> eq)
        {
            //Pierwsza faza eliminacji Gaussa, z pełnym wyborem elementu podstawowego
            //Tworzy z macierzy A macierz trójkątną górną oraz dzieli wiersze tak, aby uzyskać jedynki wiodące
            //Przenosi operacje na macierz B
            for (int i = 0; i < eq.A.ColCount; i++)
            {
                //Wybór wiodącego elementu
                int[] pos = FindMaxInSubmatrix(eq.A, i, i);


                eq.A.SwapRows(i, pos[0]);
                eq.B.SwapRows(i, pos[0]);

                eq.A.SwapCols(i, pos[1]);

                //zapisanie zmiany na później
                pos[0] = i;
                temp.Add(pos);

                eq.B.ValueMatrix[i] = Matrix<T>.MultiplyRow(eq.B.ValueMatrix[i], (eq.A.ValueMatrix[i][i].GetInverse()));
                eq.A.ValueMatrix[i] = Matrix<T>.MultiplyRow(eq.A.ValueMatrix[i], (eq.A.ValueMatrix[i][i].GetInverse()));

                for (int j = i + 1; j < eq.A.RowCount; j++)
                {
                    eq.B.ValueMatrix[j] = Matrix<T>.SubtractRows(eq.B.ValueMatrix[j], Matrix<T>.MultiplyRow(eq.B.ValueMatrix[i], eq.A.ValueMatrix[j][i]));
                    eq.A.ValueMatrix[j] = Matrix<T>.SubtractRows(eq.A.ValueMatrix[j], Matrix<T>.MultiplyRow(eq.A.ValueMatrix[i], eq.A.ValueMatrix[j][i]));
                }
            }
        }

        public int[] FindMaxInSubmatrix(Matrix<T> A, int x, int y)
        {
            int[] pos = new int[2];
            pos[0] = x;
            pos[1] = y;
            for (int i = x; i < A.RowCount; i++)
                for (int j = y; j < A.ColCount; j++)
                    if ((A.ValueMatrix[i][j].Abs().CompareTo(A.ValueMatrix[pos[0]][pos[1]].Abs())) > 0)
                    {
                        pos[0] = i;
                        pos[1] = j;
                    }
            return pos;
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
