package Matrix;

import Matrix.Datatypes.*;

public class Matrix {
    public final int rowCount;
    public final int colCount;

    public MatrixDataType[][] matrix;

    public Matrix(int[][] raw, char type) {

        this.rowCount = raw.length;
        this.colCount = raw[0].length;

        this.matrix = new MatrixDataType[this.rowCount][this.colCount];

        //utworzenie macierzy z tablicy intów
        if (type == 'f') {
            for (int i = 0; i < raw.length; i++)
                for (int j = 0; j < raw[0].length; j++)
                    this.matrix[i][j] = new MatrixFloat(raw[i][j]);
        } else if (type == 'd') {
            for (int i = 0; i < raw.length; i++)
                for (int j = 0; j < raw[0].length; j++)
                    this.matrix[i][j] = new MatrixDouble(raw[i][j]);
        } else {
            for (int i = 0; i < raw.length; i++)
                for (int j = 0; j < raw[0].length; j++)
                    this.matrix[i][j] = new MatrixBigIntFraction(raw[i][j]);
        }
    }

    private Matrix(MatrixDataType[][] raw) {
        this.rowCount = raw.length;
        this.colCount = raw[0].length;

        this.matrix=raw;
    }

    public void swapRows(int first, int second) {

        MatrixDataType[] temp = this.matrix[first];
        this.matrix[first] = this.matrix[second];
        this.matrix[second] = temp;
    }

    public void swapCols(int first, int second) {

        MatrixDataType temp;

        for (int i = 0; i < this.rowCount; i++) {
            temp = this.matrix[i][first];
            this.matrix[i][first] = this.matrix[i][second];
            this.matrix[i][second] = temp;
        }

    }

    public Matrix multiply(Matrix factor) {
        //mnoży przez siebie 2 macierze

        int newRowCount = this.rowCount;
        int newColCount = factor.colCount;

        MatrixDataType[][] raw = new MatrixDataType[newRowCount][newColCount];

        for (int i = 0; i < newRowCount; i++) //wiersze
            for (int j = 0; j < newColCount; j++) //kolumny
            {
                MatrixDataType res = this.matrix[i][0].multiply(factor.matrix[0][j]);
                for (int k = 1; k < this.colCount; k++)
                    res = res.add(this.matrix[i][k].multiply(factor.matrix[k][j]));

                raw[i][j] = res;
            }

        return new Matrix(raw);
    }

    public static MatrixDataType[] addRows(MatrixDataType[] row1,MatrixDataType[] row2)
    {
        //dodaje do siebie 2 wiersze
        MatrixDataType[] newRow = new MatrixDataType[row1.length];

        for(int i=0;i<newRow.length;i++)
            newRow[i] = row1[i].add(row2[i]);

        return newRow;
    }

    public static MatrixDataType[] subtractRows(MatrixDataType[] row1,MatrixDataType[] row2)
    {
        //odejmuje 2 wiersz od pierwszego
        MatrixDataType[] newRow = new MatrixDataType[row1.length];

        for(int i=0;i<newRow.length;i++)
            newRow[i] = row1[i].subtract(row2[i]);

        return newRow;
    }

    public static MatrixDataType[] multiplyRow(MatrixDataType[] row, MatrixDataType factor)
    {
        //mnoży wiersz przez skalar
        MatrixDataType[] newRow = new MatrixDataType[row.length];

        for(int i=0;i<newRow.length;i++)
            newRow[i] = row[i].multiply(factor);
        
        return newRow;

    }

    public String toString() {
        StringBuilder s =  new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++)
                s.append(matrix[i][j]).append(", ");
            s.append("\n");
        }
        return s.toString();
    }
}