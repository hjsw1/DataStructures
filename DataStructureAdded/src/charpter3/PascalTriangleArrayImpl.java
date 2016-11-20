package charpter3;

public class PascalTriangleArrayImpl {
	
	public static void print(int n) {
		int [][] matrix = new int[n][];
		//n行
		
		//填充
		for(int i = 0; i < n ;i++){
			matrix[i] = new int[i+2];
			matrix[i][0] = 1;
			matrix[i][i+1] = 1;//每行开头结尾必为1
			for(int j = 1; j <= i;j++){
				matrix[i][j] = matrix[i-1][j-1] + matrix[i-1][j];
			}//计算下一行元素
		}
		//打印
		for(int i = 0; i < matrix.length; i++){
			for(int j = 0; j < n-1;j++){
				System.out.print(" ");
			}
			for(int j = 0; j < matrix[i].length;j++){
				System.out.print(" "+matrix[i][j]);
			}
			System.out.println();
		}
	}
	public static void main(String[] args) {
		PascalTriangleArrayImpl.print(4);
	}
}
