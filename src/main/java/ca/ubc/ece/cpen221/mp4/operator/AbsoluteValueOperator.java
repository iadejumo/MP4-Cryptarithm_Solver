package ca.ubc.ece.cpen221.mp4.operator;

public class AbsoluteValueOperator implements UnaryOperator {

	// RI: None
	// AF: None
	
	/**
	 * Calculate and returns the absolute value to the argument.
	 * 
	 * @param arg
	 *            - the value whose absolute value will be calculated can be any
	 *            double
	 * 
	 * @return a double that is the absolute value of the arg
	 */
	@Override
	public double apply(double arg) {
		if (arg < 0)
			return (-arg);
		return (arg);
	}

	/**
	 * Creates a String representation of the absolute value operator
	 * 
	 * @return a printable representation of the absolute value operator
	 */
	@Override
	public String toString() {
		return ("abs");
	}

}
