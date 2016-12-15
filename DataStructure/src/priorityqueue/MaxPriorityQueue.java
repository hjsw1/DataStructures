package priorityqueue;

/**
 * 最大优先级队列：根节点权值比左右孩子都要大 并且是一个完全二叉树，使用数组存放
 * 数组的第0号位置为空，第i号位置的父节点的位置是i/2取下界，左孩子是2i，右孩子是2i+1
 * 
 * @ClassName: MasPriorityQueue
 * @Description: TODO
 * @author NewSong
 * @date 2016年11月28日 下午2:11:53
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

	// 将数据重新建堆
	public void buildHeap() {
		for (int i = currentSize / 2; i >= 1; i--) {
			filterDown(currentSize, i);// 建立大顶堆，每次调用都排序一棵二叉树，如果还有子树那么继续调整
		}
	}

	// 结果是升序
	// 删除n-1次，每次删除获得一个有序的元素
	public void heapSort() {
		Object e = null;
		for (int i = currentSize; i > 1; i--) {
			e = data[1];
			data[1] = data[i];
			data[i] = e;
			filterDown(i - 1, 1);// 先将堆顶元素与堆的无序区的最后一个元素交换，然后重新调整为堆(每次循环后都实现了一个元素的有序，因此调整的范围减一)
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
			System.out.println("已满。无法插入");
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
			System.out.println("堆为空，不可删除");
			return null;
		}
		E res = (E) data[1];
		data[1] = data[currentSize];
		currentSize--;
		filterDown(currentSize, 1);
		return res;
	}

	// 将以传入的child为孩子的所在树调整为大顶堆，并递归向上调整
	private void filterUp(int child) {
		// 当孩子比父节点大，就交换
		Object element = data[child];
		while (child != 1 && ((Comparable<E>) element).compareTo((E) data[child / 2]) > 0) {
			data[child] = data[child/2];
			child /= 2;
		}
		data[child] = element;// 放入到合适位置
	}

	// 将以传入的root为根节点的所在树（根+左孩子+右孩子）调整为大顶堆，并递归向下调整
	private void filterDown(int size, int root) {
		Object e = data[root];
		int child = 0;
		while (root * 2 <= size) {
			child = root * 2;
			if (child < size && ((Comparable<E>) data[child]).compareTo((E) data[child + 1]) < 0) {
				child++;
			}
			// child指向左右孩子较大的
			if (((E) e).compareTo((E) data[child]) > 0) {
				break;// 符合大顶堆的规则，直接退出
			} else {
				data[root] = data[child];
				root = child;// 继续向下调整
			}
		}
		data[root] = e;
	}

	// 层序遍历
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
