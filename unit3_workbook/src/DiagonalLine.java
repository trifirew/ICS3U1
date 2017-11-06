import java.util.Scanner;

public class DiagonalLine {
	public static void main(String[] args) {
		int n;
		Scanner input = new Scanner(System.in);

		System.out.print("Enter a number between 1 and 20: ");
		n = input.nextInt();
		if (n >= 1 && n <= 20) {
			System.out.println();
			// Combined
			for (int i = 0; i < n; i++) {
				System.out.printf("%" + (i + 1) + "s", "*");
				System.out.printf("%" + (3 + (n - i) * 2) + "s", "*");
				System.out.println();
			}
		} else {
			System.out.println("Invalid number!");
		}

	}
}
