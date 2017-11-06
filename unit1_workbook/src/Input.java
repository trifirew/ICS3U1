/*
  Keisun Wu
  20170310
  2.1.4 Input
  Ask for radius of circle, and calculate area & circumference.
 */


import java.util.Scanner;

public class Input {
	public static void main(String[] args) {
		final float PI = 3.14f;
		float area, circum, radius;
		Scanner input = new Scanner(System.in);

		System.out.print("Enter the radius of a circle: ");
		radius = input.nextFloat();
		input.close();

		area = PI * (float) Math.pow(radius, 2);
		circum = PI * 2 * radius;
		System.out.println("The area is " + area);
		System.out.println("The circumference is " + circum);
	}
}
