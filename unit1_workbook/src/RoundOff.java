/*
  Keisun Wu
  20170313
  2.1.15 Round a number off to 3 decimal places.
 */


import java.util.Scanner;

public class RoundOff {
	public static void main(String[] args) {
		double num, result;
		Scanner input = new Scanner(System.in);

		System.out.print("Enter a number: ");
		num = input.nextDouble();
		result = Math.round(num * 1000.0) / 1000.0;
		System.out.println("Result of rounding is " + result);
		
		input.close();
	}
}
