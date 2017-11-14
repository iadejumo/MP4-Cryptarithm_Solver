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

	static public void main(String[] args) throws NoSolutionException {
		// TODO implement this main method
		//String[] args1 = {"SEND", "+", "MORE", "=", "MONEY"};
		
		Cryptarithm c = new Cryptarithm(args);
		List<Map<Character,Integer>> solutions = c.solve();
		int numSolutions = solutions.size();
		
		System.out.println(numSolutions + " solution(s):");
		for (Map<Character,Integer> map: solutions) {
			System.out.println(map);
		}
		
	}
	
}
