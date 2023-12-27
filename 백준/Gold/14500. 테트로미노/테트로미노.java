 import java.util.*;
 import java.io.*;
public class Main {
	static int N;
	static int M;
	static int [][] map;
	static StringTokenizer stk;
	static int max;
	static boolean [][] v;
	static int [] dy = {-1,0,1,0};
	static int [] dx = {0,1,0,-1};
	
	public static void main(String[] args) throws Exception{
		// 4개를 고르기만 하면 테트로미노가 가능하다.
		// 근데  BFS로 풀면 같은 모양에서 대칭이나 돌렸을 경우를 완탐으로 뽑으면 시간복잡도가 터진다.
		// 따라서 이전까지의 모양에서 좌표만 바꾸어 주면서 칸 4개를 골랐을때의 sum 구하면된다.
		// ㅗ ㅓ ㅏ ㅜ 모양은 dfs로 하기 힘들다
		// 하지만 위의 모양은 중심 점을 기준으로 3개의 인접한 좌표를 뽑는 경우와 동일하다.
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		stk = new StringTokenizer(bf.readLine());		
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		v = new boolean[N][M];
		map = new int[N][M];
		max = -999999999;
		for(int i= 0;i<N;i++) {
			stk = new StringTokenizer(bf.readLine());
			for(int j= 0;j<M;j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		for(int i= 0;i<N;i++) {
			for(int j= 0;j<M;j++) {
				v[i][j] = true;
				dfs(i,j,0,map[i][j]);
				v[i][j] = false;
				comb(i,j,0,0,map[i][j]);
			}
		}
		System.out.println(max);
	}
	
	static void dfs(int y, int x, int cnt, int sum) {
		if(cnt == 3) {
			if(max < sum) {
				max = sum;
			}
			return;
		}
		
		for(int k = 0;k<4;k++) {
			int ny = y+dy[k];
			int nx = x+dx[k];
			if(ny >= N || ny < 0 || nx>=M || nx< 0 || v[ny][nx]) continue;
			v[ny][nx] = true;
			dfs(ny, nx, cnt+1, sum+(map[ny][nx]));
			v[ny][nx] = false;
		}
	}
	static void comb(int y, int x, int start, int cnt, int sum) {
		if(cnt == 3) {
			if(max < sum) {
				max = sum;
			}
			return;
		}
		for(int k = start;k<4;k++) {
			int ny = y+dy[k];
			int nx = x+dx[k];
			if(ny >= N || ny < 0 || nx>=M || nx< 0) continue;
			comb(y,x,k+1, cnt+1, sum+(map[ny][nx]));
		}
	}
}