import java.util.*;
import java.io.*;

public class Main{
	static StringTokenizer stk;
	static int n;
	static int k;
	static int r;
	static int [][] map;
	static int [] dy = {-1,1,0,0};
	static int [] dx = {0,0,-1,1};
	static boolean [][][][] isConnected;
	static boolean [][] isCan;
	static int [][] info;
	public static void main(String [] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		stk = new StringTokenizer(bf.readLine());
		n = Integer.parseInt(stk.nextToken());
		k = Integer.parseInt(stk.nextToken());
		r = Integer.parseInt(stk.nextToken());
		isConnected = new boolean[n][n][n][n];
		map = new int[n][n];
		// 어디서 어디로는 다리가 있는지를 적는다.
		for(int i= 0;i<r;i++) {
			stk = new StringTokenizer(bf.readLine());
			int y1 = Integer.parseInt(stk.nextToken());
			int x1 = Integer.parseInt(stk.nextToken());
			int y2 = Integer.parseInt(stk.nextToken());
			int x2 = Integer.parseInt(stk.nextToken());
			--y1;--x1;--y2;--x2;
			isConnected[y1][x1][y2][x2] = true;
			isConnected[y2][x2][y1][x1] = true;
		}
		// 결국 인접한 목초지는 일반적으로 건널 수 있으나, 다리를 통해서만 만날 수 있는 쌍을 구해야 한다.
		isCan = new boolean[k][k];
		info = new int[k][2];
		for(int i=0;i<k;i++) {
			stk = new StringTokenizer(bf.readLine());
			int y = Integer.parseInt(stk.nextToken());
			int x = Integer.parseInt(stk.nextToken());
			--y;--x;
			info[i][0] = y;
			info[i][1] = x;
			map[y][x] = i+1;
		}
		for(int i= 0;i<k;i++) {
			bfs(info[i], i);
		}
		int cnt = 0;
		for(int i= 0;i<k-1;i++) {
			for(int j= i+1;j<k;j++) {
				if(!isCan[i][j]) {
					cnt++;
				}
			}
		}
		System.out.print(cnt);
		
		
	}
	static void bfs(int [] info, int cur) {
		boolean [][] v  = new boolean[n][n];
		Queue<int []> queue = new ArrayDeque<>();
		queue.add(new int[] {info[0], info[1]});
		v[info[0]][info[1]] = true;
		while(!queue.isEmpty()) {
			int [] now = queue.poll();
			if(!((info[0] == now[0] && info[1] == now[1])) && map[now[0]][now[1]] > 0) {
				int other = map[now[0]][now[1]];
				if(!isCan[cur][other-1]) {
					
					isCan[cur][other-1] = true;
					isCan[other-1][cur] = true;
				}
				
			}
			for(int dir = 0;dir<4;dir++) {
				int ny = now[0] + dy[dir];
				int nx = now[1] + dx[dir];
				if(OOB(ny, nx) || v[ny][nx] || isConnected[now[0]][now[1]][ny][nx]) continue;
				v[ny][nx] = true;
				queue.add(new int[] {ny, nx});
			}
		}
	}
	static boolean OOB(int y, int x) {
		return y >= n || y < 0 || x >= n || x < 0;
	}
}