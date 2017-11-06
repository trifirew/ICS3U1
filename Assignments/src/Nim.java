import java.util.Scanner;

/**
 * <h1>Assignment #2 - The Game of Nim</h1>
 * <p>
 * There are two players in the game. Each turn a player removes one or more matches from one of the three piles. The
 * player who is forced to take the last match loses.
 *
 * @author Keisun Wu on October 12, 2017
 */

public class Nim {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String player1, player2;
		int pileA, pileB, pileC;
		char again;
		boolean choiceValid;
		// Bonus variables
		int player1Win = 0;
		int player2Win = 0;
		int mode;

		// Enter player names
		System.out.print("Player 1:  Enter your name:  ");
		player1 = scanner.nextLine();
		System.out.print("Player 2:  Enter your name:  ");
		player2 = scanner.nextLine();

		// Bonus: Show matches left using star sign
		System.out.println("\nIn which way do you want the matches left to be shown?");
		System.out.println("1: Numbers\t2: Horizontal star signs\t3: Vertical star signs");
		System.out.print("Enter your choice:  ");
		mode = Integer.parseInt(scanner.nextLine());
		while (mode < 1 || mode > 3) {
			System.out.print("Invalid option. Please enter 1, 2, or 3.\nEnter your choice:  ");
			mode = Integer.parseInt(scanner.nextLine());
		}

		do {
			// Set initial value when the game starts
			pileA = 3;
			pileB = 4;
			pileC = 5;
			int turn = 0;
			String player;
			char pile;
			int matchToRemove;

			// Keep looping when no one wins yet
			while (pileA + pileB + pileC > 1) {
				showMatchesLeft(mode, pileA, pileB, pileC);

				// Determine which player is in this turn: Odd number for player 1, Even number for player 2
				turn += 1;
				if (turn % 2 == 1) {
					player = player1;
				} else {
					player = player2;
				}

				// Choose a pile
				choiceValid = false;
				do {
					System.out.print(player + ", choose a pile:  ");
					pile = Character.toUpperCase(scanner.nextLine().charAt(0));
					if (pile < 'A' || pile > 'C') {
						System.out.println("Invalid choice, please enter A, B, or C.");
					} else if ((pile == 'A' && pileA == 0) || (pile == 'B' && pileB == 0)
							|| (pile == 'C' && pileC == 0)) {
						System.out.println("There is no match left in pile " + pile + ".");
					} else {
						choiceValid = true;
					}
				} while (!choiceValid);

				// Remove some matches from chosen pile
				choiceValid = false;
				do {
					System.out.print("How many to remove from pile " + pile + ":  ");
					matchToRemove = Integer.parseInt(scanner.nextLine());

					// Check if the number is valid
					if (matchToRemove <= 0) {
						System.out.println("Invalid number of matches, please enter a number greater than 0.");
					} else if (pile == 'A') {
						if (matchToRemove <= pileA) {
							pileA -= matchToRemove;
							choiceValid = true;
						} else {
							System.out.println(
									"Invalid number of matches, please enter a number between 0 and " + pileA + ".");
						}
					} else if (pile == 'B') {
						if (matchToRemove <= pileB) {
							pileB -= matchToRemove;
							choiceValid = true;
						} else {
							System.out.println(
									"Invalid number of matches, please enter a number between 0 and " + pileB + ".");
						}
					} else if (pile == 'C') {
						if (matchToRemove <= pileC) {
							pileC -= matchToRemove;
							choiceValid = true;
						} else {
							System.out.println(
									"Invalid number of matches, please enter a number between 0 and " + pileC + ".");
						}
					}
				} while (!choiceValid);
			}

			showMatchesLeft(mode, pileA, pileB, pileC);
			// Display the winner
			if (pileA + pileB + pileC == 0) {
				// If there is no match left, next player win. If there is one match left, current player win
				turn += 1;
			}
			if (turn % 2 == 1) {
				System.out.println(player1 + " won!!");
				player1Win += 1;
			} else {
				System.out.println(player2 + " won!!");
				player2Win += 1;
			}
			// Bonus: Display scores
			System.out.println("\nScores");
			System.out.println(player1 + ": " + player1Win);
			System.out.println(player2 + ": " + player2Win);

			// Restart or not
			System.out.print("\nPlay again?  (Enter Y/N):  ");
			again = Character.toUpperCase(scanner.nextLine().charAt(0));
			while (again != 'Y' && again != 'N') {
				System.out.print("Invalid option, please enter Y or N.\nPlay again?  (Enter Y/N):  ");
				again = Character.toUpperCase(scanner.nextLine().charAt(0));
			}
		} while (again == 'Y');

		System.out.println("\nThank you for playing!");
		scanner.close();
	}

	/**
	 * Display matches left in each pile in different ways
	 *
	 * @param mode  mode of display. 1: Numbers, 2: Horizontal stars, 3: Vertical stars
	 * @param pileA matches left in pile A
	 * @param pileB matches left in pile B
	 * @param pileC matches left in pile C
	 */
	private static void showMatchesLeft(int mode, int pileA, int pileB, int pileC) {
		System.out.println();
		if (mode == 1) {
			System.out.printf("A: %1d\tB: %1d\tC: %1d%n", pileA, pileB, pileC);
		} else if (mode == 2) {
			System.out.print("A: ");
			for (int i = 0; i < pileA; i++)
				System.out.print("*");
			System.out.print("\nB: ");
			for (int i = 0; i < pileB; i++)
				System.out.print("*");
			System.out.print("\nC: ");
			for (int i = 0; i < pileC; i++)
				System.out.print("*");
			System.out.println();
		} else if (mode == 3) {
			System.out.println("A\tB\tC");
			// When there are still matches not displayed, keep looping
			while (pileA > 0 || pileB > 0 || pileC > 0) {
				if (pileA > 0) {
					System.out.print("*");
					pileA--;
				}
				System.out.print("\t");
				if (pileB > 0) {
					System.out.print("*");
					pileB--;
				}
				System.out.print("\t");
				if (pileC > 0) {
					System.out.print("*");
					pileC--;
				}
				System.out.println();
			}
		}
		System.out.println();
	}
}
