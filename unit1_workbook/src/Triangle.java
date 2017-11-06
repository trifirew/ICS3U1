import java.util.Scanner;

public class Triangle {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter the first side: ");
		int a = scanner.nextInt();
		System.out.print("Enter the second side: ");
		int b = scanner.nextInt();
		System.out.print("Enter the third side:");
		int c = scanner.nextInt();
		
		double s = (a + b + c) / 2.0;

		if (a < s && b < s && c < s) {
			System.out.println("Area: " + Math.sqrt(s * (s - a) * (s - b) * (s - c)));
		} else {
			System.out.println("Cannot form triangle");
		}
		scanner.close();
	}

}
