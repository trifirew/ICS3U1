import java.util.Scanner;

public class PrintName {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter your name: ");
		String name = scanner.nextLine().trim();

		int spaceIndex = name.lastIndexOf(' ');
		if (spaceIndex != -1)
			System.out.printf("%s, %s\n", name.substring(spaceIndex + 1), name.substring(0, spaceIndex));
		else
			System.out.println(name);
	}
}
