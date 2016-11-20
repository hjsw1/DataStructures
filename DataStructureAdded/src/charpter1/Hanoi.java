package charpter1;

public class Hanoi {
	public void hanoi(int n, char from, char via, char to) {
		//只剩一个元素时直接从from移动到to
		if (n == 1) {
			System.out.println("move " + n + " from " + from + " to " + to);
		} else {
			//移动前n-1个，从from移动到via
			hanoi(n - 1, from, to, via);
			System.out.println("move " + n + " from " + from +  " to " + to);
			//移动前n-1个，从via移动到to
			hanoi(n - 1, via, from, to);
		}
	}

	public static void main(String[] args) {
		Hanoi h = new Hanoi();
		h.hanoi(3, 'A', 'B', 'C');
	}
}
