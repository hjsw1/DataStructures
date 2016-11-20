package algo;

import java.util.Arrays;

public class Perm {
	private char[] arr;
	public Perm(char[] arr) {
		this.arr = arr;
	}
	private void swap(int i,int j){
		char t = arr[i];
		arr[i] = arr[j];
		arr[j] = t;
	}
	public void perm(){
		perm(0,arr.length-1);
	}
	//先确定第一个元素，然后对余下的元素全排列
	//当所有元素都确定时，输出，返回
	//交换是从尾部元素开始交换的，最后交换首元素
	private void perm(int begin,int end){
		if(begin == end){
			System.out.println(Arrays.toString(arr));
		}else{
			for(int i = begin ; i<= end;i++){
				swap(begin,i);
				perm(begin+1,end);
				swap(begin,i);
			}
		}
	}
	
	public static void main(String[] args) {
		char [] arr = {'a','b','c','d'};
		Perm perm =  new Perm(arr);
		perm.perm();
	}
}
/*	arr = {1,2,3} 
*  第一步：执行 perm({1,2,3},0,2),begin=0,end=2; 
*      i=0,因此执行perm({1,2,3},1,2),begin=1,end=2; 
*          i=1,swap(arr,0,0)-->arr={1,2,3},  perm({1,2,3},2,2),begin=2,end=2; 
*              因为begin==end，因此输出数组{1,2,3} 
*          swap(arr,1,1) --> arr={1,2,3}; 
*          i=2,swap(arr,1,2)-->arr={1,3,2},  perm({1,3,2},2,2),begin=2,end=2; 
*              因为begin==end，因此输出数组{1,3,2} 
*          swap(arr,2,1) --> arr={1,2,3}; 
*      i=1,swap(arr,0,1) --> arr={2,1,3},     perm({2,1,3},1,2),begin=1,end=2; 
*          i=1,swap(arr,1,1)-->arr={2,1,3}   perm({2,1,3},2,2),begin=2,end=2; 
*              因为begin==end，因此输出数组{2,1,3} 
*          swap(arr,1,1)--> arr={2,1,3}; 
*          i=2,swap(arr,1,2)后 arr={2,3,1},并执行perm({2,3,1},2,2),begin=2,end=2; 
*              因为begin==end，因此输出数组{2,3,1} 
*          swap(arr,2,1) --> arr={2,1,3}; 
*      swap(arr,1,0)  --> arr={1,2,3} 
*      i=2,swap(arr,2,0) --> arr={3,2,1},执行perm({3,2,1},1,2),begin=1,end=2; 
*          i=1,swap(arr,1,1) --> arr={3,2,1} , perm({3,2,1},2,2),begin=2,end=2; 
*              因为begin==end，因此输出数组{3,2,1} 
*          swap(arr,1,1) --> arr={3,2,1}; 
*          i=2,swap(arr,2,1) --> arr={3,1,2},并执行perm({2,3,1},2,2),begin=2,end=2; 
*              因为begin==end，因此输出数组{3,1,2} 
*          swap(arr,2,1) --> arr={3,2,1}; 
*      swap(arr,0,2) --> arr={1,2,3} 
*/
