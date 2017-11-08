package ca.ubc.ece.cpen221.mp4.parser;

import ca.ubc.ece.cpen221.mp4.expression.Expression;

public class NumberExpression implements Expression {

	private double value;
	
	public NumberExpression(double value) {
		this.value = value;
	}
	
	@Override
	public double eval() {
		return this.value;
	}
	
	public String toString() {
		return Double.toString(value);
	}

}
