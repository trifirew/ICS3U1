public class MySqrt {

	public static double mySqrt(double n) {
		double low = 0;
		double high = n;
		double accuracy = 1;
		double r = 0;
		if (n < 0)
			return Double.NaN;
		while (low <= high) {
			if (r * r < n) {
				low = r;
				r += (high - low) / 2 + accuracy;
//				r = (high - low) / 2;
			} else if (r * r > n) {
				high = r;
				r = low;
				if (accuracy * 0.1 != 0)
					accuracy *= 0.1;
				continue;
			} else {
				return r;
			}
//			System.out.printf("%.40f %.20f %.20f %.40f %.20f %.20f ", low, r, high, accuracy, r * r, Math.sqrt(n));
//			System.out.printf("%.20f%n", high - low);
			if ((r == low || r == high) && (r * r - n < 1 && r * r - n > -1)) {
				return r;
			}
		}
		return r;
	}

	public static void main(String[] args) {
		double n = 13;

//		for (int i = 0; i < 1000; i++) {
//			System.out.println(mySqrt(n));
//			System.out.println(Math.sqrt(n));
//		}

		System.out.println();

		long time2 = System.currentTimeMillis();
		System.out.println(mySqrt(n));
		long time3 = System.currentTimeMillis();
		System.out.println(time3 - time2 + "ms");
		
		long time0 = System.currentTimeMillis();
		System.out.println(Math.sqrt(n));
		long time1 = System.currentTimeMillis();
		System.out.println(time1 - time0 + "ms");
	}
}
