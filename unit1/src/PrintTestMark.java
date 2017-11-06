/*
  Keisun Wu
  September 11, 2017
  Read in name and 3 marks, and print them.
 */


import java.util.*;

public class PrintTestMark {

	public static void main(String[] args) {
		String name;
		int age, mark1, mark2, mark3, average;
		Scanner s = new Scanner(System.in);

		System.out.print("Enter your name: ");
		name = s.nextLine();
		System.out.print("Enter your age: ");
		age = s.nextInt();
		System.out.print("Enter your first mark: ");
		mark1 = s.nextInt();
		System.out.print("Enter your second mark: ");
		mark2 = s.nextInt();
		System.out.print("Enter yout third mark: ");
		mark3 = s.nextInt();

		average = (mark1 + mark2 + mark3) / 3;

		System.out.println();
		System.out.println(name + " is " + age + " years old.");
		System.out.println("The test marks are " + mark1 + "%, " + mark2 + "%, and " + mark3 + "%,");
		System.out.println("and the average is " + average + "%.");

		s.close();
	}

}
