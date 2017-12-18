/*
Keisun Wu
28 November 2017

Assignment #4A - Fun with Arrays
Question #2

This program includes methods to sort arrays and display them in table.
 */

import java.io.*;
import java.util.Scanner;

public class ArrayMethods {

	/**
	 * Sort an array of double from smallest to largest.
	 *
	 * @param a the array to sort
	 */
	public static void sortArray(double[] a) {
		for (int i = a.length - 1; i >= 0; i--) {
			int maxIndex = i;
			for (int j = 0; j <= i; j++) {
				if (a[j] > a[maxIndex])
					maxIndex = j;
			}
			// Swap two items
			double temp = a[i];
			a[i] = a[maxIndex];
			a[maxIndex] = temp;
		}
	}

	/**
	 * Display an array in an organized table.
	 *
	 * @param a      the array to display
	 * @param column the number of columns of the table
	 */
	public static void displayArray(double[] a, int column) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i]);
			if (i % column == column - 1)
				System.out.println();
			else if (i < a.length - 1)
				System.out.printf("%4s", "");
		}
	}

	public static void main(String[] args) throws IOException {
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Enter the file name: ");
		String filename = keyboard.nextLine();
		System.out.print("Enter the number of columns: ");
		int column = Integer.parseInt(keyboard.nextLine());
		keyboard.close();
		
		// For testing
		if (filename.isEmpty())
			filename = "input/inputA4A2.txt";

		Scanner file = new Scanner(new File(filename));
		int size = Integer.parseInt(file.nextLine());

		double[] list = new double[size];
		// Read from file
		for (int i = 0; i < size; i++)
			list[i] = Double.parseDouble(file.nextLine());
		file.close();

		sortArray(list);
		displayArray(list, column);
	}
}
