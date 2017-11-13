package ca.ubc.ece.cpen221.mp4.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import ca.ubc.ece.cpen221.mp4.permutation.Permutation;

public class PermutationsTest<T> {
	
	@Test
	public void Test1() {
		List<String> list = new ArrayList<String>();
		list = Arrays.asList("r", "g", "b");
		
		Permutation<String> p = new Permutation<String>(list);
		
		List<List<String>> permutations = p.getAllPermutations();
		
		int count = 0;
		for(Object s: p.getOnePermutation()) {
			count++;
		}
		assertEquals(3, count);
	
		assertEquals(6, p.getAllPermutations().size());
		assertFalse(permutations.get(0).equals(permutations.get(1)));
	}
	
	@Test
	public void Test2() {
		List<Integer> allDigits = new ArrayList<Integer>(Arrays.asList(0,1,2,3,4,5,6,7,8,9));
		List<List<Integer>> permutations;
		
		Permutation p = new Permutation(allDigits);
		permutations = p.getAllPermutations();
		
		//System.out.println(permutations);
		
		assertEquals(3628800, permutations.size());
	}
	@Test
	public void Test3() {
		List<Integer> digits = new ArrayList<Integer>(Arrays.asList(0,1,2,3));
		List<List<Integer>> permutations;
		
		Permutation p = new Permutation(digits);
		permutations = p.getAllPermutations();
		
		assertEquals(23, p.getNumberOfSwaps());
				
		assertEquals(24, permutations.size());
		System.out.println(permutations);
	}
	
	@Test
	public void Test4() {
		List<Integer> digits = new ArrayList<Integer>();
		List<List<Integer>> permutations;
		
		Permutation p = new Permutation(digits);
		permutations = p.getAllPermutations();
	}

}
