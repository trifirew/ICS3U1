/*
  Keisun Wu
  20170313
  2.1.14 Calculate a to the power of b
 */


import java.util.Scanner;

public class Power {
	public static void main(String[] args) {
		double a, b, result;
		Scanner input = new Scanner(System.in);

		System.out.print("Enter the base: ");
		a = input.nextDouble();
		System.out.print("Enter the exponent: ");
		b = input.nextDouble();

		result = Math.pow(a, b);
		System.out.println("The result is " + result);
		
		input.close();
	}
}
