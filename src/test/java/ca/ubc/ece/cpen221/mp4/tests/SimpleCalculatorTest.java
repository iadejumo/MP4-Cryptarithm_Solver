package ca.ubc.ece.cpen221.mp4.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import ca.ubc.ece.cpen221.mp4.expression.DerivativeExpression;
import ca.ubc.ece.cpen221.mp4.expression.Expression;
import ca.ubc.ece.cpen221.mp4.expression.NewtonsMethod;
import ca.ubc.ece.cpen221.mp4.expression.VariableExpression;
import ca.ubc.ece.cpen221.mp4.gui.AdditionOperator;
import ca.ubc.ece.cpen221.mp4.gui.CoseOperator;
import ca.ubc.ece.cpen221.mp4.gui.DivisionOperator;
import ca.ubc.ece.cpen221.mp4.gui.ExponentiationOperator;
import ca.ubc.ece.cpen221.mp4.gui.MultiplicationOperator;
import ca.ubc.ece.cpen221.mp4.gui.SineOperator;
import ca.ubc.ece.cpen221.mp4.gui.SubtractionOperator;
import ca.ubc.ece.cpen221.mp4.parser.BinaryOperatorExpression;
import ca.ubc.ece.cpen221.mp4.parser.NumberExpression;
import ca.ubc.ece.cpen221.mp4.parser.UnaryOperatorExpression;


public class SimpleCalculatorTest {


	private static boolean checkClose(double arg1, double arg2) {
		if (Math.abs(arg1-arg2) < 0.1)
			return true;
		return false;
	}
	
	@Test
	public void derivativesTest() {
		VariableExpression x = new VariableExpression("x");
		x.store(0.1);
		Expression xCubed = new BinaryOperatorExpression(new ExponentiationOperator(),x,new NumberExpression(3));
		Expression tenX = new BinaryOperatorExpression(new MultiplicationOperator(),new NumberExpression(10),x);
		
		Expression xCubedMinusTenx = new BinaryOperatorExpression(new SubtractionOperator(),xCubed,tenX);
		
		Expression derivTenx = new DerivativeExpression(tenX,x);
		Expression derivxCubed = new DerivativeExpression(xCubed,x);
		Expression derivxCubedMinusTenx = new DerivativeExpression(xCubedMinusTenx,x);
		
		assertTrue(checkClose(x.eval(),0.1));
		assertTrue(checkClose(xCubed.eval(),0.001));
		assertTrue(checkClose(tenX.eval() ,1));
		System.out.println(xCubedMinusTenx.eval());
		assertTrue(checkClose(xCubedMinusTenx.eval() ,-1.001));
		
		assertTrue(checkClose(derivTenx.eval() ,10));
		assertTrue(checkClose(derivxCubed.eval() , 0.03));
		assertTrue(checkClose(derivxCubedMinusTenx.eval() , -10.03));
		
		assertTrue(checkClose(NewtonsMethod.findFnZeros(xCubed,x,1,0.0000001) , 0));
		assertTrue(checkClose(NewtonsMethod.findFnZeros(xCubed,x,-1,0.0000001) , 0));
		assertTrue(checkClose(NewtonsMethod.findFnZeros(xCubedMinusTenx,x,1,0.01) , 0));
		assertTrue(checkClose(NewtonsMethod.findFnZeros(xCubedMinusTenx,x,-10,0.0000001) , -Math.pow(10.0, 0.5)));
		assertTrue(checkClose(NewtonsMethod.findFnZeros(xCubedMinusTenx,x,10,0.0000001) , Math.pow(10.0, 0.5)));
		assertTrue(checkClose(NewtonsMethod.findFnZeros(xCubedMinusTenx,x,7,0.0000001) , Math.pow(10.0, 0.5)));
	}
	
	@Test
	public void sineCoseDerivativesTest() {
		VariableExpression x = new VariableExpression("x");
		Expression sinx = new UnaryOperatorExpression(new SineOperator(), x);
		Expression derivSinx = new DerivativeExpression(sinx,x);
		Expression cosx = new UnaryOperatorExpression(new CoseOperator(), x);
		Expression derivCosx = new DerivativeExpression(cosx,x);
		Expression tanx = new BinaryOperatorExpression(new DivisionOperator(), sinx,cosx);
		Expression derivTanx = new DerivativeExpression(tanx,x);
		
		x.store(3.1/2);
		
		assertTrue(checkClose(x.eval(),3.1/2));
		assertTrue(checkClose(sinx.eval(),0.999));
		assertTrue(checkClose(cosx.eval() ,0.0207));
		assertTrue(checkClose(tanx.eval(),48.07));
		
		assertTrue(checkClose(derivSinx.eval() ,0.0207));
		assertTrue(checkClose(derivCosx.eval() , -0.999));
		assertTrue(checkClose(derivTanx.eval() , 2312.54));
		
		assertTrue(checkClose(NewtonsMethod.findFnZeros(sinx,x,1,0.0000001) , 0));
		assertTrue(checkClose(NewtonsMethod.findFnZeros(sinx,x,-1,0.0000001) , 0));
		assertTrue(checkClose(NewtonsMethod.findFnZeros(cosx,x,1,0.01) , 1.570));
		assertTrue(checkClose(NewtonsMethod.findFnZeros(cosx,x,-10,0.0000001) , -11));
		assertTrue(checkClose(NewtonsMethod.findFnZeros(tanx,x,10,0.0000001) , 9.4245));
		assertTrue(checkClose(NewtonsMethod.findFnZeros(tanx,x,7,0.0000001) , 6.283));
		
	}
	

}