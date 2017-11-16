package ca.ubc.ece.cpen221.mp4.operator;

public class AdditionOperator implements BinaryOperator {

	// RI: None
	// AF: None

	/**
	 * Calculate and returns the sum of the two arguments
	 * 
	 * @param arg1
	 *            - the first operand to be used in the addition can be any double
	 * 
	 * @param arg2
	 *            - the second operand to be used in the addition can be any double
	 * 
	 * @return a double that is the sum of arg1 and arg2 (arg1+arg2)
	 */
	@Override
	public double apply(double arg1, double arg2) {
		return (arg1 + arg2);
	}

	/**
	 * Creates a String representation of the addition value operator
	 * 
	 * @return a printable representation of the addition value operator
	 */
	@Override
	public String toString() {
		return ("+");
	}
}
