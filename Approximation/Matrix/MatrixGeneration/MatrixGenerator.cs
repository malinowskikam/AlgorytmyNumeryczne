using System;
using System.Collections.Generic;
using System.Text;

namespace PopulationProtocols
{
    class MatrixGenerator
    {
        public int NOfAgents;
        public List<String> Keys;
        public Dictionary<String, int> KeyToIndex;
        public Dictionary<int, String> IndexToKey;

        readonly double Nover2; // n nad 2, potrzebne do liczenia prawdopodobieństwa

        public int NOfEquasions;

        public MatrixGenerator(int n)
        {
            this.NOfAgents = n;
            GenerateKeyList();
            GenerateKeyMaps();

            this.NOfEquasions = Keys.Count;

            Nover2 = (double)BinomCoefficient((long)n, 2L);
        }

        public MatrixEquasion<double> GenerateEquasion()
        {
            IMatrixDataType<double>[][] rawA,rawB;

            rawA = new IMatrixDataType<double>[NOfEquasions][];
            rawB = new IMatrixDataType<double>[NOfEquasions][];

            for(int i=0; i<NOfEquasions;i++)
            {
                rawA[i] = new IMatrixDataType<double>[NOfEquasions];
                rawB[i] = new IMatrixDataType<double>[1];
            }

            // wektor B ma same 0 i 1 na ostatnim miejscu
            for (int i = 0; i < NOfEquasions-1; i++)
            {
                rawB[i][0] = MatrixDouble.ZERO;
            }
            rawB[NOfEquasions - 1][0] = MatrixDouble.ONE;

            // wypełnianie macierzy A
            for (int i = 0; i < NOfEquasions; i++)
                for (int j = 0; j < NOfEquasions; j++)
                    rawA[i][j] = GenerateValue(i, j);

            return new MatrixEquasion<double>(new Matrix<double>(rawA), null, new Matrix<double>(rawB));
        }

        private MatrixDouble GenerateValue(int i,int j)
        {
            //Pobranie danych o przypadku, którego wiersz jest aktualnie sprawdzany ("przed")
            string currentCaseR = IndexToKey[i];
            string[] countsR = currentCaseR.Split(',');
            int yesCountR = Int32.Parse(countsR[0]);
            int noCountR = Int32.Parse(countsR[1]); 

            //Pobranie danych o przypadku, któorego kolumna jest aktualnie sprawdzana ("po")
            string currentCaseC = IndexToKey[j];
            string[] countsC = currentCaseC.Split(',');
            int yesCountC = Int32.Parse(countsC[0]);
            int noCountC = Int32.Parse(countsC[1]);


            if (yesCountR == NOfAgents && noCountR == 0 && i == j) //warunek kończoncy - wszyscy na tak
                return MatrixDouble.ONE;
            else if (yesCountR == 0 && noCountR == NOfAgents && i == j) //warunek kończoncy - wszyscy na nie
                return MatrixDouble.ONE;
            else if (yesCountR == 0 && noCountR == 0 && i == j) //warunek kończoncy - wszyscy niezdecydowani
                return MatrixDouble.ONE;
            else if (yesCountR == yesCountC && noCountR == noCountC && (yesCountR > 1 || noCountR > 1 || NOfAgents - yesCountR - noCountR > 1)) // Przypadek, gdy nic się nie zmienia (wylosowana została para agentów o tym samym zdaniu)
                return GetProbabilityCase1(NOfAgents, yesCountR, noCountR);
            else if (yesCountR + 1 == yesCountC && noCountR == noCountC && yesCountR > 0 && yesCountR + noCountR < NOfAgents) //Przypadek, gdy liczba agentów na "tak" zwiększa się o 1 (wylosowano 1 agenta na "tak" i jednego niezdecydowanego)
                return GetProbabilityCase2(NOfAgents, yesCountR, noCountR);
            else if (yesCountR == yesCountC && noCountR + 1 == noCountC && noCountR > 0 && yesCountR + noCountR < NOfAgents) //Przypadek, gdy liczba agentów na "nie" zwiększa się o 1 (wylosowano 1 agenta na "nie" i jednego niezdecydowanego)
                return GetProbabilityCase3(NOfAgents, yesCountR, noCountR);
            else if (yesCountR - 1 == yesCountC && noCountR - 1 == noCountC && yesCountR > 0 && noCountR > 0) //Przypadek, gdy liczba agentów niezdecydowanych rośnie o 2, Czyli gdy wylosowano jednego na "tak" i jednego na "nie"
                return GetProbabilityCase4(NOfAgents, yesCountR, noCountR);
            else if (i == j) // Unikalny przypadek, gdy jest po jednym agencie, i nie możliwy jest powrót do tego samego stanu(Przy tym przypadku w wierszu pojawi się współczynnik -1)
                return MatrixDouble.MINUSONE;
            else
                return MatrixDouble.ZERO;
        }

