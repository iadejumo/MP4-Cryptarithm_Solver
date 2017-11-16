package ca.ubc.ece.cpen221.mp4.operator;

public class SineOperator implements UnaryOperator {

	/**
	 * Calculate and returns the sine of arg
	 * 
	 * @param double arg - the operand to be used to calculate the sin value
	 * 						can be any double
	 * 
	 * @return a double that is sin(arg)
	 */

	@Override
	public double apply(double arg) {
		return Math.sin(arg);
	}
	
	/**
	 * Creates a String representation of the sine operator
	 * 
	 * @return a printable representation of the sine value operator
	 */
	@Override
	public String toString() {
		return ("sin");
	}

}
