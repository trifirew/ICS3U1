/*
Keisun Wu
23 November 2017
Methods and Arrays Exercise 1, 2
 */
import java.util.Scanner;

public class NumberToText {
	private static final String[] WORDS = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten"};

	/**
	 * Translate the number to spoken form from right to left. (Exercise 1)
	 *
	 * @param n the number to translate
	 * @return the spoken form of the number from right to left
	 */
	private static String numberToTextRight(int n) {
		if (n / 10 == 0)
			return WORDS[n];
		return WORDS[n % 10] + " " + numberToTextRight(n / 10);
	}

	/**
	 * Translate the number to spoken form from left to right. (Exercise 2)
	 *
	 * @param n the number to translate
	 * @return the spoken form of the number from left to right
	 */
	private static String numberToText(int n) {
		if (n / 10 == 0)
			return WORDS[n];
		return numberToText(n / 10) + " " + WORDS[n % 10];
	}

	public static void main(String[] args) {
		int n;
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter a number: ");
		n = scanner.nextInt();
		System.out.println(numberToTextRight(n));
		System.out.println(numberToText(n));
	}
}
