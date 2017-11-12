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
import ca.ubc.ece.cpen221.mp4.gui.DivisionOperator;
import ca.ubc.ece.cpen221.mp4.gui.MultiplicationOperator;
import ca.ubc.ece.cpen221.mp4.gui.SubtractionOperator;
import ca.ubc.ece.cpen221.mp4.operator.BinaryOperator;
import ca.ubc.ece.cpen221.mp4.parser.BinaryOperatorExpression;
import ca.ubc.ece.cpen221.mp4.parser.NumberExpression;

/**
 * Cryptarithm - a datatype that represents a cryptarithm
 *
 */
public class Cryptarithm {

	private static final int MAX_SIZE = 10;
	private Expression exp1; // expression on left of equals
	private Expression exp2; // expression on right of equals

	// map of Strings of letters, to their respective variable expressions
	// RI: size is less than 11, no repeated variable names
	private Map<String, VariableExpression> allLetters;
	private static final Map<String, BinaryOperator> operators =new HashMap<String,BinaryOperator>();

	/**
	 * Cryptarithm constructor
	 * 
	 * @param cryptarithm
	 *            where each element is a String that represents part of the
	 *            cryptarithm
	 */
	public Cryptarithm(String[] cryptarithm) throws IllegalArgumentException {
		// find position of equal sign, and throw exception if invalid input
		int equalSignPos = findEqualsPos(cryptarithm);
		if (equalSignPos==-1 ||!checkIfValidCryptarithmFormat(cryptarithm))
			throw new IllegalArgumentException("Bad input!");
		
		//initiate important variables
		initiateOperators();
		allLetters = new HashMap<String, VariableExpression>();
		Stack<Expression> stackOfExpressions = new Stack<Expression>();
		Stack<BinaryOperator> stackOfOperators = new Stack<BinaryOperator>();

		for (int k=0;k<equalSignPos;k++) {
			if (k%2==0)
				stackOfExpressions.push(turnToExpression(cryptarithm[k],allLetters));
			else 
				stackOfOperators.push(operators.get(cryptarithm[k]));
		}
		exp1 = stacksToExpression(stackOfExpressions,stackOfOperators);
		// should already be empty
		stackOfExpressions.removeAllElements();
		stackOfOperators.removeAllElements();
		
		for (int k=equalSignPos;k<cryptarithm.length;k++) {
			if (k%2==0)
				stackOfExpressions.push(turnToExpression(cryptarithm[k],allLetters));
			else 
				stackOfOperators.push(operators.get(cryptarithm[k]));
		}
		exp2 = stacksToExpression(stackOfExpressions,stackOfOperators);
		
		if (allLetters.size()>MAX_SIZE) {
			throw new IllegalArgumentException("Too many letters!");
		}

	} // need to change precondition or post for wrong format parameter

	// differentiate capitals?
	
	private static void initiateOperators() {
		operators.put("+",new AdditionOperator());
		operators.put("-",new SubtractionOperator());
		operators.put("*",new MultiplicationOperator());
		operators.put("/",new DivisionOperator());
	}
	
	private static void insertToStack(String[] cryptarithmPart,Map<String,VariableExpression> allLetters) {
		
	}
	public static void main(String[] args) {

		/*
		 * Map<String,VariableExpression> a = new HashMap<String,VariableExpression>();
		 * Expression e = turnToExpression("SEND",a); int k = 1; for (String s:
		 * a.keySet()) { a.get(s).store(k); } a.get("S").store(7); a.get("E").store(8);
		 * a.get("N").store(6); a.get("D").store(5); System.out.println(e.eval());
		 * a.get("S").store(9); System.out.println(e.eval());
		 */

	}

	// takes a String and map of used letters
	// returns expression with sum of letters\
	// eg. "SEND" => S*1000 + E*100* + N*10 + D*1
	// modifies Map to have S, E, N and D variableExpression
	private static Expression turnToExpression(String word, Map<String, VariableExpression> usedLetters) {
		List<Expression> lettersInOrder = new ArrayList<Expression>();
		String letter;

		for (int k = 0, multiplier = 1; k < word.length(); k++) {
			// set multiplier and get one letter
			multiplier = (int) (Math.pow(10, (word.length() - k - 1)));
			letter = word.substring(k, k + 1); // ask Satish about this

			// if new letter, create new variable and put it in map
			if (!(usedLetters.containsKey(letter)))
				usedLetters.put(letter, new VariableExpression(letter));

			// multiply expression and add it to the list
			VariableExpression var = usedLetters.get(letter);
			Expression exp = new BinaryOperatorExpression(new MultiplicationOperator(),
					new NumberExpression(multiplier), var);
			// check out if you can have list of operators
			lettersInOrder.add(exp);
		}

		// add the expressions in the list
		return addList(lettersInOrder, 0);
	}
	
	private static Expression stacksToExpression(Stack<Expression> stackOfExpressions, Stack<BinaryOperator> stackOfOperators) {
		if (stackOfOperators.isEmpty())
			return stackOfExpressions.pop();
		return new BinaryOperatorExpression(stackOfOperators.pop(),stackOfExpressions.pop(),stacksToExpression(stackOfExpressions,stackOfOperators));
	}

	// adds the
	// recursive method that adds up the expression from index to
	// lettersInOrder.size()
	private static Expression addList(List<Expression> lettersInOrder, int index) {
		// base case, add 0
		if (index == lettersInOrder.size())
			return new NumberExpression(0);

		// recursively add the next expression
		return new BinaryOperatorExpression(new AdditionOperator(), lettersInOrder.get(index),
				addList(lettersInOrder, index + 1));
	}

	// maybe need way to check if valid cryptarithm
	// takes in String[] cryptarithm
	// returns index of "=", but returns -1 if it is not there or if k is not in
	// valid position
	private static int findEqualsPos(String[] cryptarithm) {
		int k;
		for (k = 0; k < cryptarithm.length; k++) {
			if (cryptarithm[k].equals("="))
				break;
		}

		if (k % 2 == 1)
			return k;
		return -1;
	}

	// returns false if cryptarithm is null or empty or has even number elements
	// or if it has non operators at odd index positions
	// or if it has non letter strings at even positions
	// otherwise returns true
	private static boolean checkIfValidCryptarithmFormat(String [] cryptarithm) {
		// check for null or empty or invalid length
		if (cryptarithm == null || cryptarithm.length == 0 || cryptarithm.length % 2 == 0)
			return false;
		
		// check for non operators at odd indexes
		for (int k=1;k<cryptarithm.length;k=k+2) {
			if (!(checkIfOperatorString(cryptarithm[k])))
				return false;
		}
		
		//check for non letter strings at even positions
		char[] chars;
		for (int k=0;k<cryptarithm.length;k=k+2) {
			chars = cryptarithm[k].toCharArray();
			for (char c: chars) {
				if (!(Character.isLetter(c)))
					return false;
			}
		}
		
		return true;
	}

	// returns false if s is not "+" or "-" or "*" or "/"
	// otherwise return true
	private static boolean checkIfOperatorString(String s) {
		if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/"))
			return true;
		return false;
	}

	/**
	 * Find solutions to the cryptarithm
	 * 
	 * @return a list of all possible solutions to the given cryptarithm. A solution
	 *         is a map that provides the value for each alphabet in the
	 *         cryptarithm.
	 */
	public List<Map<Character, Integer>> solve() throws NoSolutionException {
		// TODO implement this method
		return null; // change this
	}

	// You will need more methods
}
