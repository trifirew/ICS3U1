import java.util.Scanner;

public class Mark {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter your mark: ");
		int mark = scanner.nextInt();
		
		if (mark < 0 || mark > 100) {
			System.out.println("Invalid mark");
		} else if (mark >= 50) {
			System.out.println("PASS");
		} else {
			System.out.println("FAIL");
		}
		
		scanner.close();
	}

}
