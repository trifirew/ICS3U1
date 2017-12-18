/*
Keisun Wu
13 November 2017
Methods Exercises 8
 */

import java.util.Scanner;

public class SumOfNumbers {

	/**
	 * Calculate the sum of integers between two integers inclusively.
	 *
	 * @param a,b the two integers
	 * @return the sum of the integers between a and b
	 */
	public static int sumOfNumbersBetween(int a, int b) {
		int sum = 0;
		for (int i = Math.min(a, b); i <= Math.max(a, b); i++)
			sum += i;
		return sum;
	}

	public static void main(String[] args) {
		int a, b;
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the first number: ");
		a = scanner.nextInt();
		System.out.print("Enter the second number: ");
		b = scanner.nextInt();
		System.out.println(sumOfNumbersBetween(a, b));
	}
}
