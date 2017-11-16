package ca.ubc.ece.cpen221.mp4.operator;

public class MultiplicationOperator implements BinaryOperator {

	/**
	 * Calculate and returns the product of the two arguments
	 * 
	 * @param double arg1 - the first operand to be used in the multiplication
	 * 						can be any double
	 * 
	 * @param double arg2 - the second operand to be used in the multiplication
	 * 						can be any double
	 * 
	 * @return a double that is the product of arg1 and arg2
	 */
	@Override
	public double apply(double arg1, double arg2) {
		return (arg1 * arg2);
	}
	
	/**
	 * Creates a String representation of the multiplication operator
	 * 
	 * @return a printable representation of the multiplication operator
	 */
	@Override
	public String toString() {
		return ("*");
	}

}
