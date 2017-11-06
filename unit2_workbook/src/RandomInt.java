/**
 * 2.4.2 Write a program that lets you print 20 random integers between 1 and 100
 * inclusive.
 *
 */
public class RandomInt {

	public static void main(String[] args) {
		for (int i = 0; i < 20; i++) {
			System.out.println((int) (Math.random() * 100) + 1);
		}

	}

}
