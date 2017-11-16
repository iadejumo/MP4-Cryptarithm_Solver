package ca.ubc.ece.cpen221.mp4.cryptarithm;

public class NoSolutionException extends Exception {

	//RI = None
	//AF = None, no fields
	
	public NoSolutionException(){
        System.out.println("No solution for the given Cryptarithm");
    } 
}
