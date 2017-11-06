import java.util.Scanner;

public class PrintWord {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a word: ");
		String word = input.nextLine();
		int len = word.length();
		System.out.println("");

		// Combined
		for (int i = 0; i < len; i++) {
			// 2.2.13
			System.out.printf("%-" + (len + 4) + "s", word.substring(0, len - i));
			// 2.2.14
			System.out.printf("%-" + (len + 2) + "s", word.substring(i));
			// 2.2.15
			System.out.printf("%" + (len + 2) + "s", word.substring(i));
			// 2.2.16
			System.out.printf("%" + (i + 4) + "s", "");
			for (int j = 0; j < len - i; j++) {
				System.out.print(word.substring(j, j + 1));
				if (j < len - i - 1)
					System.out.print("-");
			}
			System.out.printf("%" + (i + 2) + "s", "");
			// 2.2.17
			System.out.printf("%" + (i + 2) + "s", "");
			for (int j = len - i - 1; j >= 0; j--) {
				System.out.print(word.charAt(j));
			}
			System.out.print(word.substring(0, len - i));
			// Line breaker
			System.out.println();
		}

	}
}
