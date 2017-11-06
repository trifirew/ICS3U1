import java.io.*;
import java.util.*;

public class SearchAndReplace {
	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(new File("input/input11.txt"));

		while (scanner.hasNextLine()) {
			String origStr = scanner.nextLine();
			String searchStr = scanner.nextLine();
			String replaceStr = scanner.nextLine();
			System.out.println(searchAndReplace(origStr, searchStr, replaceStr));
		}
	}

	private static String searchAndReplace(String origStr, String searchStr, String replaceStr) {
		int start = 0;
		int index;
		String newString = "";
		while ((index = origStr.indexOf(searchStr, start)) != -1) {
			newString += origStr.substring(start, index) + replaceStr;
			start = origStr.indexOf(searchStr, start) + searchStr.length();
		}
		newString += origStr.substring(start);
		return newString;
	}
}
