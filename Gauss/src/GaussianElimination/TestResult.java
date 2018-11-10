package GaussianElimination;

import java.util.ArrayList;

public class TestResult {

    public double error;
    public long time1stphase;
    public long time2ndphase;
    public long time;
    public String eliminationType;
    public String datatype;
    int matrixSize;

    int elimination;
    char type;

    private final String TYPE_DOUBLE = "Double";
    private final String TYPE_BIGINT = "Big Integer";
    private final String TYPE_FLOAT = "Float";

    private final String ELIM_NOPIVOT = "No Pivot";
    private final String ELIM_HALFPIVOT = "Partial Pivot";
    private final String ELIM_FULLPIVOT = "Full Pivot";


    TestResult(double error,long timest, long timend, int elimination, char type, int matrixSize){
        this.error=error;
        this.time1stphase=timest;
        this.time2ndphase=timend;
        this.time=(timest+timend);
        this.matrixSize=matrixSize;

        this.type = type;
        this.elimination = elimination;

        if(elimination==1)
            this.eliminationType = ELIM_NOPIVOT;
        else if(elimination==2)
            this.eliminationType = ELIM_HALFPIVOT;
        else
            this.eliminationType = ELIM_FULLPIVOT;

        if(type=='f')
            this.datatype= TYPE_FLOAT;
        else if(type=='d')
            this.datatype= TYPE_DOUBLE;
        else
            this.datatype=TYPE_BIGINT;

    }

    public static TestResult getAverage(ArrayList<TestResult> list){
        double errorAverage=0.0;
        long stAverage=0L;
        long ndAverage=0L;

        for(TestResult elem : list)
        {
            errorAverage += elem.error;
            stAverage += elem.time1stphase;
            ndAverage += elem.time2ndphase;
        }

        errorAverage /= list.size();
        stAverage /= list.size();
        ndAverage /= list.size();


        return new TestResult(errorAverage,stAverage,ndAverage,list.get(0).elimination,list.get(0).type,list.get(0).matrixSize);
    }

    public static String getHeader()
    {
        return "Elimination type;Data type;Matrix size;Error;Time;1st Phase;2nd Phase";
    }

    @Override
    public String toString()
    {
        return this.eliminationType+";"+this.datatype+";"+this.matrixSize+";"+this.error+";"+this.time*1e-9+";"+this.time1stphase*1e-9+";"+this.time2ndphase*1e-9;
    }
}
