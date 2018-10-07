import PowerSeriesTests.NaturalLogarithmSeries;
import PowerSeriesTests.SineSeries;

public class Main {
    public static void main(String[] args) {

        NaturalLogarithmSeries log = new NaturalLogarithmSeries(4,0.1);
        SineSeries sin = new SineSeries(4,0.1);
        System.out.println("0.0095151408910892698507713 - wolfram");
        System.out.println(sin.evaluateWithMathLib()*log.evaluateWithMathLib() + " - Math lib");
        System.out.println(sin.getBackwardSumRecursive()*log.getBackwardSumRecursive());
        System.out.println(sin.getForwardSum()*log.getForwardSum());

        System.out.println();

        System.out.println(sin.evaluateWithMathLib());
        System.out.println(sin.getForwardSum());
        System.out.println(sin.getForwardSumRecursive());
        System.out.println(sin.getBackwardSum());
        System.out.println(sin.getBackwardSumRecursive());

    }
}
