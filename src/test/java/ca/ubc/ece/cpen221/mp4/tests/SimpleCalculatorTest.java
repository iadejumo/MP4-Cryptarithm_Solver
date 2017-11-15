package ca.ubc.ece.cpen221.mp4.tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ca.ubc.ece.cpen221.mp4.expression.BinaryOperatorExpression;
import ca.ubc.ece.cpen221.mp4.expression.DerivativeExpression;
import ca.ubc.ece.cpen221.mp4.expression.Expression;
import ca.ubc.ece.cpen221.mp4.expression.NewtonsMethod;
import ca.ubc.ece.cpen221.mp4.expression.NumberExpression;
import ca.ubc.ece.cpen221.mp4.expression.UnaryOperatorExpression;
import ca.ubc.ece.cpen221.mp4.expression.VariableExpression;
import ca.ubc.ece.cpen221.mp4.operator.AbsoluteValueOperator;
import ca.ubc.ece.cpen221.mp4.operator.AdditionOperator;
import ca.ubc.ece.cpen221.mp4.operator.CoseOperator;
import ca.ubc.ece.cpen221.mp4.operator.DivisionOperator;
import ca.ubc.ece.cpen221.mp4.operator.ExponentiationOperator;
import ca.ubc.ece.cpen221.mp4.operator.MultiplicationOperator;
import ca.ubc.ece.cpen221.mp4.operator.NegationOperator;
import ca.ubc.ece.cpen221.mp4.operator.Operator;
import ca.ubc.ece.cpen221.mp4.operator.SineOperator;
import ca.ubc.ece.cpen221.mp4.operator.SubtractionOperator;

public class SimpleCalculatorTest {

	private static boolean checkClose(double arg1, double arg2) {
		if (Math.abs(arg1 - arg2) < 0.1)
			return true;
		return false;
	}

	@Test
	public void derivativesTest() {
		VariableExpression x = new VariableExpression("x");
		x.store(0.1);
		Expression xCubed = new BinaryOperatorExpression(new ExponentiationOperator(), x, new NumberExpression(3));
		Expression tenX = new BinaryOperatorExpression(new MultiplicationOperator(), new NumberExpression(10), x);

		Expression xCubedMinusTenx = new BinaryOperatorExpression(new SubtractionOperator(), xCubed, tenX);

		Expression derivTenx = new DerivativeExpression(tenX, x);
		Expression derivxCubed = new DerivativeExpression(xCubed, x);
		Expression derivxCubedMinusTenx = new DerivativeExpression(xCubedMinusTenx, x);

		assertTrue(checkClose(x.eval(), 0.1));
		assertTrue(checkClose(xCubed.eval(), 0.001));
		assertTrue(checkClose(tenX.eval(), 1));
		assertTrue(checkClose(xCubedMinusTenx.eval(), -1.001));

		assertTrue(checkClose(derivTenx.eval(), 10));
		assertTrue(checkClose(derivxCubed.eval(), 0.03));
		assertTrue(checkClose(derivxCubedMinusTenx.eval(), -10.03));

		assertTrue(checkClose(NewtonsMethod.findFnZeros(xCubed, x, 1, 0.0000001), 0));
		assertTrue(checkClose(NewtonsMethod.findFnZeros(xCubed, x, -1, 0.0000001), 0));
		assertTrue(checkClose(NewtonsMethod.findFnZeros(xCubedMinusTenx, x, 1, 0.01), 0));
		assertTrue(checkClose(NewtonsMethod.findFnZeros(xCubedMinusTenx, x, -10, 0.0000001), -Math.pow(10.0, 0.5)));
		assertTrue(checkClose(NewtonsMethod.findFnZeros(xCubedMinusTenx, x, 10, 0.0000001), Math.pow(10.0, 0.5)));
		assertTrue(checkClose(NewtonsMethod.findFnZeros(xCubedMinusTenx, x, 7, 0.0000001), Math.pow(10.0, 0.5)));

	}

