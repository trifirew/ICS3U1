import java.util.Scanner;

public class PickDigit {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter a six digit number: ");
		int num = scanner.nextInt();
		System.out.print("Enter the digit you want: ");
		int digit = scanner.nextInt();
		System.out.println(num % (int) Math.pow(10, 7 - digit) / (int) Math.pow(10, 6 - digit));
		
		scanner.close();
	}
	
}
