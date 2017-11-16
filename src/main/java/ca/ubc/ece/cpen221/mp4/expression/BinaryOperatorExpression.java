package ca.ubc.ece.cpen221.mp4.expression;

import ca.ubc.ece.cpen221.mp4.operator.BinaryOperator;

public class BinaryOperatorExpression implements Expression {

	private final BinaryOperator operator;
	private final Expression operand1;
	private final Expression operand2;

	
	public BinaryOperatorExpression(BinaryOperator operator, Expression operand1, Expression operand2) {
		this.operator = operator;
		this.operand1 = operand1;
		this.operand2 = operand2;
	}

	/**
	 * Evaluates an binary expression: returns the expression's value
	 * 
	 * @return the binary expression's value
	 */
	@Override
	public double eval() {
		return operator.apply(operand1.eval(), operand2.eval());
	}

	/**
	 * Creates a String representation of the absolute value operator
	 * 
	 * @return a printable representation of the absolute value operator
	 */
	@Override
	public String toString() {
		return ("(" + operand1.toString() + " " + operator.toString() + " " + operand2.toString() + ")");
	}
	
	public static void main(String[] args) {
	}

}
