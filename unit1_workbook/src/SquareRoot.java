import java.util.Scanner;

public class SquareRoot {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter a number: ");
		int num = scanner.nextInt();
		if (num < 0) {
			System.out.println("Negative numbers do not have a square root value");
		} else {
			System.out.println(Math.sqrt(num));
		}
		
		scanner.close();
	}

}
