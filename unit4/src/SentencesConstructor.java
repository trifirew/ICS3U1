/*
Keisun Wu
23 November 2017
Methods and Arrays Exercise 3
 */
import java.util.Scanner;

public class SentencesConstructor {
	private static final String[] NOUNS = {"cow", "moon", "cat", "dog", "pig", "boy", "girl", "car", "horse", "monkey"};
	private static final String[] VERBS = {"jumped", "ran", "shouted", "drove", "smiled", "walked", "slept", "ate", "played", "wrote"};
	private static final String[] PREPS = {"over", "under", "around", "below", "by", "with", "to", "in", "into", "outside"};

	/**
	 * Make a random integer from 0(inclusive) to end(exclusive).
	 *
	 * @param end upper bound of the random integer, exclusive
	 * @return a random integer
	 */
	private static int rInt(int end) {
		return (int) (Math.random() * end);
	}

	/**
	 * Construct and print a random sentence.
	 */
	private static void printSentence() {
		System.out.printf("the %s %s %s the %s%n", NOUNS[rInt(10)], VERBS[rInt(10)], PREPS[rInt(10)], NOUNS[rInt(10)]);
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String s;
		do {
			printSentence();
			do {
				System.out.print("continue? (y/n) ");
				s = scanner.nextLine();
			} while (!s.equalsIgnoreCase("y") && !s.equalsIgnoreCase("n"));
		} while (s.equalsIgnoreCase("y"));
		System.out.println("the end!");
	}
}