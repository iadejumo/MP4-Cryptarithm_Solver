package ca.ubc.ece.cpen221.mp4.permutation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

// add class overview

public class Permutation<T> implements AbstractPermutation<T> {
	// @ invariant swaps == permutationsList.size() - 1
	
	//private final List<T> allElements; //Contains all the elements are to be arranged
	private final List<T[]> permutationsList; //Contains a list of all possible arrangements
	private int swaps = 0; //Keeps track of how many swaps are made to generate permutations 

	/* 
	 * Initializes Permutations class by generating a list of all possible permutations 
	 */
	public Permutation(T[] a) {
		//allElements = new ArrayList <T>();
		permutationsList = new ArrayList<T[]>();
		
		if(a.length != 0) {
			//allElements.addAll(a);	
			generate(a.length, a);
		}
		

	}
	
	/*
	 * @return a single permutation/arrangement of the elements
	 * 
	 * (non-Javadoc)
	 * @see ca.ubc.ece.cpen221.mp4.permutation.AbstractPermutation#getOnePermutation()
	 */
	@Override
	public T[] getOnePermutation() {
		// TODO implement this method
		
		return permutationsList.get(0);
	}
	
	/*
	 * Gets all possible permutation.
	 * 
	 * @returns returns all possible permutations/arrangements of the elements
	 */
	public List<T[]> getAllPermutations(){
		return Collections.unmodifiableList(permutationsList);
	}
	
	/*
	 * Uses Heaps Algorithm (recursively) to generate all possible permutations.
	 * 
	 * @param numberOfSelection 
	 * 				- the number of elements that can be switched around to generate new arrangements
	 * 				- is greater than or equal to 1
	 * @param listOfOptions
	 * 				- is not null and is not empty
	 * 				- all the elements that need to be arranged
	 * 
	 */
	private void generate(int numberOfSelection, T[] listOfOptions) {
		if (numberOfSelection == 1) {
			T[] temp = listOfOptions.clone();
			permutationsList.add(temp);	
			return;
		}
		
		else {
		     for(int i = 0; i < numberOfSelection - 1; i++) {
		    	 
					generate(numberOfSelection - 1, listOfOptions);
					
					if (numberOfSelection % 2 == 0) {
					    swap(listOfOptions, i, numberOfSelection - 1);
					}
					else {
						swap(listOfOptions, 0, numberOfSelection - 1);
					}
		     }
		     generate(numberOfSelection - 1, listOfOptions);
		}
		          
		
		
	}
	
	/*
	 * Modifies the list by swapping the elements with the given indices
	 * 
	 * @param list.size() > index1 && list.size > index2
	 */
	private void swap (T[] list, int index1, int index2){
		T element1 = list[index1];
		
		list[index1] = list[index2];
		list[index2] = element1;
		swaps++;
		
	}
	
	/*
	 * Gets the number of swaps that occurred to create permutations
	 * 
	 * @return number of swaps
	 */
	public int getNumberOfSwaps() {
		return swaps;
	}
	
}
