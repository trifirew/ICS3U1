/**
 * 2.4.1 Write a program that lets you print 20 random real numbers between 0 and 100
 *
 */
public class RandomReal {

	public static void main(String[] args) {
		for (int i = 0; i < 20; i++) {
			System.out.println(Math.random() * 100);
		}

	}

}
