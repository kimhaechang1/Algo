import java.util.*;
import java.io.*;

public class Main{
	static int N;
	static int M;
	static class Map{
		ArrayList<int []> list = new ArrayList<>();
	}
	static Map [][] map;
	static boolean [][] v;
	static boolean [][] isOn;
	static int [] dy = {-1,1,0,0};
	static int [] dx = {0,0,-1,1};
	static StringTokenizer stk;
	public static void main(String [] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		stk = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		map = new Map[N][N];
		for(int i= 0;i<N;i++) {
			for(int j= 0;j<N;j++) {
				map[i][j] = new Map();
			}
		}
		v = new boolean[N][N];
		isOn = new boolean[N][N];
		for(int i= 0;i<M;i++) {
			stk = new StringTokenizer(bf.readLine());
			int y = Integer.parseInt(stk.nextToken());
			int x = Integer.parseInt(stk.nextToken());
			int a = Integer.parseInt(stk.nextToken());
			int b = Integer.parseInt(stk.nextToken());
			map[y-1][x-1].list.add(new int[] {a-1,b-1});
		}
		bfs();
		int c = 0;
		for(int i = 0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(isOn[i][j]) {
					c++;
				}
			}
		}
		System.out.println(c);
	}
	static void bfs() {
		isOn[0][0] = true;
		v[0][0] = true;
		Queue<int []> queue = new ArrayDeque<>();
		queue.add(new int[] {0,0});
		while(!queue.isEmpty()) {
			int [] now = queue.poll();
			int cury = now[0];
			int curx = now[1];
			// 해당 방에 스위치가 있으면 켜기
			for(int [] s : map[cury][curx].list) {
				if(!isOn[s[0]][s[1]]) {
					isOn[s[0]][s[1]] = true;
					if(!v[s[0]][s[1]]) {
						boolean canGo = false;
						for(int k = 0;k<4;k++) {
							int ny = s[0] + dy[k];
							int nx = s[1] + dx[k];
							if(ny >= N || ny < 0 || nx>=N || nx<0) continue;
							if(v[ny][nx]) {
								canGo = true;
								break;
							}
						}
						if(canGo) {
							v[s[0]][s[1]] = true;
							queue.add(new int [] {s[0],s[1]});
						}
					}
				}
			}
			// 불이 켜져있는곳으로 이동
			for(int k = 0;k<4;k++) {
				int ny = cury + dy[k];
				int nx = curx + dx[k];
				if(ny >= N || ny < 0 || nx>=N || nx<0 || v[ny][nx] || !isOn[ny][nx]) continue;
				v[ny][nx] = true;
				queue.add(new int[] {ny, nx});
			}
		}
		
	}
}