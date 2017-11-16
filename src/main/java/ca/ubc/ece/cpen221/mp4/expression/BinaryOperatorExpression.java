package ca.ubc.ece.cpen221.mp4.expression;

import ca.ubc.ece.cpen221.mp4.operator.BinaryOperator;

public class BinaryOperatorExpression implements Expression {

	// RI = operator,operand1,operand2 != null
	
	// AF = operator x operand1 x operand 2
	
	private final BinaryOperator operator;
	private final Expression operand1;
	private final Expression operand2;

	/**
	 * Creates a binary operator expression with two operands and one operator
	 * 
	 * @param operator
	 *            - operator to be applied to the two operands must not be null
	 * 
	 * @param operand1
	 *            - operand on left side of the binary operator must not be null
	 * 
	 * @param operand2
	 *            - operand on the right side of the binary operator must not be
	 *            null
	 */
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
	 * Creates a String representation of the binary operator expression
	 * 
	 * @return a printable representation of the binary operator expression
	 */
	@Override
	public String toString() {
		return ("(" + operand1.toString() + " " + operator.toString() + " " + operand2.toString() + ")");
	}

}
