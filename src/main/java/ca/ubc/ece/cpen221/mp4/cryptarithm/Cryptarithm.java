package ca.ubc.ece.cpen221.mp4.cryptarithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import ca.ubc.ece.cpen221.mp4.expression.BinaryOperatorExpression;
import ca.ubc.ece.cpen221.mp4.expression.Expression;
import ca.ubc.ece.cpen221.mp4.expression.NumberExpression;
import ca.ubc.ece.cpen221.mp4.expression.VariableExpression;
import ca.ubc.ece.cpen221.mp4.operator.AdditionOperator;
import ca.ubc.ece.cpen221.mp4.operator.BinaryOperator;
import ca.ubc.ece.cpen221.mp4.operator.DivisionOperator;
import ca.ubc.ece.cpen221.mp4.operator.MultiplicationOperator;
import ca.ubc.ece.cpen221.mp4.operator.SubtractionOperator;
import ca.ubc.ece.cpen221.mp4.permutation.Permutation;

/**
 * Cryptarithm - a datatype that represents a cryptarithm
 *
 */
public class Cryptarithm {

	// RI: mapOfUsedLetters.size() < 11, has no repeated letters, 
	//contains all letters in cryptarithm list
	// RI: cryptarithm list has (+-*/=) in every odd index and a word of only
	// characters in even indexes
	// RI: cryptarithm.size() is an odd number and contains "="
	// AF: cryptarithm=
	
	private static final int MAX_SIZE = 10;
	private static final int INVALID = -1;
	private static final int MIN_SIZE = 3;
	private static final double PRECISION = 0.0000001;
	private static final Map<String, BinaryOperator> operators = new HashMap<String, BinaryOperator>();

	private final Expression exp1; // expression on left of equals
	private final Expression exp2; // expression on right of equals

	// map of Strings of letters, to their respective variable expressions
	private final Map<String, VariableExpression> mapOfUsedLetters;

	private final List<String> cryptarithm;
	private final Set<String> firstLetters;

	/**
	 * Cryptarithm constructor - takes an array of Strings and converts it into a
	 * cryptarithm
	 * 
	 * @param cryptarithm
	 *            where each element is a String that represents part of the
	 *            cryptarithm, NOTE: upper case and lower case input will be treated
	 *            as equal
	 * @throws IllegalArgumentException
	 *             when cryptarithm parameter is invalid invalid cryptarithm include
	 *             being null, having null elements being shorter than 3 elements,
	 *             having more than 10 different letters, having non-operators at
	 *             odd index positions having non-letters at even index positions
	 */

	public Cryptarithm(String[] cryptarithm) throws IllegalArgumentException {
		// check for null or too short cryptarithm
		if (!checkIfNullOrShort(cryptarithm))
			throw new IllegalArgumentException("Null or Too Short!");

		this.cryptarithm = removeWhiteSpaces(cryptarithm);
		if (!checkIfValid(this.cryptarithm))
			throw new IllegalArgumentException("Invalid input values!");

		int equalSignPos = findEqualsPos(this.cryptarithm);
		if (equalSignPos == INVALID)
			throw new IllegalArgumentException("Equals sign missing or in wrong position!");

		// initiate important variables
		initiateOperators();
		firstLetters = getFirstLetters(this.cryptarithm);

		mapOfUsedLetters = new HashMap<String, VariableExpression>();
		Stack<Expression> stackOfExpressions = new Stack<Expression>();
		Stack<BinaryOperator> stackOfOperators = new Stack<BinaryOperator>();

		// make stack of expression and operators for left hand side of equation
		// eg ["SEND","+","MORE","-","MONEY"] => Stack["MONEY","MORE","SEND"] and
		// Stack["-","+"]
		for (int k = 0; k < equalSignPos; k++) {
			if (k % 2 == 0)
				stackOfExpressions.push(stringToExpression(this.cryptarithm.get(k), mapOfUsedLetters));
			else
				stackOfOperators.push(operators.get(this.cryptarithm.get(k)));
		}
		exp1 = stacksToExpression(stackOfExpressions, stackOfOperators);

		// repeat for the right hand side of equation
		for (int k = equalSignPos + 1; k < this.cryptarithm.size(); k++) {
			if (k % 2 == 0)
				stackOfExpressions.push(stringToExpression(this.cryptarithm.get(k), mapOfUsedLetters));
			else
				stackOfOperators.push(operators.get(this.cryptarithm.get(k)));
		}
		exp2 = stacksToExpression(stackOfExpressions, stackOfOperators);

		// check if too many letters
		if (mapOfUsedLetters.size() > MAX_SIZE)
			throw new IllegalArgumentException("Too many letters!");

	}

