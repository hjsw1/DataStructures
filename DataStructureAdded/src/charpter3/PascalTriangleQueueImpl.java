package charpter3;

import java.util.ArrayDeque;
import java.util.Queue;

public class PascalTriangleQueueImpl {

	public static void print(int n) {
		// 队列是从尾部添加，头部删除
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(1);
		queue.add(1);
		// 将第一行进队
		int first = 0;// 保存每一行的第一个数字
		for (int i = 0; i < n; i++) {
			System.out.println();
			for (int j = 0; j < n - i; j++) {
				System.out.print(" ");
			}
			// 循环打印空格
			queue.add(0);
			// 为了计算下一行的最后一个数字，补零
			for (int j = 0; j <= i + 2; j++) {
				int front = queue.remove();// 出队
				queue.add(front + first);// 计算下一行的第一个元素，并入队
				first = front;
				if (j < i + 2) {// 跳过最后一个0
					System.out.print(first + " ");
				}
			}
		}
	}

	public static void main(String[] args) {
		PascalTriangleQueueImpl.print(4);
	}
}
