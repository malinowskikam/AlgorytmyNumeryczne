namespace PopulationProtocols {
    public class MatrixDouble : IMatrixDataType<double> {
        private static readonly MatrixDouble ONE = new MatrixDouble(1.0);
        private static readonly MatrixDouble MINUSONE = new MatrixDouble(-1.0);
        private static readonly MatrixDouble ZERO = new MatrixDouble(0.0);

        private double value;

        public MatrixDouble(int v) {
            SetValue(((double)v) / 0x10000); //0x10000 = 2^16
        }

        private MatrixDouble(double v) {
            value = v;
        }

        public IMatrixDataType<double> GetZero() {
            return ZERO;
        }

        public IMatrixDataType<double> GetMinusOne() {
            return MINUSONE;
        }
        
        public IMatrixDataType<double> GetOne() {
            return ONE;
        }
     
        public double Evaluate() {
            return value;
        }

        public double GetValue() {
            return value;
        }

        public void SetValue(double v) {
            value = v;
        }

        public override string ToString() {
            return value.ToString();
        }

        public IMatrixDataType<double> Add(IMatrixDataType<double> number) {
            return new MatrixDouble(GetValue() + number.GetValue());
        }

        public IMatrixDataType<double> Subtract(IMatrixDataType<double> number) {
            return new MatrixDouble(GetValue() - number.GetValue());
        }

        public IMatrixDataType<double> Multiply(IMatrixDataType<double> number) {
            return new MatrixDouble(GetValue() * number.GetValue());
        }

        public IMatrixDataType<double> Divide(IMatrixDataType<double> number) {
            return new MatrixDouble(GetValue() / number.GetValue());
        }

        public IMatrixDataType<double> GetInverse() {
            return ONE.Divide(this);
        }

        public int CompareTo(IMatrixDataType<double> number) {
            return GetValue().CompareTo(number.GetValue());
        }

        public IMatrixDataType<double> Abs() {
            if (CompareTo(ZERO) > 0)
                return this;
            else
                return Multiply(MINUSONE);
        }
    }
}