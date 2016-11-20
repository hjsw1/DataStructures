package charpter3;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ExpressionEvaluation {
	private static int icp(char ch){
		switch(ch){
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
	private static int isp(char ch){
		switch(ch){
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
	
	public static String toPostfix(String infix){
		StringBuilder sb = new StringBuilder();
		Stack<Character> stack = new Stack<>();
		stack.push('#');
		char ch = 0;
		char op = 0;
		for(int i = 0; i < infix.length(); i++){
			ch = infix.charAt(i);
			if(Character.isAlphabetic(ch)){
				sb.append(ch);//������ֱ�����
			}else if(ch == ')'){//���������ţ����ϳ�ջ���ֱ�������ų�ջ
				for(op = stack.pop();op != '(';op = stack.pop()){
					sb.append(op);
				}
			}else{//�����������ź������
				//isp��ջ������������ȼ���icp�Ǳ��ʽ������������ȼ�����ͬ������������isp�����ȼ���ͣ���icp�����ȼ����
				for(op = stack.pop();isp(op) > icp(ch);op = stack.pop()){
					//��ջ����������ȼ����ڵ�ǰ��������ȼ�ʱ����������Ͳ��ϳ�ջ���
					sb.append(op);
				}
				//����ʱ����ǰ�������ջ
				stack.push(op);
				stack.push(ch);
			}
		}
		while(!stack.isEmpty()){
			//��ջ��ʣ��δ��ջ��ȫ����ջ
			sb.append(stack.pop());
		}
		System.out.println(sb.toString());
		return sb.toString();
	}
	
	
	public static void main(String[] args) {
		String infix = "(a+b)*(c-e^f)/d";
		Calculator calc = new Calculator();
		Map<Character,Double> params = new HashMap<>();
		params.put('a', 3.0);
		params.put('b', 1.0);
		params.put('c', 5.0);
		params.put('d', 2.0);
		params.put('e', 3.0);
		params.put('f', 2.0);
		calc.run(ExpressionEvaluation.toPostfix(infix), params);
	}
}

/**
 * ���ڼ����׺ʽ
 * @author songx
 *
 */
class Calculator{
	private Stack<Double> stack;
	public Calculator() {
		stack = new Stack<>();
	}
	
	public void run(String expr,Map<Character,Double> params){
		char ch = 0;
		for(int i = 0; i < expr.length(); i++){
			ch  = expr.charAt(i);
			switch(ch){
			//���������
			case '+':
			case '-':
			case '*':
			case '/':
			case '^':
				doOperator(ch);
				break;
			case '#'://����
				break;
			//���������
			default:
//				System.out.println("Map:"+ch);
				addOperand(params.get(ch));
				break;
			}
		}
		System.out.println("Result:"+stack.peek());
	}
	
	private void addOperand(double operand){
		stack.push(operand);
	}
	
	private void doOperator(char operator){
		double left = 0 ,right = 0;
		if(!stack.isEmpty()){
			right = stack.pop();
		}else{
			System.err.println("���ʽ�����޷�ȡ���Ҳ�����");
			stack.clear();
			return ;
		}
		if(!stack.isEmpty()){
			left = stack.pop();
		}else{
			System.err.println("���ʽ�����޷�ȡ���������");
			stack.clear();
			return ;
		}
		
		double result = 0;
		switch(operator){
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
			if(Math.abs(right) < 1e-6){
				System.err.println("����Ϊ��!");
			}else{
				result = left / right;
			}
			break;
		case '^':
			result = Math.pow(left, right);
			break;
		}
		stack.push(result);
	}
}