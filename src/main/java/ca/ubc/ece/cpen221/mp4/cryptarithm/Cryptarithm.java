package ca.ubc.ece.cpen221.mp4.cryptarithm;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.text.html.HTMLDocument.Iterator;

import ca.ubc.ece.cpen221.mp4.expression.Expression;

/**
 * Cryptarithm - a datatype that represents a cryptarithm
 *
 */
public class Cryptarithm {

	
	
	/**
	 * Cryptarithm constructor
	 * 
	 * @param cryptarithm
	 *            where each element is a String that represents part of the
	 *            cryptarithm
	 */
	public Cryptarithm(String[] cryptarithm) throws IllegalArgumentException{
		
	} //need to change precondition or post for wrong format parameter

	// differentiate capitals?
	/**
	 * Find solutions to the cryptarithm
	 * 
	 * @return a list of all possible solutions to the given cryptarithm. A
	 *         solution is a map that provides the value for each alphabet in
	 *         the cryptarithm.
	 */
	public List<Map<Character, Integer>> solve() throws NoSolutionException {
		// TODO implement this method
		return null; // change this
	}

	// You will need more methods
}
