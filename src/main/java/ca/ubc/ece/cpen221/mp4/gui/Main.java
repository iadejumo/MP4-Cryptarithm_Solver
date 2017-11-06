package ca.ubc.ece.cpen221.mp4.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import ca.ubc.ece.cpen221.mp4.operator.Operator;

/**
 * Main - creates and runs a new Calculator GUI. 
 *
 */
public class Main {

	/**
	 * Runs the calculator GUI.
	 * 
	 * @param args arguments to the main function 
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> createAndShowSetupScreen());
	}
	
	private static void createAndShowSetupScreen() {
		List<Operator> operators = getListOfAllOperators();

		Calculator calculator = new Calculator(operators);
		calculator.launch();
	}
	
	private static List<Operator> getListOfAllOperators(){
		List<Operator> operators = new ArrayList<Operator>();	
		
		// create every operator object
		Operator a = new AdditionOperator();
		Operator s = new SubtractionOperator();
		Operator m = new MultiplicationOperator();
		Operator d = new DivisionOperator();
		Operator e = new ExponentiationOperator();
		Operator n = new NegationOperator();
		Operator abs = new AbsoluteValueOperator();
		
		// add each operator to list
		operators.add(a);
		operators.add(s);
		operators.add(m);
		operators.add(d);
		operators.add(e);
		operators.add(n);
		operators.add(abs);
		return operators;
	}

}
