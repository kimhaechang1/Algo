import java.util.*;
import java.io.*;

public class Main{
	static int [][] map;
	static int n;
	static int q;
	static StringTokenizer stk;
	static int [] ls;
	static int [] dy = {-1,1,0,0};
	static int [] dx = {0,0,-1,1};
	public static void main(String [] args) throws Exception{
		BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
		stk = new StringTokenizer(bf.readLine());
		n = Integer.parseInt(stk.nextToken());
		q = Integer.parseInt(stk.nextToken());
		map = new int[(int)Math.pow(2, n)][(int)Math.pow(2, n)];
		ls = new int[q];
		for(int i = 0;i<map.length;i++) {
			stk = new StringTokenizer(bf.readLine());
			for(int j = 0;j<map[0].length;j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		stk = new StringTokenizer(bf.readLine());
		for(int i =0;i<q;i++) {
			ls[i] = Integer.parseInt(stk.nextToken());
		}
		for(int i = 0;i<q;i++) {
			magic(ls[i]);
			melt();
		}
		System.out.println(getSum());
		System.out.print(getMax());
	}
	static void magic(int phase) {
		int n = map.length;
		int [][] res = new int[n][n];
		int len = (int)Math.pow(2, phase);
		for(int i = 0 ;i<n;i += (int)Math.pow(2, phase)) {
			for(int j = 0;j<n;j += (int)Math.pow(2, phase)) {
				rotate(len, i, j, res);
			}
		}
		// 원본배열에 반영
		for(int i = 0;i<n;i++) {
			for(int j = 0;j<n;j++) {
				map[i][j] = res[i][j];
			}
		}
	}
	static void rotate(int len, int sy, int sx, int [][] res) {
		for(int l = len;l > 0; l-=2) {
			// 위쪽 90도
			
			int y = sy;
			for(int i = sx;i<sx+l;i++) {
				res[y++][sx+l-1] = map[sy][i];
			}
			// 오른쪽 90도 회전
			int x = sx + l-1;
			for(int i = sy;i<sy+l;i++) {
				res[sy+l-1][x--] = map[i][sx + l-1];
			}
			// 아래쪽 90도 회전
			y = sy+l-1;
			for(int i = sx+l-1;i>sx-1;i--) {
				res[y--][sx] = map[sy+l-1][i];
			}
			// 왼쪽 90도 회전
			x = sx;
			for(int i = sy+l-1;i>sy-1;i--) {
				res[sy][x++] = map[i][sx];
			}
			sy +=1;
			sx +=1;
		}
	}
	static void melt() {
		int n = map.length;
		int [][] res = new int[n][n];
		for(int i= 0;i<n;i++) {
			for(int j = 0;j<n;j++) {
				if(map[i][j] == 0) continue;
				int cnt = 0;
				for(int k = 0;k<4;k++) {
					int ny= i + dy[k];
					int nx = j + dx[k];
					if(ny >= n || ny < 0 || nx>=n || nx < 0 || map[ny][nx] == 0) continue;
					cnt++;
				}
				if(cnt < 3) {
					res[i][j] = map[i][j]-1;
				}else {
					res[i][j] = map[i][j];
				}
			}
		}
		map = res;
	}
	static int getSum() {
		int sum = 0;
		for(int i = 0;i<map.length;i++) {
			for(int j = 0;j<map[0].length;j++) {
				sum += map[i][j];
			}
		}
		return sum;
	}
	
	static int getMax() {
		boolean[][] v = new boolean[map.length][map[0].length];
		int max = 0;
		Queue<int []> queue =new ArrayDeque<>();
		for(int i= 0;i<map.length;i++) {
			for(int j= 0;j<map[0].length;j++) {
				if(map[i][j] > 0 && !v[i][j]) {
					v[i][j] = true;
					queue.add(new int[] {i, j});
					int cnt = 1;
					while(!queue.isEmpty()) {
						int [] now = queue.poll();
						for(int k = 0 ;k<4;k++) {
							int ny = now[0] + dy[k];
							int nx = now[1] + dx[k];
							if(ny >=map.length || ny < 0 || nx>= map.length ||nx < 0 || v[ny][nx] || map[ny][nx] == 0) continue;
							v[ny][nx] = true;
							cnt++;
							queue.add(new int[] {ny, nx});
						}
					}
					max = Math.max(max, cnt);
				}
			}
		}
		return max;
	}
	static void print(int [][] map) {
		StringBuilder sb = new StringBuilder();
		int n = map.length;
		for(int i = 0;i<n;i++) {
			for(int j = 0;j<n;j++) {
				sb.append(map[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb+"\n===================\n");
	}
}