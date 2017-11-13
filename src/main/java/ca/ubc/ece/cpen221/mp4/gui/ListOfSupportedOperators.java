package ca.ubc.ece.cpen221.mp4.gui;

import java.util.ArrayList;
import java.util.List;

import ca.ubc.ece.cpen221.mp4.operator.Operator;

public class ListOfSupportedOperators {

	
	public static List<Operator> getList(){
		List<Operator> supportedOperators = new ArrayList<Operator>();
		
		// add each operator to list
		supportedOperators.add(new AdditionOperator());
		supportedOperators.add(new SubtractionOperator());
		supportedOperators.add(new MultiplicationOperator());
		supportedOperators.add(new DivisionOperator());
		supportedOperators.add(new ExponentiationOperator());
		supportedOperators.add(new NegationOperator());
		supportedOperators.add(new AbsoluteValueOperator());
		supportedOperators.add(new SineOperator());
		supportedOperators.add(new CoseOperator());
		return supportedOperators;
	}
}
