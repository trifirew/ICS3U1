public class SampleArray {

	private static int countNegative(int[] a) {
		int count = 0;
		for (int i = 0; i < a.length; i++) {
			if (a[i] < 0)
				count += 1;
		}
		return count;
	}

	private static void displayGreaterThan10(int[] a) {
		for (int i = 0; i < a.length; i++) {
			if (a[i] > 10)
				System.out.print(a[i] + "\t");
		}
		System.out.println();
	}

	private static int[] reverse(int[] a) {
		int[] result = new int[a.length];
		for (int i = 0; i < a.length; i++) result[i] = a[a.length - i - 1];
		return result;
	}

	public static void main(String[] args) {
		int[] sample = {34, 12, 17, 67, 50, 99, 18};

		System.out.println(countNegative(sample));
		displayGreaterThan10(sample);
		int[] newSample = reverse(sample);
		for (int i = 0; i < newSample.length; i++) System.out.print(newSample[i] + "\t");
		System.out.println();
	}
}
