import java.util.Scanner;

public class LoginPassword {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Login: ");
		String login = scanner.nextLine();
		System.out.print("Password: ");
		String password = scanner.nextLine();
		if (login.equalsIgnoreCase("login") && password.equals("password"))
			System.out.println("welcome");
		else
			System.out.println("password or login incorrect");
	}
}