	@Test
	public void toStringAndNameTest() {
		VariableExpression x = new VariableExpression("x");
		x.store(10.0);
		assertTrue(x.equals(x));
		assertTrue(x.eval() == 10);
		assertTrue(x.name().equals("x"));
		assertTrue(x.toString().equals("x"));

		Expression xSquared = new BinaryOperatorExpression(new ExponentiationOperator(), x, new NumberExpression(2));
		Expression derivXSquared = new DerivativeExpression(xSquared, x);

		assertTrue(derivXSquared.toString().equals("(d/dx)((x ^ 2.0))"));

		Operator plus = new AdditionOperator();
		Operator minus = new SubtractionOperator();
		Operator times = new MultiplicationOperator();
		Operator divide = new DivisionOperator();
		Operator exp = new ExponentiationOperator();
		Operator neg = new NegationOperator();
		Operator abs = new AbsoluteValueOperator();
		Operator sin = new SineOperator();
		Operator cos = new CoseOperator();

		assertTrue(plus.toString().equals("+"));
		assertTrue(minus.toString().equals("-"));
		assertTrue(times.toString().equals("*"));
		assertTrue(divide.toString().equals("/"));
		assertTrue(exp.toString().equals("^"));
		assertTrue(neg.toString().equals("~"));
		assertTrue(abs.toString().equals("abs"));
		assertTrue(sin.toString().equals("sin"));
		assertTrue(cos.toString().equals("cos"));

		Expression ten = new NumberExpression(10);
		Expression two = new NumberExpression(2);
		Expression e1 = new BinaryOperatorExpression(new AdditionOperator(), ten, two);
		Expression e2 = new BinaryOperatorExpression(new SubtractionOperator(), ten, two);
		Expression e3 = new BinaryOperatorExpression(new MultiplicationOperator(), ten, two);
		Expression e4 = new BinaryOperatorExpression(new DivisionOperator(), ten, two);
		Expression e5 = new BinaryOperatorExpression(new ExponentiationOperator(), ten, two);

		Expression e6 = new UnaryOperatorExpression(new NegationOperator(), ten);
		Expression e7 = new UnaryOperatorExpression(new AbsoluteValueOperator(), ten);
		Expression e8 = new UnaryOperatorExpression(new SineOperator(), ten);
		Expression e9 = new UnaryOperatorExpression(new CoseOperator(), ten);
		Expression e10 = new UnaryOperatorExpression(new AbsoluteValueOperator(), e6);

		assertTrue(checkClose(e1.eval(), 12));
		assertTrue(checkClose(e2.eval(), 8));
		assertTrue(checkClose(e3.eval(), 20));
		assertTrue(checkClose(e4.eval(), 5));
		assertTrue(checkClose(e5.eval(), 100));
		assertTrue(checkClose(e6.eval(), -10));
		assertTrue(checkClose(e7.eval(), 10));
		assertTrue(checkClose(e8.eval(), Math.sin(10)));
		assertTrue(checkClose(e9.eval(), Math.cos(10)));
		assertTrue(checkClose(e10.eval(), 10));

		assertTrue(e1.toString().equals("(10.0 + 2.0)"));
		assertTrue(e2.toString().equals("(10.0 - 2.0)"));
		assertTrue(e3.toString().equals("(10.0 * 2.0)"));
		assertTrue(e4.toString().equals("(10.0 / 2.0)"));
		assertTrue(e5.toString().equals("(10.0 ^ 2.0)"));

		assertTrue(e6.toString().equals("~(10.0)"));
		assertTrue(e7.toString().equals("abs(10.0)"));
		assertTrue(e8.toString().equals("sin(10.0)"));
		assertTrue(e9.toString().equals("cos(10.0)"));
		assertTrue(e10.toString().equals("abs(~(10.0))"));

		Expression f1 = new BinaryOperatorExpression(new MultiplicationOperator(), e1, xSquared);
		Expression f2 = new BinaryOperatorExpression(new DivisionOperator(), e10, xSquared);
		Expression f3 = new BinaryOperatorExpression(new SubtractionOperator(), f1, f2);
		Expression f4 = new BinaryOperatorExpression(new ExponentiationOperator(), e5, e4);

		Expression f5 = new DerivativeExpression(f1, x);
		Expression f6 = new DerivativeExpression(f2, x);
		Expression f7 = new DerivativeExpression(f3, x);
		Expression f8 = new DerivativeExpression(f4, x);

		assertTrue(f1.toString().equals("((10.0 + 2.0) * (x ^ 2.0))") && checkClose(f1.eval(), 1200));
		assertTrue(f2.toString().equals("(abs(~(10.0)) / (x ^ 2.0))") && checkClose(f2.eval(), 0.1));
		assertTrue(f3.toString().equals("(((10.0 + 2.0) * (x ^ 2.0)) - (abs(~(10.0)) / (x ^ 2.0)))")
				&& checkClose(f3.eval(), 1199.9));
		assertTrue(f4.toString().equals("((10.0 ^ 2.0) ^ (10.0 / 2.0))") && checkClose(f4.eval(), 10000000000.0));

		assertTrue(f5.toString().equals("(d/dx)(((10.0 + 2.0) * (x ^ 2.0)))") && checkClose(f5.eval(), 240));
		assertTrue(f6.toString().equals("(d/dx)((abs(~(10.0)) / (x ^ 2.0)))") && checkClose(f6.eval(), -0.02));
		assertTrue(f7.toString().equals("(d/dx)((((10.0 + 2.0) * (x ^ 2.0)) - (abs(~(10.0)) / (x ^ 2.0))))")
				&& checkClose(f7.eval(), 240));
		assertTrue(f8.toString().equals("(d/dx)(((10.0 ^ 2.0) ^ (10.0 / 2.0)))") && checkClose(f8.eval(), 0));

	}

