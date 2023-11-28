import java.util.*;
import java.io.*;

public class Main{
	static int N;
	static int [][] map;
	static StringTokenizer stk;
	static int [] dy = {-1,1,0,0};
	static int [] dx = {0,0,-1,1};
	public static void main(String [] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		map = new int[N*N][5];
		for(int i = 0;i<N*N;i++) {
			stk = new StringTokenizer(bf.readLine());
			for(int j= 0;j<5;j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		// 비어있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리를정한다.
		// -> 자기 자신을 기준으로 좋아하는 학생이 가장 많이 인접해 있는 칸
		// 1을 만족하는 칸이 여러개이면 인접한 칸 중에서 비어있는 칸이 가장 많은 칸으로 자리를 정한다.
		// -> 각 인접한 칸이 여러개라면 그 중에서 빈칸이 많이 인접한 칸 하나
		// 2를 만족하는 칸도 여러개인 경우 행의 번호가 작은 칸으로, 그러한 칸도 여러개이면 열의 번호가 가장 작은 칸으로 // > 가장 왼쪽 위
		// -> 그것조차 많다면 가장 위, 위에서도 많다면 가장 왼쪽
		
		//
		/*
		 * 0 0 0 0 0
		 * 0 0 0 0 0
		 * 0 0 0 0 0
		 * 0 0 0 0 0
		 * 0 0 0 0 0 
		 * */
		// 
		int [][] res = new int[N][N];
		HashMap<Integer, int[]> hashmap= new HashMap<>();
		// 항상 초기 학생은 위치가 중앙으로 잡힌다.
		// 초기 학생 번호
		int init = map[0][0];
		res[1][1] = init;
		hashmap.put(init, new int[] {1,1});
//		for(int j = 0;j<N;j++) {
//			for(int k= 0;k<N;k++) {
//				System.out.print(res[j][k]+" ");
//			}
//			System.out.println();
//		}
//		System.out.println("----------------");
		for(int i= 1;i<N*N;i++) {
			int [] info = map[i];
			int number = info[0];
			int [][] score = new int[N][N];
			boolean find = false;
			for(int j = 1;j<info.length;j++) {
				int [] yx = hashmap.get(info[j]);
				if(yx == null) continue;
				for(int k = 0;k<4;k++) {
					int ny= yx[0]+dy[k];
					int nx= yx[1]+dx[k];
					if(ny >= N || ny < 0 || nx >=N || nx<0 || res[ny][nx] != 0) continue;
					find = true;
					score[ny][nx]++;
				}
			}
			int minY = 99;
			int minX = 99;
			int max = -1;
			int maxBlank = -1;
			//System.out.println("number : "+number +" : "+find);
			if(find) {
				for(int j = 0;j<N;j++) {
					for(int k=0;k<N;k++) {
						if(score[j][k] > max) {
							minY = j;
							minX = k;
							max = score[j][k];
							int c = 0;
							for(int f = 0;f<4;f++) {
								int ny = j + dy[f];
								int nx = k + dx[f];
								if(ny >= N || ny < 0 || nx >=N || nx<0 || res[ny][nx] != 0) continue;
								c++;
							}
							maxBlank = c;
						}else if(score[j][k] == max) {
							int c = 0;
							for(int f = 0;f<4;f++) {
								int ny = j + dy[f];
								int nx = k + dx[f];
								if(ny >= N || ny < 0 || nx >=N || nx<0 || res[ny][nx] != 0) continue;
								c++;
							}
							if(c > maxBlank) {
								minY = j;
								minX = k;
								maxBlank = c;
							}else if(c == maxBlank) {
								if(minY > j) {
									minY = j;
									minX = k;
								}else if(minY == j) {
									if(minX > k) {
										minX = k;
									}
								}
							}
						}
					}
				}
			}else {
				for(int j = 0;j<N;j++) {
					for(int k = 0;k<N;k++) {
						if(res[j][k] != 0 ) continue;
						int c = 0;
						for(int f = 0;f<4;f++) {
							int ny = j + dy[f];
							int nx = k + dx[f];
							if(ny >= N || ny < 0 || nx >=N || nx<0 || res[ny][nx] != 0) continue;
							c++;
						}
						if(c > maxBlank) {
							minY = j;
							minX = k;
							maxBlank = c;
						}else if(c == maxBlank) {
							if(minY > j) {
								minY = j;
								minX = k;
							}else if(minY == j) {
								if(minX > k) {
									minX = k;
								}
							}
						}
					}
				}
			}
			res[minY][minX] = number;
			hashmap.put(number, new int[] {minY, minX});
			
		}
		int score = 0;
		for(int i = 0;i<N*N;i++) {
			int [] info = map[i];
			int [] yx = hashmap.get(info[0]);
			int c = 0;
			for(int j= 1;j<info.length;j++) {
				int [] tyx = hashmap.get(info[j]);
				int cha = Math.abs(yx[0] - tyx[0]) + Math.abs(yx[1]-tyx[1]);
				if(cha == 1) c++;
			}
			if(c == 0) continue;
			score += Math.pow(10, c-1);
		}
		System.out.print(score);
	}
}