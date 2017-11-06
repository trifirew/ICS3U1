import java.util.Scanner;

public class PrimeNumber {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter an integer: ");
		int num = scanner.nextInt();
		
		long startTime = System.nanoTime();
		boolean isPrimeNumber = true;
		for (int i = num / 2; i > 1; i--) {
			if (num % i == 0 || num == 0 || num == 1) {
				isPrimeNumber = false;
				break;
			}
		}
		long endTime = System.nanoTime();
		if (isPrimeNumber && num != 0 && num != 1) {
			System.out.println(num + " is a prime number.");
		} else {
			System.out.println(num + " is not a prime number.");
		}
		System.out.println("Took " + (endTime - startTime) + " ns");
		scanner.close();
	}

}
