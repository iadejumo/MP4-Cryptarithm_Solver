package ca.ubc.ece.cpen221.mp4.expression;

import ca.ubc.ece.cpen221.mp4.operator.UnaryOperator;

public class UnaryOperatorExpression implements Expression {

	UnaryOperator operator;
	Expression operand;

	public UnaryOperatorExpression(UnaryOperator operator, Expression operand) {
		this.operator = operator;
		this.operand = operand;
	}

	@Override
	public double eval() {
		return operator.apply(operand.eval());
	}

	public String toString() {
		return operator.toString() + "(" + operand.toString() + ")";
	}

}
