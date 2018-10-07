package PowerSeriesTests;

class Utils {
    protected static double intPow(double x, int power){

        double result=1.0;

        if(power<0){
            x=1.0/x;
            power*=-1;
        }

        for(int i=0;i<power;i++)
            result*=x;

        return result;

    }

    protected static double factorial(int n){
        if (n<0) return 0;
        else if (n==0) return 1;
        else {
            double result =1.0;

            for(int i=2;i<=n;i++)
                result*=i;

            return result;
        }
    }

}
