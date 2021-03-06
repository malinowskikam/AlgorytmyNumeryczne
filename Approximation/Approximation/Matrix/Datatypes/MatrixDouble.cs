namespace Approximation.Matrix.Datatypes
{
    public class MatrixDouble : IMatrixDataType<double> {
        public static readonly MatrixDouble ONE = new MatrixDouble(1.0);
        public static readonly MatrixDouble MINUSONE = new MatrixDouble(-1.0);
        public static readonly MatrixDouble ZERO = new MatrixDouble(0.0);

        private double Value { get; set; }

        public MatrixDouble(double v) {
            Value = v;
        }
   
        public double Evaluate() {
            return Value;
        }

        public double GetValue() {
            return Value;
        }

        public void SetValue(double v) {
            Value = v;
        }

        public override string ToString() {
            return Value.ToString();
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