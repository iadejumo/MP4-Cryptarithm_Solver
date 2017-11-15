package ca.ubc.ece.cpen221.mp4.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import ca.ubc.ece.cpen221.mp4.operator.ListOfSupportedOperators;
import ca.ubc.ece.cpen221.mp4.operator.Operator;

/**
 * Main - creates and runs a new Calculator GUI.
 *
 */
public class Main {

	/**
	 * Runs the calculator GUI.
	 * 
	 * @param args
	 *            arguments to the main function
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> createAndShowSetupScreen());
	}

	private static void createAndShowSetupScreen() {
		List<Operator> operators = ListOfSupportedOperators.getList();

		Calculator calculator = new Calculator(operators);
		calculator.launch();
	}

}
