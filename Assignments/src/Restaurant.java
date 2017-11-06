import java.util.Scanner;

/**
 * <h1>Assignment #1 - My Awesome Restaurant</h1>
 * 
 * A program that simulates the cashier of a restaurant. It calculates the bills
 * and changes, and suggests the amount of bills and coins needed for change.
 * 
 * @author Keisun Wu
 * @since September 18, 2017
 */

public class Restaurant {

	public static void main(String[] args) {
		// Amounts of food
		int waffle, cheeseburger, sandwich, icecream, bubbleTea;
		double total = 0;
		// Prices of food
		double wafflePrice = 2.50;
		double cheeseburgerPrice = 3.00;
		double sandwichPrice = 4.00;
		double icecreamPrice = 2.00;
		double bubbleTeaPrice = 3.30;
		// Names of the bills and coins
		String strTwenty = "twenty-dollar bill";
		String strTen = "ten-dollar bill";
		String strFive = "five-dollar bill";
		String strToonie = "toonie";
		String strLoonie = "loonie";
		String strQuarter = "quarter";
		String strDime = "dime";
		String strNickel = "nickel";
		String strPenny = "penny";
		// Scanner to get user input
		Scanner scanner = new Scanner(System.in);

		System.out.println("WELCOME TO MY AWESOME RESTAURANT\n");

		// Ask user what food they want
		System.out.print("How many waffles would you like?  ");
		waffle = scanner.nextInt();
		System.out.print("How many cheeseburgers would you like?  ");
		cheeseburger = scanner.nextInt();
		System.out.print("How many pulled pork sandwiches would you like?  ");
		sandwich = scanner.nextInt();
		System.out.print("How many bowls of ice-cream would you like?  ");
		icecream = scanner.nextInt();
		System.out.print("How many bubble teas would you like?  ");
		bubbleTea = scanner.nextInt();

		// Calculate and show the bill
		System.out.println("\nYOUR BILL\n");
		System.out.printf("%d waffles @ $%.2f each = $%.2f\n", waffle, wafflePrice, waffle * wafflePrice);
		total += waffle * wafflePrice;
		System.out.printf("%d cheeseburgers @ $%.2f each = $%.2f\n", cheeseburger, cheeseburgerPrice,
				cheeseburger * cheeseburgerPrice);
		total += cheeseburger * cheeseburgerPrice;
		System.out.printf("%d pulled pork sandwiches @ $%.2f each = $%.2f\n", sandwich, sandwichPrice,
				sandwich * sandwichPrice);
		total += sandwich * sandwichPrice;
		System.out.printf("%d bowls of ice-cream @ $%.2f each = $%.2f\n", icecream, icecreamPrice,
				icecream * icecreamPrice);
		total += icecream * icecreamPrice;
		System.out.printf("%d bubble teas @ $%.2f each = $%.2f\n", bubbleTea, bubbleTeaPrice,
				bubbleTea * bubbleTeaPrice);
		total += bubbleTea * bubbleTeaPrice;
		System.out.printf("\nTotal = $%.2f\n\n", total);

		// Let user pay and give change
		System.out.print("Please enter the amount paid:  $");
		double pay = scanner.nextDouble();
		// If user did not pay enough money, ask them to pay more
		while (pay < total) {
			System.out.printf("Insufficient payment, you need $%.2f more.\n", total - pay);
			System.out.print("Please enter the amount paid:  $");
			pay += scanner.nextDouble();
		}
		double change = roundPrice(pay - total);
		System.out.printf("The change will be:  $%.2f\n", change);

		// Calculate the amount of bills and coins
		int twenty = (int) (change / 20);
		change = roundPrice(change % 20);
		int ten = (int) (change / 10);
		change = roundPrice(change % 10);
		int five = (int) (change / 5);
		change = roundPrice(change % 5);
		int toonie = (int) (change / 2);
		change = roundPrice(change % 2);
		int loonie = (int) (change / 1);
		change = roundPrice(change % 1);
		int quarter = (int) (change / 0.25);
		change = roundPrice(change % 0.25);
		int dime = (int) (change / 0.10);
		change = roundPrice(change % 0.10);
		int nickel = (int) (change / 0.05);
		change = roundPrice(change % 0.05);
		int penny = (int) (change / 0.01);

		// Add plural forms if amount is greater than 1
		if (twenty > 1) {
			strTwenty += "s";
		}
		if (ten > 1) {
			strTen += "s";
		}
		if (five > 1) {
			strFive += "s";
		}
		if (toonie > 1) {
			strToonie += "s";
		}
		if (loonie > 1) {
			strLoonie += "s";
		}
		if (quarter > 1) {
			strQuarter += "s";
		}
		if (dime > 1) {
			strDime += "s";
		}
		if (nickel > 1) {
			strNickel += "s";
		}
		if (penny > 1) {
			strPenny = "pennies";
		}

		// Print out amount of bills and coins needed
		System.out.println("\nTo make up this amount, you will need:");
		System.out.printf("   %-2d %-23s%-2d %s\n", twenty, strTwenty, ten, strTen);
		System.out.printf("   %-2d %-23s%-2d %s\n", five, strFive, toonie, strToonie);
		System.out.printf("   %-2d %-23s%-2d %s\n", loonie, strLoonie, quarter, strQuarter);
		System.out.printf("   %-2d %-23s%-2d %s\n", dime, strDime, nickel, strNickel);
		System.out.printf("   %-2d %s\n", penny, strPenny);

		// Close the scanner
		scanner.close();
	}

	// Round double to two decimals (price format)
	private static double roundPrice(double price) {
		return Math.round(price / 0.01) * 0.01;
	}
}
