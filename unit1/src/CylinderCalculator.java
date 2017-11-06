import java.util.Scanner;

public class CylinderCalculator {

	public static void main(String[] args) {
		final double PI = Math.PI;
		double height, radius;
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter the height: ");
		height = scanner.nextInt();
		System.out.print("Enter the radius: ");
		radius = scanner.nextInt();
		
		double surfaceArea = 2 * PI * radius * height + 2 * PI * Math.pow(radius, 2);
		double volume = PI * Math.pow(radius, 2) * height;
		
		System.out.printf("The surface area is %.1f\n", surfaceArea);
		System.out.printf("The volume is %.1f", volume);
		
		scanner.close();
	}

}
