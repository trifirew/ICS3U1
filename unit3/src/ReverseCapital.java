import java.util.Scanner;

public class ReverseCapital {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String line = scanner.nextLine().toUpperCase();
		for (int i = line.length() - 1; i >= 0; i--) {
			System.out.print(line.charAt(i));
		}
	}
}
