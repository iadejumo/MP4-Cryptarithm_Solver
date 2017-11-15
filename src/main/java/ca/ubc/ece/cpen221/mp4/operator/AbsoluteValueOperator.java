package ca.ubc.ece.cpen221.mp4.operator;

public class AbsoluteValueOperator implements UnaryOperator {

	@Override
	public double apply(double arg) {
		if (arg < 0)
			return (-arg);
		return (arg);
	}
	
	@Override
	public String toString() {
		return ("abs");
	}

}
