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
		String[] list = {"r", "g", "b"};
		
		
		Permutation<String> p = new Permutation<String>(list);
		
		List<String[]> permutations = p.getAllPermutations();
		
		
		for(String[] a: permutations) {
			for (String s: a) {
				System.out.print(s);
			}
		}
		
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
		Integer[] allDigits = {0,1,2,3,4,5,6,7,8,9};
		List<Integer[]> permutations;
		
		Permutation<Integer> p = new Permutation<Integer>(allDigits);
		permutations = p.getAllPermutations();
		
		//System.out.println(permutations);
		
		assertEquals(3628800, permutations.size());
	}
	@Test
	public void Test3() {
		Integer[] digits = {0,1,2,3};
		List<Integer[]> permutations;
		
		Permutation<Integer> p = new Permutation<Integer>(digits);
		permutations = p.getAllPermutations();
		
		assertEquals(23, p.getNumberOfSwaps());
				
		assertEquals(24, permutations.size());
		System.out.println(permutations);
	}
	/*
	@Test
	public void Test4() {
		List<Integer> digits = new ArrayList<Integer>();
		List<List<Integer>> permutations;
		
		Permutation p = new Permutation(digits);
		permutations = p.getAllPermutations();
	}
*/
}
