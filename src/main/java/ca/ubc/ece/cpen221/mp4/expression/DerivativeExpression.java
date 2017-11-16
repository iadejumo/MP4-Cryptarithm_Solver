package ca.ubc.ece.cpen221.mp4.expression;

/**
 * DerivativeExpression - The derivative of specific function.
 *
 */
public class DerivativeExpression implements Expression {

	// RI = fn,independentVar != null
	
	// AF = fn x independentVar
	
	public static final double DELTA_X = Math.pow(10, -9);
	private final Expression fn;
	private final VariableExpression independentVar;

	/**
	 * Create an expression representing the derivative of the specified function
	 * with respect to the specified variable.
	 * 
	 * @param fn
	 *            the function whose derivative this expression represents
	 *            fn must not be null
	 * @param independentVar
	 *            the variable with respect to which we're differentiating
	 *            must not be null
	 */
	public DerivativeExpression(Expression fn, VariableExpression independentVar) {
		this.fn = fn;
		this.independentVar = independentVar;
	}

	@Override
	/**
	 * Evaluates the derivative of the function at the variable expression's stored
	 * value
	 * 
	 * @return a double that is the value of the derivative of the function at the
	 *         variable expression's stored value
	 */
	public double eval() {
		double fnWithoutDelta = fn.eval();
		independentVar.store(independentVar.eval() + DELTA_X);

		double fnWithDelta = fn.eval();
		independentVar.store(independentVar.eval() - DELTA_X);
		return ((fnWithDelta - fnWithoutDelta) / DELTA_X);
	}

	@Override
	/**
	 * Overrides the toString of the DerivativeExpression
	 * Returns a String representation of the derivative expression 
	 * 
	 * @return a String showing that what function that this DerivativeExpression
	 *         differentiates
	 */
	public String toString() {
		return "(d/d" + independentVar.name() + ")(" + fn.toString() + ")";
	}

}
