package ca.ubc.ece.cpen221.mp4.expression;

public class NewtonsMethod {

	//RI = none
	//AF = none b/c no fields
	
	/**
	 * Returns a zero of the specified function using Newton’s method with
	 * approxZero as the initial estimate.
	 **
	 * @param fn
	 *            the function whose zero is to be found must be a function that
	 *            converges to 0 when using Newton's Method functions that don't
	 *            reach 0 or have undefined derivatives or slopes of 0 may not work
	 *            must not be null
	 * @param x
	 *            the independent variable of the function must not be null
	 * @param approxZero
	 *            initial approximation for the zero of the function
	 * @param tolerance
	 *            how close zero the returned zero has to be
	 */
	public static double findFnZeros(Expression fn, VariableExpression independentVar, double initialGuess,
			double tolerance) {
		double storeOriginalVal = independentVar.eval();
		independentVar.store(initialGuess);

		double zeroX = findFnZerosHelper(fn, independentVar, tolerance);
		independentVar.store(storeOriginalVal);
		return zeroX;
	}

	private static double findFnZerosHelper(Expression fn, VariableExpression independentVar, double tolerance) {
		// base case: check if close enough
		if (Math.abs(fn.eval()) <= tolerance)
			return independentVar.eval();

		// recursive step: do function again with new indep var value
		Expression derivFn = new DerivativeExpression(fn, independentVar);
		double derivVal = derivFn.eval();
		double fnVal = fn.eval();
		independentVar.store(independentVar.eval() - fnVal / derivVal);

		return findFnZerosHelper(fn, independentVar, tolerance);
	}
}
