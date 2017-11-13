/**
 * A simple command line parser for arithmetic expressions
 */
package ca.ubc.ece.cpen221.mp4.parser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import ca.ubc.ece.cpen221.mp4.expression.Expression;
import ca.ubc.ece.cpen221.mp4.gui.AbsoluteValueOperator;
import ca.ubc.ece.cpen221.mp4.gui.AdditionOperator;
import ca.ubc.ece.cpen221.mp4.gui.CoseOperator;
import ca.ubc.ece.cpen221.mp4.gui.DivisionOperator;
import ca.ubc.ece.cpen221.mp4.gui.ExponentiationOperator;
import ca.ubc.ece.cpen221.mp4.gui.ListOfSupportedOperators;
import ca.ubc.ece.cpen221.mp4.gui.MultiplicationOperator;
import ca.ubc.ece.cpen221.mp4.gui.NegationOperator;
import ca.ubc.ece.cpen221.mp4.gui.SineOperator;
import ca.ubc.ece.cpen221.mp4.gui.SubtractionOperator;
import ca.ubc.ece.cpen221.mp4.operator.Operator;

/**
 * CommandLineParser - a command line calculator.
 * 
 * You will need to add any new Operators you create to the operatorSet or they
 * will not be usable in the command line calculator.
 *
 */
public class CommandLineParser {

	/**
	 * @param args
	 *            program arguments
	 */
	public static void main(String[] args) {

		Set<Operator> operatorSet = new HashSet<Operator>(ListOfSupportedOperators.getList());

		ExpressionParser parser = new ExpressionParser(operatorSet, new ExpressionMaker());

		Scanner scanner = new Scanner(System.in);
		do {
			System.out.println("Enter an expression");
			String expression = scanner.nextLine();
			try {
				Expression exp = parser.parse(expression);
				System.out.println("Result: " + exp.eval());
			} catch (Exception e) {
				System.out.println("Input format not accepted. Please try again.");
			}
		} while (true);

	}

}
