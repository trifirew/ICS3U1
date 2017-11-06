/*
  Keisun Wu
  20170310
  2.1.5 Mix integers with reals
  Convert Fahrenheit to celsius
 */


import java.util.Scanner;

public class Reals {
	public static void main(String[] args) {
		float c, f;
		Scanner input = new Scanner(System.in);

		System.out.print("Enter a degree measure in Fahrenheit: ");
		f = input.nextFloat();
		input.close();

		c = 5f / 9f * (f - 32);
		System.out.println("It's " + c + " degree celsius.");
	}
}
