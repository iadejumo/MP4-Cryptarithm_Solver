package ca.ubc.ece.cpen221.mp4.operator;

public class CoseOperator implements UnaryOperator {

	@Override
	public double apply(double arg) {
		return Math.cos(arg);
	}
	
	public String toString() {
		return ("cos");
	}

}
