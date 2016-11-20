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
	//���еĵ�����������һ������ڴ浥Ԫ
	//���һ������nextΪ0����Ϊ��β���
	
	//Listʵ����ӵ�е�����
	private int head;//��ǰ������ı�ͷ�±�
	
	//Ϊ���������һ����Ԫ���ڴ棬���Ǵӵ�1����Ч���ݿ�ʼ����
	private static int alloc() throws Exception  { 
		int ptr = cursorSpace[0].next;
		cursorSpace[0].next = cursorSpace[ptr].next;//�������õ�һ����Ч����
		if(ptr == 0){
			throw new Exception("Out of Memory");
		}
		return ptr;
	}
	
	private static void free(int ptr){
		//���Խ�p��ָ����ڴ浥Ԫ�ÿ�
		cursorSpace[ptr].data = null;
		//��p�������ĵ�������ȥ���ص���һ����Ч���ݵ�λ��
		cursorSpace[ptr].next = cursorSpace[0].next;
		cursorSpace[0].next = ptr;
	}
	
	
	//���¾�Ϊʵ������
	public CursorList() throws Exception{
		//����һ��������
		head = alloc();//Ϊ��ͷ������һ����λ���ڴ�
		cursorSpace[head].next = 0;//��ͷ��nect�ÿ�
	}
	public boolean isEmpty(){
		return cursorSpace[head].next == 0;
	}
	public int find(Object val){
		int ptr = cursorSpace[head].next;//��һ����ЧԪ�ص�λ��
		while(cursorSpace[ptr].data != val && cursorSpace[ptr].next != 0){
			ptr = cursorSpace[ptr].next;//++����ȷ����
		}
		return ptr;
	}
	//���ڸ�����λ��֮��
	public void insert(Object val,int ptr) throws Exception{
		if(ptr != 0){
			//����һ����λ���ڴ�
			int newNode = alloc();
			cursorSpace[newNode].data = val;
			cursorSpace[newNode].next = cursorSpace[ptr].next;
			cursorSpace[ptr].next = newNode;
		}
	}
	public void remove(Object val){
		int prev = findPrevious(val);
		if(cursorSpace[prev].next != 0){
			//�����һ��Ԫ�ش���
			int ptr = cursorSpace[prev].next;
			cursorSpace[prev].next = cursorSpace[ptr].next;
			free(ptr);
		}
	}
	public int findPrevious(Object val){
		int ptr = cursorSpace[head].next;//��һ����ЧԪ�ص�λ��
		while(cursorSpace[ptr].next != 0 && cursorSpace[cursorSpace[ptr].next].data != val){
			ptr = cursorSpace[ptr].next;//++����ȷ����
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
