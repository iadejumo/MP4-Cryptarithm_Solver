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
	Set<List<T>> permutations; 
	List<List<T>> allPermutations;

	// you may need more here
	public Permutation(Collection <T> c) {
		allElements = new ArrayList <T>();
		permutations = new HashSet<List<T>>();
		
		allElements.addAll(c);		
		generate(c.size(), allElements);
		
		allPermutations = new ArrayList<List<T>>();
		allPermutations.addAll(permutations);
	}
	
	@Override
	public T[] getOnePermutation() {
		// TODO implement this method
		
		Object[] t= new Object[(allPermutations.get(0).size())];
		t = allPermutations.get(0).toArray();
		
		return (T[])t;
	}
	
	public List<List<T>> getAllPermutations(){
		return allPermutations;
	}
	
	private void generate(int numberOfSelection, List<T> listOfOptions) {
		if (numberOfSelection == 0){
			 permutations.add(listOfOptions);
			 return;
		}
		
		else {
		     for(int i = 0; i < numberOfSelection - 1; i++) {
		            generate(numberOfSelection - 1, listOfOptions);
		            if (numberOfSelection % 2 != 0) {
		                listOfOptions = swap(listOfOptions, i, numberOfSelection - 1);
		            }
		            else {
		            	listOfOptions = swap(listOfOptions, 0, numberOfSelection - 1);
		            }
		            System.out.println("end: " + listOfOptions);
		     }
		}
		          
		generate(numberOfSelection - 1, listOfOptions);
		
	}
	
	private List<T> swap (List<T> list, int index1, int index2){
		List <T> editedList = new ArrayList<T>();
		
		for(T object: list) {
			editedList.add(object);
		}
		
		T element1 = list.get(index1);
		T element2 = list.get(index2);
		
		editedList.set(index1,  element2);
		editedList.set(index2,  element1);
		
		return editedList;
	}
	
}
