import java.util.*;
import java.io.*;

public class Main{
	static StringTokenizer stk;
	static int [][] map;
	static int n;
	static int m;
	static ArrayList<int []>list;
	static boolean[][] isCloud;
	static int [] dy = {0,-1,-1,-1,0,1,1,1};
	static int [] dx = {-1,-1,0,1,1,1,0,-1};
	public static void main(String [] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		stk = new StringTokenizer(bf.readLine());
		n = Integer.parseInt(stk.nextToken());
		m = Integer.parseInt(stk.nextToken());
		map = new int[n][n];
		for(int i = 0;i<n;i++) {
			stk = new StringTokenizer(bf.readLine());
			for(int j = 0;j<n;j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		// init cloud
		list = new ArrayList<>();
		list.add(new int[] {n-2, 0});
		list.add(new int[] {n-2, 1});
		list.add(new int[] {n-1, 0});
		list.add(new int[] {n-1, 1});
		
		for(int i = 0;i<m;i++) {
			stk = new StringTokenizer(bf.readLine());
			int d = Integer.parseInt(stk.nextToken())-1;
			int s = Integer.parseInt(stk.nextToken());
			isCloud = new boolean[n][n];
			move(d, s);
			mark();
			rain();
			list.clear();
			makeCloud();
			
		}
		
		System.out.println(getSum());
	}
	static void move(int dir, int s) {

		ArrayList<int []> newList = new ArrayList<>();
		
		for(int [] yx : list) {
			int distance = s;
			while(distance-- > 0) {
				yx[0] += dy[dir];
				yx[1] += dx[dir];
				if(yx[0] >= n) {
					yx[0] = 0;
				}
				else if(yx[0] < 0) {
					yx[0] = n-1;
				}
				
				if(yx[1] >= n) {
					yx[1] = 0;
				}
				else if(yx[1] < 0) {
					yx[1] = n-1;
				}
			}
			newList.add(new int[] {yx[0], yx[1]});
		}
		list = newList;
	}
	static void mark() {
		for(int [] pos : list) {
			isCloud[pos[0]][pos[1]] = true;
		}
	}
	
	static void rain() {
		// 구름이 있는 장소에 비 내리기
		for(int [] pos : list) {
			map[pos[0]][pos[1]]++;
		}
		// 물복사 버그
		for(int [] pos : list) {
			for(int k =2;k<=8;k+=2) {
				int ny = pos[0]+dy[k-1];
				int nx = pos[1]+dx[k-1];
				if(ny >= n || ny < 0 || nx>=n || nx<0 || map[ny][nx] == 0) continue;
				map[pos[0]][pos[1]]++;
			}
		}
	}
	static void makeCloud() {
		// 전에 구름이 있엇던 장소가 아니면서
		// 현재 바구니에 물이 2이상인 모든칸에 대하여 구름 생성가능
		// 단 생성후 2 빼야함
		for(int i = 0;i<n;i++) {
			for(int j = 0;j<n;j++) {
				if(isCloud[i][j]) continue;
				if(map[i][j] < 2) continue;
				map[i][j]-=2;
				list.add(new int[] {i, j});
			}
		}
	}
	static int getSum() {
		int sum = 0;
		for(int i = 0;i<n;i++) {
			for(int j = 0;j<n;j++) {
				sum += map[i][j];
			}
		}
		return sum;
	}
	static void print() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0;i<n;i++) {
			for(int j = 0;j<n;j++) {
				sb.append(map[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.print(sb+"=====================\n");
	}
	
}