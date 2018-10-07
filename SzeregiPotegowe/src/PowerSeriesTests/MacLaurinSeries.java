package PowerSeriesTests;

abstract class MacLaurinSeries {

    int nOfIterations;
    double argument;

    MacLaurinSeries(int n, double x)
    {
        this.nOfIterations=n;
        this.argument=x;
    }

    private double[] getFirstNElements() {
        double[] series = new double[this.nOfIterations];
        for (int i = 0; i < this.nOfIterations; i++)
            series[i] = getNthElement(i);
        return series;
    }

    public double getForwardSum() {
        double series[] = getFirstNElements();
        double sum = 0.0;
        for (int i = 0; i < nOfIterations; i++)
            sum += series[i];
        return sum;
    }

    public double getBackwardSum() {
        double series[] = getFirstNElements();
        double sum = 0.0;
        for (int i = nOfIterations - 1; i >= 0; i--)
            sum += series[i];
        return sum;
    }

    public double getForwardSumRecursive() {
        double series[] = getFirstNElementsRecursive();
        double sum = 0.0;
        for (int i = 0; i < nOfIterations; i++)
            sum += series[i];
        return sum;
    }

    public double getBackwardSumRecursive() {
        double series[] = getFirstNElementsRecursive();
        double sum = 0.0;
        for (int i = nOfIterations - 1; i >= 0; i--)
            sum += series[i];
        return sum;
    }

    protected abstract double[] getFirstNElementsRecursive();

    public abstract double evaluateWithMathLib();

    protected abstract double getNthElement(int n);
}
