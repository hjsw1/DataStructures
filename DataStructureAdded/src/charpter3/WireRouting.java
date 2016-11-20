package charpter3;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 布线寻路 找到两点之间最近的路径 可以横着走或竖着走
 * 
 * 步骤: 1.搜索过程 先从位置a(3,2)开始,把a可到达的相邻方格都表为1(表示与a相距为1).
 * 注意:具体实现时,将a位置置为2,其它相邻方格为a位置的值+1 然后把标记为1的方格可到达的相邻方格都标记为2(表示与a相距为2).
 * 这里需要什么数据结构? 标记过程继续进行下去,直至到达b或找不到可到达的相邻方格为止. 本例中,当到达b时,b上的表记为9(实现时为9+2=11)
 * 2.构造a---b的路径.从b回溯到a. 这里需要什么数据结构? 从b出发,并将b的位置保存在path中.首先移动到比b的编号小1的相邻
 * 位置上(5,6) 接着再从当前位置继续移动到比当前标号小1的相邻位置上. 重复这一过程,直至到达a.
 * 
 * @author songx
 *
 */
public class WireRouting {
	private int grid[][];
	public static final int UNREACHABLE = -2;
	public static final int NOTASSIGNED = -1;
	public static final int UP = 0;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;
	private Position[] offset;
	private Position[] path;

	public WireRouting(int n) {
		grid = new int[n + 2][n + 2];
		for (int i = 0; i < n + 2; i++) {
			grid[0][i] = grid[n + 1][i] = UNREACHABLE;
			grid[i][0] = grid[i][n + 1] = UNREACHABLE;
		}
		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < n + 1; j++) {
				grid[i][j] = NOTASSIGNED;
			}
		}

		// 初始化方向数据
		offset = new Position[4];
		for (int i = 0; i < offset.length; i++) {
			offset[i] = new Position();
		}
		offset[UP].row = -1;
		offset[UP].col = 0;
		offset[DOWN].row = 1;
		offset[DOWN].col = 0;
		offset[LEFT].row = 0;
		offset[LEFT].col = -1;
		offset[RIGHT].row = 0;
		offset[RIGHT].col = 1;
	}

	public boolean findPath(Position start, Position finish) throws CloneNotSupportedException {
		if (start.equals(finish)) {
			return true;
		}
		Position here = (Position) start.clone();
		grid[here.row][here.col] = 0;// 开始位置的值为0
		Position nearby = new Position();
		Queue<Position> queue = new ArrayDeque<>();
		// 下面是填充标记
		while (true) {
			for (int i = UP; i <= RIGHT; i++) {
				nearby.row = here.row + offset[i].row;
				nearby.col = here.col + offset[i].col;
				if (grid[nearby.row][nearby.col] == NOTASSIGNED) {
					grid[nearby.row][nearby.col] = grid[here.row][here.col] + 1;
//					System.out.println(nearby.row+" "+nearby.col+" "+grid[nearby.row][nearby.col]);
					// 加一
					if (nearby.equals(finish)) {
						break;
					} else {
						queue.add((Position) nearby.clone());
					}
				}
			}
			if (nearby.equals(finish)) {
				break;// 退出
			} else if (queue.isEmpty()) {
				return false;// 空栈表示找不到路
			} else {
				here = queue.remove();
			}
		}
//		printGrid();
//		System.out.println();
		// 回溯
		path = new Position[grid[finish.row][finish.col]];
		here = finish;
		// 从后往前
		for (int i = path.length - 1; i >= 0; i--) {
			path[i] = (Position) here.clone();
			// 保存当前位置
			for (int j = UP; j <= RIGHT; j++) {
				nearby.row = here.row + offset[j].row;
				nearby.col = here.col + offset[j].col;
				if (grid[nearby.row][nearby.col] == i) {
					break;// 找到合适的位置了，就不再找了
				}
			}
			here = (Position) nearby.clone();
		}
		return true;
	}

	public void printGrid() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				System.out.print(grid[i][j]+"\t");
			}
			System.out.println();
		}
	}

	public void printPath() {
		for (int i = 0; i < path.length; i++) {
			System.out.println(path[i].row + "," + path[i].col);
		}
	}
	//输入从1开始的下标
	public void setObstacle(int x,int y){
		grid[x][y] = UNREACHABLE;
	}
	public static void main(String[] args) throws CloneNotSupportedException {
		WireRouting routing = new WireRouting(7);
		routing.setObstacle(1, 3);
		routing.setObstacle(2, 3);
		routing.setObstacle(2, 4);
		routing.setObstacle(3, 5);
		routing.setObstacle(4, 4);
		routing.setObstacle(4, 5);
		routing.setObstacle(5, 5);
		routing.setObstacle(5, 1);
		routing.setObstacle(6, 1);
		routing.setObstacle(6, 2);
		routing.setObstacle(6, 3);
		routing.setObstacle(7, 1);
		routing.setObstacle(7, 2);
		routing.setObstacle(7, 3);
		if (routing.findPath(new Position(3, 2), new Position(4, 6))) {
			// 这里的坐标是从1开始的，因为四周被填充了
			routing.printPath();
		}
		routing.printGrid();
	}
}

class Position {
	public int row;
	public int col;

	public Position() {
		// TODO Auto-generated constructor stub
	}

	public Position(int row, int col) {
		this.row = row;
		this.col = col;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (col != other.col)
			return false;
		if (row != other.row)
			return false;
		return true;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		Position p = new Position(row, col);
		return p;
	}
}
