import java.util.Scanner;

public class PrintPrimeNumber {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter an integer: ");
		int maxNum = scanner.nextInt();
		for (int num = 2; num <= maxNum; num++) {
			boolean isPrimeNumber = true;
			for (int i = num / 2; i > 1; i--) {
				if (num % i == 0) {
					isPrimeNumber = false;
					break;
				}
			}
			if (isPrimeNumber && num != 1 && num != 0) {
				System.out.print(num + ", ");
			}
		}
		scanner.close();
	}

}
