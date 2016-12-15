package charpter4;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BiTree<E extends Comparable<E>> implements Cloneable {
	private TreeNode<E> root;
	private static int index = -1;
	// ����ʹ����������ַ�������
	private LinkedList<TreeNode<E>> pathList;
	// ��������Ӹ��ڵ㵽����Ҷ�ӽ�������·��

	public BiTree() {
	}

	public BiTree(TreeNode<E> root) {
		this.root = root;
	}

	public int depth() {
		return depth(root);
	}

	private int depth(TreeNode<E> subTree) {
		if (subTree == null) {
			return 0;
			// �������Ϊ0���ݹ��������ʼ����
		} else {
			int Ldepth = depth(subTree.Lchild);
			int Rdepth = depth(subTree.Rchild);
			return Ldepth > Rdepth ? (Ldepth + 1) : (Rdepth + 1);
		}
	}

	// �������Ľ�����
	public int size() {
		return size(root);
	}

	private int size(TreeNode<E> subTree) {
		if (subTree == null) {
			return 0;
		} else {
			int Lsize = size(subTree.Lchild);
			int Rsize = size(subTree.Rchild);
			return Lsize + Rsize + 1;
		}
	}

	public TreeNode<E> parent(TreeNode<E> element) {
		if (element == null) {
			return null;
		}
		return parent(root, element);
	}

	// ��һ������subTree��ָ�ý��֮�½����������ڶ�������element��Ҫ�Ҹ��ڵ���ӽڵ�
	private TreeNode<E> parent(TreeNode<E> subTree, TreeNode<E> element) {
		TreeNode<E> parent;
		if (subTree == null) {
			return null;
		} else {
			if (subTree.Lchild == element || subTree.Rchild == element) {
				return subTree;
			} else {
				parent = parent(subTree.Lchild, element);
				// ���������еݹ�����element�ĸ��ڵ�
				return parent != null ? parent : parent(subTree.Rchild, element);
				// ��������������ҵ��ˣ��ͷ��أ�û���ҵ�������������������
				// �����û���ҵ����ͷ���null
			}
		}
	}

	public TreeNode<E> leftChild(TreeNode<E> node) {
		if (node == null) {
			return null;
		}
		return node.Lchild;
	}

	public TreeNode<E> rightChild(TreeNode<E> node) {
		if (node == null) {
			return null;
		}
		return node.Rchild;
	}

	public TreeNode<E> root() {
		return this.root;
	}

	public void destroy() {
		destory(root);
		root = null;
	}

	// ���ٲ��ú������
	// ����ʱҪ�������������������������������Ϊnull���������ֿ�ָ���쳣
	private void destory(TreeNode<E> subTree) {
		if (subTree != null) {
			destory(subTree.Lchild);
			destory(subTree.Rchild);
			subTree = null;
		}
	}

	// ���Ӧ��ÿ�ֱ�����ʽ�����У�����������������
	public void clear(TreeNode<E> subTree) {
		if (subTree != null) {
			subTree.data = null;
			clear(subTree.Lchild);
			clear(subTree.Rchild);
		}
	}

	public void preOrder(TreeNode<E> subTree) {
		if (subTree != null) {
			System.out.print(subTree.data + " ");
			preOrder(subTree.Lchild);
			preOrder(subTree.Rchild);
		}
	}

	public void inOrder(TreeNode<E> subTree) {
		if (subTree != null) {
			inOrder(subTree.Lchild);
			System.out.print(subTree.data + " ");
			inOrder(subTree.Rchild);
		}
	}

	public void postOrder(TreeNode<E> subTree) {
		if (subTree != null) {
			postOrder(subTree.Lchild);
			postOrder(subTree.Rchild);
			System.out.print(subTree.data + " ");
		}
	}

	// �ǵݹ� �������
	public void noRecPreOrder(TreeNode<E> subTree) {
		Stack<TreeNode<E>> stack = new Stack<>();
		while (subTree != null || !stack.isEmpty()) {
			while (subTree != null) {
				System.out.print(subTree.data + " ");
				stack.push(subTree);
				subTree = subTree.Lchild;// ����������
			}
			if (!stack.isEmpty()) {
				subTree = stack.pop();
				subTree = subTree.Rchild;// ����������
			}
		}
	}

	// �ǵݹ� �������
	public void noRecInOrder(TreeNode<E> subTree) {
		Stack<TreeNode<E>> stack = new Stack<>();
		// subTree��Ϊ�գ����ʾ���ڣ���Ҫ���з��ʣ�ջ��Ϊ�ձ�ʾû��ȫ�������ꡣֻҪ��ǰû�б����������û�б����꣬������ѭ����
		// ���subTree��Ϊ�գ����ȷ��������������ڴ�֮ǰ��Ҫ�������ջ��ÿ�η���������֮ǰ������ǰ�����ջ��
		// ���Ϊ�գ���ʾ�����������ڣ���ô��ջ�����������������½���ѭ����
		while (subTree != null || !stack.isEmpty()) {
			while (subTree != null) {
				// while��ʾ���ϵط�������������ֱ���ﵽ����������׶�
				stack.push(subTree);
				subTree = subTree.Lchild;// ����������
			}
			if (!stack.isEmpty()) {
				subTree = stack.pop();
				System.out.print(subTree.data + " ");// ����������
				subTree = subTree.Rchild;// ����������
			}
		}
	}

	/**
	 * ʹ��tag������ǵڼ��η��� tag = 0 ����δ���� tag = 1 ���Ѿ����ʹ����ȴ����Ĵ�ӡ
	 * 
	 * @param subTree
	 */
	public void noRecPostOrderUseTag(TreeNode<E> subTree) {
		Stack<StackNode<E>> stack = new Stack<>();
		StackNode<E> stkPtr = null;
		while (true) {
			while (subTree != null) {
				stkPtr = new StackNode<>(subTree, 0);
				stack.push(stkPtr);
				subTree = subTree.Lchild;
			}
			// �����ƶ�����ѹջ
			stkPtr = stack.pop();
			subTree = stkPtr.ptr;

			// ��tag == 1ʱ
			while (stkPtr.tag == 1) {// ������������
				System.out.print(subTree.data + " ");
				if (stack.isEmpty()) {
					return;
				} else {
					stkPtr = stack.pop();
					subTree = stkPtr.ptr;
				}
			}
			// ��tag == 0ʱ
			// ������������
			stkPtr.tag = 1;
			stack.push(stkPtr);
			subTree = subTree.Rchild;
		}
	}

	// ���������ʹ��queue
	public void levelOrder() {
		Queue<TreeNode<E>> queue = new ArrayDeque<>();
		TreeNode<E> node = root;
		while (node != null) {
			System.out.print(node.data + " ");
			if (node.Lchild != null) {
				queue.add(node.Lchild);
			}
			if (node.Rchild != null) {
				queue.add(node.Rchild);
			}
			if (!queue.isEmpty()) {
				node = queue.poll();
			} else {
				return;
			}
		}
		System.out.println();
	}

	public int leafSize() {
		return leafSize(root);
	}

	private int leafSize(TreeNode<E> subTree) {
		if (subTree == null) {
			return 0;
		} else if (subTree.Lchild == null && subTree.Rchild == null) {
			return 1;
		} else {
			// �������Ҷ�ӽ�㣬��ô�ֱ�����������Ҷ�ӽ�����������Ҷ�ӽ�㣬��Ӻ󷵻�
			return leafSize(subTree.Lchild) + leafSize(subTree.Rchild);
		}
	}

	// �������ĸ��ƣ���������һģһ���Ķ�����
	// ������������ķ�ʽ
	public BiTree<E> clone() {
		BiTree<E> tree = new BiTree<>();
		tree.root = clone(root);
		return tree;
	}

	private TreeNode<E> clone(TreeNode<E> subTree) {
		if (subTree != null) {
			TreeNode<E> newNode = new TreeNode<>(subTree.data);
			newNode.Lchild = clone(subTree.Lchild);
			newNode.Rchild = clone(subTree.Rchild);
			return newNode;
		} else {
			return null;
		}
	}

	// ʹ����������ַ���������ֻ�ܴ����洢�ַ��Ķ�����
	public static BiTree<Character> createBiTree(String preStr) {
		char[] pre = preStr.toCharArray();
		BiTree<Character> tree = new BiTree<>(createSubTree(pre));
		index = -1;
		return tree;
	}

	private static TreeNode<Character> createSubTree(char[] pre) {
		index++;
		TreeNode<Character> subTree = null;
		if (pre[index] != '#') {
			subTree = new TreeNode<>(pre[index]);
			subTree.Lchild = createSubTree(pre);
			subTree.Rchild = createSubTree(pre);
		}
		return subTree;
	}

	// ����������Ӹ��ڵ㵽ÿ��Ҷ�ӽ���·��
	public void printAllBiTreePaths() {
		if (pathList == null) {
			pathList = new LinkedList<>();
		}
		printBiTreePath(root);
		pathList.clear();
	}

	private void printBiTreePath(TreeNode<E> node) {
		if (node != null) {
			pathList.addLast(node);
			if (node.Lchild == null && node.Rchild == null) {
				for (TreeNode<E> treeNode : pathList) {
					System.out.print(treeNode.data + " ");
				}
				System.out.println();
				// �������Ӹ��ڵ㵽Ҷ�ӵ�������
			} else {
				printBiTreePath(node.Lchild);
				printBiTreePath(node.Rchild);
			}
			pathList.removeLast();
			// �����굱ǰ��㣬�˳�����
		}
	}

	public static BiTree<Character> preInCreateBiTreeUseSubString(String pre, String in) {
		return new BiTree<Character>(createSubTreeUseSubString(pre, in));
	}

	// ����㷨���ã���ʹ���±ֱ꣬��ȡ�Ӵ�
	private static TreeNode<Character> createSubTreeUseSubString(String pre, String in) {
		TreeNode<Character> subTree = null;
		if (pre.length() == 0) {
			return null;
		} else {
			subTree = new TreeNode<>(pre.charAt(0));
			int mid = in.indexOf(pre.charAt(0));
			subTree.Lchild = createSubTreeUseSubString(pre.substring(1, mid + 1), in.substring(0, mid));
			subTree.Rchild = createSubTreeUseSubString(pre.substring(mid + 1), in.substring(mid + 1));
		}
		return subTree;
	}
}

class StackNode<E extends Comparable<E>> {
	TreeNode<E> ptr;
	int tag;// ���ڷǵݹ�������
	// ��һ��ѹջtag = 0
	// �ڶ���ѹջtag = 1

	public StackNode(TreeNode<E> ptr, int tag) {
		this.ptr = ptr;
		this.tag = tag;
	}

}

class TreeNode<E extends Comparable<E>> {
	E data;
	TreeNode<E> Lchild;
	TreeNode<E> Rchild;

	public TreeNode(E data) {
		this.data = data;
	}

	public TreeNode(E data, TreeNode<E> Lchild, TreeNode<E> Rchild) {
		this.data = data;
		this.Lchild = Lchild;
		this.Rchild = Rchild;
	}
}
