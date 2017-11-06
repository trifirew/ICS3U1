import java.util.Scanner;

public class Palindrome {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String word, reverseWord;
		do {
			System.out.print("Enter a word: ");
			word = scanner.nextLine();
			reverseWord = "";
			for (int i = word.length() - 1; i >= 0; i--) {
				reverseWord += word.charAt(i);
			}
			if (word.equalsIgnoreCase(reverseWord))
				System.out.println("palindrome");
			else
				System.out.println("not palindrome");
		} while (!word.equalsIgnoreCase("stop"));
	}
}
