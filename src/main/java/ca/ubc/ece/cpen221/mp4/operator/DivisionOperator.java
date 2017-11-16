package ca.ubc.ece.cpen221.mp4.operator;

public class DivisionOperator implements BinaryOperator {

	// RI: None
	// AF: None
	
	/**
	 * Calculate and returns the quotient of the two arguments (arg1/arg2)
	 * 
	 * @param arg1
	 *            - the number before the division sign (dividend) can be any double
	 * 
	 * @param arg2
	 *            - the number after the division sign (divisor) can be any double
	 * 
	 * @return a double that is the quotient of arg1/arg2
	 */
	@Override
	public double apply(double arg1, double arg2) {
		return (arg1 / arg2);
	}

	/**
	 * Creates a String representation of the division value operator
	 * 
	 * @return a printable representation of the division value operator
	 */
	@Override
	public String toString() {
		return ("/");
	}
}
