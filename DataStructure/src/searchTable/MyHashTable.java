package searchTable;

public class MyHashTable<E> {
	private Entry<E>[] value;
	private int currentSize;
	public static final int DEFAULT_TABLE_SIZE = 11;
	public static double loadFactor = 0.75;
	// װ������Խ�󣬾�Խ���׳��ֳ�ͻ

	public MyHashTable() {
		this(DEFAULT_TABLE_SIZE);
	}

	@SuppressWarnings("unchecked")
	public MyHashTable(int size) {
		value = new Entry[nextPrime(size)];
		size = 0;
	}

	public void clear() {
		for (int i = 0; i < value.length; i++) {
			value[i] = null;
		}
		this.currentSize = 0;
	}

	public boolean add(E data) {
		if (data == null || contains(data)) {
			return false;
		}
		int pos = hash(data);
		value[pos] = new Entry<E>(data);
		currentSize++;
		if (1.0 * currentSize / value.length >= loadFactor) {
			rehash();
		}
		return true;
	}

	public void remove(E data) {
		if(isActive(data)){
			value[hash(data)].isActive = false;
		}
		currentSize--;
	}

	public boolean contains(E data) {
		if (isActive(data)) {
			return true;
		} else {
			return false;
		}
	}

	// ���Ԫ�صĵ�ַ��������ɢ�еĹ���
	// ʹ��ƽ��̽����ɢ��f(i)=f(i-1)+2i-1
	private int hash(E data) {
		int delta = 1;
		int pos = data.hashCode() % value.length;
		while (value[pos] != null && !value[pos].data.equals(data)) {
			// ��û�ҵ�����λ��ʱ
			pos += 2 * delta - 1;
			pos %= value.length;
			delta++;
		}
		return pos;
	}

	@SuppressWarnings("unchecked")
	private void rehash() {
		Entry<E>[] oldValue = value;
		value = new Entry[nextPrime(value.length * 2)];
		currentSize = 0;
		for (int i = 0; i < oldValue.length; i++) {
			if (oldValue[i] != null && oldValue[i].isActive) {
				add(oldValue[i].data);
			}
		}
	}

	public void traverse() {
		for (int i = 0; i < value.length; i++) {
			if (value[i] != null && isActive(value[i].data)) {
				System.out.print(value[i] + " ");
			}
		}
		System.out.println();
	}

	private static boolean isPrime(int x) {
		for (int i = 2; i < x; ++i) {
			if (x % i == 0) {
				return false;
			}
		}
		return true;
	}

	// ���ظ���x����һ������
	private static int nextPrime(int x) {
		while (true) {
			if (isPrime(x)) {
				return x;
			}
			x++;
		}
	}

	private boolean isActive(E data){
		int pos = hash(data);
		return value[pos] != null && value[pos].isActive;
	}
	
	// ��̬�ڲ��࣬�������ⲿnew���������ҷ������ⲿ���޹�
	private static class Entry<E> {
		E data;
		boolean isActive = true;

		public Entry(E data) {
			this.data = data;
		}

		@Override
		public String toString() {
			return "" + data;
		}
	}

	public static void main(String[] args) {
		MyHashTable<Integer> hashtable = new MyHashTable<>();
//		Random r = new Random();
//		for (int i = 0; i < 30; i++) {
//			hashtable.add("" + (char) (r.nextInt(60) + 65) + (char) (r.nextInt(60) + 65));
//		}
		for(int i = 0 ; i < 20; ++ i){
			hashtable.add(i);
		}
		hashtable.traverse();
		hashtable.remove(3);
		hashtable.traverse();
		
		System.out.println(hashtable.contains(12));
		
	}
}
