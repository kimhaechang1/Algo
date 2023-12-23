import java.util.*;
import java.io.*;

public class Main{
	static int n;
	static int m;
	static int sy;
	static int sx;
	static char [][] map;
	static StringTokenizer stk;
	static int [] dy = {-1,0,1,0};
	static int [] dx = {0,1,0,-1};
	static int max;
	public static void main(String [] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		stk = new StringTokenizer(bf.readLine());
		n = Integer.parseInt(stk.nextToken());
		m = Integer.parseInt(stk.nextToken());
		map = new char[n][m];
		for(int i = 0;i<n;i++) {
			map[i] = bf.readLine().toCharArray().clone();
		}
		
		stk = new StringTokenizer(bf.readLine());
		sy = Integer.parseInt(stk.nextToken())-1;
		sx = Integer.parseInt(stk.nextToken())-1;
		max = 0;
		PriorityQueue<int []> pq = new PriorityQueue<>((a, b)->{
			if(a[4] == b[4]) {
				return a[3] - b[3];
			}
			return b[4] - a[4];
		});
		//[y,x,d,id,t]
		for(int k = 0;k<4;k++) {
			pq.add(new int[] {sy, sx, k, k, 0});
		}
		boolean isInf = false;
		int resD  = -1;
		int resT = 0;
		while(!pq.isEmpty()) {
			int [] now = pq.poll();
			int ny = now[0] + dy[now[2]];
			int nx = now[1] + dx[now[2]];
			if(ny == sy && nx == sx && now[2] == now[3]) {
				resD = now[3];
				isInf = true;
				break;
			}
			if(ny >= n || ny < 0 || nx>=m || nx < 0) {
				if(resT < now[4]+1) {
					resT = now[4]+1;
					resD = now[3];
				}
				continue;
			}
			if(map[ny][nx] == '\\') {
				// 상 우 하 좌
				if(now[2] == 0) {
					now[2] = 3;
				}else if(now[2] == 1) {
					now[2] = 2;
				}else if(now[2] == 2) {
					now[2] = 1;
				}else if(now[2] == 3) {
					now[2] = 0;
				}
			}else if(map[ny][nx] == '/') {
				if(now[2] == 0) {
					now[2] = 1;
				}else if(now[2] == 1) {
					now[2] = 0;
				}else if(now[2] == 2) {
					now[2] = 3;
				}else if(now[2] == 3) {
					now[2] = 2;
				}
			}else if(map[ny][nx] == 'C') {
				if(resT < now[4]+1) {
					resD = now[3];
					resT = now[4]+1;
				}
				continue;
			}
			pq.add(new int[] {ny, nx, now[2], now[3], now[4]+1});
		}
		System.out.print(getStrDir(resD)+"\n"+(isInf ? "Voyager" : resT));
	}
	static String getStrDir(int dir) {
		switch(dir) {
		case 0 :
			return "U";
		case 1 : 
			return "R";
		case 2 : 
			return "D";
		default : 
			return "L";
		}
	}
}