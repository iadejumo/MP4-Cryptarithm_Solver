package ca.ubc.ece.cpen221.mp4.operator;

public class NegationOperator implements UnaryOperator {

	// RI: None
	// AF: None

	/**
	 * Calculate and returns the negative of the argument.
	 * 
	 * @param arg
	 *            - the value whose negatation value will be calculated can be any
	 *            double
	 * 
	 * @return a double that is the negative of arg (-arg)
	 */
	@Override
	public double apply(double arg) {
		return (-arg);
	}

	/**
	 * Creates a String representation of the negation operator
	 * 
	 * @return a printable representation of the negation operator
	 */
	@Override
	public String toString() {
		return ("~");
	}
}
