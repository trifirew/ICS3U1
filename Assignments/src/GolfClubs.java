/*
Keisun Wu
28 November 2017

Assignment #4A - Fun with Arrays
BONUS Question

This program finds the minimum number of shots required to get the golf ball in the hold.
 */

import java.io.*;
import java.util.Scanner;

public class GolfClubs {

	/**
	 * Sort an array of integer from smallest to largest.
	 *
	 * @param a the array to sort
	 */
	private static void sortAscending(int[] a) {
		for (int i = a.length - 1; i >= 0; i--) {
			int maxIndex = i;
			for (int j = 0; j <= i; j++) {
				if (a[j] > a[maxIndex])
					maxIndex = j;
			}
			// Swap two items
			int temp = a[i];
			a[i] = a[maxIndex];
			a[maxIndex] = temp;
		}
	}

	/**
	 * Find the minimum number of shots required to get the golf ball in the hold.
	 *
	 * @param distance the distance to the hole
	 * @param clubs    the set of clubs in an array
	 * @param start    the starting location of the clubs array
	 * @return the number of shots required
	 */
	private static int numberOfShots(int distance, int[] clubs, int start) {
		if (start < 0 || distance < clubs[0] || distance == 0) {
			return 0;
		}

		int shot = distance / clubs[start];
		if (distance % clubs[start] == 0) {
			return shot;
		} else if (start == 0) {
			return 0;
		}

		if (shot > 1) {
			return 1 + numberOfShots(distance - clubs[start], clubs, start);
		}
		if (shot == 1) {
			if (numberOfShots(distance - clubs[start], clubs, start - 1) == 0) {
				return numberOfShots(distance, clubs, start - 1);
			}
			return 1 + numberOfShots(distance - clubs[start], clubs, start - 1);
		}
		return numberOfShots(distance - clubs[start], clubs, start - 1);
	}

	/**
	 * Find the minimum number of shots required to get the golf ball in the hold.
	 *
	 * @param distance the distance to the hole
	 * @param clubs    the set of clubs in an array
	 * @return the number of shots required
	 */
	public static int numberOfShots(int distance, int[] clubs) {
		sortAscending(clubs);
		return numberOfShots(distance, clubs, clubs.length - 1);
	}

	public static void main(String[] args) throws IOException {
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Enter the file name: ");
		String filename = keyboard.nextLine();
		keyboard.close();

		// For testing
		if (filename.isEmpty())
			filename = "input/inputA4A3.txt";

		Scanner file = new Scanner(new File(filename));

		int cases = Integer.parseInt(file.nextLine());
		for (int i = 0; i < cases; i++) {
			int distance = Integer.parseInt(file.nextLine());
			int clubNumber = Integer.parseInt(file.nextLine());

			// Read the clubs in from file
			int[] clubs = new int[clubNumber];
			for (int j = 0; j < clubNumber; j++) {
				clubs[j] = Integer.parseInt(file.nextLine());
			}
			int shot = numberOfShots(distance, clubs);
			if (shot == 0) {
				System.out.println("Impossible");
			} else {
				System.out.printf("It would take %d shots%n", shot);
			}
		}
		file.close();
	}
}
