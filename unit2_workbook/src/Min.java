import java.util.Scanner;

public class Min {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter 5 numbers");
		int min = scanner.nextInt();
		for (int i = 0; i < 4; i++) {
			min = Math.min(min, scanner.nextInt());
		}
		System.out.println("Min: " + min);
		scanner.close();
	}

}
