
public class InterestingPrimeNumber {

	public static void main(String[] args) {
		int maxNum = Integer.MAX_VALUE;
		for (int num = 2; num <= maxNum; num++) {
			if (isPrime(num)) {
				int primeNum = num;
				boolean isInteresting = true;
				while (primeNum >= 10) {
					primeNum = primeNum / 10;
					if (!isPrime(primeNum)) {
						isInteresting = false;
					}
				}
				if (isInteresting) {
					System.out.println(num);
				}
			}
		}

	}
	
	private static boolean isPrime(int number) {
		if (number == 0 || number == 1) {
			return false;
		}
		for (int i = number / 2; i > 1; i--) {
			if (number % i == 0) {
				return false;
			}
		}
		return true;
	}

}
