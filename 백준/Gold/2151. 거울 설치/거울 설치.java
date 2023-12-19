import java.util.*;
import java.io.*;

public class Main{
	static int n;
	static char [][] map;
	static int [][] doorPos;
	static int [] dy = {-1,1,0,0};
	static int [] dx = {0,0,-1,1};
	static int [][][] v;
	public static void main(String [] args) throws Exception{
		BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(bf.readLine());
		map = new char[n][n];
		doorPos = new int[2][2];
		int idx= 0;
		int cnt = 0;
		for(int i = 0;i<n;i++) {
			String elem = bf.readLine();
			for(int j = 0;j<n;j++) {
				map[i][j] = elem.charAt(j);
				if('#' == map[i][j]) {
					doorPos[idx][0] = i;
					doorPos[idx++][1] = j;
				}
			}
		}
		bfs(doorPos[0][0], doorPos[0][1], doorPos[1][0], doorPos[1][1]);
		Arrays.sort(v[doorPos[1][0]][doorPos[1][1]]);
		System.out.print(v[doorPos[1][0]][doorPos[1][1]][0]);
	}
	static void bfs(int sy, int sx, int ty, int tx) {
		//System.out.println("sy : "+sy +" sx : "+sx +" ty : "+ty+" tx : "+tx);
		Queue<int []> queue = new ArrayDeque<>();
		v = new int[n][n][4];
		for(int k = 0;k<4;k++) {
			for(int i = 0;i<n;i++) {
				for(int j = 0;j<n;j++) {
					v[i][j][k] = 999999;
				}
			}
		}
		for(int k = 0;k<4;k++) {
			queue.add(new int[] {sy,sx,k, 0});
			v[sy][sx][k] = 0;
		}
		while(!queue.isEmpty()) {
			
			int [] now = queue.poll();
			
			int ny= now[0] + dy[now[2]];
			int nx= now[1] + dx[now[2]];
			
			if(ny>=n || ny < 0 || nx>=n || nx<0 || map[ny][nx] == '*' || v[ny][nx][now[2]] <= now[3]) continue;
			v[ny][nx][now[2]] = now[3];
			if(map[ny][nx] == '!') {			
				if(now[2] == 0 || now[2] == 1) {
					queue.add(new int[] {ny, nx, 2, now[3]+1});
					queue.add(new int[] {ny, nx, 3, now[3]+1});
				}else if(now[2] == 2 || now[2] == 3) {
					queue.add(new int[] {ny, nx, 0, now[3]+1});
					queue.add(new int[] {ny, nx, 1, now[3]+1});
				}
				queue.add(new int[] {ny, nx, now[2], now[3]});
			}else {
				if(now[0] == ty && now[1] == tx) {
					continue;
				}else {
					queue.add(new int[] {ny, nx, now[2], now[3]});
				}
			}
		}
	}
}