package charpter2;

import charpter2.MyLinkedList.Node;

public class L1OPL2 {

	public static <E extends Comparable<E>> MyLinkedList<E> intersect(MyLinkedList<E> l1, MyLinkedList<E> l2) {
		MyLinkedList<E> l3 = new MyLinkedList<>();
		Node<E> pL1 = l1.head, pL2 = l2.head;
		while (pL1 != null && pL2 != null) {
			if (pL1.data.compareTo(pL2.data) == 0) {
				l3.add(pL1.data);
				pL1 = pL1.next;
				pL2 = pL2.next;
			} else if (pL1.data.compareTo(pL2.data) < 0) {
				pL1 = pL1.next;
			} else {
				pL2 = pL2.next;
			}
		}
		return l3;
	}
	
	public static <E extends Comparable<E>> MyLinkedList<E> union(MyLinkedList<E> l1, MyLinkedList<E> l2) {
		MyLinkedList<E> l3 = new MyLinkedList<>();
		Node<E> pL1 = l1.head, pL2 = l2.head;
		while (pL1 != null && pL2 != null) {
			if (pL1.data.compareTo(pL2.data) < 0) {
				l3.add(pL1.data);
				pL1 = pL1.next;
			} else if (pL1.data.compareTo(pL2.data) == 0) {
				l3.add(pL1.data);
				pL1 = pL1.next;
				pL2 = pL2.next;
			} else {
				l3.add(pL2.data);
				pL2 = pL2.next;
			}
		}
		
		while (pL1 != null) {
			l3.add(pL1.data);
			pL1 = pL1.next;
		}
		while (pL2 != null) {
			l3.add(pL2.data);
			pL2 = pL2.next;
		}
		return l3;
	}
	public static <E extends Comparable<E>> MyLinkedList<E> reverse(MyLinkedList<E> list){
		MyLinkedList<E> res = new MyLinkedList<>();
		Node<E> src = list.head,node = new Node<>(src.data,null);//node是前面一个，post是后面一个，将其位置倒过来
		Node<E> post = null;
		while(src.next != null){
			src = src.next;
			post = new Node<>(src.data,node);
			node = post;
		}
		res.head = post;
		return res;
	}
	
	public static void main(String[] args) {
		MyLinkedList<Integer> l1 = new MyLinkedList<>();
		MyLinkedList<Integer> l2 = new MyLinkedList<>();
		MyLinkedList<Integer> l3;

		l1.add(2);
		l1.add(3);
		l1.add(4);
		l1.add(9);
		
		l2.add(2);
		l2.add(3);
		l2.add(4);
		l2.add(7);
		
//		l3 = intersec(l1, l2);
//		l3.traverse();
//		l3 = union(l1,l2);
//		l3.traverse();
		l3 = reverse(l1);
		l3.traverse();
	}
}

class MyLinkedList<E extends Comparable<E>> {
	Node<E> head;
	Node<E> tail;

	public boolean add(E e) {
		if (head == null) {
			head = new Node<>(e, tail);
			tail = head;
		} else {
			Node<E> newNode = new Node<>(e, null);
			tail.next = newNode;
			tail = newNode;
		}
		return true;
	}

	public void traverse() {
		Node<E> node = head;
		System.out.print("[");
		while (node != null) {
			System.out.print(node.data + (node == tail ? "" : ","));
			node = node.next;
		}
		System.out.println("]");
	}

	static class Node<E extends Comparable<E>> {
		E data;
		Node<E> next;

		public Node(E data, Node<E> next) {
			this.data = data;
			this.next = next;
		}
	}
}