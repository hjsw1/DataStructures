package algo;

public class GCD {
	
	//շת�����
	//ÿ��ѭ������������������������������m��n��������������,
	//Ĭ����Ҫ��m>n��
	public static int gcd(int m,int n){
		int remainder = 0;
		while(n != 0){
			remainder = m%n;
			m = n;
			n = remainder;
		}
		return m;
	}
	
	public static void main(String[] args) {
		System.out.println(gcd(50,15));
	}
}
