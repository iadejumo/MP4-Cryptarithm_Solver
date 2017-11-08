package ca.ubc.ece.cpen221.mp4.expression;

import ca.ubc.ece.cpen221.mp4.gui.ExponentiationOperator;
import ca.ubc.ece.cpen221.mp4.gui.MultiplicationOperator;
import ca.ubc.ece.cpen221.mp4.parser.BinaryOperatorExpression;
import ca.ubc.ece.cpen221.mp4.parser.NumberExpression;

public class VariableExpressionRunner {

	public static void main(String args[]) {
		VariableExpression exp = new VariableExpression("x");
		Expression fn = new BinaryOperatorExpression(new ExponentiationOperator(),exp,new NumberExpression(4));
		Expression deriv = new DerivativeExpression(fn,exp);
		((VariableExpression)exp).store(2);
		System.out.println(exp.eval());
		System.out.println(fn.eval());
		System.out.println(deriv.eval());
	}
}
