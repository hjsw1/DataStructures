package charpter3;

import java.util.ArrayDeque;
import java.util.Queue;

public class PascalTriangleQueueImpl {

	public static void print(int n) {
		// �����Ǵ�β����ӣ�ͷ��ɾ��
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(1);
		queue.add(1);
		// ����һ�н���
		int first = 0;// ����ÿһ�еĵ�һ������
		for (int i = 0; i < n; i++) {
			System.out.println();
			for (int j = 0; j < n - i; j++) {
				System.out.print(" ");
			}
			// ѭ����ӡ�ո�
			queue.add(0);
			// Ϊ�˼�����һ�е����һ�����֣�����
			for (int j = 0; j <= i + 2; j++) {
				int front = queue.remove();// ����
				queue.add(front + first);// ������һ�еĵ�һ��Ԫ�أ������
				first = front;
				if (j < i + 2) {// �������һ��0
					System.out.print(first + " ");
				}
			}
		}
	}

	public static void main(String[] args) {
		PascalTriangleQueueImpl.print(4);
	}
}
