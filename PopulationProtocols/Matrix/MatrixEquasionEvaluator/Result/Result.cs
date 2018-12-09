using System;
using System.Collections.Generic;
using System.Text;

namespace PopulationProtocols
{
    public class Result
    {
        public string Method;
        public double Error;
        public long TimeElapsed;
        public int NOfIterations;
        public Matrix<double> ResultVector;

        public Result() { }

        public Result(string s,double e, long te, int n, Matrix<double> m)
        {
            this.Method = s;
            this.Error = e;
            this.TimeElapsed = te;
            this.NOfIterations = n;
            this.ResultVector = m;
        }

        public Result(List<Result> list)
        {
            long timesum = 0L;

            foreach(Result r in list)
            {
                timesum += r.TimeElapsed;
            }

            this.Error = list[0].Error;
            this.TimeElapsed = timesum / list.Count;
            this.Method = list[0].Method;
            this.NOfIterations = list[0].NOfIterations;
            this.ResultVector = null;

        }

        public override string ToString()
        {
            return $"method: {this.Method}, time: {this.TimeElapsed} ms, error: {this.Error} " + (this.NOfIterations > 1 ? $"iterations: {this.NOfIterations}":"") + "\n" + (this.ResultVector!=null?this.ResultVector.ToString():"");
        }
    }
}
