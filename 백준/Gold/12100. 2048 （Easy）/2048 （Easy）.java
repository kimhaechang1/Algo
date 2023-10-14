import java.util.*;
import java.io.*;

public class Main{
	static int [][] map;
	static int []  dy = {-1,1,0,0};
	static int [] dx = {0,0,-1,1};
	static int [] res;
	static int [][] copy;
	static int N;
	static int max;
	static StringTokenizer stk;
	public static void main(String [] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		map = new int[N][N];
		for(int i = 0;i<N;i++) {
			stk = new StringTokenizer(bf.readLine());
			for(int j= 0;j<N;j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		copy = new int[N][N];
		res = new int[5];
		max = -1;
		dfs(0);
		//copymap();
		//go(0);
		/*for(int i = 0;i<N;i++) {
			for(int j = 0;j<N;j++) {
				System.out.print(copy[i][j]+" ");
			}
			System.out.println();
		}*/
		System.out.println(max);
	}
	static void dfs(int depth) {
		if(depth == 5) {
			copymap();
			for(int i = 0;i<res.length;i++) {
				go(res[i]);
			}
			chk();
			
			return;
		}
		
		for(int i = 0;i<4;i++) {
			res[depth] = i;
			dfs(depth+1);
		}
	}
	static void go(int d) {
		boolean [][] isChanged = new boolean[N][N];
		if(d == 0) {
			// 맨 윗줄은 움직이지 못한다.
			for(int i= 1;i<N;i++) {
				for(int j = 0;j<N;j++) {
					if(copy[i][j] > 0) {
						int val = copy[i][j];
						copy[i][j] = 0;
						boolean canMerged = false;
						int ny = i;
						while(--ny > -1) {
							if(isChanged[ny][j]) break;
							if(copy[ny][j] > 0 && copy[ny][j] != val) break;
							if(copy[ny][j] == val) {
								isChanged[ny][j] = true;
								canMerged = true;
								break;
							}
						}
						if(canMerged) {
							copy[ny][j] += val;
						}else {
							copy[++ny][j] = val;
						}
					}
				}
			}
		}else if(d == 1) {
			for(int i= N-2;i>-1;i--) {
				// 맨 아랫줄은 움직이지 못한다.
				for(int j = 0;j<N;j++) {
					if(copy[i][j] > 0) {
						int val = copy[i][j];
						copy[i][j] = 0;
						boolean canMerged = false;
						int ny = i;
						while(++ny < N) {
							if(isChanged[ny][j]) break;
							if(copy[ny][j] > 0 && copy[ny][j] != val) break;
							if(copy[ny][j] == val) {
								isChanged[ny][j] = true;
								canMerged = true;
								break;
							}
						}
						if(canMerged) {
							copy[ny][j] += val;
						}else {
							copy[--ny][j] = val;
						}
					}
				}
			}
		}else if(d == 2) {
			for(int i= 1;i<N;i++) {
				// 좌측이므로 가장 왼쪽 세로줄은 이동할 수 없다.
				for(int j = 0;j<N;j++) {
					if(copy[j][i] > 0) {
						int val = copy[j][i];
						copy[j][i] = 0;
						boolean canMerged = false;
						int nx = i;
						while(--nx > -1) {
							if(isChanged[j][nx]) break;
							if(copy[j][nx] > 0 && copy[j][nx] != val) break;
							if(copy[j][nx] == val) {
								isChanged[j][nx] = true;
								canMerged = true;
								break;
							}
						}
						if(canMerged) {
							copy[j][nx] += val;
						}else {
							copy[j][++nx] = val;
						}
					}
				}
			}
		}else if(d == 3) {
			for(int i= N-2;i>-1;i--) {
				// 우측이므로 가장 오른쪽 세로줄은 이동할 수 없다.
				for(int j = 0;j<N;j++) {
					if(copy[j][i] > 0) {
						int val = copy[j][i];
						copy[j][i] = 0;
						boolean canMerged = false;
						int nx = i;
						while(++nx < N) {
							if(isChanged[j][nx]) break;
							if(copy[j][nx] > 0 && copy[j][nx] != val) break;
							if(copy[j][nx] == val) {
								isChanged[j][nx] = true;
								canMerged = true;
								break;
							}
						}
						/*System.out.println("from : (" +j+", "+i+")");
						System.out.println("canMerged : "+canMerged);
						System.out.println("j : "+j +" nx : "+nx);
						System.out.println("val : "+val);*/
						if(canMerged) {
							copy[j][nx] += val;
						}else {
							copy[j][--nx] = val;
						}
					}
				}
			}
		}
		/*System.out.println("d : "+d);
		for(int i = 0;i<N;i++) {
			for(int j = 0;j<N;j++) {
				System.out.print(copy[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("--------------------");*/
	}
	static void copymap() {
		for(int i = 0;i<N;i++) {
			for(int j = 0;j<N;j++) {
				 copy[i][j] = map[i][j];
			}
		}
	}
	static void chk() {
		for(int [] vals: copy) {
			for(int val : vals) {
				if(max < val) {
					//System.out.println("res : "+Arrays.toString(res));
					/*for(int i = 0;i<N;i++) {
						for(int j = 0;j<N;j++) {
							System.out.print(copy[i][j]+" ");
						}
						System.out.println();
					}*/
					//System.out.println("==========경우의수 분리선========");
					max = val;
				}
			}
		}
	}
}