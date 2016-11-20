package charpter2;

public class CursorList {
	private static final int MAX_SIZE = 100;
	private static CursorNode[] cursorSpace;
	static{
		cursorSpace = new CursorNode[MAX_SIZE];
		for(int i = 0; i < MAX_SIZE;i++){
			cursorSpace[i] = new CursorNode(null, i+1);
		}
		cursorSpace[MAX_SIZE-1].next = 0;
	}
	//所有的单链表都共享这一数组的内存单元
	//如果一个结点的next为0，则为表尾结点
	
	//List实例所拥有的数据
	private int head;//当前单链表的表头下标
	
	//为单链表分配一个单元的内存，总是从第1个有效数据开始分配
	private static int alloc() throws Exception  { 
		int ptr = cursorSpace[0].next;
		cursorSpace[0].next = cursorSpace[ptr].next;//重新设置第一个有效数据
		if(ptr == 0){
			throw new Exception("Out of Memory");
		}
		return ptr;
	}
	
	private static void free(int ptr){
		//可以将p所指向的内存单元置空
		cursorSpace[ptr].data = null;
		//将p所串联的单链表串回去，回到第一个有效数据的位置
		cursorSpace[ptr].next = cursorSpace[0].next;
		cursorSpace[0].next = ptr;
	}
	
	
	//以下均为实例方法
	public CursorList() throws Exception{
		//创建一个单链表
		head = alloc();//为表头结点分配一个单位的内存
		cursorSpace[head].next = 0;//表头的nect置空
	}
	public boolean isEmpty(){
		return cursorSpace[head].next == 0;
	}
	public int find(Object val){
		int ptr = cursorSpace[head].next;//第一个有效元素的位置
		while(cursorSpace[ptr].data != val && cursorSpace[ptr].next != 0){
			ptr = cursorSpace[ptr].next;//++的正确姿势
		}
		return ptr;
	}
	//插在给定的位置之后
	public void insert(Object val,int ptr) throws Exception{
		if(ptr != 0){
			//分配一个单位的内存
			int newNode = alloc();
			cursorSpace[newNode].data = val;
			cursorSpace[newNode].next = cursorSpace[ptr].next;
			cursorSpace[ptr].next = newNode;
		}
	}
	public void remove(Object val){
		int prev = findPrevious(val);
		if(cursorSpace[prev].next != 0){
			//如果下一个元素存在
			int ptr = cursorSpace[prev].next;
			cursorSpace[prev].next = cursorSpace[ptr].next;
			free(ptr);
		}
	}
	public int findPrevious(Object val){
		int ptr = cursorSpace[head].next;//第一个有效元素的位置
		while(cursorSpace[ptr].next != 0 && cursorSpace[cursorSpace[ptr].next].data != val){
			ptr = cursorSpace[ptr].next;//++的正确姿势
		}
		return ptr;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		int ptr = cursorSpace[head].next;
		while(ptr != 0 ){
			sb.append(cursorSpace[ptr].data+" ");
			ptr = cursorSpace[ptr].next;
		}
		return sb.toString();
	}
	public int getHead(){
		return head;
	}
	public static void main(String[] args) throws Exception {
		CursorList list = new CursorList();
		list.insert(0, list.getHead());
		System.out.println(list.find(2));
		for(int i = 0; i < 20; i++){
			list.insert(i+1,list.find(i));
		}
		System.out.println(list.find(20));
		System.out.println(list.find(13));
		System.out.println(list.findPrevious(20));
		list.remove(12);
		System.out.println(list);
	}
	
	
	
	
	
	static class CursorNode{
		public CursorNode(Object data,int next) {
			this.data = data;
			this.next = next;
		}
		Object data;
		int next;
	}
}
