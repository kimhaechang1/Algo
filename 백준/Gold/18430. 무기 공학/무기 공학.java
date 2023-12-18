import java.util.*;
import java.io.*;

public class Main{
	static StringTokenizer stk;
	static int [][] map;
	static int n;
	static int m;
	static boolean [][] used;
	static int[][][] d= {
			{
				{1,0},
				{0,-1},
			},
			{
				{-1,0},
				{0,-1}
			},
			{
				{-1,0},
				{0,1}
			},
			{
				{1,0},
				{0,1}
			}
	};
	static int max;
	static int [][] memo;
	public static void main(String [] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		stk = new StringTokenizer(bf.readLine());
		n = Integer.parseInt(stk.nextToken());
		m = Integer.parseInt(stk.nextToken());
		map = new int[n][m];
		for(int i = 0;i<n;i++) {
			stk = new StringTokenizer(bf.readLine());
			for(int j = 0;j<m;j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		memo = new int[n][m];
		used = new boolean[n][m];
		max = 0;
		dfs(0,0,0);
		System.out.print(max);
	}
	static void dfs(int sy, int sx, int sum) {
		// 격자점에서 백트래킹 할 때에는
		// 격자점을 하나씩 이동시키는 경우엔 굳이 dfs로 재탐색 할 필요가 없다.
		if(sx == m) {
			sy++;
			sx = 0;
		}
		if(sy == n) {
			max = Math.max(max, sum);
			return;
		}
		if(!used[sy][sx]) {
			for(int k = 0;k<4;k++) {
				int [][] dir = d[k];
				int ny1 = sy + dir[0][0];
				int nx1 = sx + dir[0][1];
				if(ny1 >= n || ny1 < 0 || nx1 >= m || nx1 < 0 || used[ny1][nx1]) continue;
				int ny2 = sy + dir[1][0];
				int nx2 = sx + dir[1][1];
				if(ny2 >= n || ny2 < 0 || nx2 >= m || nx2 < 0 || used[ny2][nx2]) continue;
				used[ny1][nx1] = true;
				used[ny2][nx2] = true;
				used[sy][sx] = true;
				dfs(sy, sx+1, sum + (map[sy][sx] * 2 + map[ny1][nx1]+map[ny2][nx2]));
				used[ny1][nx1] = false;
				used[ny2][nx2] = false;
				used[sy][sx] = false;
			}
		}
		dfs(sy, sx+1, sum);
	}
}