namespace PopulationProtocols {
    public class MatrixFloat : IMatrixDataType<float> {
        private static readonly MatrixFloat ONE = new MatrixFloat(1.0f);
        private static readonly MatrixFloat MINUSONE = new MatrixFloat(-1.0f);
        private static readonly MatrixFloat ZERO = new MatrixFloat(0.0f);

        private float value;

        private MatrixFloat(float v) {
            value = v;
        }

        public double Evaluate() {
            return (double)value;
        }

        public float GetValue() {
            return value;
        }

        public void SetValue(float v) {
            value = v;
        }

        public override string ToString() {
            return value.ToString();
        }

        public IMatrixDataType<float> Add(IMatrixDataType<float> number) {
            return new MatrixFloat(GetValue() + number.GetValue());
        }

        public IMatrixDataType<float> Subtract(IMatrixDataType<float> number) {
            return new MatrixFloat(GetValue() - number.GetValue());
        }

        public IMatrixDataType<float> Multiply(IMatrixDataType<float> number) {
            return new MatrixFloat(GetValue() * number.GetValue());
        }

        public IMatrixDataType<float> Divide(IMatrixDataType<float> number) {
            return new MatrixFloat(GetValue() / number.GetValue());
        }

        public IMatrixDataType<float> GetInverse() {
            return ONE.Divide(this);
        }

        public int CompareTo(IMatrixDataType<float> number) {
            return GetValue().CompareTo(number.GetValue());
        }

        public IMatrixDataType<float> Abs() {
            if (CompareTo(ZERO) > 0)
                return this;
            else
                return Multiply(MINUSONE);
        }
    }
}