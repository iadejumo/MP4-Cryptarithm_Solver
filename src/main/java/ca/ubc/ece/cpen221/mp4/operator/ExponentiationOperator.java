package ca.ubc.ece.cpen221.mp4.operator;

public class ExponentiationOperator implements BinaryOperator {

	/**
	 * Calculate and returns exponentiation of the two arguments (arg1^arg2)
	 * 
	 * @param double arg1 - the number before the exponentiation sign (base)
	 * 						can be any double
	 * 
	 * @param double arg2 - the number after the exponentiation sign (exponent)
	 * 						can be any double
	 * 
	 * @return a double that is the arg1 ^ arg2
	 */
	
	@Override
	public double apply(double arg1, double arg2) {
		return (Math.pow(arg1, arg2));
	}
	
	/**
	 * Creates a String representation of the exponentiation operator
	 * 
	 * @return a printable representation of the exponentiation value operator
	 */
	@Override
	public String toString() {
		return ("^");
	}

}
