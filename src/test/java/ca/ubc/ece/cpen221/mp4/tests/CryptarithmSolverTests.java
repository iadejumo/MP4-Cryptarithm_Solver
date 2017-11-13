package ca.ubc.ece.cpen221.mp4.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import ca.ubc.ece.cpen221.mp4.cryptarithm.Cryptarithm;
import ca.ubc.ece.cpen221.mp4.cryptarithm.NoSolutionException;

public class CryptarithmSolverTests {

	@Test
	public void Test1() throws NoSolutionException {
		String[] c = {"SEND","+","MORE","=","MONEY"};
		Cryptarithm crypt = new Cryptarithm(c);
		List<Map<Character,Integer>> solutions = new ArrayList<Map<Character,Integer>>();
		solutions = crypt.solve();
		
		assertEquals(1, solutions.size());
		System.out.println(solutions);
	}
	
	@Test
	public void Test2() throws NoSolutionException {
		String[] c = {"WINTER","+","IS","+","WINDIER","+","SUMMER","+","IS","=","SUNNIER"};
		Cryptarithm crypt = new Cryptarithm(c);
		List<Map<Character,Integer>> solutions = new ArrayList<Map<Character,Integer>>();
		solutions = crypt.solve();
		
		assertEquals(1, solutions.size());
		System.out.println(solutions);
	}
	/*
	@Test
	public void Test3() throws NoSolutionException {
		String[] c = {"NORTH","/","SOUTH","=","EAST","/","WEST"};
		Cryptarithm crypt = new Cryptarithm(c);
		List<Map<Character,Integer>> solutions = new ArrayList<Map<Character,Integer>>();
		solutions = crypt.solve();
		
		assertEquals(1, solutions.size());
		System.out.println(solutions);
	}
	*/
	@Test
	public void Test4() throws NoSolutionException {
		String[] c = {"JEDER","+","LIEBT","=","BERLIN"};
		Cryptarithm crypt = new Cryptarithm(c);
		List<Map<Character,Integer>> solutions = new ArrayList<Map<Character,Integer>>();
		solutions = crypt.solve();
		
		assertEquals(2, solutions.size());
		System.out.println(solutions);
	}
	
	@Test (expected = Exception.class)
	public void Test5() throws NoSolutionException {
		String[] c = {"I","+","CANT","+","GET","=","NO","+","SATISFACTION"};
		Cryptarithm crypt = new Cryptarithm(c);
		List<Map<Character,Integer>> solutions = new ArrayList<Map<Character,Integer>>();
		solutions = crypt.solve();
		
		assertEquals(0, solutions.size());
		System.out.println(solutions);
	}
}
