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
		
		// add each operator to list
		operators.add(new AdditionOperator());
		operators.add(new SubtractionOperator());
		operators.add(new MultiplicationOperator());
		operators.add(new DivisionOperator());
		operators.add(new ExponentiationOperator());
		operators.add(new NegationOperator());
		operators.add(new AbsoluteValueOperator());
		operators.add(new SineOperator());
		operators.add(new CoseOperator());
		return operators;
	}

}
