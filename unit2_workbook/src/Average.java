import java.util.Scanner;

public class Average {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int sum = 0;
		System.out.println("Enter 5 numbers");
		for (int i = 0; i < 5; i++) {
			sum += scanner.nextInt();
		}
		double avg = sum / 5.0;
		System.out.println("Average: " + avg);
		scanner.close();
	}

}
