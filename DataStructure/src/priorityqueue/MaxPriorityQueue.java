package priorityqueue;

/**
 * ������ȼ����У����ڵ�Ȩֵ�����Һ��Ӷ�Ҫ�� ������һ����ȫ��������ʹ��������
 * ����ĵ�0��λ��Ϊ�գ���i��λ�õĸ��ڵ��λ����i/2ȡ�½磬������2i���Һ�����2i+1
 * 
 * @ClassName: MasPriorityQueue
 * @Description: TODO
 * @author NewSong
 * @date 2016��11��28�� ����2:11:53
 *
 */

@SuppressWarnings("all")
public class MaxPriorityQueue<E extends Comparable<E>> {
	private Object[] data;
	private int currentSize;
	private int maxSize;
	public static final int DEFAULT_CAPACITY = 20;

	public MaxPriorityQueue(int maxSize) {
		data = new Object[maxSize + 1];
		this.maxSize = maxSize;
	}

	public MaxPriorityQueue() {
		this(DEFAULT_CAPACITY);
	}

	public MaxPriorityQueue(E[] value) {
		this.data = new Object[value.length + 1];
		System.arraycopy(value, 0, data, 1, value.length);
		maxSize = value.length;
		currentSize = value.length;
	}

	public MaxPriorityQueue(int size, E[] value) {
		if (size < value.length) {
			System.out.println("Input Error");
		}
		this.data = new Object[size + 1];
		System.arraycopy(value, 0, data, 1, value.length);
		maxSize = size;
		currentSize = value.length;
	}

	// ���������½���
	public void buildHeap() {
		for (int i = currentSize / 2; i >= 1; i--) {
			filterDown(currentSize, i);// �����󶥶ѣ�ÿ�ε��ö�����һ�ö��������������������ô��������
		}
	}

	// ���������
	// ɾ��n-1�Σ�ÿ��ɾ�����һ�������Ԫ��
	public void heapSort() {
		Object e = null;
		for (int i = currentSize; i > 1; i--) {
			e = data[1];
			data[1] = data[i];
			data[i] = e;
			filterDown(i - 1, 1);// �Ƚ��Ѷ�Ԫ����ѵ������������һ��Ԫ�ؽ�����Ȼ�����µ���Ϊ��(ÿ��ѭ����ʵ����һ��Ԫ�ص�������˵����ķ�Χ��һ)
		}
	}

	public int size() {
		return currentSize;
	}

	public E findMax() {
		return (E) data[1];
	}

	public void insert(E e) {
		if (isFull()) {
			System.out.println("�������޷�����");
			return;
		}
		currentSize++;
		data[currentSize] = e;
		filterUp(currentSize);
	}

	public void clear() {
		for (int i = 0; i < data.length; ++i) {
			data[i] = null;
		}
		currentSize = 0;
	}

	public boolean isFull() {
		return currentSize == maxSize;
	}

	public boolean isEmpty() {
		return currentSize == 0;
	}

	public E removeMax() {
		if (isEmpty()) {
			System.out.println("��Ϊ�գ�����ɾ��");
			return null;
		}
		E res = (E) data[1];
		data[1] = data[currentSize];
		currentSize--;
		filterDown(currentSize, 1);
		return res;
	}

	// ���Դ����childΪ���ӵ�����������Ϊ�󶥶ѣ����ݹ����ϵ���
	private void filterUp(int child) {
		// �����ӱȸ��ڵ�󣬾ͽ���
		Object element = data[child];
		while (child != 1 && ((Comparable<E>) element).compareTo((E) data[child / 2]) > 0) {
			data[child] = data[child/2];
			child /= 2;
		}
		data[child] = element;// ���뵽����λ��
	}

	// ���Դ����rootΪ���ڵ������������+����+�Һ��ӣ�����Ϊ�󶥶ѣ����ݹ����µ���
	private void filterDown(int size, int root) {
		Object e = data[root];
		int child = 0;
		while (root * 2 <= size) {
			child = root * 2;
			if (child < size && ((Comparable<E>) data[child]).compareTo((E) data[child + 1]) < 0) {
				child++;
			}
			// childָ�����Һ��ӽϴ��
			if (((E) e).compareTo((E) data[child]) > 0) {
				break;// ���ϴ󶥶ѵĹ���ֱ���˳�
			} else {
				data[root] = data[child];
				root = child;// �������µ���
			}
		}
		data[root] = e;
	}

	// �������
	public void print() {
		for (int i = 1; i <= currentSize; ++i) {
			System.out.print(data[i] + " ");
		}
		System.out.println();
	}
	
	//Done!
	public static void main(String[] args) {
		Integer[] data = { 20, 12, 35, 15, 10, 80, 30, 17, 2, 1 };
		MaxPriorityQueue<Integer> queue = new MaxPriorityQueue<>(20, data);
		queue.buildHeap();
		queue.print();

//		queue.insert(32);
//		queue.print();

//		queue.removeMax();
//		queue.print();
		queue.heapSort();
		queue.print();
	}
}
