/*
  Keisun Wu
  20170314
  2.2.11 Calculate the nth triangular number
 */

import java.util.Scanner;

public class Triangular {
	public static void main(String[] args) {
		int n;
		int tri = 0;
		Scanner input = new Scanner(System.in);

		System.out.print("Enter a positive number: ");
		n = input.nextInt();
		for (int i = 1; i <= n; i++) {
			tri += i;
		}
		System.out.println("The triangular number with a base of " + n + " is " + tri);
		input.close();
	}
}
