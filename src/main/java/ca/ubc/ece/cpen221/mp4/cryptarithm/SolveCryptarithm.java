package ca.ubc.ece.cpen221.mp4.cryptarithm;

import java.util.List;
import java.util.Map;
import ca.ubc.ece.cpen221.mp4.cryptarithm.Cryptarithm;
import ca.ubc.ece.cpen221.mp4.cryptarithm.NoSolutionException;

public class SolveCryptarithm {

	static public void main(String[] args) throws NoSolutionException {
		
		Cryptarithm c = new Cryptarithm(args);
		List<Map<Character,Integer>> solutions = c.solve();
		int numSolutions = solutions.size();
		
		System.out.println(numSolutions + " solution(s):");
		for (Map<Character,Integer> map: solutions) {
			System.out.println(map);
		}
		
	}
	
}
