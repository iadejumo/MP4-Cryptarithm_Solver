package ca.ubc.ece.cpen221.mp4.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.rules.ExpectedException;

import ca.ubc.ece.cpen221.mp4.cryptarithm.Cryptarithm;

public class CryptarithmRepresentationTest {

	@Test (expected = IllegalArgumentException.class)
	public void incorrectLengthInputTest() {
		String[] c = {"SEND","+","QREWRIOPVSANDKNDZXNDFMNDSKLGJ","=","MONEY"};
		Cryptarithm crypt = new Cryptarithm(c);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void missingEqualsInputTest() {
		String[] c = {"SEND","+","QRLGJ","+","MONEY"};
		Cryptarithm crypt = new Cryptarithm(c);
	}
	
	
	@Test (expected = IllegalArgumentException.class)
	public void nullInputTest() {
		String[] c = null;
		Cryptarithm crypt = new Cryptarithm(c);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void emptyInputTest() {
		String[] c = new String[1];
		Cryptarithm crypt = new Cryptarithm(c);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void invalidStringValuesInputTest() {
		String[] c = {"SEN0343D","+","QREWRIOPVS^ANDKNDZXNDFMNDSKLGJ","=","MONEY"};
		Cryptarithm crypt = new Cryptarithm(c);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void missingOperatorStringsInputTest() {
		String[] c = {"SEND","POP","+","QREWRIOPVSANDKNDZXNDFMNDSKLGJ","=","MONEY"};
		Cryptarithm crypt = new Cryptarithm(c);
	}
	
	
	
	
}
