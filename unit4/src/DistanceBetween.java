/*
Keisun Wu
13 November 2017
Methods Exercises 9
 */

import java.util.Scanner;

public class DistanceBetween {

	/**
	 * Calculate the distance between two points.
	 *
	 * @param x1,y1 coordinate of the first point
	 * @param x2,y2 coordinate of the second point
	 * @return the distance between two points
	 */
	public static double distanceBetween(int x1, int y1, int x2, int y2) {
		return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
	}

	public static void main(String[] args) {
		int x1, y1, x2, y2;
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter x1: ");
		x1 = scanner.nextInt();
		System.out.print("Enter y1: ");
		y1 = scanner.nextInt();
		System.out.print("Enter x2: ");
		x2 = scanner.nextInt();
		System.out.print("Enter y2: ");
		y2 = scanner.nextInt();
		System.out.println(distanceBetween(x1, y1, x2, y2));
	}
}
