package ca.ubc.ece.cpen221.mp4.permutation;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// add class overview

public class Permutation<T> implements AbstractPermutation<T> {
	List<T> allElements;  
	List<List<T>> permutationsList;
	public int swaps = 0;


	// you may need more here
	public Permutation(Collection <T> c) {
		allElements = new ArrayList <T>();
		permutationsList = new ArrayList<List<T>>();
		
		allElements.addAll(c);	
		generate(c.size(), allElements);

	}
	
	@Override
	public T[] getOnePermutation() {
		// yo Satish said you can remove this TODO after you're done
		// TODO implement this method
		
		Object[] t= new Object[(permutationsList.get(0).size())];
		t = permutationsList.get(0).toArray();
		
		return (T[])t;
	}
	
	public List<List<T>> getAllPermutations(){
		return permutationsList;
	}
	
	private void generate(int numberOfSelection, List<T> listOfOptions) {
		if (numberOfSelection == 1){
			 permutationsList.add(listOfOptions);
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
	
	private void swap (List<T> list, int index1, int index2){
		T element1 = list.get(index1);
		T element2 = list.get(index2);
		
		list.set(index1,  element2);
		list.set(index2,  element1);
		swaps++;
	}

	
}
