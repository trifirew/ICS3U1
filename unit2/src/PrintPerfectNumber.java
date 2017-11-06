
public class PrintPerfectNumber {

	public static void main(String[] args) {
		int maxNum = 30000;
		for (int num = 1; num <= maxNum; num++) {
			int sum = 0;
			for (int i = 1; i <= num / 2; i++) {
				if (num % i == 0) {
					sum += i;
				}
			}
			if (num == sum) {
				System.out.print(num + ", ");
			}
		}
		
		
	}

}
