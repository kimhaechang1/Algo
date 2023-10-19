import java.util.*;
import java.io.*;

public class Main{
	static int [][] map;
	static boolean [][][] v;
	static int ty,tx,sy,sx;
	static StringTokenizer stk;
	static int [] dy = {-1,1,0,0};
	static int [] dx = {0,0,-1,1};
	static int N, M;
	public static void main(String [] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		// bfs
		stk = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		stk = new StringTokenizer(bf.readLine());
		sy = Integer.parseInt(stk.nextToken())-1;
		sx = Integer.parseInt(stk.nextToken())-1;
		stk = new StringTokenizer(bf.readLine());
		ty = Integer.parseInt(stk.nextToken())-1;
		tx = Integer.parseInt(stk.nextToken())-1;
		map = new int[N][M];
		v = new boolean[N][M][2];
		for(int i=0;i<N;i++) {
			stk = new StringTokenizer(bf.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		System.out.println(bfs());
	}
	static int bfs() {
		Queue<int []> queue = new ArrayDeque<>();
		int [][] cost = new int[N][M];
		queue.add(new int[] {sy,sx,0,0});
		v[sy][sx][0] = true;
		while(!queue.isEmpty()) {
			int [] now = queue.poll();
			if(now[0] == ty && now[1] == tx) return now[2];
			for(int k = 0;k<4;k++) {
				int ny = now[0] + dy[k];
				int nx = now[1] + dx[k];
				if(ny >= N || ny < 0 || nx>= M || nx < 0) continue;
				if(!v[ny][nx][now[3]] && map[ny][nx] == 0) {
					v[ny][nx][now[3]] = true;
					queue.add(new int[] {ny, nx, now[2]+1, now[3]});
				}else if(map[ny][nx] == 1) {
					if(!v[ny][nx][now[3]] && now[3] == 0) {
						v[ny][nx][now[3]+1] = true;
						queue.add(new int[] {ny, nx, now[2]+1, now[3]+1});
					}
				}
			}
		}
		return -1;
	}
}