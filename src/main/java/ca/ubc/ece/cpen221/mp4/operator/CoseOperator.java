package ca.ubc.ece.cpen221.mp4.operator;

public class CoseOperator implements UnaryOperator {

	// RI: None
	// AF: None
	/**
	 * Calculate and returns the cosine of arg
	 * 
	 * @param arg
	 *            - the operand to be used to calculate the cos value can be any
	 *            double
	 * 
	 * @return a double that is cos(arg)
	 */
	@Override
	public double apply(double arg) {
		return Math.cos(arg);
	}

	/**
	 * Creates a String representation of the cose operator
	 * 
	 * @return a printable representation of the cose value operator
	 */
	public String toString() {
		return ("cos");
	}

}
