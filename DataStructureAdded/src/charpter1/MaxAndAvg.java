package charpter1;

public class MaxAndAvg {
	public int max(int[] arr,int n) {
		if(n == 0){
			return arr[0];
		}else{
			int t = max(arr,n-1);
			return t > arr[n] ? t:arr[n];
		}
	}
	
	public double average(int[] arr, int n) {
		if(n == 0){
			return arr[0];
		}else{
			return (average(arr, n-1)*n+arr[n])/(n+1);
		}
	}
	
	public static void main(String[] args) {
		MaxAndAvg test = new MaxAndAvg();
		int [] arr = {2,3,4,5};
		System.out.println(test.max(arr,arr.length-1));
		System.out.println(test.average(arr, arr.length-1));
	}
}
