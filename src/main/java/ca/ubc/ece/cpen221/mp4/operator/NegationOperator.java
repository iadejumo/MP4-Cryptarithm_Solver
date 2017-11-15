package ca.ubc.ece.cpen221.mp4.operator;

public class NegationOperator implements UnaryOperator {

	@Override
	public double apply(double arg) {
		return (-arg);
	}

	@Override
	public String toString() {
		return ("~");
	}
}
