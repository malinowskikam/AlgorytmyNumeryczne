using System;
using System.Collections.Generic;
using System.Text;

namespace PopulationProtocols
{
    public class Matrix<T>
    {
        public readonly int RowCount;
        public readonly int ColCount;

        public IMatrixDataType<T>[][] ValueMatrix;


        public Matrix(Matrix<T> prototype) : this(prototype.ValueMatrix) { }

        public Matrix(IMatrixDataType<T>[][] raw)
        {
            this.RowCount = raw.Length;
            this.ColCount = raw[0].Length;

            ValueMatrix = new IMatrixDataType<T>[this.RowCount][];
            for (int i = 0; i < this.RowCount; i++)
                ValueMatrix[i] = new IMatrixDataType<T>[this.ColCount];

            for (int i = 0; i < this.RowCount; i++)
                for (int j = 0; j < this.ColCount; j++)
                    this.ValueMatrix[i][j] = raw[i][j];
        }

        public void SwapRows(int first, int second)
        {
            if (first == second) return;
            IMatrixDataType<T>[] temp = this.ValueMatrix[first];
            this.ValueMatrix[first] = this.ValueMatrix[second];
            this.ValueMatrix[second] = temp;
        }

        public void SwapCols(int first, int second)
        {
            if (first == second) return;
            IMatrixDataType<T> temp;

            for (int i = 0; i < this.RowCount; i++)
            {
                temp = this.ValueMatrix[i][first];
                this.ValueMatrix[i][first] = this.ValueMatrix[i][second];
                this.ValueMatrix[i][second] = temp;
            }

        }

        public Matrix<T> Multiply(Matrix<T> factor)
        {
            //mnoży przez siebie 2 macierze

            int newRowCount = this.RowCount;
            int newColCount = factor.ColCount;

            IMatrixDataType<T>[][] raw = new IMatrixDataType<T>[newRowCount][];
            for(int i=0;i< this.RowCount;i++)
                raw[i] = new IMatrixDataType<T>[newColCount];

            for (int i = 0; i < newRowCount; i++) //wiersze
                for (int j = 0; j < newColCount; j++) //kolumny
                {
                    IMatrixDataType<T> res = this.ValueMatrix[i][0].Multiply(factor.ValueMatrix[0][j]);
                    for (int k = 1; k < this.ColCount; k++)
                        res = res.Add(this.ValueMatrix[i][k].Multiply(factor.ValueMatrix[k][j]));

                    raw[i][j] = res;
                }

            return new Matrix<T>(raw);
        }

        public static IMatrixDataType<T>[] AddRows(IMatrixDataType<T>[] row1, IMatrixDataType<T>[] row2)
        {
            //dodaje do siebie 2 wiersze
            IMatrixDataType<T>[] newRow = new IMatrixDataType<T>[row1.Length];

            for (int i = 0; i < newRow.Length; i++)
                newRow[i] = row1[i].Add(row2[i]);

            return newRow;
        }

        public static IMatrixDataType<T>[] SubtractRows(IMatrixDataType<T>[] row1, IMatrixDataType<T>[] row2)
        {
            //odejmuje 2 wiersz od pierwszego
            IMatrixDataType<T>[] newRow = new IMatrixDataType<T>[row1.Length];

            for (int i = 0; i < newRow.Length; i++)
                newRow[i] = row1[i].Subtract(row2[i]);

            return newRow;
        }

        public static IMatrixDataType<T>[] MultiplyRow(IMatrixDataType<T>[] row, IMatrixDataType<T> factor)
        {
            //mnoży wiersz przez skalar
            IMatrixDataType<T>[] newRow = new IMatrixDataType<T>[row.Length];

            for (int i = 0; i < newRow.Length; i++)
                newRow[i] = row[i].Multiply(factor);

            return newRow;

        }

        public static double GetNormOfDiffrence(Matrix<T> A, Matrix<T> B)
        {
            //wyliczenie normy różnicy 2 macierzy
            //Jako normę traktujemy największy element macierzy (wartością bezwzględną)

            double norm = 0.0;

            for (int i = 0; i < A.RowCount; i++)
                for (int j = 0; j < A.ColCount; j++)
                {
                    double elem = (A.ValueMatrix[i][j].Subtract(B.ValueMatrix[i][j])).Abs().Evaluate();
                    if (elem > norm) norm = elem;
                }
            return norm;
        }

        public override String ToString()
        {
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < ValueMatrix.Length; i++)
            {
                for (int j = 0; j < ValueMatrix[0].Length; j++)
                    s.Append(ValueMatrix[i][j].ToString()).Append(" ");
                s.Append("\n");
            }
            return s.ToString();
        }
    }
}

