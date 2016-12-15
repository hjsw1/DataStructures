package disjointset;

public class DisjointSet {
	private int [] parent;
//	parent[i] = j  i���Լ���ֵ��ͬʱҲ���±꣩��j�Ǹ��ڵ��ֵ��ͬʱҲ���±꣩
	//�����е�0��λ�ò��洢Ԫ�أ�parent[���ڵ��±�] = 0
	public DisjointSet(int capacity) {
		parent = new int[capacity + 1];
		for(int i = 1 ; i < parent.length ;++i){
			parent[i] = 0;//Ĭ�϶��Ǹ��ڵ�
		}
	}
	
	//���Ҹ�Ԫ�صĸ��ڵ㣨������һ�ѵȼ��ࣩ
	public int find(int e){
		while(parent[e] != 0){
			e = parent[e];
		}
		return e;
	}
	//�����ȼ���Ҫ�ϲ���i��j�ֱ��ǵȼ���ĸ��ڵ��±�
	public void union(int i, int j){
		parent[i] = j;
	}
	
	public void print(){
		for(int i = 1; i < parent.length;++i){
			System.out.print(parent[i]+" ");
		}
		System.out.println();
	}
	
}
