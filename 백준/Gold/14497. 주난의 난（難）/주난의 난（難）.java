import java.util.*;
import java.io.*;

public class Main{
	static int N;
	static int M;
	static char [][] map;
	static StringTokenizer stk;
	static int ty,tx, sy, sx;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		stk = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		map = new char[N][M];
		bf.readLine();
		for(int i = 0;i<N;i++) {
			String str = bf.readLine();
			for(int j=0;j<M;j++) {
				map[i][j]  = str.charAt(j);
				if(map[i][j] == '#') {
					ty = i;
					tx = j;
				}else if(map[i][j] == '*') {
					sy = i;
					sx = j;
				}
			}
		}
		int t=0;
		while(true) {
			++t;
			if(bfs()) {
				break;
			}
		}
		System.out.println(t);
	}
	static int [] dy = {-1,1,0,0};
	static int [] dx = {0,0,-1,1};
	
	static boolean bfs() {
		boolean [][] v = new boolean[N][M];
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] {sy, sx});
		int [][] cnt = new int[N][M];
		v[sy][sx] = true;
		while(!queue.isEmpty()) {
			int [] now = queue.poll();
			for(int k = 0;k<4;k++) {
				int ny = now[0]+dy[k];
				int nx = now[1]+dx[k];
				if(ny >= N || ny < 0 || nx>=M || nx < 0 || v[ny][nx]) continue;
				if(map[ny][nx] == '1') {
					v[ny][nx] = true;
					cnt[ny][nx]++;
				}else if(map[ny][nx] == '#') {
					return true;
				}else if(map[ny][nx] == '0') {
					v[ny][nx] = true;
					queue.add(new int[] {ny, nx});
				}
			}
		}
		for(int i= 0;i<N;i++) {
			for(int j =0;j<M;j++) {
				if(cnt[i][j] > 0) map[i][j] = '0';
			}
		}
		return false;
	}

}