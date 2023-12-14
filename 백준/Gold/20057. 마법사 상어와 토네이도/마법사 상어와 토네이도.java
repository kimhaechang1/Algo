import java.util.*;
import java.io.*;

public class Main{
	static int [][] map;
	static int n;
	static StringTokenizer stk;
	static int [] dy = {0,1,0,-1};
	static int [] dx = {-1,0,1,0};
	static int [][][] blow ={
			{
				{0,-2},
				{-1,-1},
				{1,-1},
				{-2,0},
				{-1,0},
				{2, 0},
				{1, 0},
				{-1,1},
				{1,1}
			},
			{
				// 5 10 10  2 7 2 7 1 1
				{2, 0},
				{1,-1},
				{1,1},
				{0,-2},
				{0,-1},
				{0,2},
				{0,1},
				{-1,-1},
				{-1,1},
			},
			{
				// 5 10 10  2 7 2 7 1 1
				{0, 2},
				{-1,1},
				{1,1},
				{-2,0},
				{-1,0},
				{2, 0},
				{1, 0},
				{-1,-1},
				{1,-1},
			},
			{
				// 5 10 10 2 7 2 7 1 1
				{-2, 0},
				{-1,-1},
				{-1,1},
				{0,-2},
				{0,-1},
				{0,2},
				{0,1},
				{1,-1},
				{1,1},
			}
			

	};
			

	static int [] arr = {5,10,10,2,7,2,7,1,1};
	public static void main(String [] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(bf.readLine());
		map = new int[n][n];
		for(int i = 0;i<n;i++) {
			stk = new StringTokenizer(bf.readLine());
			for(int j= 0;j<n;j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		int sy = n/2;
		int sx = n/2;
		boolean [][] v = new boolean[n][n];
		v[sy][sx] = true;
		int len = 1;
		int dir = 0;
		int sum = 0;
		while(true) {
			int l = len;
			boolean isBlock = false;
			while(l-- > 0) {
				int ny = sy + dy[dir];
				int nx = sx + dx[dir];
				
				if(v[ny][nx]) {
					isBlock = true;
					len++;
					break;
				}
				v[ny][nx] = true;
				sum += sendBlow(ny, nx, dir);
				sy = ny;
				sx = nx;
			}
			if(isBlock) {
				if(dir-1 < 0) dir = 3;
				else dir -= 1;
				int ny = sy + dy[dir];
				int nx = sx + dx[dir];
				sum += sendBlow(ny, nx, dir);
				v[ny][nx] = true;
				sy = ny;
				sx = nx;
			}
			if(sy == 0 && sx == 0) break;
			dir++;
			if(dir == 4) {
				dir = 0;
			}
		}
		System.out.println(sum);
	}
	static int sendBlow(int y, int x, int dir) {
		// 격자 밖으로 나간 모래양
		int res = 0;
		// 현재칸은 0이다.
		int presentSend = map[y][x];
		// 계산끝난 알파의 양
		int alpa = 0;
		if(presentSend == 0) {
			// Y칸에 모래의 양이 0이라면 불어나갈것이 없다.
			return 0;
		}
		map[y][x] = 0;
		// 알파 칸 좌표를 구한다.
		int ay = y + dy[dir];
		int ax = x + dx[dir];
		
		int computedSum = 0;
		for(int i = 0;i<9;i++) {
			int [] nd = blow[dir][i];
			int ny = y + nd[0];
			int nx = x + nd[1];
			int computed = (int)((double)presentSend * ((double) arr[i] / 100));
			if(ny >= n || ny < 0 || nx >= n || nx < 0) {
				res += computed;
			}else {
				map[ny][nx] += computed;
			}
			computedSum+=computed;
		}
		presentSend -= computedSum;
		if(ay >= n || ay < 0 || ax >= n || ax < 0) {
			res += presentSend;
		}else {
			map[ay][ax] += presentSend;
		}
		return res;
	}
	static void print(boolean [][] v) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0;i<n;i++) {
			for(int j = 0;j<n;j++) {
				if(v[i][j]) {
					sb.append(1).append(" ");
				}else {
					sb.append(0).append(" ");
				}
				
			}
			sb.append("\n");
		}
		System.out.println(sb+"\n=================\n");
	}
	static void printmap() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0;i<n;i++) {
			for(int j = 0;j<n;j++) {
				sb.append(map[i][j]+" ");
			}
			sb.append("\n");
		}
		System.out.println(sb+"\n=================\n");
	}
}