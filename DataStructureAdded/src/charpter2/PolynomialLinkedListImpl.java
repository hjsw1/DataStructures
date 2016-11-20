package charpter2;

public class PolynomialLinkedListImpl {
	private Node head;// 带有头结点的
	private Node tail;

	public PolynomialLinkedListImpl() {
		head = new Node(0, 0, tail);
		tail = head;
	}

	public PolynomialLinkedListImpl(String expression) {
		this();
		parse(expression);
	}

	private String removeBracket(String term) {
		if (term.charAt(0) == '(') {
			return term.substring(1, term.length() - 1);
		} else {
			return term;
		}
	}

	private void parse(String expression) {
		String[] terms = expression.split("[+]");
		int coef = 0, exp = 0;
		for (String term : terms) {
			term = removeBracket(term);
			String[] parts = term.split("x\\^");
			coef = Integer.parseInt(parts[0]);
			exp = parts.length == 2 ? Integer.parseInt(parts[1]) : 0;
			insert(coef, exp);
		}
	}

	public void clear() {
		head.next = null;
		if (head != tail) {
			tail = null;
		}
	}

	public void insert(int coef, int exp) {
		Node newNode = new Node(coef, exp, null);
		tail.next = newNode;
		tail = newNode;
	}

	// 相加后左右多项式均丢弃
	public PolynomialLinkedListImpl add(PolynomialLinkedListImpl rhs) {
		PolynomialLinkedListImpl sum = new PolynomialLinkedListImpl();
		Node pLhs = head.next, pRhs = rhs.head.next, pSum = sum.tail;
		// pSum是sum的尾指针
		while (pLhs != null && pRhs != null) {
			if (pLhs.exp == pRhs.exp) {
				pLhs.coef += pRhs.coef;
				pRhs = pRhs.next;// 移动rhs指针
				if (pLhs.coef != 0) {
					pSum.next = pLhs;// 连接，如果抵消了那么就不再连接
					pSum = pLhs;
				}
				pLhs = pLhs.next;// 移动lhs指针
			} else if (pLhs.exp > pRhs.exp) {// 次数小的放在前
				pSum.next = pRhs;// 连接
				pSum = pRhs;
				pRhs = pRhs.next;// 移动rhs指针
			} else if (pLhs.exp < pRhs.exp) {
				pSum.next = pLhs;// 连接
				pSum = pLhs;
				pLhs = pLhs.next;// 移动lhs指针
			}
		}
		if (pLhs != null) {
			pSum.next = pLhs;
		} else if (pRhs != null) {
			pSum.next = pRhs;
		}
		return sum;
	}

	@Override
	public String toString() {
		Node ptr = head.next;
		StringBuilder sb = new StringBuilder();
		while (ptr != null) {
			sb.append(ptr.coef + (ptr.exp == 0 ? "" : ("x^" + ptr.exp + " ")));
			if (ptr.next != null) {
				sb.append(" + ");
			}
			ptr = ptr.next;
		}
		return sb.toString();
	}

	class Node {
		public Node(int coef, int exp, Node next) {
			super();
			this.coef = coef;
			this.exp = exp;
			this.next = next;
		}

		int coef;
		int exp;
		Node next;
	}

	public static void main(String[] args) {
		PolynomialLinkedListImpl p = new PolynomialLinkedListImpl("2+2x^3+23x^12+(-9x^23)");
		PolynomialLinkedListImpl p2 = new PolynomialLinkedListImpl("(-1)+3x^1+(-5x^3)+3x^8");
		PolynomialLinkedListImpl sum = p.add(p2);
		System.out.println(sum);
	}
}
