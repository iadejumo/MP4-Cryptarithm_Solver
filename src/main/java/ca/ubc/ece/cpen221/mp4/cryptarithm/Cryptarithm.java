package ca.ubc.ece.cpen221.mp4.cryptarithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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
	private static final int INVALID = -1;
	private static final Map<String, BinaryOperator> operators = new HashMap<String, BinaryOperator>();

	private final Expression exp1; // expression on left of equals
	private final Expression exp2; // expression on right of equals

	// map of Strings of letters, to their respective variable expressions
	// RI: size is less than 11, no repeated variable names
	private final Map<String, VariableExpression> mapOfUsedLetters;

	private final Set<String> firstLetters;
	/**
	 * Cryptarithm constructor
	 * 
	 * @param cryptarithm
	 *            where each element is a String that represents part of the
	 *            cryptarithm
	 */
	public Cryptarithm(String[] cryptarithm) throws IllegalArgumentException {
		// find position of equal sign, and throw exception if invalid input
		if (!checkIfValidCryptarithmFormat(cryptarithm))
			throw new IllegalArgumentException("Bad input!");

		int equalSignPos = findEqualsPos(cryptarithm);
		if (equalSignPos == INVALID)
			throw new IllegalArgumentException("Bad input!");

		// initiate important variables
		initiateOperators();
		firstLetters = getFirstLetters(cryptarithm);
		
		mapOfUsedLetters = new HashMap<String, VariableExpression>();
		Stack<Expression> stackOfExpressions = new Stack<Expression>();
		Stack<BinaryOperator> stackOfOperators = new Stack<BinaryOperator>();

		// make stack of expression and operators for left hand side of equation
		// eg ["SEND","+","MORE","-","MONEY"] => Stack["MONEY","MORE","SEND"] and
		// Stack["-","+"]
		for (int k = 0; k < equalSignPos; k++) {
			if (k % 2 == 0)
				stackOfExpressions.push(stringToExpression(cryptarithm[k], mapOfUsedLetters));
			else
				stackOfOperators.push(operators.get(cryptarithm[k]));
		}
		exp1 = stacksToExpression(stackOfExpressions, stackOfOperators);

		// repeat for the right hand side of equation
		for (int k = equalSignPos+1; k < cryptarithm.length; k++) {
			if (k % 2 == 0)
				stackOfExpressions.push(stringToExpression(cryptarithm[k], mapOfUsedLetters));
			else
				stackOfOperators.push(operators.get(cryptarithm[k]));
		}
		exp2 = stacksToExpression(stackOfExpressions, stackOfOperators);

		// check if too many letters
		if (mapOfUsedLetters.size() > MAX_SIZE)
			throw new IllegalArgumentException("Too many letters!");

	} // need to change precondition or post for wrong format parameter
		// differentiate capitals?

	// gets the first letters from the array of String 
	private static Set<String> getFirstLetters(String[] cryptarithm){
		Set<String> firstLetters = new HashSet<String>();
		for (int k=0;k<cryptarithm.length;k=k+2) {
			firstLetters.add(cryptarithm[k].substring(0, 1));
		}
		return firstLetters;
	}
	
	public static void main(String[] args) {
		String[] c = {"SEND","+","MORE","=","MONEY"};
		Cryptarithm crypt = new Cryptarithm(c);
		System.out.println("Map: "+crypt.mapOfUsedLetters);
		System.out.println("Operators: "+operators);
		for (String s:crypt.mapOfUsedLetters.keySet()) {
			crypt.mapOfUsedLetters.get(s).store(1);
		}
		crypt.mapOfUsedLetters.get("M").store(4);
		crypt.mapOfUsedLetters.get("O").store(7);
		System.out.println("Exp 1 = " + crypt.exp1.eval());
		System.out.println("Exp 2 = " + crypt.exp2.eval());
		System.out.println("Map: "+crypt.mapOfUsedLetters);
		System.out.println("Operators: "+operators);
		System.out.println("First Letters: "+crypt.firstLetters);
	}
	
	// initiate the Map to be able to use binary operators
	private static void initiateOperators() {
		operators.put("+", new AdditionOperator());
		operators.put("-", new SubtractionOperator());
		operators.put("*", new MultiplicationOperator());
		operators.put("/", new DivisionOperator());
	}

	// takes 1. a String and 2. a map of used letters
	// returns expression with sum of letters
	// eg. "SEND" => S*1000 + E*100* + N*10 + D*1
	// modifies Map to have S, E, N and D variableExpressions
	private static Expression stringToExpression(String word, Map<String, VariableExpression> usedLetters) {
		List<Expression> multipliedLetterVariables = new ArrayList<Expression>();
		String letter;

		for (int k = 0, multiplier = 1; k < word.length(); k++) {
			// set multiplier and get one letter
			multiplier = (int) (Math.pow(10, (word.length() - k - 1)));
			letter = word.substring(k, k + 1); // ask Satish about this

			// if new letter, create new variable and put it in map
			if (!(usedLetters.containsKey(letter)))
				usedLetters.put(letter, new VariableExpression(letter));

			// multiply variableExpression and add it to the list
			VariableExpression var = usedLetters.get(letter);
			Expression exp = new BinaryOperatorExpression(new MultiplicationOperator(),
					new NumberExpression(multiplier), var);
			// check out if you can have list of operators
			multipliedLetterVariables.add(exp);
		}

		// add the expressions in the list
		return addList(multipliedLetterVariables, 0);
	}

	// takes 1. a stack of Expressions 2. a stack of operators
	// must have stackOfExpressions.size() = stackOfOperators.size() + 1 always
	// does performs operators
	// eg ["MONEY,"MORE","SEND"] x ["+","-"] => SEND + MORE - MONEY

	private static Expression stacksToExpression(Stack<Expression> stackOfExpressions,
			Stack<BinaryOperator> stackOfOperators) {
		// base case: once it is at the end, return the last expression left
		if (stackOfOperators.isEmpty())
			return stackOfExpressions.pop();

		// recursive step: pop out the next operators and expression and apply
		BinaryOperator operator = stackOfOperators.pop();
		Expression expression = stackOfExpressions.pop();
		return new BinaryOperatorExpression(operator, stacksToExpression(stackOfExpressions, stackOfOperators),
				expression);
	}

	// adds a list of expressions
	// recursive method that adds up the expression from index to
	// listOfExpressions.size()
	private static Expression addList(List<Expression> listOfExpressions, int index) {
		// base case, add 0
		if (index == listOfExpressions.size())
			return new NumberExpression(0);

		// recursively add the next expression
		return new BinaryOperatorExpression(operators.get("+"), listOfExpressions.get(index),
				addList(listOfExpressions, index + 1));
	}

	// takes in String[] cryptarithm
	// returns index of "=", but returns -1 if it is not there or if k is not in
	// valid position
	private static int findEqualsPos(String[] cryptarithm) {
		// look for index of "="
		int k;
		for (k = 0; k < cryptarithm.length; k++) {
			if (cryptarithm[k].equals("="))
				break;
		}

		// return k index if it is an odd number within the range
		if (k < cryptarithm.length && k % 2 == 1)
			return k;

		return -1;
	}

	// returns false if cryptarithm is null or empty or has even number elements
	// or if it has non operators at odd index positions
	// or if it has non letter strings at even positions
	// otherwise returns true
	private static boolean checkIfValidCryptarithmFormat(String[] cryptarithm) {
		// check for null or empty or invalid length
		if (cryptarithm == null || cryptarithm.length == 0 || cryptarithm.length % 2 == 0)
			return false;

		for (String s : cryptarithm) {
			if (s == null)
				return false;
		}
		// check for non operators at odd indexes
		for (int k = 1; k < cryptarithm.length; k = k + 2) {
			if (!(checkIfOperatorString(cryptarithm[k])))
				return false;
		}

		// check for non letter strings at even positions
		char[] chars;
		for (int k = 0; k < cryptarithm.length; k = k + 2) {
			chars = cryptarithm[k].toCharArray();
			for (char c : chars) {
				if (!(Character.isLetter(c)))
					return false;
			}
		}
		return true;
	}

	// returns false if s is not "+" or "-" or "*" or "/"
	// otherwise return true
	private static boolean checkIfOperatorString(String s) {
		if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/") || s.equals("="))
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
