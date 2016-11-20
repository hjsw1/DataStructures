package charpter3;

import java.util.Stack;

public class ParenthesisMatching {
	public static  void match(String exprssion){
		Stack<Integer> stack = new Stack<>();
		char [] expr = exprssion.toCharArray();
		int leftBracketIndex = 0;
		for(int i = 0; i < expr.length;i++){
			if(expr[i] == '('){
				stack.push(i);//�����±�
			}else if(expr[i] == ')'){
				if(!stack.isEmpty()){
					leftBracketIndex = stack.pop();
					System.out.println("������λ��:"+leftBracketIndex +"   ������λ��:"+i);
				}else{
					System.err.println("No match for right parenthesis at "+i);//������û����֮��Ӧ��������
				}
			}
		}
		while(!stack.isEmpty()){
			leftBracketIndex = stack.pop();
			System.err.println("No match for left parenthesis at "+leftBracketIndex);
		}
	}
	public static void main(String[] args) {
		match("(d+(a+b)*c*(d+e)�Cf))(()");
	}
}
