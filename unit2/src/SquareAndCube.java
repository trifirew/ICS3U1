public class SquareAndCube {
	public static void main(String[] args) {
		System.out.printf("%25s%n", "Squares and Cubes");
		System.out.printf("%-12s%-12s%-12s%n", "Number", "Square", "Cube");
		for (int i = 1; i <= 15; i++) {
			System.out.printf("%4d%12d%12d%n", i, i * i, i * i * i);
		}
	}
}
