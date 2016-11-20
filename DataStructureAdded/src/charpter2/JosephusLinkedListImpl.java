package charpter2;

/*

*/
public class JosephusLinkedListImpl {
	private Node head;// 指向一个没有头结点的循环链表
	// 传入总数
	private Node backupHead;
	private Node backupTail;
	public JosephusLinkedListImpl(int n) {
		initial(n);
	}
	//1~n
	private void initial(int n) {
		head = new Node(1,null);
		Node tail = head;
		for (int i = 2; i <= n; i++) {
			Node newNode = new Node(i,null);
			tail.next = newNode;
			tail = newNode;
		}
		tail.next = head;
	}
	
	//传入间隔，每隔gap个元素就出队
	public void go(int gap){
		int count = 0;
		Node ptr = head;
		while(true){//当ptr指向最后一个结点时退出循环
			count++;
			if((count+1) % gap == 0){//ptr指向待删除的结点的前一个结点
				if(backupHead == null){
					backupHead = ptr.next;
					backupTail = backupHead;
				}else{
					backupTail.next = ptr.next;
					backupTail = ptr.next;
				}
				//删除下一个结点
				ptr.next = backupTail.next;
				//删掉的也要计数
				count++;
			}
			ptr = ptr.next;//向后移动
			if(ptr == ptr.next){
				ptr.next = null;//设置结束标记
				break;
			}
		}
		backupTail.next = ptr;
		backupTail = ptr;
	}
	
	public int getLast(){
		return backupTail.data;
	}
	public void printSequence(){
		Node ptr = backupHead;
		while(ptr != null){
			System.out.print(ptr.data+" ");
			ptr = ptr.next;
		}
	}
	
	class Node {
		public Node(int data, Node next) {
			this.data = data;
			this.next = next;
		}

		int data;
		Node next;
	}
	
	public static void main(String[] args) {
		JosephusLinkedListImpl jose = new JosephusLinkedListImpl(8);
		jose.go(3);
		System.out.println(jose.getLast());
		jose.printSequence();
	}
}
