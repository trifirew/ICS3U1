/*
  2.4.4 Write a program that lets the computer generate a secret number (an integer
  between 1 and 100, for your friend to guess. If they guess too high, print: "too high,
  guess again." If they guess too low, print: "too low, guess again" If they guess the
  number, let the program stop and print "congratulations"
 */

import java.util.Scanner;

public class GuessNumber {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int num = (int) (Math.random() * 100) + 1;
		int guess;
		int count = 0;
		do {
			System.out.print("Guess a number: ");
			guess = scanner.nextInt();
			count += 1;
			if (guess > num) {
				System.out.println("too high, guess again.");
			} else if (guess < num) {
				System.out.println("too low, guess again.");
			}
		} while (guess != num);
		System.out.println("congratulations, it takes you " + count + " times");
		scanner.close();
	}

}
