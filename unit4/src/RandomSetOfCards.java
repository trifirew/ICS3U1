/*
Keisun Wu
23 November 2017
Methods and Arrays Exercise 4
 */
public class RandomSetOfCards {
	private static final String[] CARDS = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king", "ace"};
	private static final String[] SUITS= {"clubs", "diamonds", "hearts", "spades"};

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
	 * Print a random set of 5 different cards.
	 */
	private static void printCards() {
		int randomCard, randomSuit;
		int[] oldCard = new int[5], oldSuit = new int[5];
		for (int i = 0; i < 5; i++) {
			boolean contain;
			do {
				randomCard = rInt(CARDS.length);
				randomSuit = rInt(SUITS.length);
				contain = false;
				for (int j = 0; j < i; j++) {
					if (oldCard[j] == randomCard && oldSuit[j] == randomSuit) {
						contain = true;
						break;
					}
				}
			} while (contain);
			System.out.printf("the %s of the %s%n", CARDS[randomCard], SUITS[randomSuit]);
			oldCard[i] = randomCard;
			oldSuit[i] = randomSuit;
		}
	}

	public static void main(String[] args) {
		printCards();
	}
}
