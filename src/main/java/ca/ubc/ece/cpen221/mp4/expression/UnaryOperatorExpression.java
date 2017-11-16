package ca.ubc.ece.cpen221.mp4.expression;

import ca.ubc.ece.cpen221.mp4.operator.BinaryOperator;
import ca.ubc.ece.cpen221.mp4.operator.UnaryOperator;

public class UnaryOperatorExpression implements Expression {

	// RI = operator,operand != null

	// AF = operator x operand

	UnaryOperator operator;
	Expression operand;

	/**
	 * Creates a unary operator expression with one operand and one operator
	 * 
	 * @param operator
	 *            - operator to be applied to the two operands must not be null
	 * 
	 * @param operand
	 *            - operand on to have unaryOperator applied to
	 */
	public UnaryOperatorExpression(UnaryOperator operator, Expression operand) {
		this.operator = operator;
		this.operand = operand;
	}

	/**
	 * Evaluates an unary expression: returns the expression's value
	 * 
	 * @return the unary expression's value
	 */
	@Override
	public double eval() {
		return operator.apply(operand.eval());
	}

	/**
	 * Creates a String representation of the unary operator expression
	 * 
	 * @return a printable representation of the unary operator expression
	 */
	@Override
	public String toString() {
		return operator.toString() + "(" + operand.toString() + ")";
	}

}
