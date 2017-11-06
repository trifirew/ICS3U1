/*
  Keisun Wu
  20170313
  2.1.12 Use sine function to calculate the area of a triangle.
 */


import java.util.Scanner;

public class SineArea {
	public static void main(String[] args) {
		double sideA, sideB, angleC, area;
		Scanner input = new Scanner(System.in);

		System.out.print("Enter side a: ");
		sideA = input.nextDouble();
		System.out.print("Enter side b: ");
		sideB = input.nextDouble();
		System.out.print("Enter angle C: ");
		angleC = input.nextDouble();

		area = (sideA * sideB * Math.sin(Math.toRadians(angleC))) / 2;
		System.out.println("The area is " + area);
		
		input.close();
	}
}
