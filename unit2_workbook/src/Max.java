import java.util.Scanner;

public class Max {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter 5 numbers");
		int max = scanner.nextInt();
		for (int i = 0; i < 4; i++) {
			max = Math.max(max, scanner.nextInt());
		}
		System.out.println("Max: " + max);
		scanner.close();
	}

}
