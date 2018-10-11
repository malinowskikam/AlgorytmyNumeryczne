import PowerSeriesTest.DataGathering;

public class Main {
    public static void main(String[] args) {
        double argument = -0.99;
        double delta = 0.0000019;
        int count = 1000000;
        int[] iterations = {2, 3, 5, 7, 10, 12, 15, 20, 25, 30, 50, 70, 100, 150};

        DataGathering.perform(argument, delta, count, iterations);
    }
}
