package ca.ubc.ece.cpen221.mp4.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import ca.ubc.ece.cpen221.mp4.permutation.Permutation;

public class PermutationsTest<T> {
	
	@Test
	public void test1() {
		String[] list = {"r", "g", "b"};
		
		
		Permutation<String> p = new Permutation<String>(list);
		
		List<String[]> permutations = p.getAllPermutations();
		
		int count = 0;
		for(Object s: p.getOnePermutation()) {
			count++;
		}
		assertEquals(3, count);
	
		assertEquals(6, p.getAllPermutations().size());
		assertFalse(permutations.get(0).equals(permutations.get(1)));
	}
	
	@Test
	public void test2() {
		Integer[] allDigits = {0,1,2,3,4,5,6,7,8,9};
		List<Integer[]> permutations;
		
		Permutation<Integer> p = new Permutation<Integer>(allDigits);
		permutations = p.getAllPermutations();
		
		assertEquals(3628800, permutations.size());
	}
	@Test
	public void test3() {
		Integer[] digits = {0,1,2,3};
		List<Integer[]> permutations;
		
		Permutation<Integer> p = new Permutation<Integer>(digits);
		permutations = p.getAllPermutations();
		
		assertEquals(23, p.getNumberOfSwaps());
				
		assertEquals(24, permutations.size());
	}
	
	@Test
	public void test4() {
		Integer[] digits = {0};
		Integer[] permutations1;
		Integer[] permutations2;
		
		Permutation<Integer> p = new Permutation<Integer>(digits);
		permutations1 = p.getOnePermutation();
		permutations2 = p.getOnePermutation();
		
		
		assertEquals(permutations1[0], permutations2[0]);

	}

}
