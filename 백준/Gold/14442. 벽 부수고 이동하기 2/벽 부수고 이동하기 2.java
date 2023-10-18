import java.util.*;
import java.io.*;

public class Main{
	static int N;
	static int M;
	static int K;
	static boolean [][][] v;
	static int [][] map;
	static StringTokenizer stk;
	public static void main(String [] args)throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		stk = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		K = Integer.parseInt(stk.nextToken());
		v = new boolean[N][M][K+1];
		map = new int[N][M];
		for(int i= 0;i<N;i++) {
			String str = bf.readLine();
			for(int j= 0;j<M;j++) {
				map[i][j] = str.charAt(j)-'0';
			}
		}
		if(N == 1 && M == 1) {
			System.out.println(1);
			// 시작과 동시에 도착할 수도 있다는 것 확인하기
		}else {
			System.out.println(bfs());			
		}
	}
	static int[] dy = {-1,1,0,0};
	static int[] dx = {0,0,-1,1};
	static int bfs() {
		int time = -1;
		Queue<int []> queue = new LinkedList<>();
		queue.add(new int[] {0,0,1,0});
		v[0][0][0] = true;
		while(!queue.isEmpty()) {
			int [] now = queue.poll();
			int bc = now[3];
			int c  = now[2];
			for(int k = 0;k<4;k++) {
				int ny = now[0]+dy[k];
				int nx = now[1]+dx[k];
				if(ny >= N || ny < 0 || nx>=M || nx<0 ) continue;
				if(ny == N-1 && nx == M-1) {
					return c+1;
				}
				if(map[ny][nx] == 1 && bc+1 <= K && !v[ny][nx][bc]) {
					v[ny][nx][bc] = true;
					queue.add(new int [] {ny, nx, c+1, bc+1});
				}
				
				if(map[ny][nx] == 0 && !v[ny][nx][bc]) {
					v[ny][nx][bc] = true;
					queue.add(new int [] {ny, nx, c+1, bc});
				}
			}	
		}
		return time;
	}
}