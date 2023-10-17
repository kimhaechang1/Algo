import java.util.*;
import java.io.*;

public class Main{
	static char [][] map;
	static boolean [][] v;
	static boolean canGo;
	public static void main(String[] args) throws Exception{
		// bfs 문제
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		map = new char[12][6];
		for(int i = 0;i<12;i++) {
			char [] ch = bf.readLine().toCharArray();
			for(int j = 0;j<6;j++) {
				map[i][j] = ch[j];
			}
		}
		int t = 0;
		while(true) {
			v = new boolean[12][6];
			canGo = false;
			for(int i = 11;i>-1;i--) {
				for(int j = 0;j<6;j++) {
					if(v[i][j] || map[i][j] == '.') continue;
					bfs(i,j);
				}
			}
			
			if(!canGo) {
				break;
			}else {
				
				/*for(int i = 0;i<12;i++) {
					for(int j=0;j<6;j++) {
						if(v[i][j])System.out.print(1);
						else System.out.print(0);
					}
					System.out.println();
				}*/
				go();
				/*for(int i = 0;i<12;i++) {
					for(int j=0;j<6;j++) {
						System.out.print(map[i][j]);
					}
					System.out.println();
				}*/
				t++;
			}
			

		}
		System.out.println(t);
	}
	static int [] dy = {-1,1,0,0};
	static int [] dx = {0,0,-1,1};
	static void bfs(int sy, int sx) {
		int cnt = 1;
		char val = map[sy][sx];
		v[sy][sx] = true;
		Queue<int []> temp = new ArrayDeque<>();
		Queue<int []> queue = new ArrayDeque<>();
		queue.add(new int[] {sy, sx});
		temp.add(new int[] {sy, sx});
		while(!queue.isEmpty()) {
			int [] now = queue.poll();
			for(int k = 0;k<4;k++) {
				int ny = now[0]+dy[k];
				int nx = now[1]+dx[k];
				if(ny >= 12 || ny < 0 || nx >= 6 || nx < 0 || v[ny][nx] || map[ny][nx] != val) continue;
				cnt++;
				v[ny][nx] = true;
				temp.add(new int[] {ny, nx});
				queue.add(new int[] {ny, nx});
			}
		}
		if(cnt >= 4) {
			canGo = true;
			while(!temp.isEmpty()) {
				int [] now = temp.poll();
				map[now[0]][now[1]] = '.';
			}
		}else {
			while(!temp.isEmpty()) {
				int [] now = temp.poll();
				if(now[0] == 11) continue;
				v[now[0]][now[1]] = false;
			}
		}
	}
	static void go() {
		Queue<int []> queue = new ArrayDeque<>();
		for(int i = 10;i>-1;i--) {
			for(int j = 0;j<6;j++) {
				if(map[i][j] != '.' &&!v[i][j]) {
					queue.add(new int[] {i,j});
					//System.out.println("i : "+i +" j : "+j);
				}
			}
		}
		
		while(!queue.isEmpty()) {
			int [] now = queue.poll();
			char val = map[now[0]][now[1]];
			int ny = now[0]+dy[1];
			if(ny >= 12 || map[ny][now[1]] != '.') continue;
			map[now[0]][now[1]] = '.';
			map[ny][now[1]] = val;
			queue.add(new int[] {ny, now[1]});
		}
		
	}
}