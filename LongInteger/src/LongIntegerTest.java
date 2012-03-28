import static org.junit.Assert.*;

import org.junit.Test;


public class LongIntegerTest {

	//Tests toString methods by comparing the resultant string with the expected string, as well as testing for exceptions.
	@Test
	public void testToString() {  
		LongInteger num1 = new LongInteger("9001");
		assertTrue(num1.toString().equals("9001"));
		boolean errorThrown = false;
		try {
		LongInteger num2 = new LongInteger("0063");
		num2.toString();
		} catch (IllegalStateException e) {
			errorThrown = true;
		}
		assertTrue(errorThrown);
		LongInteger num3 = new LongInteger("0");
		assertTrue(num3.toString().equals("0"));
		errorThrown = false;
		try {
		LongInteger num4 = new LongInteger("IcanhazNumbers?");
		num4.toString();
		} catch (IllegalStateException e) {
			errorThrown = true;
		}
		assertTrue(errorThrown);
	}
	
	//Tests isNaN() by testing for certain values that should or should not result in NaN
	@Test
	public void testIsNan() {
		LongInteger num1 = new LongInteger("42");
		assertFalse(num1.isNaN());
		LongInteger num2 = new LongInteger("314159");
		assertFalse(num2.isNaN());
		LongInteger num3 = new LongInteger("999999999999999999999999999999");
		assertFalse(num3.isNaN());
		LongInteger num4 = new LongInteger("3.14159");
		assertTrue(num4.isNaN());
		LongInteger num5 = new LongInteger("ThisIsSparta!!!!!");
		assertTrue(num5.isNaN());
		LongInteger num6 = new LongInteger("000");
		assertTrue(num6.isNaN());
	}
	
	//Tests add() by adding various numbers and testing for equality with the expected value.
	@Test
	public void testAdd() {
		LongInteger num1 = new LongInteger("13");
		LongInteger num2 = new LongInteger("87");
		num1.add(num2);
		assertTrue(Integer.parseInt(num1.toString()) == 100);
		num1.add(new LongInteger("34123513285103251325018312589123570120351"));
		assertTrue(num1.toString().equals("34123513285103251325018312589123570120451"));
		boolean errorThrown = false;
		LongInteger num3 = new LongInteger("ImNaN");
		try {
			num1.add(num3);
		} catch (IllegalArgumentException e) {
			errorThrown = true;
		}
		assertTrue(errorThrown);
		errorThrown = false;
		try {
			num3.add(num1);
		} catch (IllegalStateException e) {
			errorThrown = true;
		}
		assertTrue(errorThrown);
		errorThrown = false;
		LongInteger num4 = null;
		try {
			num1.add(num4);
		} catch (NullPointerException e) {
			errorThrown = true;
		}
		assertTrue(errorThrown);
	}
	
	//Tests equals by testing various numbers that should or should not be equal.
	@Test
	public void testEquals() {
		LongInteger num1 = new LongInteger("WDF!!!!");
		LongInteger num2 = new LongInteger("Indubitably good sir");
		assertFalse(num1.equals(num2));
		LongInteger num3 = new LongInteger("99");
		assertFalse(num1.equals(num3));
		LongInteger num4 = new LongInteger("99");
		assertTrue(num3.equals(num4));
		assertTrue(num3.equals(99));
	}
	
	
	

}