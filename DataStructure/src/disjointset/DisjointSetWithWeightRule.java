package disjointset;

/**
 * ʹ������ԭ��
 * 
 * @ClassName: DisjointSetWithWeightRule
 * @Description: TODO
 * @author NewSong
 * @date 2016��12��6�� ����11:29:03
 *
 */
public class DisjointSetWithWeightRule {
	private int[] parent;//���ڸ��ڵ���ԣ� ����������Ľ����Ŀ�����к���+�Լ���������������㣬��Ÿ��ڵ���±�
	private boolean[] isRoot;// ÿ������Ƿ�Ϊ���ڵ�

	public DisjointSetWithWeightRule(int capacity) {
		parent = new int[capacity + 1];
		isRoot = new boolean[capacity + 1];
		for(int i = 0;i < parent.length;++i){
			parent[i] = 1;
			isRoot[i] = true;
		}
	}
	
	public int find(int e){
		while(!isRoot[e]){
			e = parent[e];
		}
		return e;
	}
	
	public void union(int i, int j){
		if(parent[i] < parent[j]){//i��j������
			parent[j] += parent[i];
			isRoot[i] = false;
			parent[i] = j;
		}else{
			parent[i] += parent[j];
			isRoot[j] = false;
			parent[j] = i;
		}
	}
}
