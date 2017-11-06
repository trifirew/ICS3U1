/*
  Keisun Wu
  20170311
  2.1.7 Mod
  Convert hours to days and hours
 */


import java.util.Scanner;

public class Mod {
	public static void main(String[] args) {
		int day, hour, fullHour;
		Scanner input = new Scanner(System.in);

		System.out.print("Enter a number of hours: ");
		fullHour = input.nextInt();
		day = fullHour / 24;
		hour = fullHour % 24;
		System.out.println(fullHour + " hours is " + day + " days and " + hour + " hours.");
		input.close();
	}
}
