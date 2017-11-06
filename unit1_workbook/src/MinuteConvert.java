/*
  Keisun Wu
  20170311
  2.1.8 Convert minutes to days, hours, and minutes
 */


import java.util.Scanner;

public class MinuteConvert {
	public static void main(String[] args) {
		int fullMinute, day, hour, minute;
		Scanner input = new Scanner(System.in);

		System.out.print("Enter a number of minutes: ");
		fullMinute = input.nextInt();
		day = fullMinute / 60 / 24;
		hour = fullMinute / 60 % 24;
		minute = fullMinute % 60;
		System.out
				.println(fullMinute + " minutes is " + day + " days, " + hour + " hours, and " + minute + " minutes.");
		input.close();
	}
}
