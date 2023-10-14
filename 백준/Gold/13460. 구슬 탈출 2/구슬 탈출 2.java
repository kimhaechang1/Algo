import java.util.*;
import java.io.*;

public class Main{
	static int N;
	static int M;
	static char [][] map;
	static int cnt;
	static StringTokenizer stk;
	static int [][] time;
	static int [] dy = {-1,1,0,0};
	static int [] dx = {0,0,-1,1};
	static int [] res;
	static int ty,tx;
	static int iby, ibx;
	static int iry, irx;
	static int min;
	public static void main(String [] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		stk = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		map = new char[N][M];
		for(int i= 0;i<N;i++) {
			String str = bf.readLine();
			for(int j= 0;j<M;j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == 'O') {
					ty = i;
					tx = j;
				}else if(map[i][j] == 'R') {
					iry = i;
					irx = j;
				}else if(map[i][j] == 'B') {
					iby = i;
					ibx = j;
				}
			}
		}
		
		// 동시에 두 구슬이 들어가도 안된다.
		// 빨간구슬만 구멍에 들어가야한다.
		// 기울이면서 중복순열?을 해야할지도 모른다.
		// 중복순열로 10개의 기울이는 방향을 구하고
		// 시뮬레이션을 돌려서 한번이라도 성공하는것이 존재한다면 모든 시뮬레이션을 종료시킨다.

		res = new int[10];
		min = Integer.MAX_VALUE;
		dfs(0);
		if(min != Integer.MAX_VALUE) {
			System.out.println(min);
		}else {
			System.out.println(-1);
		}
	}
	static void dfs(int depth) {
		if(depth == 10) {
			boolean flg = true;
			time = new int[N][M];
			int k= 0;
			int by = iby;
			int bx = ibx;
			int ry = iry;
			int rx = irx;
			while(k < 10) {
				int d = res[k];
				int [] simul = new int[4];
				int f = 1; // 1이 빨간공, -1이 파란공
				if(d == 0) {
					if(by > ry) {
						
						simul[0] = ry;
						simul[1] = rx;
						simul[2] = by;
						simul[3] = bx;
					}else {
						f *= -1;
						simul[0] = by;
						simul[1] = bx;
						simul[2] = ry;
						simul[3] = rx;
					}
				}else if(d == 1) {
					if(by > ry) {
						f *= -1;
						simul[0] = by;
						simul[1] = bx;
						simul[2] = ry;
						simul[3] = rx;
					}else {
						simul[0] = ry;
						simul[1] = rx;
						simul[2] = by;
						simul[3] = bx;
					}
				}else if(d == 2) {
					if(bx > rx) {
						simul[0] = ry;
						simul[1] = rx;
						simul[2] = by;
						simul[3] = bx;
					}else {
						f *= -1;
						
						simul[0] = by;
						simul[1] = bx;
						simul[2] = ry;
						simul[3] = rx;
					}
				}else {
					if(bx > rx) {
						f *= -1;
						simul[0] = by;
						simul[1] = bx;
						simul[2] = ry;
						simul[3] = rx;
					}else {
						simul[0] = ry;
						simul[1] = rx;
						simul[2] = by;
						simul[3] = bx;
					}
				}
				int bt = -1;
				int rt = -1;
				for(int i= 0;i<=2;i+=2) {
					int y = simul[i];
					int x = simul[i+1];
					while(true) {
						y += dy[d];
						x += dx[d];
						if(y >= N || y < 0 || x >= M || x< 0 || map[y][x] == '#' || time[y][x] == k+1) {
							y-=dy[d];
							x-=dx[d];
							time[y][x] = k+1;
							break;
						}
						if(map[y][x] == 'O') {
							if(f == -1) {
								bt = k+1;
							}else if(f == 1) {
								rt = k+1;
							}
							break;
						}
					}
					simul[i] = y;
					simul[i+1] = x;
					f *= -1;
					
				}
				if(f == 1) {
					ry = simul[0];
					rx = simul[1];
					by = simul[2];
					bx = simul[3];
				}else {
					by = simul[0];
					bx = simul[1];
					ry = simul[2];
					rx = simul[3];
				}
				
				if(rt > 0) {
					
					if(bt == -1 || (bt!=-1 && rt < bt)) {
						flg = true;
						if(min > rt) {
							min = rt;
						}
						break;
					}
				}
				if(bt > 0) {
					return;
				}
				k++;
			}
			return;
		}
		for(int i = 0;i<4;i++) {
			res[depth] = i;
			dfs(depth+1);
		}
	}
}