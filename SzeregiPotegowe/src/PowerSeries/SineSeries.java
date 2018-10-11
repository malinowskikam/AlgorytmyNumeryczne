package PowerSeries;

class SineSeries extends MacLaurinSeries {

    // Mclaurin series of sin(x)
    //
    //  SUM n: 0->inf  (-1)^(n) * x^(1+2n) / (1+2n)!  (wolframalpha)

    public SineSeries(double x, int n) {
        super(n, x);
        if (x <= -1 || x > 1)
            System.err.println("Szereg nie jest zbieżny dla tego argumentu");
    }

    @Override
    protected double[] getFirstNElementsRecursive() {
        double series[] = new double[this.nOfIterations];
        double element = this.argument;
        series[0] = element;

        for (int i = 1; i < this.nOfIterations; i++) {
            element = element * -1 * this.argument * this.argument / (2 * i * (2 * i + 1));
            series[i] = element;
        }

        return series;
    }

    @Override
    public double evaluateWithMathLib() {
        return Math.sin(this.argument);
    }

    @Override
    protected double getNthElement(int n) {
        return Utils.intPow(-1, n) * Utils.intPow(this.argument, 1 + 2 * n) / Utils.factorial(1 + 2 * n);
    }
}

for i=0;i<1e16;i++
-3.14159265359+i*-0.00000314159