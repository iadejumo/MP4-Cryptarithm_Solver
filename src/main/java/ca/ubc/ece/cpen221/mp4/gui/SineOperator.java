package ca.ubc.ece.cpen221.mp4.gui;

import ca.ubc.ece.cpen221.mp4.operator.UnaryOperator;

public class SineOperator implements UnaryOperator {

	@Override
	public double apply(double arg) {
		return Math.sin(arg);
	}
	
	@Override
	public String toString() {
		return ("sin");
	}

}
