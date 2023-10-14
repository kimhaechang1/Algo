import java.util.*;
import java.io.*;

public class Main{
	static int M;
	static int N;
	static int [][] map;
	static int [][] dp;
	static StringTokenizer stk;
	static int [] dy = {-1,1,0,0};
	static int [] dx = {0,0,-1,1};
	public static void main(String [] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		stk = new StringTokenizer(bf.readLine());
		M = Integer.parseInt(stk.nextToken());
		N = Integer.parseInt(stk.nextToken());
		map= new int[M][N];
		for(int i= 0;i<M;i+=1) {
			stk = new StringTokenizer(bf.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		dp = new int[M][N];
		// 하향식으로 바꾸자
		
		for(int i= 0;i<M;i+=1) {
			for(int j=0;j<N;j++) {
				dp[i][j] = -1;
			}
		}
		dp[M-1][N-1] = 1;
		dfs(0,0);
		
		System.out.println(dp[0][0]);
		
	}
	// 특정 칸에서 도착지점까지 갈 수 있는 경우
	// 그 경로들을 카운트 해야한다.
	
	static int dfs(int sy, int sx) {
		if(sy == M-1 && sx == N-1) return 1;
		if(dp[sy][sx] != -1) {
			return dp[sy][sx];
		}
		dp[sy][sx] = 0;
		for(int k = 0;k<4;k++) {
			int ny = sy+dy[k];
			int nx = sx+dx[k];
			if(ny >= M || ny < 0 || nx>= N || nx<0) continue;
			if(map[ny][nx] < map[sy][sx]) {
				dp[sy][sx] += dfs(ny, nx);
			}
		}
		
		
		return dp[sy][sx];
	}
}