package ca.ubc.ece.cpen221.mp4.operator;

public class SubtractionOperator implements BinaryOperator {

	/**
	 * Calculate and returns the difference of the two arguments (arg1-arg2)
	 * 
	 * @param double arg1 - the operand on the left of the subtraction (minuend)
	 * 						can be any double
	 * 
	 * @param double arg2 - the operand on the right of the subtraction (subtrahend)
	 * 						can be any double
	 * 
	 * @return a double that is the difference of arg1 and arg2 (arg1-arg2)
	 */
	@Override
	public double apply(double arg1, double arg2) {
		return (arg1-arg2);
	}


	/**
	 * Creates a String representation of the subtraction value operator
	 * 
	 * @return a printable representation of the subtraction value operator
	 */
	@Override
	public String toString() {
		return ("-");
	}
}
