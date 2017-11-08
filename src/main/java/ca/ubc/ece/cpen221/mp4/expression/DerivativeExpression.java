package ca.ubc.ece.cpen221.mp4.expression;

/**
 * DerivativeExpression - The derivative of specific function.
 *
 */
public class DerivativeExpression implements Expression {
		
	public static final double DELTA_X = Math.pow(10, -9);
	Expression fn;
	VariableExpression independentVar;
	
	/**
	 * Create an expression representing the derivative of the specified
	 * function with respect to the specified variable.
	 * 
	 * @param fn the function whose derivative this expression represents
	 * @param independentVar the variable with respect to which we're
	 * 		  differentiating
	 */
	public DerivativeExpression(Expression fn, 
					VariableExpression independentVar) {
		this.fn = fn;
		this.independentVar = independentVar;
	}

	@Override
	public double eval() {
		double fnWithoutDelta = fn.eval();
		independentVar.store(independentVar.eval()+DELTA_X);
		
		double fnWithDelta = fn.eval();
		independentVar.store(independentVar.eval()-DELTA_X);
		return ( (fnWithDelta - fnWithoutDelta)/DELTA_X);
	}
	

}
