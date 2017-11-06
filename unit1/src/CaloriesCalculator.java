import java.util.Scanner;

public class CaloriesCalculator {

	public static void main(String[] args) {
		int pizzaSlice;
		double hour;
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter the number of pizza slices: ");
		pizzaSlice = scanner.nextInt();
		
		hour = pizzaSlice * 355 / 550.0;
		System.out.printf("You would have to cycle for %.2f hours.", hour);
		
		int minuteInt = (int) (Math.round(hour * 60) % 60);
		int hourInt = (int) (Math.round(hour * 60) / 60);
		System.out.printf("\nYou would have to cycle for %s:%s.", hourInt, minuteInt);
		
		scanner.close();
	}

}
