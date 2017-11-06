import java.util.Scanner;

public class VowelsConsonants {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String word;
		while (!(word = scanner.nextLine().toLowerCase()).equals("stop")) {
			int vowel = 0;
			int consonant = 0;
			for (int i = 0; i < word.length(); i++) {
				char c = word.charAt(i);
				if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')
					vowel++;
				else
					consonant++;
			}
			System.out.printf("Vowel: %d\tConsonant: %d%n", vowel, consonant);
		}
	}
}
