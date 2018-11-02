package Matrix;

import Matrix.Datatypes.MatrixDataType;

public class Matrix {
    public int rowCount;
    public int colCount;

    public MatrixDataType[][] matrix;
    public Class classOfContent;



    public Matrix(MatrixDataType[][] raw)
    {
        try {
            int cC = raw[0].length;
            Class coc = raw[0][0].inferedClass;

            for(int i=0;i<raw.length;i++) //wiersze
                for(int j=0;j<raw[0].length;j++) //kolumny
                    if(!raw[i][j].inferedClass.equals(coc)) throw new Exception("Content type mismatch");

            for (int row = 0; row < raw.length; row++)
                if(raw[row].length!=cC) throw new Exception("col count mismatch");

        } catch ( Exception e ) {
            System.err.println("Matrix initialization failed:");
            System.err.println(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }

        this.rowCount = raw.length;
        this.colCount = raw[0].length;

        this.matrix = raw;
        this.classOfContent = raw[0][0].inferedClass;
    }

    public String toString() {
        String s = "";
        for(int i=0;i<matrix.length;i++) {
            for(int j=0;j<matrix[0].length;j++)
                s += matrix[i][j] + " ";
            s += "\n";
        }
        return s;
    }

    public void swapRows(int first, int second)
    {
        try {
            if(first<0||first>=rowCount) throw new Exception("invalid first row");
            if(second<0||second>=rowCount) throw new Exception("invalid second row");
        } catch ( Exception e ) {
            System.err.println("Swap rows failed");
            System.err.println(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }


        MatrixDataType[] temp = this.matrix[first];
        this.matrix[first] = this.matrix[second];
        this.matrix[second] = temp;
    }

    public void swapCols(int first, int second)
    {
        try {
            if(first<0||first>=colCount) throw new Exception("invalid first row");
            if(second<0||second>=colCount) throw new Exception("invalid second row");
        } catch ( Exception e ) {
            System.err.println("Swap cols failed:");
            System.err.println(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }

        MatrixDataType[] temp = new MatrixDataType[rowCount];

        for(int i=0;i<this.rowCount;i++)
            temp[i] = this.matrix[i][first];

        for(int i=0;i<this.rowCount;i++)
            this.matrix[i][first] = this.matrix[i][second];

        for(int i=0;i<this.rowCount;i++)
            this.matrix[i][second] = temp[i];
    }

    public Matrix multiply(Matrix factor) {
        try {
            if(this.colCount!=factor.rowCount) throw new Exception("first colcount/second rowcount mismatch");
            if(!this.classOfContent.equals(factor.classOfContent))  throw new Exception("Content type mismatch");

        } catch ( Exception e ) {
            System.err.println("Matrix multiplication failed:");
            System.err.println(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }

        int newRowCount = this.rowCount;
        int newColCount = factor.colCount;
        int vectorSize = this.colCount;

        MatrixDataType[][] raw = new MatrixDataType[newRowCount][newColCount];

        for(int i=0;i<newRowCount;i++) //wiersze
            for(int j=0;j<newColCount;j++) //kolumny
            {
                MatrixDataType res = this.matrix[i][0].multiply(factor.matrix[0][j]);
                // ^ poprawność sprawdzana wyżej

                for(int k=1;k<vectorSize;k++)//wektor
                    res = res.add(this.matrix[i][k].multiply(factor.matrix[k][j]));

                raw[i][j]=res;

            }

        return new Matrix(raw);
    }
}