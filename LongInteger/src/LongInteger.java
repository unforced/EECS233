/**
 * A class containing a LinkedList used to represent a positive integer of any length.
 * @author Aaron Neyer
 */

import java.util.*;
public class LongInteger {
	
	private List<Integer> digits = new LinkedList<Integer>();
	
	/**
	 * Creates a LongInteger from the string of numbers.
	 * @param s String converted into a list of digits.
	 */
	public LongInteger(String s) {
        if (s.length() > 1 && s.charAt(0) == '0') {
            digits.clear();
            digits.add(-1);
        }
        else {
        	//Iterates through every value in the string, adding each digit to the string, or making it NaN if it finds a non-digit.
            for (char c : s.toCharArray()) {
                if (Character.isDigit(c)) {
                    digits.add(Character.getNumericValue(c));
                }
                else {
                    digits.clear();
                    digits.add(-1);
                    break;
                }
            }
        }
    }

	/**s
	 * Gets the first value to determine if the LongInteger is NaN(Not a Number)
	 * @return boolean value representing if the object is NaN
	 */
    public boolean isNaN() {
        return digits.get(0) == -1;
    }

    /**
     * Iterates through the string, converting each object within to a character in a string.
     * @return A string containing the value of the LongInteger.
     * @throws IllegalStateException If the object is not a number.
     */
    public String toString() {
        if (isNaN()) {
            throw (new IllegalStateException("This is not a number"));
        }
        else {
            StringBuilder b = new StringBuilder();
            //Iterates through every integer within your LongInteger list.
            for (int x : digits) {
                b.append(x);
            }
            return b.toString();
        }
    }

    /**
     * Adds another LongInteger to the value of this LongInteger.
     * @param x The LongInteger to add.
     * @throws IllegalStateException If this object is not a number.
     * @throws IllegalArgumentException If the LongInteger to be added is not a number.
     */
    public void add(LongInteger x) {
        if (isNaN()) throw (new IllegalStateException("This is not a number"));
        if (x.isNaN()) throw (new IllegalArgumentException("X is not a number"));
        if (x == null || x.digits == null) throw (new NullPointerException("X does not exist")); //Receiving dead code warning here.  Not sure why.
        LongInteger tempNum = new LongInteger(""); //Stores the value of x and this added.
        LongInteger shortNum = this; //Represents the shorter of the two numbers.
        LongInteger longNum = x; //Represents the longer of the two numbers.
        if (digits.size() > x.digits.size()) {
            shortNum = x;
            longNum = this;
        }
        int difference = longNum.digits.size() - shortNum.digits.size(); //The difference between the two numbers.
        int carryOut = 0; //Stores whether there should be a carryOut when adding two digits.
        //Iterates through every digit shared by both numbers, adding them together along with a carryOut if it exists.
        for (int i = shortNum.digits.size()-1; i >= 0; i--) {
            int tempDigit = (shortNum.digits.get(i) + longNum.digits.get(i+difference) + carryOut);
            if (tempDigit > 9) {
                tempNum.digits.add(0, tempDigit%10);
                carryOut = 1;
            } else {
                tempNum.digits.add(0, tempDigit);
                carryOut = 0;
            }
        }
        //Iterates through  the remainder of the digits of the longer number, adding them to any carryOut.
        for (int i = difference-1; i >= 0; i--) {
            int tempDigit = (longNum.digits.get(i) + carryOut);
            if (tempDigit > 9) {
                tempNum.digits.add(0, tempDigit%10);
                carryOut = 1;
            } else {
                tempNum.digits.add(0, tempDigit);
                carryOut = 0;
            }
        }
        //Adds a 1 if there was a carryOut on the last digit.
        if (carryOut == 1) tempNum.digits.add(0, 1);
        //Clears the current values from your LongInteger, and adds those found in tempNum.
        digits.clear();
        digits.addAll(tempNum.digits);
    }

    /**
     * Tests if two LongIntegers are equal, and represent the same value.
     * @param obj The LongInteger to test for equality.
     * @return True if they are equal, false is they are not.
     */
    public boolean equals(Object obj) {
    	if (isNaN()) {
    		return false;
        } else if (obj instanceof Integer) {
        	return obj.equals(Integer.parseInt(toString()));
        } else if (obj instanceof LongInteger) {
        	if (((LongInteger) obj).isNaN()) {
        		return false;
        	}
        	return obj.toString().equals(toString());
        } else {
        	return false;
        }
    }

    /**
     * Adds together 5 LongIntegers and prints out the result.
     * @param args Arguments to be passed into the main function(Not currently used)
     */
    public static void main(String[] args) {
    LongInteger num1 = new LongInteger("0");
    LongInteger num2 = new LongInteger("42");
    LongInteger num3 = new LongInteger("9001");
    LongInteger num4 = new LongInteger("2147483647");
    LongInteger num5 = new LongInteger("9223372036854775807");
    num1.add(num2);
    num1.add(num3);
    num1.add(num4);
    num1.add(num5);
    System.out.println(num1);
    }
}