	// reformats String[] cryptarithm to remove white spaces and capitalizes all
	// letters
	private static List<String> removeWhiteSpaces(String[] cryptarithm) {
		List<String> updatedCryptarithm = new ArrayList<String>();

		for (String s : cryptarithm) {
			if (!(s.trim().contains(" ")) && !(s.trim().isEmpty()))
				updatedCryptarithm.add(s.trim().toUpperCase());
		}
		return updatedCryptarithm;
	}

	// checks if trimmed cryptarithm is valid
	// return false if it has non-operator strings at odd index positions
	// return false if it has non-letter strings at even index positions
	// otherwise returns true
	private static boolean checkIfValid(List<String> cryptarithm) {
		// check for non operators at odd indexes
		for (int k = 1; k < cryptarithm.size(); k = k + 2) {
			if (!(checkIfOperatorString(cryptarithm.get(k))))
				return false;
		}

		// check for non letter strings at even positions
		char[] chars;
		for (int k = 0; k < cryptarithm.size(); k = k + 2) {
			chars = cryptarithm.get(k).toCharArray();
			for (char c : chars) {
				if (!(Character.isLetter(c)))
					return false;
			}
		}
		return true;
	}

	// gets the first letters from the list of strings
	private static Set<String> getFirstLetters(List<String> cryptarithm) {
		Set<String> firstLetters = new HashSet<String>();
		for (int k = 0; k < cryptarithm.size(); k = k + 2) {
			firstLetters.add(cryptarithm.get(k).substring(0, 1));
		}
		return firstLetters;
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

	// takes in List of Strings
	// returns index of "=", but returns -1 if it is not there or if k is not in
	// valid position
	private static int findEqualsPos(List<String> cryptarithm) {
		// look for index of "="
		int k;
		for (k = 0; k < cryptarithm.size(); k++) {
			if (cryptarithm.get(k).equals("="))
				break;
		}

		// return k index if it is an odd number within the range
		if (k < cryptarithm.size() && k % 2 == 1)
			return k;

		return -1;
	}

	// returns false if cryptarithm is null or too short or has null elements
	// otherwise returns true
	private static boolean checkIfNullOrShort(String[] cryptarithm) {
		// check for null or empty or invalid length
		if (cryptarithm == null || cryptarithm.length < MIN_SIZE)
			return false;

		for (String s : cryptarithm) {
			if (s == null)
				return false;
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
	 *         is a Map<Character,Integer> that provides the value for each alphabet
	 *         in the cryptarithm.
	 */
	public List<Map<Character, Integer>> solve() throws NoSolutionException {
		List<Map<Character, Integer>> solutions = new ArrayList<Map<Character, Integer>>();
		Integer[] allDigits = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		int numLetters = mapOfUsedLetters.size();

		// Generate all permutations of the 10 digits
		Permutation<Integer> permutation = new Permutation<Integer>(allDigits);
		List<Integer[]> allPermutations = permutation.getAllPermutations();

		// Creates a list of all the Letters used within the cryptarithm
		List<String> lettersList = new ArrayList<String>();
		lettersList.addAll(mapOfUsedLetters.keySet());

		// Get index of letter that appear first in the expressions
		List<Integer> firstLetterIndices = new ArrayList<Integer>();
		for (String letter : firstLetters) {
			firstLetterIndices.add(lettersList.indexOf(letter));
		}

		// Filter out all solution where any letter that appears first is equal to zero.
		List<Integer[]> nonZeroFirstPermutations = new ArrayList<Integer[]>();
		for (Integer[] possiblePermutation : allPermutations) {
			if (checkIfFirstIsZero(possiblePermutation, firstLetterIndices)) {
				nonZeroFirstPermutations.add(possiblePermutation);
			}
		}

		// Checks possible possible solutions and stores the ones that yield the correct
		// result
		for (Integer[] singlePermutation : nonZeroFirstPermutations) {
			if (evaluateSingleExpression(lettersList, singlePermutation)) {
				// Creates a map for the given solution if it is correct
				Map<Character, Integer> singleSolution = new HashMap<Character, Integer>();
				for (int index = 0; index < numLetters; index++) {
					Character letter = lettersList.get(index).charAt(0);
					Integer value = singlePermutation[index];
					singleSolution.put(letter, value);
				}

				// Checks whether given solution already exists within lists of solutions
				if (solutionDoesNotExist(singleSolution, solutions)) {
					solutions.add(singleSolution);
				}
			}
		}
		// Throws exception if no solutions are found
		if (solutions.size() == 0) {
			throw new NoSolutionException();
		}

		return solutions;
	}

	private static boolean checkClose(double arg1, double arg2) {
		if (Math.abs(arg1 - arg2) < PRECISION)
			return true;
		return false;
	}

	/*
	 * Checks that for a given permutation none of the Characters that appear first
	 * in any expression is equals to zero
	 * 
	 * @param possiblePermutation - permutation that would be checked
	 * 
	 * @param firstLetterIndices - indices of all the characters that appear first
	 * in the expressions
	 * 
	 * @return true - if and only if none of the Characters that appear first in any
	 * expression is equals to zero
	 */
	private boolean checkIfFirstIsZero(Integer[] possiblePermutation, List<Integer> firstLetterIndices) {
		for (Integer indexOfFirst : firstLetterIndices) {
			if (possiblePermutation[indexOfFirst] == 0) {
				return false;
			}
		}
		return true;
	}

	/*
	 * Checks that two solutions are different
	 * 
	 * @param newSolution - first solution
	 * 
	 * @param existingSolution - second solution
	 * 
	 * @return true - if and only if the solutions are different
	 */
	private boolean twoDifferentSolutions(Map<Character, Integer> newSolution,
			Map<Character, Integer> existingSolution) {
		for (Character c : newSolution.keySet()) {
			if (!newSolution.get(c).equals(existingSolution.get(c))) {
				return true;
			}
		}
		return false;
	}

	/*
	 * Checks that the list of possible solutions does not already contain a given
	 * solution
	 * 
	 * @param newSolution - solution that has just been generated
	 * 
	 * @param solutions - List of all solutions already discovered
	 * 
	 * @return true - if and only if the new solution is not already listed among
	 * possible solutions
	 */
	private boolean solutionDoesNotExist(Map<Character, Integer> newSolution, List<Map<Character, Integer>> solutions) {
		for (Map<Character, Integer> existingSolution : solutions) {
			if (!twoDifferentSolutions(newSolution, existingSolution)) {
				return false;
			}
		}
		return true;

	}

	/*
	 * Evaluates a given solution and checks that expr1 (right hand side) is equal
	 * to expr2 (left hand side)
	 * 
	 * @param lettersList - list of all the letters used in the cryptarithm
	 * 
	 * @param assigmentsList - single permutation/list of assignments
	 * 
	 * @return true - if and only if expr1 (right hand side) is equal to expr2 (left
	 * hand side) for the given solution
	 */
	private boolean evaluateSingleExpression(List<String> lettersList, Integer[] assignmentsList) {
		int numLetters = lettersList.size();

		// Set the variables/letters within the expression to the numbers in the list.
		for (int index = 0; index < numLetters; index++) {
			String letter = lettersList.get(index);
			Integer value = assignmentsList[index];
			mapOfUsedLetters.get(letter).store(value);
		}

		double expression1 = exp1.eval();
		double expression2 = exp2.eval();

		return checkClose(expression1, expression2);
	}

	@Override
	/**
	 * Changes the cryptarithm's toString value to show what the cryptarithm
	 * expressions are
	 * 
	 * @return a String that shows the cryptarithm's words and operators that make
	 *         it up
	 */
	public String toString() {
		String name = "";
		for (String s : cryptarithm)
			name = name + s + " ";
		return name.substring(0, name.length() - 1);
	}

}
