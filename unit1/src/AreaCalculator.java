import java.util.Scanner;

public class AreaCalculator {

	public static void main(String[] args) {
		int length, width, area;
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter the length: ");
		length = scanner.nextInt();
		System.out.print("Enter the width: ");
		width = scanner.nextInt();
		
		area = length * width;
		System.out.println("The area is " + area + ".");

		scanner.close();
	}

}
