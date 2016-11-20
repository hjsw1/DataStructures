package algo;

import java.util.Arrays;

public class RankSort {
	public static void rankSort(int []arr,int[]rank){
		rank(arr,rank);
		rearrange(arr,rank);
	}
	
	public static void rank(int[] arr, int[] rank) {
		for(int i = 0; i < rank.length;i++){
			rank[i] = 0;
		}
		for(int i = 1;i < arr.length;i++){
			for(int j = 0; j < i;j++){
				if(arr[i] >= arr[j]){
					rank[i]++;
				}else{
					rank[j]++;
				}
			}
		}
	}

	public static void rearrange(int[] arr,int[]rank) {
		for(int i = 0; i < arr.length;i++){
			while(i != rank[i]){
				int idx = rank[i];
				swap(arr,i,idx);
				swap(rank,i,idx);
			}
			//每次while循环起码可以调整i元素至合适的位置
		}
	}

	private static void swap(int[] arr, int i, int j) {
		int t = arr[i];
		arr[i] = arr[j];
		arr[j] = t;
	}
	
	public static void main(String[] args) {
		int[] arr = {0 ,2, 10, 3, 5};
		int[] rank = new int[5];
		RankSort.rankSort(arr,rank);
		System.out.println(Arrays.toString(arr));
	}
}
