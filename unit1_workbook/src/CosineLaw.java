/*
  Keisun Wu
  20170313
  2.1.13 Use the cosine law to calculate the third side of triangle.
 */


import java.util.Scanner;

public class CosineLaw {
	public static void main(String[] args) {
		double sideA, sideB, sideC, angleC;
		Scanner input = new Scanner(System.in);

		System.out.print("Enter side a: ");
		sideA = input.nextDouble();
		System.out.print("Enter side b: ");
		sideB = input.nextDouble();
		System.out.print("Enter angle C: ");
		angleC = input.nextDouble();

		sideC = Math.sqrt(sideA * sideA + sideB * sideB - 2 * sideA * sideB * Math.cos(Math.toRadians(angleC)));
		System.out.println("Side c is " + sideC);
		
		input.close();
	}
}
