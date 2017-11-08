package ca.ubc.ece.cpen221.mp4.expression;

import ca.ubc.ece.cpen221.mp4.gui.AdditionOperator;
import ca.ubc.ece.cpen221.mp4.gui.ExponentiationOperator;
import ca.ubc.ece.cpen221.mp4.gui.MultiplicationOperator;
import ca.ubc.ece.cpen221.mp4.parser.BinaryOperatorExpression;
import ca.ubc.ece.cpen221.mp4.parser.NumberExpression;

public class VariableExpressionRunner {

	public static void main(String args[]) {
		VariableExpression exp = new VariableExpression("x");
		Expression fn1 = new BinaryOperatorExpression(new ExponentiationOperator(),exp,new NumberExpression(4));
		Expression fn = new BinaryOperatorExpression(new AdditionOperator(),fn1,new NumberExpression(-10));
		Expression deriv = new DerivativeExpression(fn,exp);
		((VariableExpression)exp).store(2);
		System.out.println(exp.eval());
		System.out.println(fn.eval());
		System.out.println(deriv.eval());
		System.out.println(findFnZeros(fn,exp,10,0.0000001));
	}
	
	/**
	* Returns a zero of the specified function using
	* Newton’s method with approxZero as the initial estimate.
	**
	@param fn the function whose zero is to be found
	* @param x the independent variable of the function
	* @param approxZero initial approximation for the
	* zero of the function
	* @param tolerance how close zero the returned
	* zero has to be
	*/
	public static double findFnZeros(Expression fn, VariableExpression independentVar, double initialGuess, double tolerance) {
		independentVar.store(initialGuess);
		//note guessed that tolerance meant closeness of fn = 0, rather than realx - approxx
		return findFnZerosHelper(fn,independentVar,tolerance);
	}
	
	private static double findFnZerosHelper(Expression fn, VariableExpression independentVar, double tolerance) {
		//base case: check if close enough
		if (fn.eval() <= tolerance)
			return independentVar.eval();
		
		//recursive step: do function again with new indep var value
		Expression derivFn = new DerivativeExpression(fn,independentVar);
		double derivVal = derivFn.eval();
		double fnVal = fn.eval();
		independentVar.store(independentVar.eval() - fnVal/derivVal );
		
		return findFnZerosHelper(fn,independentVar,tolerance);
	}
}
