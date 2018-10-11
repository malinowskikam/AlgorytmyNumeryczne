package PowerSeries;

public class Function3 {

    //sin(x) · ln(1 + x)

    public static double calculatePN(double x, int n) {
        return (new SineSeries(x, n).getForwardSum()) * (new NaturalLogarithmSeries(x, n).getForwardSum());
    }

    public static double calculateTN(double x, int n) {
        return (new SineSeries(x, n).getBackwardSum()) * (new NaturalLogarithmSeries(x, n).getBackwardSum());
    }

    public static double calculatePR(double x, int n) {
        return (new SineSeries(x, n).getForwardSumRecursive()) * (new NaturalLogarithmSeries(x, n).getForwardSumRecursive());
    }

    public static double calculateTR(double x, int n) {
        return (new SineSeries(x, n).getBackwardSumRecursive()) * (new NaturalLogarithmSeries(x, n).getBackwardSumRecursive());
    }

    public static double calculateMATH(double x, int n) {
        return (new SineSeries(x, n).evaluateWithMathLib()) * (new NaturalLogarithmSeries(x, n).evaluateWithMathLib());
    }
}