	@Test
	public void sineCoseDerivativesTest() {
		VariableExpression x = new VariableExpression("x");
		Expression sinx = new UnaryOperatorExpression(new SineOperator(), x);
		Expression derivSinx = new DerivativeExpression(sinx, x);
		Expression cosx = new UnaryOperatorExpression(new CoseOperator(), x);
		Expression derivCosx = new DerivativeExpression(cosx, x);
		Expression tanx = new BinaryOperatorExpression(new DivisionOperator(), sinx, cosx);
		Expression derivTanx = new DerivativeExpression(tanx, x);

		x.store(3.1 / 2);

		NewtonsMethod n = new NewtonsMethod();
		NewtonsMethod m = new NewtonsMethod();
		assertTrue(!n.equals(m));
		assertTrue(checkClose(x.eval(), 3.1 / 2));
		assertTrue(checkClose(sinx.eval(), 0.999));
		assertTrue(checkClose(cosx.eval(), 0.0207));
		assertTrue(checkClose(tanx.eval(), 48.07));

		assertTrue(checkClose(derivSinx.eval(), 0.0207));
		assertTrue(checkClose(derivCosx.eval(), -0.999));
		assertTrue(checkClose(derivTanx.eval(), 2312.54));

		assertTrue(checkClose(NewtonsMethod.findFnZeros(sinx, x, 1, 0.0000001), 0));
		assertTrue(checkClose(NewtonsMethod.findFnZeros(sinx, x, -1, 0.0000001), 0));
		assertTrue(checkClose(NewtonsMethod.findFnZeros(cosx, x, 1, 0.01), 1.570));
		assertTrue(checkClose(NewtonsMethod.findFnZeros(cosx, x, -10, 0.0000001), -11));
		assertTrue(checkClose(NewtonsMethod.findFnZeros(tanx, x, 10, 0.0000001), 9.4245));
		assertTrue(checkClose(NewtonsMethod.findFnZeros(tanx, x, 7, 0.0000001), 6.283));

	}

}