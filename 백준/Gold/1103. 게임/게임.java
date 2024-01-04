import java.util.*;
import java.io.*;

public class Main{
	static int n;
	static int m;
	static char [][] map;
	static StringTokenizer stk;
	static int [] dy=  {-1,1,0,0};
	static int [] dx = {0,0,-1,1};
	static boolean isInf;
	static int [][] dp;
	static boolean [][] v;
	public static void main(String [] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		stk = new StringTokenizer(bf.readLine());
		n = Integer.parseInt(stk.nextToken());m= Integer.parseInt(stk.nextToken());
		map = new char[n][m];
		for(int i = 0;i<n;i++) {
			map[i] = bf.readLine().toCharArray();
		}
		isInf = false;
		dp = new int[n][m];
		
		for(int i = 0;i<n;i++) {
			Arrays.fill(dp[i], -1);
		}
		v = new boolean[n][m];
		v[0][0] = true;
		dfs(0,0, -1, -1);

		System.out.println(isInf ? -1 : dp[0][0]);
	
	}
	static int dfs(int y, int x, int py, int px) {
		if(isInf) return -1;
		if(dp[y][x] != -1) {
			return dp[y][x];
		}
		
		dp[y][x] = 1;
		int dis = map[y][x]-'0';
		for(int dir = 0;dir<4;dir++) {
			int ny = y + dy[dir] * dis;
			int nx = x + dx[dir] * dis;
			if(OOB(ny, nx) || map[ny][nx] == 'H') continue;
			if(v[ny][nx]) {
				isInf = true;
				return -1;
			}
			v[ny][nx] = true;
			int c = 1;
			c += dfs(ny, nx, y, x);
			dp[y][x] = Math.max(c, dp[y][x]);
			v[ny][nx] = false;
		}
		return dp[y][x];
		
	}
	static boolean OOB(int y, int x) {
		return y >= n || y < 0 || x>= m || x < 0;
	}
}