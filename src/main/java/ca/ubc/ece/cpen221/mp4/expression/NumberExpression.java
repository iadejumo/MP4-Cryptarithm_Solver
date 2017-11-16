package ca.ubc.ece.cpen221.mp4.expression;

import ca.ubc.ece.cpen221.mp4.operator.BinaryOperator;

public class NumberExpression implements Expression {

	// RI = none, can be any value
	// AF = value
	private double value;

	/**
	 * Creates a number expression with the input value
	 * 
	 * @param value
	 *            - the value of this number expression
	 * 
	 */
	public NumberExpression(double value) {
		this.value = value;
	}

	/**
	 * Evaluates the number expression: returns the numberExpression's value
	 * 
	 * @return the number expression's value
	 */
	@Override
	public double eval() {
		return this.value;
	}

	/**
	 * Creates a String representation of the number expression
	 * 
	 * @return a printable representation of the number expression
	 */
	@Override
	public String toString() {
		return Double.toString(value);
	}

}
