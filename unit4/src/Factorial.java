/*
Keisun Wu
13 November 2017
Methods Exercises 13
 */

public class Factorial {

	/**
	 * Find n!
	 *
	 * @param n integer to find of n!
	 * @return n!
	 */
	public static int factorial(int n) {
		int product = 1;
		for (int i = n; i > 0; i--)
			product *= i;
		return product;
	}

	public static void main(String[] args) {
		int answer = factorial(0);
		System.out.println(answer);
	}
}
