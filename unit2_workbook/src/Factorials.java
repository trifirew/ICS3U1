/*
  Keisun Wu
  20170314
  2.2.10 Calculate n!
 */

import java.util.Scanner;

public class Factorials {
	public static void main(String[] args) {
		int n;
		int fac = 1;
		Scanner input = new Scanner(System.in);

		System.out.print("Enter a positive number: ");
		n = input.nextInt();
		for (int i = 1; i <= n; i++) {
			fac *= i;
		}
		System.out.println(n + "! = " + fac);
		input.close();
	}
}
