package charpter1;

import java.util.Arrays;

public class PermutationSelectR {
	//1~end 选取的个数为size
	//负责建立数组，数组元素个数为size，从0~size
	public void calculate(int end, int size) {
		int[] arr = new int[size];
		for (int i = 0; i < size; i++) {
			arr[i] = i + 1;
		}
		calculate(arr, end);
	}
	
	//真正进行选取
	private void calculate(int[] arr, int end) {
		System.out.println(Arrays.toString(arr));
		arr[arr.length - 1]++;
		int flag = end;//选取总数
		int index = arr.length - 1;//索引，开始指向最后一个元素
		while (arr[index] == flag + 1) {//当 当前位满了（比选取总数要大1），则进位
			if (index == 0) {//如果进位到第一位的前一位，则组合结束，直接退出
				return;
			}
			arr[--index]++;//进位
			for (int i = index + 1; i < arr.length; i++) {
				arr[i] = arr[i - 1] + 1;
			}//将进位之后的元素赋值为前一个的元素的值加一
			flag--;//之前各位的进位的阈值是后一位的加一
		}
		calculate(arr, end);//递归
	}

	public static void main(String[] args) {
		PermutationSelectR test = new PermutationSelectR();
		test.calculate(5, 3);
	}
}

/*
1 2 3
1 2 4
1 2 5
1 3 4
1 3 5
1 4 5
2 3 4
2 3 5
2 4 5
3 4 5
*/
