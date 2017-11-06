import java.util.Scanner;

public class Rectangle {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the height: ");
		int height = scanner.nextInt();
		System.out.print("Enter the width: ");
		int width = scanner.nextInt();
		for (int i = 1; i <= height; i++) {
			for (int j = 1; j <= width; j++) {
				if (i > 1 && i < height && j > 1 && j < width)
					System.out.print(" ");
				else
					System.out.print("*");
			}
			System.out.println();
		}
	}
}
