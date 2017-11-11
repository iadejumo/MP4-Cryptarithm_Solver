package ca.ubc.ece.cpen221.mp4.expression;

import ca.ubc.ece.cpen221.mp4.gui.AdditionOperator;
import ca.ubc.ece.cpen221.mp4.gui.CoseOperator;
import ca.ubc.ece.cpen221.mp4.gui.ExponentiationOperator;
import ca.ubc.ece.cpen221.mp4.gui.MultiplicationOperator;
import ca.ubc.ece.cpen221.mp4.gui.SineOperator;
import ca.ubc.ece.cpen221.mp4.gui.SubtractionOperator;
import ca.ubc.ece.cpen221.mp4.parser.BinaryOperatorExpression;
import ca.ubc.ece.cpen221.mp4.parser.NumberExpression;
import ca.ubc.ece.cpen221.mp4.parser.UnaryOperatorExpression;

public class VariableExpressionRunner {

	public static void main(String args[]) {
		VariableExpression exp = new VariableExpression("x");
		Expression fn1 = new BinaryOperatorExpression(new ExponentiationOperator(),exp,new NumberExpression(4));
		Expression fn = new BinaryOperatorExpression(new AdditionOperator(),fn1,new NumberExpression(-10));
		Expression deriv = new DerivativeExpression(fn,exp);
		((VariableExpression)exp).store(0.1);
		System.out.println(exp.eval());
		System.out.println(fn1.eval());
		System.out.println(fn.eval());
		System.out.println(deriv.eval());
		System.out.println(NewtonsMethod.findFnZeros(fn,exp,10,0.0000001));
		System.out.println("Y"+NewtonsMethod.findFnZeros(fn1,exp,10,0.0000001));
		Expression sinx = new UnaryOperatorExpression(new SineOperator(), exp);
		Expression derivSinx = new DerivativeExpression(sinx,exp);
		Expression cosx = new UnaryOperatorExpression(new CoseOperator(), exp);
		Expression derivCosx = new DerivativeExpression(cosx,exp);
		
		System.out.println(exp.eval());
		System.out.println("Find Sinx: "+sinx.eval());
		System.out.println("Find Cosx: "+cosx.eval());
		System.out.println("Find dSinx: "+derivSinx.eval());
		System.out.println("Find dCosx: "+derivCosx.eval());
		System.out.println("Find Sinx Zero "+NewtonsMethod.findFnZeros(sinx,exp,1,0.0000001));
		System.out.println("Find Cosx Zero "+NewtonsMethod.findFnZeros(cosx,exp,1,0.0000001));
		System.out.println(exp.eval());
		
		VariableExpression x = new VariableExpression("x");
		x.store(0.1);
		Expression xCubed = new BinaryOperatorExpression(new ExponentiationOperator(),x,new NumberExpression(3));
		Expression tenX = new BinaryOperatorExpression(new MultiplicationOperator(),new NumberExpression(10),x);
		Expression xCubedMinusTenx = new BinaryOperatorExpression(new SubtractionOperator(),xCubed,tenX);
		
		System.out.println(xCubed.eval());
		System.out.println(tenX.eval());
		System.out.println(xCubedMinusTenx.eval());
		
		System.out.println("1"+NewtonsMethod.findFnZeros(xCubed,x,1,0.0001));
		System.out.println("2"+NewtonsMethod.findFnZeros(tenX,x,1,0.01));
		System.out.println("3"+NewtonsMethod.findFnZeros(xCubedMinusTenx,x,1,0.01));
		Expression derivTenx = new DerivativeExpression(tenX,x);
		Expression derivxCubed = new DerivativeExpression(xCubed,x);
		Expression derivxCubedMinusTenx = new DerivativeExpression(xCubedMinusTenx,x);
		//System.out.println(NewtonsMethod.findFnZeros(derivxCubedMinusTenx,x,1,0.01));
	}
	
}
