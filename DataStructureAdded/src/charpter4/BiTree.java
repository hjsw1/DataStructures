package charpter4;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BiTree<E extends Comparable<E>> implements Cloneable {
	private TreeNode<E> root;
	private static int index = -1;
	// 用于使用先序遍历字符串建树
	private LinkedList<TreeNode<E>> pathList;
	// 用于输出从根节点到各个叶子结点的所有路径

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
			// 空树深度为0，递归结束，开始返回
		} else {
			int Ldepth = depth(subTree.Lchild);
			int Rdepth = depth(subTree.Rchild);
			return Ldepth > Rdepth ? (Ldepth + 1) : (Rdepth + 1);
		}
	}

	// 二叉树的结点个数
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

	// 第一个参数subTree是指该结点之下进行搜索，第二个参数element是要找父节点的子节点
	private TreeNode<E> parent(TreeNode<E> subTree, TreeNode<E> element) {
		TreeNode<E> parent;
		if (subTree == null) {
			return null;
		} else {
			if (subTree.Lchild == element || subTree.Rchild == element) {
				return subTree;
			} else {
				parent = parent(subTree.Lchild, element);
				// 在左子树中递归搜索element的父节点
				return parent != null ? parent : parent(subTree.Rchild, element);
				// 如果在左子树中找到了，就返回；没有找到，就在右子树中搜索
				// 如果都没有找到，就返回null
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

	// 销毁采用后序遍历
	// 销毁时要先销毁左子树和右子树，最后将自身设为null，否则会出现空指针异常
	private void destory(TreeNode<E> subTree) {
		if (subTree != null) {
			destory(subTree.Lchild);
			destory(subTree.Rchild);
			subTree = null;
		}
	}

	// 清空应该每种遍历方式都可行，在这里采用先序遍历
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

	// 非递归 先序遍历
	public void noRecPreOrder(TreeNode<E> subTree) {
		Stack<TreeNode<E>> stack = new Stack<>();
		while (subTree != null || !stack.isEmpty()) {
			while (subTree != null) {
				System.out.print(subTree.data + " ");
				stack.push(subTree);
				subTree = subTree.Lchild;// 访问左子树
			}
			if (!stack.isEmpty()) {
				subTree = stack.pop();
				subTree = subTree.Rchild;// 访问右子树
			}
		}
	}

	// 非递归 中序遍历
	public void noRecInOrder(TreeNode<E> subTree) {
		Stack<TreeNode<E>> stack = new Stack<>();
		// subTree不为空，则表示存在，需要进行访问；栈不为空表示没有全部遍历完。只要当前没有遍历完或整体没有遍历完，都进入循环体
		// 如果subTree不为空，则先访问其左子树，在此之前需要将结点入栈（每次访问左子树之前都将当前结点入栈）
		// 如果为空，表示左子树不存在，那么出栈，访问右子树，重新进入循环体
		while (subTree != null || !stack.isEmpty()) {
			while (subTree != null) {
				// while表示不断地访问其左子树，直至达到左子树的最底端
				stack.push(subTree);
				subTree = subTree.Lchild;// 访问左子树
			}
			if (!stack.isEmpty()) {
				subTree = stack.pop();
				System.out.print(subTree.data + " ");// 访问数据域
				subTree = subTree.Rchild;// 访问右子树
			}
		}
	}

	/**
	 * 使用tag来标记是第几次访问 tag = 0 是尚未访问 tag = 1 是已经访问过，等待最后的打印
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
			// 向左移动，并压栈
			stkPtr = stack.pop();
			subTree = stkPtr.ptr;

			// 当tag == 1时
			while (stkPtr.tag == 1) {// 从右子树回来
				System.out.print(subTree.data + " ");
				if (stack.isEmpty()) {
					return;
				} else {
					stkPtr = stack.pop();
					subTree = stkPtr.ptr;
				}
			}
			// 当tag == 0时
			// 从左子树回来
			stkPtr.tag = 1;
			stack.push(stkPtr);
			subTree = subTree.Rchild;
		}
	}

	// 层序遍历，使用queue
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
			// 如果不是叶子结点，那么分别求左子树的叶子结点和右子树的叶子结点，相加后返回
			return leafSize(subTree.Lchild) + leafSize(subTree.Rchild);
		}
	}

	// 二叉树的复制，返回整棵一模一样的二叉树
	// 采用先序遍历的方式
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

	// 使用先序遍历字符串建树，只能创建存储字符的二叉树
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

	// 输出二叉树从根节点到每个叶子结点的路径
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
				// 输出链表从根节点到叶子的数据域
			} else {
				printBiTreePath(node.Lchild);
				printBiTreePath(node.Rchild);
			}
			pathList.removeLast();
			// 处理完当前结点，退出链表
		}
	}

	public static BiTree<Character> preInCreateBiTreeUseSubString(String pre, String in) {
		return new BiTree<Character>(createSubTreeUseSubString(pre, in));
	}

	// 这个算法更好，不使用下标，直接取子串
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
	int tag;// 用于非递归后序遍历
	// 第一次压栈tag = 0
	// 第二次压栈tag = 1

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
