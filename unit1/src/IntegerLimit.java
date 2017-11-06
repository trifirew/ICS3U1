public class IntegerLimit {

	public static void main(String[] args) {
		final int N = 1024;
		double fraction = 1.0 / N;
		double total = 0;
		for (int count = 1; count <= N; count++) 
			total += fraction;
		System.out.println(total);

	}

}
