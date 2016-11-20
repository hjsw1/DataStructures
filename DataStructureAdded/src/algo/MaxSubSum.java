package algo;

/**
 * ��������У�����a1,a2,a3,a4.... an �����и��� ���ĳ�������У����ĺ������������������
 * �����еĳ��ȿ�����0,1,2,3.����
 * �������Ϊ0������ֵΪ0
 * ��������к�һ�����ڵ���0
 * @author songx
 *
 */
public class MaxSubSum {
	//�㷨1
	//����ѭ��
	//ǰ����ѭ���õ����е�������
	//������ѭ������ÿ�������еĺ�
	//i: 0 ~ n
	//j: i ~ n
	//k: i ~ j
	public static int maxSubSum1(int []arr){
		int maxSum = 0;
		for(int i = 0; i < arr.length;i++){
			for(int j = i; j < arr.length;j++){
				//��i��j�ǵ�ǰ������
				int sum = 0;
				for(int k = i;k <= j;k++){
					sum += arr[k];
				}
				if(sum > maxSum){
					maxSum = sum;
				}
			}
		}
		return maxSum;
	}
	
	//�㷨2
	//��ʵ������ѭ����û�б�Ҫ��
	//�ڵڶ��ص�ѭ��ʱ�Ϳ�����������еĺ�
	public static int maxSubSum2(int []arr){
		int maxSum = 0;
		for(int i = 0; i < arr.length;i++){
			int sum = 0;
			for(int j = i; j < arr.length;j++){
				//��i��j�ǵ�ǰ������
				sum += arr[j];
				if(sum > maxSum){
					maxSum = sum;
				}
			}
		}
		return maxSum;
	}
	
	//�㷨3
	//ʹ�÷��η�����������
	//�֣���Ϊ����������ȵ������⣬Ȼ��ݹ����
	//�Σ�������Ľ�ϲ�
	
	//4 -3 5 -2 | -1 2 6 -2
	//���ֻ���������
	//��ߵ����������
	//�ұߵ����������
	//�����ߵ������У����м��Ԫ����������������У������������������
	//���������������Ϊ����֮�� 
	public static int maxSubSumRec(int []arr,int left,int right){
		if(left == right){
			if(arr[left] > 0){
				return arr[left];
			}else{
				return 0;
			}
		}
		int center = (left + right)/2;
		int maxLeftSubSum = maxSubSumRec(arr, left, center);
		int maxRightSubSum = maxSubSumRec(arr,center+1,right);
		
		int maxLeftBorderSum = 0,leftBorderSum = 0;
		for(int i = center; i >= left; i--){
			leftBorderSum += arr[i];
			if(leftBorderSum > maxLeftBorderSum){
				maxLeftBorderSum = leftBorderSum;
			}
		}
		int maxRightBorderSum = 0,rightBorderSum = 0;
		for(int i = center+1; i <= right; i++){
			rightBorderSum += arr[i];
			if(rightBorderSum > maxRightBorderSum){
				maxRightBorderSum = rightBorderSum;
			}
		}
		return max(maxLeftSubSum,maxRightSubSum,maxLeftBorderSum+maxRightBorderSum);
	}
	
	
	private static int max(int i,int j, int k ){
		return Math.max(Math.max(i, j), k);
	}
	
	public static void main(String[] args) {
		int []arr = {4,-3,5,-2,-1,2,6,-2};
		System.out.println(maxSubSum1(arr));
		System.out.println(maxSubSum2(arr));
		System.out.println(maxSubSumRec(arr, 0, arr.length-1));
	}
}
