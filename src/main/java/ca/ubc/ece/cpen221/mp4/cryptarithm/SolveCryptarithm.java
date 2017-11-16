package ca.ubc.ece.cpen221.mp4.cryptarithm;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import ca.ubc.ece.cpen221.mp4.cryptarithm.Cryptarithm;
import ca.ubc.ece.cpen221.mp4.cryptarithm.NoSolutionException;

public class SolveCryptarithm {

	static public void main(String[] args) throws NoSolutionException {
		Scanner scanner = new Scanner(System.in);
		
		while (true) {
			//prompt user for input
			System.out.println("Enter an cryptarithm:");
			String inputString = scanner.nextLine();
			
			try {
				// create Cryptarithm from the input String
				String[] splitString  = inputString.split(" ");
				
				Cryptarithm c = new Cryptarithm(splitString);
				List<Map<Character,Integer>> solutions = c.solve();
				int numSolutions = solutions.size();
				
				// print number of solutions and the solutions
				System.out.println(numSolutions + " solution(s):");
				for (Map<Character,Integer> map: solutions) {
					System.out.println(map);
				}
				
			} catch (Exception e) {
				// if exception thrown
				System.out.println("Bad input or no solution. Exiting.");
				scanner.close();
			} 
		}

			
	}
	
}
