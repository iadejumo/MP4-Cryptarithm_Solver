package ca.ubc.ece.cpen221.mp4.cryptarithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import ca.ubc.ece.cpen221.mp4.expression.VariableExpression;
import ca.ubc.ece.cpen221.mp4.permutation.Permutation;

public class SolveCryptarithm {

	static public void main(String[] args) {
		// TODO implement this main method
	}
	
	public List<Map<Character, Integer>> getPossibleSolutions(Set<String> allLetters, Set<String> firstLetters){
		List<Integer> allDigits = new ArrayList<Integer>(Arrays.asList(0,1,2,3,4,5,6,7,8,9));
		
		//Generate all permutations of the 10 digits
		Permutation<Integer> permutation = new Permutation<Integer> (allDigits);
		List<List<Integer>> allPermutations = permutation.getAllPermutations();
		
		List<String> lettersList = new ArrayList<String>();
		lettersList.addAll(allLetters);
		
		//Get index of letter that appear first in the expressions
		List<Integer> firstLetterIndices = new ArrayList<Integer>();
		for (String letter: firstLetters) {
			firstLetterIndices.add(lettersList.indexOf(letter));
		}
		
		//Filter out all solution where any letter that appears first is equal to zero.
		List<List<Integer>> nonZeroFirstPermutations;
		for (Integer indexOfFirst: firstLetterIndices) {
			nonZeroFirstPermutations =  allPermutations.parallelStream()
																 .filter(p -> p.get(indexOfFirst) != 0)
																 .collect(Collectors.toCollection(ArrayList::new));
		}
		
		return null;
	}
	/*
	private boolean evaluateSingleExpressions(Cryptarithm crypt, List<String> lettersList, List<Integer> assignmentsList) {
		int numLetters = lettersList.size();
		//Map<String, VariableExpression> copyOfMap = new HashMap<String, VariableExpression> ();
	
		
		for(int index = 0; index < numLetters; index++) {
			String letter = lettersList.get(index);
			Integer value = assignmentsList.get(index);
			crypt.mapOfUsedLetters.get(letter).store(value);
		}
		
		double expression1 = crypt.exp1.eval();
		double expression2 = crypt.exp2.eval();		
		
		return false;
	}
	*/
	
}
