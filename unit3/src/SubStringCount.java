import java.io.*;
import java.util.*;

public class SubStringCount {
	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(new File("input/input10.txt"));

		while (scanner.hasNextLine()) {
			String s = scanner.nextLine();
			String subs = scanner.nextLine();
			System.out.println(subStringCount(s, subs));
		}
	}

	private static String subStringCount(String origStr, String searchStr) {
		int start = 0;
		int index;
		String newString = "";
		while ((index = origStr.indexOf(searchStr, start)) != -1) {
			newString += origStr.substring(start, index);
			start = origStr.indexOf(searchStr, start) + searchStr.length();
		}
		newString += origStr.substring(start);
		return newString;
	}
}
