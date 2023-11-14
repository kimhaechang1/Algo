import java.util.*;
import java.io.*;

public class Solution{
	static int N;
	static int [][] map;
	static StringTokenizer stk;
	static int max;
	static Map<Integer, ArrayList<int[]>> hs;
	static int []  dy = {-1,1,0,0};
	static int [] dx = {0,0,-1,1};
	public static void main(String [] args) throws Exception {
		BufferedReader bf =new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t= 1;t<=T;t++) {
			max = Integer.MIN_VALUE;
			N = Integer.parseInt(bf.readLine().trim());
			map = new int[N][N];
			hs = new HashMap<>();
			for(int i= 0;i<N;i++) {
				stk = new StringTokenizer(bf.readLine().trim());
				for(int j= 0;j<N;j++) {
					map[i][j] = Integer.parseInt(stk.nextToken());
					if(map[i][j]>=6) {
						if(hs.get(map[i][j]) == null) {
							ArrayList<int []> list = new ArrayList<>();
							list.add(new int[] {i, j});
							hs.put(map[i][j], list);
						}else {
							ArrayList<int []> list = hs.get(map[i][j]);
							list.add(new int [] {i, j});
							hs.put(map[i][j], list);
						}
					}
				}
			}
			
			
			for(int i= 0;i<N;i++) {
				for(int j= 0;j<N;j++) {
					if(map[i][j] !=0 ) continue;
					for(int k = 0;k<4;k++) {
						max = Math.max(go(i, j, k), max);
					}
				}
			}
			
			sb.append("#").append(t).append(" ").append(max).append("\n");
		}
		System.out.print(sb);
	}
	static int go(int sy, int sx, int d) {
		int res = 0;
		int ny;
		int nx;
		int dir = d;
		ny = sy + dy[dir];
		nx = sx + dx[dir];
		while(true) {
			if(ny >=N || ny < 0 || nx >= N || nx < 0) {
				// 벽을 만났을 경우
				if(dir == 0) {
					dir = 1;
				}else if(dir == 1) {
					dir = 0;
				}else if(dir == 2) {
					dir = 3;
				}else if(dir == 3) {
					dir = 2;
				}
				res++;
			}
			else if((ny == sy && nx == sx) || map[ny][nx] == -1) {
				break;
			}
			else if(map[ny][nx] >= 1 && map[ny][nx] <= 5) {
				res++;
				if(map[ny][nx] == 1) {
					if(dir == 0) {
						dir = 1;
					}else if(dir == 1) {
						dir = 3;
					}else if(dir == 2) {
						dir = 0;
					}else if(dir == 3) {
						dir = 2;
					}
				}else if(map[ny][nx] == 2) {
					if(dir == 0) {
						dir = 3;
					}else if(dir == 1) {
						dir = 0;
					}else if(dir == 2) {
						dir = 1;
					}else if(dir == 3) {
						dir = 2;
					}
				}else if(map[ny][nx] == 3) {
					if(dir == 0) {
						dir = 2;
					}else if(dir == 1) {
						dir = 0;
					}else if(dir == 2) {
						dir = 3;
					}else if(dir == 3) {
						dir = 1;
					}
				}else if(map[ny][nx] == 4) {
					if(dir == 0) {
						dir = 1;
					}else if(dir == 1) {
						dir = 2;
					}else if(dir == 2) {
						dir = 3;
					}else if(dir == 3) {
						dir = 0;
					}
				}else if(map[ny][nx] == 5) {
					if(dir == 0) {
						dir = 1;
					}else if(dir == 1) {
						dir = 0;
					}else if(dir == 2) {
						dir = 3;
					}else if(dir == 3) {
						dir = 2;
					}
				}
			}
			else if(map[ny][nx] >= 6) {
				ArrayList<int[]> list = hs.get(map[ny][nx]);
				int [] candi = list.get(0);
				int [] candi2 = list.get(1);
				if(ny == candi[0] && nx == candi[1]) {
					ny = candi2[0];
					nx = candi2[1];
				}else if(ny == candi2[0] && nx == candi2[1]) {
					ny = candi[0];
					nx = candi[1];
				}
			}
			ny = ny + dy[dir];
			nx = nx + dx[dir];
		}
		return res;
	}
}