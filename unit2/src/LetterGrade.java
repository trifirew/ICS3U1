import java.util.Scanner;

public class LetterGrade {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter your mark: ");
		int mark = scanner.nextInt();
		if (mark > 100 || mark < 0)
			System.out.println("Invalid");
		else if (mark>= 80)
			System.out.println("A");
		else if (mark >= 70)
			System.out.println("B");
		else if (mark >= 60)
			System.out.println("C");
		else if (mark >= 50)
			System.out.println("D");
		else
			System.out.println("E");
	}
}
