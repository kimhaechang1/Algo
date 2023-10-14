import java.util.*;
import java.io.*;


public class Main{
	static char [][] map;
	/*
	 * 싸이클 찾는다 => disjoint-set
	/*
	 * 1  2  3  4
	 * 5  6  7  8
	 * 9 10 11 12
	 */
	 /*
	  * 1 - 5
	  * 5 - 9
	  * 9 - 10
	  * 10 - 11
	  * 11 - 12
	  * 12 - 8
	  * 8 - 4
	  * 4 - 3
	  * 3 - 2
	  * 2 - 1 // 문제 발생!
	  * 
	  * 6 - 7
	  * 7 - 6 // 문제 발생 !
	  * */
	static int N;
	static int M;
	static StringTokenizer stk;
	
	static int [] p;
	static void makeSet(int max) {
		p = new int[max];
		for(int i= 1;i<max;i++) {
			p[i] = i;
		}
	}
	static int find(int a) {
		if(p[a] == a) return a;
		return p[a] = find(p[a]);
	}
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot == bRoot) return false;
		p[bRoot] = aRoot;
		return true;
	}
	
	public static void main(String [] args)throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		stk = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		map = new char[N][M];
		
		for(int i= 0;i<N;i++) {
			String temp = bf.readLine();
			for(int j= 0;j<M;j++) {
				map[i][j] = temp.charAt(j);
			}
		}
		int num = 1;
		int[][] idx = new int[N][M];
		for(int i = 0;i<N;i++) {
			for(int j= 0;j<M;j++) {
				idx[i][j] = num;
				num++;
			}
		}
		makeSet(num);
		boolean [][] v = new boolean[N][M];
		int y = 0;
		int x = 0;
		int cnt = 0;
		if(N == 1 && M == 1) {
			System.out.println(0);
		}else {
			for(int i = 0;i<N;i++) {
				for(int j = 0;j<M;j++) {
					if(v[i][j]) continue;
					y = i;
					x = j;
					while(true) {
						if(v[y][x]) break;
						char dir = map[y][x];
						v[y][x] = true;
						int ny = y, nx = x;
						if(dir == 'U') {
							ny = y-1;
						}else if(dir == 'D') {
							ny = y+1;
						}else if(dir == 'R') {
							nx = x+1;
						}else {
							nx = x-1;
						}
						if(union(idx[ny][nx], idx[y][x])) {
						}else {
							cnt++;
						}
						y = ny;
						x = nx;
					}
				}
			}
			System.out.println(cnt);
		}
	}
}