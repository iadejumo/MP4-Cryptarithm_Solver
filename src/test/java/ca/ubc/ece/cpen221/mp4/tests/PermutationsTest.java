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
		String r = "r";
		String g = "g";
		String b = "b";
		list = Arrays.asList(r, g, b);
		Permutation<String> p = new Permutation<String>(list);
		System.out.println(p.getAllPermutations());
		for(Object s: p.getOnePermutation()) {
			System.out.println(s + ", ");
		}
	
		assertEquals(6, p.getAllPermutations().size());
	}
	
	@Test
	public void Test2() {
		List<Integer> allDigits = new ArrayList<Integer>(Arrays.asList(0,1,2,3,4,5,6,7,8,9));
		List<List<Integer>> permutations;
		
		Permutation p = new Permutation(allDigits);
		permutations = p.getAllPermutations();
		
		assertEquals(3628800, permutations.size());
	}
	@Test
	public void Test3() {
		List<Integer> digits = new ArrayList<Integer>(Arrays.asList(0,1,2,3));
		List<List<Integer>> permutations;
		
		Permutation p = new Permutation(digits);
		permutations = p.getAllPermutations();
		
		assertEquals(23, p.swaps);
				
		assertEquals(24, permutations.size());
		
	}

}
