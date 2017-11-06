/*
  Keisun Wu
  20170310
  2.1.6 Convert kilometer to mile
 */


import java.util.Scanner;

public class KmToMi {
	public static void main(String[] args) {
		final float FACTOR = 0.621371f;
		float km, mi;
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a distance in kilometers: ");
		km = input.nextFloat();
		mi = km * FACTOR;
		System.out.println("It is " + mi + " miles.");
		input.close();
	}
}
