/*
  Keisun Wu
  20170313
  2.1.10 Use Heron's formula to calculate the area of triangle.
 */


import java.util.Scanner;

public class Heron {
	public static void main(String[] args) {
		double a, b, c, s, area;
		Scanner input = new Scanner(System.in);

		System.out.print("Enter the first side of triangle: ");
		a = input.nextDouble();
		System.out.print("Enter the second side of triangle: ");
		b = input.nextDouble();
		System.out.print("Enter the third side of triangle: ");
		c = input.nextDouble();

		s = (a + b + c) / 2;
		area = Math.sqrt(s * (s - a) * (s - b) * (s - c));
		System.out.println("The area of the triangle is " + area);
		
		input.close();
	}
}
