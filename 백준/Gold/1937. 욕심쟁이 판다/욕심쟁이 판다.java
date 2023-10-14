import java.util.*;
import java.io.*;

public class Main{
	static int N;
	static int [][] map;
	static int [][] memo;
	static int [] dy = {-1,1,0,0};
	static int [] dx = {0,0,-1,1};
	static StringTokenizer stk;
	static int max;
	public static void main(String [] args) throws Exception{
		// 판다가 최대한 많이 먹어야 한다.
		// 이전에 먹었던 곳의 원소값 보다 더 큰곳으로만 이동할 수 있다.
		// 특정 지점에서의 최대로 이동가능한 칸은 정해져있다.
		// 따라서 한 칸에 대한 최대값을 갱신하고 메모를 해놓는다.
		// 한번도 방문한 적이 없는 칸에 대해서는 -1로 초기화 해놓는다.
		
		BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		map = new int[N][N];
		memo = new int[N][N];
		for(int i= 0;i<N;i++) {
			stk = new StringTokenizer(bf.readLine());
			for(int j = 0;j<N;j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		
		for(int i = 0;i<N;i++) {
			for(int j= 0;j<N;j++) {
				memo[i][j] = -1;
			}
		}
		
		// 최소칸수는 1이다. 왜냐하면 해당칸에서 시작해서 아무곳도 이동못할 경우이므로
		max  = 1;
		for(int i = 0;i<N;i++) {
			for(int j= 0;j<N;j++) {
				if(memo[i][j] == -1) {
					dfs(i,j);
				}
			}
		}
		
		System.out.println(max);
	}
	static int dfs(int sy, int sx) {
		if(memo[sy][sx] != -1) {
			return memo[sy][sx];
		}
		memo[sy][sx] = 1;
		int ny, nx;
		for(int k= 0;k<4;k++) {
			ny = sy+dy[k];
			nx = sx+dx[k];
			// 갈수있는만큼 최대로 갔다가 리턴되어져 오면서 몇칸걸렸는지 찍는다.
			if(ny >=N || ny < 0 || nx>=N || nx < 0) continue;
			if(map[ny][nx] > map[sy][sx]) {
				int count = 1;
				count+= dfs(ny,nx);
				memo[sy][sx] = Math.max(count, memo[sy][sx]);
				max = Math.max(max, memo[sy][sx]);
			}
		}
		
		return memo[sy][sx];
		
	}
}