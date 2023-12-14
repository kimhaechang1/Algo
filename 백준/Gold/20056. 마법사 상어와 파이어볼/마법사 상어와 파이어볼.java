import java.util.*;
import java.io.*;


public class Main{
	static int n;
	static int m;
	static int k;
	static class Data{
		int r;
		int c;
		int m;
		int s;
		int d;
		public Data(int r, int c, int m, int s, int d) {
			super();
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}
		@Override
		public String toString() {
			return "Data [r=" + r + ", c=" + c + ", m=" + m + ", s=" + s + ", d=" + d + "]";
		}
	}
	static ArrayList<Data> datalist;
	static StringTokenizer stk;
	static int [] dy = {-1,-1,0,1,1,1,0,-1};
	static int [] dx = {0,1,1,1,0,-1,-1,-1};
	static ArrayList<Data> [][] state;
	static Queue<int []> queue;
	public static void main(String [] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		stk = new StringTokenizer(bf.readLine());
		n = Integer.parseInt(stk.nextToken());
		m = Integer.parseInt(stk.nextToken());
		k = Integer.parseInt(stk.nextToken());
		datalist = new ArrayList<>();
		for(int i = 0;i<m;i++) {
			stk = new StringTokenizer(bf.readLine());
			int y= Integer.parseInt(stk.nextToken())-1;
			int x= Integer.parseInt(stk.nextToken())-1;
			int m= Integer.parseInt(stk.nextToken());
			int s= Integer.parseInt(stk.nextToken());
			int d= Integer.parseInt(stk.nextToken());
			datalist.add(new Data(y,x,m,s,d));
		}
		
		
		while(k-- > 0) {
			move();
			queue = new ArrayDeque<>();
			initStatus();
			mark();
			findDoubleData();
			makeList();
		}
		System.out.println(getSum());
	}
	static void move() {
		ArrayList<Data> temp = new ArrayList<>();
		for(Data data : datalist) {
			int distance = data.s;
			int sy = data.r;
			int sx = data.c;
			while(distance-- > 0) {
				sy += dy[data.d];
				sx += dx[data.d];
				if(sy >= n) {
					sy = 0;
				}else if(sy < 0) {
					sy = n-1;
				}
				if(sx >= n) {
					sx = 0;
				}else if(sx < 0) {
					sx = n-1;
				}
			}
			temp.add(new Data(sy, sx, data.m,data.s, data.d));
		}
		datalist = temp;
	}
	static void initStatus() {
		state = new ArrayList[n][n];
		for(int i = 0;i<n;i++) {
			for(int j =0;j<n;j++) {
				state[i][j] = new ArrayList<>();
			}
		}
	}
	static void mark() {
		for(Data data : datalist) {
			state[data.r][data.c].add(data);
		}
	}
	static void findDoubleData() {
		for(int i = 0;i<n;i++){
			for(int j = 0;j<n;j++) {
				if(state[i][j].size() < 2) continue;
				int y = i;
				int x = j;
				int cnt = state[y][x].size();
				int oddCnt = 0;
				int evenCnt = 0;
				int messSum = 0;
				int velocitySum = 0;
				for(Data data : state[y][x]) {
					messSum += data.m;
					velocitySum += data.s;
					if(data.d == 0 || data.d % 2 ==0) {
						evenCnt++;
					}else {
						oddCnt++;
					}
				}
				state[y][x].clear();
				if(messSum < 5) continue;
				if((oddCnt == 0 && evenCnt > 0) || (oddCnt > 0 && evenCnt == 0)) {
					// 방향은 짝수
					for(int k = 0;k<8;k+=2) {
						// m s d	
						state[y][x].add(new Data(y, x, messSum/5, velocitySum/cnt, k));
					}
				}else {
					// 방향 홀수
					for(int k = 1;k<8;k+=2) {
						// m s d	
						state[y][x].add(new Data(y, x, messSum/5, velocitySum/cnt, k));
					}
				}
			}	
		}		
	}
	static void makeList() {
		ArrayList<Data> temp = new ArrayList<>();
		for(int i = 0;i<n;i++) {
			for(int j = 0;j<n;j++) {
				for(Data data : state[i][j]) {
					temp.add(data);
				}
			}
		}
		datalist = temp;
	}
	static int getSum() {
		int sum = 0;
		for(Data data : datalist) {
			sum +=data.m;
		}
		return sum;
	}
}