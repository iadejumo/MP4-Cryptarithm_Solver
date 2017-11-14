package ca.ubc.ece.cpen221.mp4.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import ca.ubc.ece.cpen221.mp4.cryptarithm.Cryptarithm;

public class CryptarithmRepresentationTest {

	@Test(expected = IllegalArgumentException.class)
	public void incorrectLengthInputTest() {
		String[] c = { "SEND", "+", "QREWRIOPVSANDKNDZXNDFMNDSKLGJ", "=", "MONEY" };
		Cryptarithm crypt = new Cryptarithm(c);
		assertFalse(crypt.toString().equals("SEND + MORE = MONEY"));
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void missingEqualsInputTest() {
		String[] c = { "SEND", "+", "QRLGJ", "+", "MONEY" };
		Cryptarithm crypt = new Cryptarithm(c);
		assertFalse(crypt.toString().equals("SEND + MORE = MONEY"));
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void nullInputTest() {
		String[] c = null;
		Cryptarithm crypt = new Cryptarithm(c);
		assertFalse(crypt.toString().equals("SEND + MORE = MONEY"));
		fail();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nullInsideInputTest() {
		String[] c = {null,null,null,null};
		Cryptarithm crypt = new Cryptarithm(c);
		assertFalse(crypt.toString().equals("SEND + MORE = MONEY"));
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void emptyInputTest() {
		String[] c = new String[1];
		Cryptarithm crypt = new Cryptarithm(c);
		assertFalse(crypt.toString().equals("SEND + MORE = MONEY"));
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void invalidStringValuesInputTest() {
		String[] c = { "SEN0343D", "+", "QREWRIOPVS^ANDKNDZXNDFMNDSKLGJ", "=", "MONEY" };
		Cryptarithm crypt = new Cryptarithm(c);
		assertFalse(crypt.toString().equals("SEND + MORE = MONEY"));
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void missingOperatorStringsInputTest() {
		String[] c = { "SEND", "POP", "+", "QREWRIOPVSANDKNDZXNDFMNDSKLGJ", "=", "MONEY" };
		Cryptarithm crypt = new Cryptarithm(c);
		assertFalse(crypt.toString().equals("SEND + MORE = MONEY"));
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void hasInvalidStringTypesTest() {
		String[] c = { "SEND", ",", "+", "QREWRIOPVSANDKNDZXNDFMNDSKLGJ", "=", "MONEY" };
		Cryptarithm crypt = new Cryptarithm(c);
		assertFalse(crypt.toString().equals("SEND + MORE = MONEY"));
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void tooShortInputTest() {
		String[] c = { "SEND", "MONEY" };
		Cryptarithm crypt = new Cryptarithm(c);
		assertFalse(crypt.toString().equals("SEND + MORE = MONEY"));
		fail();
	}

	@Test
	public void whiteSpacesInStringTest() {
		String[] c = { "SEND", "+", " MORE", "=", "MONEY" };
		Cryptarithm crypt = new Cryptarithm(c);
		assertTrue(crypt.toString().equals("SEND + MORE = MONEY"));
	}

	@Test
	public void lowercasesStringTest() {
		String[] c = { "SEND", "+", "moRe", "=", "MONEY" };
		Cryptarithm crypt = new Cryptarithm(c);
		assertTrue(crypt.toString().equals("SEND + MORE = MONEY"));
	}

	@Test
	public void whiteSpacesBetweenStringTest() {
		String[] c = { "SEND", "+", " ", " More", "=", " ", "MONEY" };
		Cryptarithm crypt = new Cryptarithm(c);
		assertTrue(crypt.toString().equals("SEND + MORE = MONEY"));
	}

	@Test
	public void emptyStringsBetweenStringTest() {
		String[] c = { "SEND", "", "+", "", " More", "=", " ", "MONEY" };
		Cryptarithm crypt = new Cryptarithm(c);
		assertTrue(crypt.toString().equals("SEND + MORE = MONEY"));
	}

	@Test
	public void toStringTest() {
		String[] c = { "SEND", "", "+", "", " More", "=", " ", "MONEY" };
		Cryptarithm crypt = new Cryptarithm(c);
		assertTrue(crypt.toString().equals("SEND + MORE = MONEY"));
	}

}
