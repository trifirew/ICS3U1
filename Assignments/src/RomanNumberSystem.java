/*
Keisun Wu
27 November 2017

Assignment #4A - Fun with Arrays
Question #1

This program converts a list of roman numbers into standard numbers, and calculate the sum of the numbers.
 */

import java.io.*;
import java.util.Scanner;

public class RomanNumberSystem {

	private static final int[] NUMBERS = {1, 5, 10, 50, 100, 500, 1000};
	private static final String SYMBOLS = "IVXLCDM";

	/**
	 * Translate a roman number into a standard number using recursion.
	 *
	 * @param roman a roman number in String
	 * @return a standard number
	 */
	public static int toNumber(String roman) {
		if (roman.length() == 0)
			return 0;
		if (roman.length() == 1)
			return NUMBERS[SYMBOLS.indexOf(roman)];
		int digit = toNumber(roman.substring(0, 1));
		if (digit < toNumber(roman.substring(1, 2)))
			return -digit + toNumber(roman.substring(1));
		return digit + toNumber(roman.substring(1));
	}

	/**
	 * Translate a roman number into a standard number using for loop.
	 *
	 * @param roman a roman number in String
	 * @return a standard number
	 */
	public static int romanToStandard(String roman) {
		int n = 0;
		int len = roman.length();
		for (int i = 0; i < len; i++) {
			int index = SYMBOLS.indexOf(roman.charAt(i));
			if (i < len - 1 && SYMBOLS.indexOf(roman.charAt(i + 1)) > index)
				n -= NUMBERS[index];
			else
				n += NUMBERS[index];
		}
		return n;
	}

	public static void main(String[] args) throws IOException {
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Enter the file name: ");
		String filename = keyboard.nextLine();
		keyboard.close();

		// For testing
		if (filename.isEmpty())
			filename = "input/inputA4A1.txt";

		Scanner file = new Scanner(new File(filename));
		int times = Integer.parseInt(file.nextLine());
		String roman;
		int num;
		int sum = 0;
		for (int i = 0; i < times; i++) {
			roman = file.nextLine();
			num = romanToStandard(roman);
//			num = toNumber(roman);
			sum += num;
			System.out.printf("%s = %d%n", roman, num);
		}
		System.out.println("Total of all of the numbers is: " + sum);
		file.close();
	}
}
