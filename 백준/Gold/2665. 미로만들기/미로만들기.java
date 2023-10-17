import java.util.*;
import java.io.*;

public class Main{
	static int [][] map;
	static int N;
	static int [][] b;
	public static void main(String [] args) throws Exception{
		// bfs 문제
		BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		map = new int[N][N];
		for(int i =0;i<N;i++) {
			String str = bf.readLine();
			for(int j= 0;j<N;j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		b = new int[N][N];
		for(int i = 0;i<N;i++) {
			for(int j=0;j<N;j++) {
				b[i][j] = 987654321;
			}
		}
		bfs();
		System.out.print(b[N-1][N-1]);
	}
	static int [] dy = {-1,1,0,0};
	static int [] dx = {0,0,-1,1};
	static public void bfs() {
		Queue<int []> queue = new ArrayDeque<>();
		queue.add(new int[] {0,0,0});
		while(!queue.isEmpty()) {
			int [] now = queue.poll();
			if(map[now[0]][now[1]] == 0) {
				now[2]+=1;
			}
			if(now[2] < b[now[0]][now[1]]) {
				b[now[0]][now[1]] = now[2];
			}else {
				continue;
			}
			for(int k = 0;k<4;k++) {
				int ny = now[0] + dy[k];
				int nx = now[1] + dx[k];
				if(ny >= N || ny < 0 || nx >= N || nx < 0) continue;
				queue.add(new int[] {ny, nx, now[2]});
			}
		}
	}
}