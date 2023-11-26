import java.util.*;
import java.io.*;

public class Solution{
	static int N;
	static int W;
	static int H;
	static int [][] map;
	static StringTokenizer stk;
	static int [] res;
	static int [][] copy;
	static int min;
	static int r;
	public static void main(String [] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t=1;t<=T;t++) {
			stk = new StringTokenizer(bf.readLine());
			N = Integer.parseInt(stk.nextToken());
			W = Integer.parseInt(stk.nextToken());
			H = Integer.parseInt(stk.nextToken());
			map = new int[H][W];
			for(int i= 0;i<H;i++) {
				stk = new StringTokenizer(bf.readLine());
				for(int j=0;j<W;j++) {
					map[i][j] = Integer.parseInt(stk.nextToken());
				}
			}
			// 중복 순열을 구하고
			// 각 순열이 결국 맨 윗줄기준 y좌표가 된다.
			// 구한 순열의 결과로 터트리는 벽돌 시뮬레이션을 돌린다.
			r= Integer.MAX_VALUE;
			copy = new int[H][W];
			res = new int[N];
			dfs(0);
			sb.append("#").append(t).append(" ").append(r).append("\n");
		}
		System.out.print(sb);
	}
	static void dfs(int depth) {
		if(depth == N) {
			// 맵 카피하기
			copymap();
			// 시뮬레이션 시작
			int k = 0;
			while(k < N) {
				int sy = -1;
				int sx = res[k];
				for(int i= 0;i<H;i++) {
					if(copy[i][sx] > 0) {
						sy= i;
						break;
					}
				}
				if(sy !=-1) {
					bfs(sy, sx);
					go();
				}
				k++;
			}
			
			int t = chk();
			if(t < r) {
				r = t;
			}
			return;
		}
		
		for(int i= 0;i<W;i++) {
			res[depth] = i;
			dfs(depth+1);
		}
	}
	static void copymap() {
		for(int i= 0;i<H;i++) {
			for(int j= 0;j<W;j++) {
				copy[i][j] = map[i][j];
			}
		}
	}
	static int chk() {
		int c = 0;
		for(int i= 0;i<H;i++) {
			for(int j= 0;j<W;j++) {
				if(copy[i][j] >= 1) c++;
			}
		}
		return c;
	}
	static void go() {
		Queue<int []> queue = new ArrayDeque<>();
		for(int i= H-1;i>-1;i--) {
			for(int j = 0;j< W;j++) {
				if(copy[i][j] >=1) {
					
					queue.add(new int[] {i,j});
				}
			}
		}
		while(!queue.isEmpty()) {
			int [] now = queue.poll();
			int ny = now[0]+dy[1];
			if(ny >= H || copy[ny][now[1]] >=1) continue;
			
			while(true) {
				
				copy[ny][now[1]] = copy[ny-1][now[1]];
				
				copy[ny-1][now[1]] = 0;
				
				ny += dy[1];
				if(ny >= H || copy[ny][now[1]] >=1) break;
			}
		}
	}
	static int [] dy= {-1,1,0,0};
	static int [] dx = {0,0,-1,1};
	static void bfs(int sy, int sx) {
		Queue<int []> queue = new ArrayDeque<>();
		queue.add(new int[] {sy, sx});
		while(!queue.isEmpty()) {
			int [] now = queue.poll();
			int offset = copy[now[0]][now[1]]-1;
			copy[now[0]][now[1]] = 0;
			
			int y = now[0];
			int x = now[1];
			// 오른쪽 
			for(int i= 0;i<offset;i++) {
				y += dy[3];
				x += dx[3];
				if(y >= H || y < 0 || x >= W || x < 0 || copy[y][x] == 0) continue;
				if(copy[y][x] == 1) copy[y][x] = 0;
				else if(copy[y][x] > 1) queue.add(new int[] {y, x});
			}
			y = now[0];
			x = now[1];
			// 왼쪽
			for(int i= 0;i<offset;i++) {
				y += dy[2];
				x += dx[2];
				if(y >= H || y < 0 || x >= W || x < 0 || copy[y][x] == 0) continue;
				if(copy[y][x] == 1) copy[y][x] = 0;
				else if(copy[y][x] > 1) queue.add(new int[] {y, x});
			}
			y = now[0];
			x = now[1];
			// 위
			for(int i= 0;i<offset;i++) {
				y += dy[0];
				x += dx[0];
				if(y >= H || y < 0 || x >= W || x < 0 || copy[y][x] == 0) continue;
				if(copy[y][x] == 1) copy[y][x] = 0;
				else if(copy[y][x] > 1) queue.add(new int[] {y, x});
			}
			y = now[0];
			x = now[1];
			// 아래
			for(int i= 0;i<offset;i++) {
				y += dy[1];
				x += dx[1];
				if(y >= H || y < 0 || x >= W || x < 0 || copy[y][x] == 0) continue;
				if(copy[y][x] == 1) copy[y][x] = 0;
				else if(copy[y][x] > 1) queue.add(new int[] {y, x});
			}
		}
	}
}