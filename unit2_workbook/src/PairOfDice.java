
public class PairOfDice {

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			int dice1 = (int) (Math.random() * 6) + 1;
			int dice2 = (int) (Math.random() * 6) + 1;
			if (dice1 + dice2 == 2) {
				System.out.println("snake eyes!");
			} else if (dice1 + dice2 == 7) {
				System.out.println("lucky seven");
			}
		}

	}

}
