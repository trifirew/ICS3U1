/*
Keisun Wu
17 November 2017
Methods Exercises 14
 */

public class HCF {

	/**
	 * Find the highest common factor of two integers
	 *
	 * @param a,b two integers
	 * @return the highest common factor
	 */
	public static int highestCommonFactor(int a, int b) {
		int hcf = 1;
		for (int i = 1; i < Math.min(a, b); i++) {
			if (a % i == 0 && b % i == 0)
				hcf = i;
		}
		return hcf;
	}

	public static void main(String[] args) {
		int a = 100;
		int b = 50;
		System.out.println(highestCommonFactor(a, b));
	}
}
