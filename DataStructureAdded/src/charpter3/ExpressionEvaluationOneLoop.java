package charpter3;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ExpressionEvaluationOneLoop {

	/**
	 * ���ʽջ
	 */
	private Stack<Character> exprStack;
	/**
	 * ���ջ
	 */
	private Stack<Double> resultStack;

	private String infix;
	private Map<Character, Double> params;

	public ExpressionEvaluationOneLoop(String infix, Map<Character, Double> params) {
		exprStack = new Stack<>();
		resultStack = new Stack<>();
		this.infix = infix;
		this.params = params;
	}

	/**
	 * ������
	 */
	public void execute() {
		exprStack.push('#');
		char ch = 0;
		char op = 0;
		for (int i = 0; i < infix.length(); i++) {
			ch = infix.charAt(i);
			if (Character.isAlphabetic(ch)) {
				resultStack.push(params.get(ch));// ��������ֱ�ӽ����ջ
			} else if (ch == ')') {// ���������ţ����ϳ�ջ���ֱ�������ų�ջ
				for (op = exprStack.pop(); op != '('; op = exprStack.pop()) {
					doOperator(op);// �Բ��������ԣ���Ҫ�����ջ��ջ���Σ��ٽ���������ջ
				}
			} else {// �����������ź�����������
					// isp��ջ������������ȼ���icp�Ǳ��ʽ������������ȼ�����ͬ������������isp�����ȼ���ͣ���icp�����ȼ����
				for (op = exprStack.pop(); isp(op) > icp(ch); op = exprStack.pop()) {
					// ��ջ����������ȼ����ڵ�ǰ��������ȼ�ʱ����������Ͳ��ϳ�ջ���
					// ջ����������ȼ���ͣ�ջ����������ȼ����
					doOperator(op);
				}
				exprStack.push(op);
				// ����ʱ����ǰ�������ջ
				exprStack.push(ch);
			}
		}
		while (!exprStack.isEmpty()) {
			// ��ջ��ʣ��δ��ջ��ȫ����ջ��ȫ���������
			ch = exprStack.pop();
			if (ch == '#') {
				break;
			} else {
				doOperator(ch);
			}
		}
		System.out.println(resultStack.peek());
	}

	/**
	 * ����������в���
	 * 
	 * @param operator
	 */
	private void doOperator(char operator) {
		double left = 0, right = 0;
		if (!resultStack.isEmpty()) {
			right = resultStack.pop();
		} else {
			System.err.println("���ʽ�����޷�ȡ���Ҳ�����");
			resultStack.clear();
			return;
		}
		if (!resultStack.isEmpty()) {
			left = resultStack.pop();
		} else {
			System.err.println("���ʽ�����޷�ȡ���������");
			resultStack.clear();
			return;
		}

		double result = 0;
		switch (operator) {
		case '+':
			result = left + right;
			break;
		case '-':
			result = left - right;
			break;
		case '*':
			result = left * right;
			break;
		case '/':
			if (Math.abs(right) < 1e-6) {
				System.err.println("����Ϊ��!");
			} else {
				result = left / right;
			}
			break;
		case '^':
			result = Math.pow(left, right);
			break;
		}
		resultStack.push(result);
	}

	/**
	 * �ж����ȼ�
	 * 
	 * @param ch
	 * @return
	 */
	private static int icp(char ch) {
		switch (ch) {
		case '#':
			return 0;
		case '(':
			return 6;
		case '*':
		case '/':
		case '^':
			return 6;
		case '+':
		case '-':
			return 2;
		case ')':
			return 1;
		}
		return 0;
	}

	private static int isp(char ch) {
		switch (ch) {
		case '#':
			return 0;
		case '(':
			return 1;
		case '*':
		case '/':
		case '^':
			return 5;
		case '+':
		case '-':
			return 3;
		case ')':
			return 6;
		}
		return 0;
	}

	public static void main(String[] args) {
		String infix = "(a+b)*(c-e^f)/d";
		Map<Character, Double> params = new HashMap<>();
		params.put('a', 3.0);
		params.put('b', 1.0);
		params.put('c', 5.0);
		params.put('d', 2.0);
		params.put('e', 3.0);
		params.put('f', 2.0);
		ExpressionEvaluationOneLoop program = new ExpressionEvaluationOneLoop(infix, params);
		program.execute();
	}
}
