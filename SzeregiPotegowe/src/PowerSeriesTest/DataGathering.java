package PowerSeriesTest;

import PowerSeries.*;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class DataGathering {

    //sumowanie od konca daje dokladniejsze wyniki niz sumowanie od poczatku.

    public static void perform(double argument, double delta, int count, int[] iterations) {
        String filenamePN = "data/h1dataPN" + count + "arguments.csv";
        String filenameTN = "data/h1dataTN" + count + "arguments.csv";
        String filenamePR = "data/h1dataPR" + count + "arguments.csv";
        String filenameTR = "data/h1dataTR" + count + "arguments.csv";
        String heading = "argument;błąd przy dodawaniu od przodu;błąd przy dodawaniu od tyłu;błąd przy dodawaniu od przodu (elementy wyliczane rekurencyjnie);błąd przy dodawaniu od tyłu (elementy wyliczane rekurencyjnie)\n";
        try {
            BufferedWriter writerPN = new BufferedWriter(new FileWriter(filenamePN));
            BufferedWriter writerTN = new BufferedWriter(new FileWriter(filenameTN));
            BufferedWriter writerPR = new BufferedWriter(new FileWriter(filenamePR));
            BufferedWriter writerTR = new BufferedWriter(new FileWriter(filenameTR));

            writerPN.write("argument;");
            writerTN.write("argument;");
            writerPR.write("argument;");
            writerTR.write("argument;");

            for (int i = 0; i < iterations.length; i++) {

                writerPN.append(iterations[i] + " iteracji;");
                writerTN.append(iterations[i] + " iteracji;");
                writerPR.append(iterations[i] + " iteracji;");
                writerTR.append(iterations[i] + " iteracji;");
            }

            writerPN.append("\n");
            writerTN.append("\n");
            writerPR.append("\n");
            writerTR.append("\n");

            while (count > 0) {

                writerPN.append(argument+";");
                writerTN.append(argument+";");
                writerPR.append(argument+";");
                writerTR.append(argument+";");

                for (int i = 0; i < iterations.length; i++) {
                    double pn = Function3.calculatePN(argument, iterations[i]);
                    double tn = Function3.calculateTN(argument, iterations[i]);
                    double pr = Function3.calculatePR(argument, iterations[i]);
                    double tr = Function3.calculateTR(argument, iterations[i]);
                    double math = Function3.calculateMATH(argument, iterations[i]);

                    writerPN.append(Math.abs((pn-math)/math)+";");
                    writerTN.append(Math.abs((tn-math)/math)+";");
                    writerPR.append(Math.abs((pr-math)/math)+";");
                    writerTR.append(Math.abs((tr-math)/math)+";");
                }

                writerPN.append("\n");
                writerTN.append("\n");
                writerPR.append("\n");
                writerTR.append("\n");

                argument += delta;
                count--;
            }

            writerPN.close();
            writerTN.close();
            writerPR.close();
            writerTR.close();

        } catch (Exception e) {
            System.err.println("Test H1 zakończył się wyjątkiem");
        }
    }
}
