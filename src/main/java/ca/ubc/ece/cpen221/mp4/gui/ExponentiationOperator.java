package ca.ubc.ece.cpen221.mp4.gui;

import ca.ubc.ece.cpen221.mp4.operator.BinaryOperator;

public class ExponentiationOperator implements BinaryOperator {

	@Override
	public double apply(double arg1, double arg2) {
		return (Math.pow(arg1, arg2));
	}
	
	@Override
	public String toString() {
		return ("^");
	}

}
