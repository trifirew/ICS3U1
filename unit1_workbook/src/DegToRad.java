/*
  Keisun Wu
  20170313
  2.1.11 Convert degree to radian
 */


import java.util.Scanner;

public class DegToRad {
	public static void main(String[] args) {
		double deg, rad;
		Scanner input = new Scanner(System.in);

		System.out.print("Enter degree measure: ");
		deg = input.nextDouble();

		rad = Math.toRadians(deg);
		System.out.println("Radian measure: " + rad);
		
		input.close();
	}
}
