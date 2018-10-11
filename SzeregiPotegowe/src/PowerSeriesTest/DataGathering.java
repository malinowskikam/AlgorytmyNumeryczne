package PowerSeriesTest;

import PowerSeries.*;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class DataGathering {

    //sumowanie od konca daje dokladniejsze wyniki niz sumowanie od poczatku.

    public static void perform(double argument, double delta, int count, int[] iterations) {
        String filename = "data/data" + count + "argumentsAVG.csv";

        int avg; //wyliczanie ilości elementów branej do średniej, jeśli liczba argumentów za mała lub niepodzielna na 100, brak średniej;
        if(count>100&&count%100==0)
            avg=count/100;
        else
            avg=1;

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));

            writer.append("argument"); //nagłówek
            for (int i = 0; i < iterations.length; i++) {
                writer.append(";" + iterations[i] + " iteracji");
            }
            writer.append("\n\n");

            writer.append(";;;Błąd;obliczanie ze wzoru;zliczanie od przodu;\n");
            double argsum=0.0; // średnia argumentów
            double[] avgtab = new double[iterations.length]; // średnia wyników
            for(int j=0;j<iterations.length;j++)
                avgtab[j]=0.0;

            for(int i=1;i<=count;i++) {

                argsum+=argument+(i-1)*delta;
                if(i%avg==0) {
                    writer.append(argsum/avg+"");
                    argsum=0.0;
                    System.out.println(i);
                }

                for (int j = 0; j < iterations.length; j++) {
                    double result = Function3.calculatePN(argument+(i-1)*delta, iterations[j]);
                    double math = Function3.calculateMATH(argument+(i-1)*delta, iterations[j]);

                    avgtab[j]+=Math.abs((result-math)/math);
                }
                if(i%avg==0) {
                    for (int k = 0; k < iterations.length; k++) {
                        writer.append(";" + avgtab[k]/avg);
                    }
                    writer.append("\n");
                    for(int k=0;k<iterations.length;k++)
                        avgtab[k]=0.0;
                }

            }

            writer.append("\n");

            writer.append(";;;Błąd;obliczanie ze wzoru;zliczanie od końca;\n");
             argsum=0.0; // średnia argumentów
            for(int j=0;j<iterations.length;j++)
                avgtab[j]=0.0;

            for(int i=1;i<=count;i++) {

                argsum+=argument+(i-1)*delta;
                if(i%avg==0) {
                    writer.append(argsum/avg+"");
                    argsum=0.0;
                    System.out.println(i);
                }

                for (int j = 0; j < iterations.length; j++) {
                    double result = Function3.calculateTN(argument+(i-1)*delta, iterations[j]);
                    double math = Function3.calculateMATH(argument+(i-1)*delta, iterations[j]);

                    avgtab[j]+=Math.abs((result-math)/math);
                }
                if(i%avg==0) {
                    for (int k = 0; k < iterations.length; k++) {
                        writer.append(";" + avgtab[k]/avg);
                    }
                    writer.append("\n");
                    for(int k=0;k<iterations.length;k++)
                        avgtab[k]=0.0;
                }

            }

            writer.append("\n");

            writer.append(";;;Błąd;obliczanie rekurencyjne;zliczanie od początku;\n");
            argsum=0.0; // średnia argumentów
            for(int j=0;j<iterations.length;j++)
                avgtab[j]=0.0;

            for(int i=1;i<=count;i++) {

                argsum+=argument+(i-1)*delta;
                if(i%avg==0) {
                    writer.append(argsum/avg+"");
                    argsum=0.0;
                    System.out.println(i);
                }

                for (int j = 0; j < iterations.length; j++) {
                    double result = Function3.calculatePR(argument+(i-1)*delta, iterations[j]);
                    double math = Function3.calculateMATH(argument+(i-1)*delta, iterations[j]);

                    avgtab[j]+=Math.abs((result-math)/math);
                }
                if(i%avg==0) {
                    for (int k = 0; k < iterations.length; k++) {
                        writer.append(";" + avgtab[k]/avg);
                    }
                    writer.append("\n");
                    for(int k=0;k<iterations.length;k++)
                        avgtab[k]=0.0;
                }

            }

            writer.append(";;;Błąd;obliczanie rekurencyjne;zliczanie od końca;\n");
            argsum=0.0; // średnia argumentów
            for(int j=0;j<iterations.length;j++)
                avgtab[j]=0.0;

            for(int i=1;i<=count;i++) {

                argsum+=argument+(i-1)*delta;
                if(i%avg==0) {
                    writer.append(argsum/avg+"");
                    argsum=0.0;
                    System.out.println(i);
                }

                for (int j = 0; j < iterations.length; j++) {
                    double result = Function3.calculateTR(argument+(i-1)*delta, iterations[j]);
                    double math = Function3.calculateMATH(argument+(i-1)*delta, iterations[j]);

                    avgtab[j]+=Math.abs((result-math)/math);
                }
                if(i%avg==0) {
                    for (int k = 0; k < iterations.length; k++) {
                        writer.append(";" + avgtab[k]/avg);
                    }
                    writer.append("\n");
                    for(int k=0;k<iterations.length;k++)
                        avgtab[k]=0.0;
                }

            }

            writer.close();

        } catch (Exception e) {
            System.err.println("Test H1 zakończył się wyjątkiem");
        }
    }
}
