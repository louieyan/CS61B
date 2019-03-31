public class HelloNumbers {
	public static void main(String[] args) {
		int x = 0;
		while (x < 10) {
			int i = 0;
			int sum_x = 0;
			while (i <= x) {
				sum_x = sum_x + i;
				i = i + 1;
			}
			System.out.print(sum_x + " ");
			x = x + 1;
		}
	}
}
