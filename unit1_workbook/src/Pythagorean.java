/*
  Keisun Wu
  20170313
  2.1.9 Enter two sides of a right triangle, prints the hypothenuse.
 */


import java.util.Scanner;

public class Pythagorean {
	public static void main(String[] args) {
		double a, b, hyp;
		Scanner input = new Scanner(System.in);

		System.out.print("Enter the first side: ");
		a = input.nextFloat();
		System.out.print("Enter the second side: ");
		b = input.nextFloat();

		hyp = Math.sqrt(a * a + b * b);
		System.out.println("The hypothenuse is " + hyp);
		
		input.close();
	}
}
