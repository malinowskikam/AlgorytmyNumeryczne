package PowerSeries;

class NaturalLogarithmSeries extends MacLaurinSeries {

    //MacLaurin series of ln(x+1)
    //
    //    SUM n: 1->inf  (-1)^(n+1) * x^n / n    (wolframalpha)

    public NaturalLogarithmSeries(double x, int n) {
        super(n, x);
        if (x <= -1 || x > 1)
            System.err.println("Szereg nie jest zbieżny dla tego argumentu");
    }

    @Override
    protected double[] getFirstNElementsRecursive() {
        double element = this.argument;
        double series[] = new double[nOfIterations];
        series[0] = element;

        for (int i = 1; i < nOfIterations; i++) {
            element = element * argument * -1 * i / (i + 1);
            series[i] = element;
        }

        return series;
    }


    @Override
    public double evaluateWithMathLib() {
        return Math.log(this.argument + 1);
    }

    @Override
    protected double getNthElement(int n) {
        n++;
        return Utils.intPow(-1.0, n + 1) * Utils.intPow(this.argument, n) / n;
    }
}
