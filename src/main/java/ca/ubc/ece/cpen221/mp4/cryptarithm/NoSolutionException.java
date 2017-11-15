package ca.ubc.ece.cpen221.mp4.cryptarithm;

public class NoSolutionException extends Exception {
	// You can customize your exception class
	// if you want
	public NoSolutionException(){
        System.out.println("No solution for the given Cryptarithm");
    } 
}
