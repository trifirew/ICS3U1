/*
Keisun Wu
11 November 2017

Assignment #3 - Cipher

This program encrypts or decrypts a file according to the user's option. Only lines contain MORE THAN (>) 4 characters
can be encrypted or decrypted. Otherwise "eErr" (encrypt error) or "dErr" (decrypt error) will be shown in the file at
the corresponding line.
*/

import java.io.*;
import java.util.*;

public class Cipher {
	private static final String ORIG = "AEIJOPRSTVX ";
	private static final String SUBS = "@=!?*#&$+^%_";
	private static final char LOWER = '1';

	public static void main(String[] args) throws IOException {
		final int ENCRYPT = 1;
		final int DECRYPT = 2;

		Scanner inKey = new Scanner(System.in);
		// Ask user to enter options
		System.out.println("What do you want to do?");
		System.out.println("(1) Encrypt\n(2) Decrypt");
		System.out.print("Enter an option: ");
		int mode = Integer.parseInt(inKey.nextLine());
		System.out.print("Enter the key number: ");
		int key = Integer.parseInt(inKey.nextLine());
		System.out.print("Enter input file name: ");
		String inFileName = inKey.nextLine();
		System.out.print("Enter output file name: ");
		String outFileName = inKey.nextLine();
		System.out.println();

//		inFileName = "input/inputA3.txt";
//		outFileName = "input/outputA3.txt";
		Scanner inFile = new Scanner(new File(inFileName));
		PrintWriter outFile = new PrintWriter(new FileWriter(outFileName));

		String line;
		if (mode == ENCRYPT) {
			while (inFile.hasNextLine()) {
				line = inFile.nextLine();
				if (line.length() > 4) {
					outFile.println(encrypt(line, key));
					System.out.println("Completed encrypting 1 line.");
				} else {
					outFile.println("eErr");
					System.out.println("Cannot encrypt. You need more than 4 characters.");
				}
			}
		} else if (mode == DECRYPT) {
			while (inFile.hasNextLine()) {
				line = inFile.nextLine();
				if (line.length() > 4) {
					outFile.println(decrypt(line, key));
					System.out.println("Completed decrypting 1 line.");
				} else {
					outFile.println("dErr");
					System.out.println("Cannot decrypt. You need more than 4 characters.");
				}
			}
		} else {
			System.out.println("Do nothing.");
		}

		inKey.close();
		inFile.close();
		outFile.close();
	}

	/**
	 * Returns an encrypted String, following certain steps of encryption. The origin argument must contain more than 4
	 * characters. The key argument is an integer which indicates how many positions the substitution pattern shifts
	 * right.
	 *
	 * @param origin a String to be encrypted
	 * @param key    the key used to encrypt the String
	 * @return the encrypted String
	 */
	private static String encrypt(String origin, int key) {
		origin = origin.trim();
		int len = origin.length();

		String e = origin;
		// Step 4: move the first half of the string to be the last half
		e = e.substring((len + 1) / 2) + e.substring(0, (len + 1) / 2);

		// Step 5: swap the first two characters with the last two
		e = e.substring(len - 2) + e.substring(2, len - 2) + e.substring(0, 2);

		// Step 6: swap the middle characters
		e = e.substring(0, (len + 1) / 2 - 3)
				+ e.substring((len + 1) / 2 - 1, (len + 1) / 2 + 1)
				+ e.substring((len + 1) / 2 - 3, (len + 1) / 2 - 1)
				+ e.substring((len + 1) / 2 + 1);

		// Step 7: swap all every two letters
		String temp = e;
		e = "";
		for (int i = 0; i < len; i += 2) {
			if (i + 1 < len)
				e += temp.charAt(i + 1);
			e += temp.charAt(i);
		}

		// Step 3: character substitutions
		temp = e;
		e = "";
		for (int i = 0; i < len; i++) {
			// Add a special character before the actual character if it is lower case
			if (Character.isLowerCase(temp.charAt(i))) {
				e += LOWER;
			}
			char ch = temp.charAt(i);
			for (int j = 0; j < ORIG.length(); j++) {
				int shifted = (j + key) % ORIG.length();
				if (temp.substring(i, i + 1).equalsIgnoreCase(ORIG.substring(j, j + 1))) {
					ch = SUBS.charAt(shifted);
					break;
				}
			}
			e += ch;
		}

		return e.toUpperCase();
	}

	private static String decrypt(String encrypted, int key) {
		encrypted = encrypted.trim().toUpperCase();
		int len = encrypted.length();
		String d = "";

		// Step 3: character substitutions
		boolean isLower = false;
		for (int i = 0; i < len; i++) {
			char ch = encrypted.charAt(i);
			// If ch is a special character which indicates the next character is lower case, skip this character
			if (ch == LOWER) {
				isLower = true;
				continue;
			}
			for (int j = 0; j < ORIG.length(); j++) {
				int shifted = (j + key) % ORIG.length();
				if (encrypted.charAt(i) == SUBS.charAt(shifted)) {
					ch = ORIG.charAt(j);
					break;
				}
			}
			if (isLower) {
				d += Character.toLowerCase(ch);
				isLower = false;
			} else {
				d += ch;
			}
		}
		len = d.length();

		// Step 7: swap all every two letters
		String temp = d;
		d = "";
		for (int i = 0; i < len; i += 2) {
			if (i + 1 < len)
				d += temp.charAt(i + 1);
			d += temp.charAt(i);
		}

		// Step 6: swap the middle characters
		d = d.substring(0, (len + 1) / 2 - 3)
				+ d.substring((len + 1) / 2 - 1, (len + 1) / 2 + 1)
				+ d.substring((len + 1) / 2 - 3, (len + 1) / 2 - 1)
				+ d.substring((len + 1) / 2 + 1);

		// Step 5: swap the first two characters with the last two
		d = d.substring(len - 2) + d.substring(2, len - 2) + d.substring(0, 2);

		// Step 4: move the first half of the string to be the last half
		d = d.substring(len / 2) + d.substring(0, len / 2);

		return d;
	}
}
