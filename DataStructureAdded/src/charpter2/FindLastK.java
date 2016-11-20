package charpter2;


//�ڲ��ı䵥����ṹ��ǰ���£������ܿ���ҵ�������k��Ԫ��
public class FindLastK<E> {
	private Node head;
	private Node tail;

	// ��ӵ�β
	public boolean add(E e) {
		if (head == null) {
			head = new Node(e, tail);
			tail = head;
		} else {
			Node newNode = new Node(e, null);
			tail.next = newNode;
			tail = newNode;
		}
		return true;
	}

	public E findLast(int index) {
		Node target = head, ptr = head;
		for (int i = 0; i < index; i++) {
			ptr = ptr.next;// ���ƶ�index��λ��
		}
		while (ptr != null) {
			ptr = ptr.next;
			target = target.next;
		}
		return target.data;
	}

	class Node {
		E data;
		Node next;

		public Node(E data, Node next) {
			this.data = data;
			this.next = next;
		}
	}

	public static void main(String[] args) {
		FindLastK<Integer> list = new FindLastK<>();
		for (int i = 0; i < 20; i++) {
			list.add(i);
		}
		System.out.println(list.findLast(2));// �ҵ�����2��
	}
}
