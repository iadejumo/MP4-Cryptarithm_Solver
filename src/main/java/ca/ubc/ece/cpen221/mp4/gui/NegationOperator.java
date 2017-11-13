package ca.ubc.ece.cpen221.mp4.gui;

import ca.ubc.ece.cpen221.mp4.operator.UnaryOperator;

public class NegationOperator implements UnaryOperator {

	@Override
	public double apply(double arg) {
		return (-arg);
	}

	@Override
	public String toString() {
		return ("neg");
	}
}
