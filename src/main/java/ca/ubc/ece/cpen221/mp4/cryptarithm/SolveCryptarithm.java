package ca.ubc.ece.cpen221.mp4.cryptarithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import ca.ubc.ece.cpen221.mp4.permutation.Permutation;

public class SolveCryptarithm {

	static public void main(String[] args) {
		// TODO implement this main method
	}
	
	public List<Map<Character, Integer>> getPossibleSolutions(List<String> letters){
		List<Integer> allDigits = new ArrayList<Integer>(Arrays.asList(0,1,2,3,4,5,6,7,8,9));
		List<List<Integer>> permutations;
		
		Permutation p = new Permutation(allDigits);
		permutations = p.getAllPermutations();
		
		
		
		return null;
	}
	
}
