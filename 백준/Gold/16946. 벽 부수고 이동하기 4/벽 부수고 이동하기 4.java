import java.util.*;
import java.io.*;

public class Main{
	static int N;
	static int M;
	static int [][] map;
	static StringTokenizer stk;
	static int [] dy = {-1,1,0,0};
	static int [] dx = {0,0,-1,1};
	static boolean[][] v = new boolean[N][M];
	static int [][] copy;
	public static void main(String [] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		stk = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		
		map = new int[N][M];
		for(int i= 0;i<N;i++) {
			String dat = bf.readLine();
			for(int j= 0;j<M;j++) {
				map[i][j] = dat.charAt(j)-'0';
			}
		}
		v = new boolean[N][M];
		copy = new int[N][M];
		for(int i = 0;i<N;i++) {
			for(int j= 0;j<M;j++) {
				copy[i][j] = map[i][j];
			}
		}
			for(int i= 0;i<N;i++) {
				for(int j=0;j<M;j++) {
					if(map[i][j] == 0 && !v[i][j]) {
						
						bfs(i,j);
					}
				}
			}
			for(int i= 0;i<N;i++) {
				for(int j=0;j<M;j++) {
					sb.append(copy[i][j]);
				}
				sb.append("\n");
			}
			System.out.print(sb);
			
		
		
	}
	static void bfs(int sy, int sx) {
		int c = 1;	
		Queue<int []> queue = new ArrayDeque<>();
		queue.add(new int[] {sy, sx});
		v[sy][sx] = true;
		Queue<int []> temp = new ArrayDeque<>();
		while(!queue.isEmpty()) {
			int [] now = queue.poll();	
			for(int k = 0;k<4;k++) {
				int ny = now[0] + dy[k];
				int nx = now[1] + dx[k];
				if(ny>=N || ny < 0 || nx>=M||nx<0 || v[ny][nx]) continue;
				if(map[ny][nx] == 0) {
					v[ny][nx] = true;
					c++;
					queue.add(new int[] {ny, nx});
				}
				else if(!v[ny][nx] && map[ny][nx] == 1) {
					v[ny][nx] = true;
					temp.add(new int[] {ny,nx});
				}
 			}
		}
		while(!temp.isEmpty()) {
			int [] now = temp.poll();
			copy[now[0]][now[1]]+=c;
			copy[now[0]][now[1]]%=10;
			v[now[0]][now[1]] = false;
		}
	}
}