package charpter3;

import java.util.Stack;

public class ParenthesisMatching {
	public static  void match(String exprssion){
		Stack<Integer> stack = new Stack<>();
		char [] expr = exprssion.toCharArray();
		int leftBracketIndex = 0;
		for(int i = 0; i < expr.length;i++){
			if(expr[i] == '('){
				stack.push(i);//保存下标
			}else if(expr[i] == ')'){
				if(!stack.isEmpty()){
					leftBracketIndex = stack.pop();
					System.out.println("左括号位置:"+leftBracketIndex +"   右括号位置:"+i);
				}else{
					System.err.println("No match for right parenthesis at "+i);//右括号没有与之对应的左括号
				}
			}
		}
		while(!stack.isEmpty()){
			leftBracketIndex = stack.pop();
			System.err.println("No match for left parenthesis at "+leftBracketIndex);
		}
	}
	public static void main(String[] args) {
		match("(d+(a+b)*c*(d+e)–f))(()");
	}
}