        //Liczy prawdopodobieństwo dla przypadku, gdy nie zmienia się stan
        private MatrixDouble GetProbabilityCase1(int noa, int yesc, int noc)
        {
            double prob = -1.0;

            if (yesc > 1) //jeśli jest możliwe, że wybrano 2x"tak"
                prob += (double)BinomCoefficient(yesc, 2) / Nover2;

            if (noc > 1) //jeśli jest możliwe, że wybrano 2x"nie"
                prob += (double)BinomCoefficient(noc, 2) / Nover2;

            if (noa-yesc-noc > 1) //jeśli jest możliwe, że wybrano 2x"niezdecydowany"
                prob += (double)BinomCoefficient(noa - yesc - noc, 2) / Nover2;


            return new MatrixDouble(prob);
        }

        //Liczy prawdopodobieństwo dla przypadku, gdy liczba agentów na "tak" zwiększa się o 1
        private MatrixDouble GetProbabilityCase2(int noa, int yesc, int noc)
        {
            double prob = 0.0;

            if (yesc > 0 && noa - yesc - noc > 0) //jeśli jest możliwe, że wybrano 1x"tak" i 1x"niezdecydowany"
                prob += (double) (yesc *(noa - yesc - noc)) / Nover2;

            return new MatrixDouble(prob);
        }

        //Liczy prawdopodobieństwo dla przypadku, gdy liczba agentów na "nie" zwiększa się o 1
        private MatrixDouble GetProbabilityCase3(int noa, int yesc, int noc)
        {
            double prob = 0.0;

            if (noc > 0 && noa - yesc - noc > 0) //jeśli jest możliwe, że wybrano 1x"nie" i 1x"niezdecydowany"
                prob += (double)(noc * (noa - yesc - noc)) / Nover2;

            return new MatrixDouble(prob);
        }

        //Liczy prawdopodobieństwo dla przypadku, gdy liczba agentów na "tak" i na "nie" zmniejsza się o 1
        private MatrixDouble GetProbabilityCase4(int noa, int yesc, int noc)
        {
            double prob = 0.0;

            if (noc > 0 && yesc > 0) //jeśli jest możliwe, że wybrano 1x"nie" i 1x"niezdecydowany"
                prob += (double)(noc * yesc) / Nover2;

            return new MatrixDouble(prob);
        }

        private void GenerateKeyList()
        {
            Keys = new List<String>();

            for(int i=0;i<=NOfAgents;i++)
            {
                for(int j=0;j<=NOfAgents-i;j++)
                {
                    Keys.Add($"{i},{j}");
                }
            }
        }

        private void GenerateKeyMaps()
        {
            KeyToIndex = new Dictionary<String, int>();
            IndexToKey = new Dictionary<int, String>();

            int i = 0;
            foreach(String s in Keys)
            {
                KeyToIndex[s] = i;
                IndexToKey[i] = s;
                i++;
            }
        }

        //funkcja do liczenia dwumianu newtona ze stacka
        private static long BinomCoefficient(long n, long k)
        {
            if (k > n) { return 0; }
            if (n == k) { return 1; } // only one way to chose when n == k
            if (k > n - k) { k = n - k; } // Everything is symmetric around n-k, so it is quicker to iterate over a smaller k than a larger one.
            if (k == 1) { return n; }
            long c = 1;
            for (long i = 1; i <= k; i++)
            {
                c *= n--;
                c /= i;
            }
            return c;
        }
    }
}
