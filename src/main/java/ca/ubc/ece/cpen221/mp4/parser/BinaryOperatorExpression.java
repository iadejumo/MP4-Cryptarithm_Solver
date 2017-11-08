package ca.ubc.ece.cpen221.mp4.parser;

import ca.ubc.ece.cpen221.mp4.expression.Expression;
import ca.ubc.ece.cpen221.mp4.operator.BinaryOperator;

public class BinaryOperatorExpression implements Expression {

	BinaryOperator operator;
	Expression operand1;
	Expression operand2;
	
	public BinaryOperatorExpression(BinaryOperator operator, Expression operand1, Expression operand2) {
		this.operator = operator;
		this.operand1 = operand1;
		this.operand2 = operand2;
	}
	
	@Override
	public double eval() {
		return operator.apply(operand1.eval(), operand2.eval());
	}
	
	public String toString() {
		return (operand1.toString() + " " + operator.toString() + " " + operand2.toString());
	}

}
