package ca.ubc.ece.cpen221.mp4.cryptarithm;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import ca.ubc.ece.cpen221.mp4.cryptarithm.Cryptarithm;
import ca.ubc.ece.cpen221.mp4.cryptarithm.NoSolutionException;
import ca.ubc.ece.cpen221.mp4.expression.Expression;
import ca.ubc.ece.cpen221.mp4.operator.ListOfSupportedOperators;
import ca.ubc.ece.cpen221.mp4.operator.Operator;
import ca.ubc.ece.cpen221.mp4.parser.ExpressionMaker;
import ca.ubc.ece.cpen221.mp4.parser.ExpressionParser;

public class SolveCryptarithm {

	static public void main(String[] args) throws NoSolutionException {
		
		Set<Operator> operatorSet = new HashSet<Operator>(ListOfSupportedOperators.getList());

		//ExpressionParser parser = new ExpressionParser(operatorSet, new ExpressionMaker());

		Scanner scanner = new Scanner(System.in);
		do {
			System.out.println("Enter an crytarithm:");
			String inputString = scanner.nextLine();
			try {
				String[] splitString  = inputString.split(" ");
				
				Cryptarithm c = new Cryptarithm(splitString);
				List<Map<Character,Integer>> solutions = c.solve();
				int numSolutions = solutions.size();
				
				System.out.println(numSolutions + " solution(s):");
				for (Map<Character,Integer> map: solutions) {
					System.out.println(map);
				}
				
			} catch (Exception e) {
				System.out.println("Input format not accepted. Please try again.");
				scanner.close();
			} 
		} while (true);

	}
	
}
