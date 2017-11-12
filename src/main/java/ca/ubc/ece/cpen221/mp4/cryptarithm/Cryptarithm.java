package ca.ubc.ece.cpen221.mp4.cryptarithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import ca.ubc.ece.cpen221.mp4.expression.Expression;
import ca.ubc.ece.cpen221.mp4.expression.VariableExpression;
import ca.ubc.ece.cpen221.mp4.gui.AdditionOperator;
import ca.ubc.ece.cpen221.mp4.gui.MultiplicationOperator;
import ca.ubc.ece.cpen221.mp4.parser.BinaryOperatorExpression;
import ca.ubc.ece.cpen221.mp4.parser.NumberExpression;

/**
 * Cryptarithm - a datatype that represents a cryptarithm
 *
 */
public class Cryptarithm {

	Expression exp1;
	Expression exp2;
	
	/**
	 * Cryptarithm constructor
	 * 
	 * @param cryptarithm
	 *            where each element is a String that represents part of the
	 *            cryptarithm
	 */
	public Cryptarithm(String[] cryptarithm) throws IllegalArgumentException{
		Set<String> characters = new HashSet<String>();
		String nextStr;
		String letter;

		for (int k = 0; k < cryptarithm.length; k++) {
			nextStr = cryptarithm[k];
			for (int j = 0; j < nextStr.length(); j++) {
				letter = nextStr.substring(j, j + 1);
				if (!(characters.contains(letter))) {
					characters.add(letter);
				}
					
				else
					characters.add(letter);
			}
		}

		for (String s: characters) {
			
		}
	} //need to change precondition or post for wrong format parameter

	// differentiate capitals?
	
	public static void main(String[] args) {
		Map<String,VariableExpression> a = new HashMap<String,VariableExpression>();
		Expression e = turnToExpression("SEND",a);
		for (String s: a.keySet()) {
			a.get(s).store(1);
		}
		System.out.println(e.eval());
	}
	private static Expression turnToExpression(String word,Map<String,VariableExpression> usedLetters) {
		List<Expression> lettersInOrder = new ArrayList<Expression>();
		
		for (int k=0, multiplier = 1;k<word.length();k++) {
			multiplier = multiplier * (int)(Math.pow(10, (word.length()-k-1)));
			String letter = word.substring(k, k+1); //ask Satish about this
			if ( !(usedLetters.containsKey(letter))) {
				usedLetters.put(letter, new VariableExpression(letter));
			}
			VariableExpression var = usedLetters.get(letter);
			Expression exp = new BinaryOperatorExpression(new MultiplicationOperator(),new NumberExpression(multiplier),var);
			lettersInOrder.add(exp);
		}
		
		return addList(lettersInOrder,0);
	}
	
	private static Expression addList(List<Expression> lettersInOrder,int index) {
		if (index==lettersInOrder.size()){
			return new NumberExpression(0);
		}
		
		return new BinaryOperatorExpression(new AdditionOperator(),lettersInOrder.get(index),addList(lettersInOrder,index+1));
	}
	/**
	 * Find solutions to the cryptarithm
	 * 
	 * @return a list of all possible solutions to the given cryptarithm. A
	 *         solution is a map that provides the value for each alphabet in
	 *         the cryptarithm.
	 */
	public List<Map<Character, Integer>> solve() throws NoSolutionException {
		// TODO implement this method
		return null; // change this
	}

	// You will need more methods
}
