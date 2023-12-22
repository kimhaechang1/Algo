import java.util.*;
import java.io.*;

public class Main{
	static int m;
	static int s;
	static int [] dy= {0,0,-1,-1,-1,0,1,1,1};
	static int [] dx= {0,-1,-1,0,1,1,1,0,-1};
	static int sy,sx;
	static StringTokenizer stk;
	static int [][] smell;
	// 상 좌 하 
	static int [] sdy = {0,-1,0,1,0};
	static int [] sdx = {0,0,-1,0,1};
	static ArrayList<int[]>[][] map;
	static int [] res;
	static int [] min;
	static int maxFish;
	static ArrayList<int[]> fish;
	
	public static void main(String [] args) throws Exception{
		BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
		stk = new StringTokenizer(bf.readLine());
		m = Integer.parseInt(stk.nextToken());
		s = Integer.parseInt(stk.nextToken());
		fish = new ArrayList<>();
		for(int i = 0;i<m;i++) {
			int [] f = new int[3];
			stk = new StringTokenizer(bf.readLine());
			f[0]= Integer.parseInt(stk.nextToken())-1;
			f[1] = Integer.parseInt(stk.nextToken())-1;
			f[2] = Integer.parseInt(stk.nextToken());
			fish.add(f);
		}
		stk = new StringTokenizer(bf.readLine());
		sy = Integer.parseInt(stk.nextToken())-1;
		sx = Integer.parseInt(stk.nextToken())-1;
		map = new ArrayList[4][4];
		
		// 상어 마법 시전
		// 물고기 한칸 이동, 단 상어가 있는칸, 물고기 냄세가 있는칸, 격자 범위밖 이동 X
		// 상어 연속 3칸 이동, 상어는 상하좌
		// 상어역시 격자범위밖 이동 불가
		// 상어의 연속해서 이동하는 중 물고기가 있다면 모든 물고기는 격자에서 제외되고, 냄세를 남긴다.
		// 가능한 이동방법 들 중 가장 많은 물고기가 제거되는쪽으로 이동, 사전순으로 가장 앞서는 방법	
		// 처음 복제를 시전했던 물고기가 격자에서 나타난다.
		init();
		while(s-- > 0) {
			ArrayList<int[]> cf = copyFish();
			//1, 2, 3, 4 ,5 ,6, 7, 8	
			//←, ↖, ↑, ↗, →, ↘, ↓, ↙
			fishMove();
			sharkMove();
			addFish(cf);
			mapToArray();
		}
		System.out.print(getTotal());
	}
	static void init() {
		for(int i = 0;i<4;i++) {
			for(int j = 0;j<4;j++) {
				map[i][j] = new ArrayList<>();
			}
		}
		for(int [] f : fish) {
			int y= f[0];
			int x= f[1];
			int d = f[2];
			map[y][x].add(f);
		}
		smell = new int[4][4];
	}
	static ArrayList<int []> copyFish() {
		ArrayList<int []> temp = new ArrayList<>();
		for(int [] f : fish) {
			temp.add(f);
		}
		return temp;
	}
	static void fishMove() {
		ArrayList<int[]>[][] temp = new ArrayList[4][4];
		for(int i = 0;i<4;i++) {
			for(int j = 0;j<4;j++) {
				temp[i][j] = new ArrayList<>();
			}
		}
		ArrayList<int[]> tempFish = new ArrayList<>();
		for(int [] f : fish) {
			
			int idir = f[2];
			int ny = f[0] + dy[idir];
			int nx = f[1] + dx[idir];
			int [] newFish = new int[3];
			boolean find = false;
			if(ny >= 4 || ny < 0 || nx >= 4 || nx < 0 || smell[ny][nx] !=0 || (ny == sy && nx == sx)) {
				int nd = idir-1;
				if(nd == 0) nd = 8;
				while(true) {
					if(nd == idir) break;
					ny = f[0] + dy[nd];
					nx = f[1] + dx[nd];
					if(ny >= 4 || ny < 0 || nx >= 4 || nx < 0 || smell[ny][nx] !=0 || (ny == sy && nx == sx)) {
						nd -=1;
						if(nd == 0) nd = 8;
						continue;
					}else {
						find = true;
						idir = nd;
						break;
					}
				}
			}else {
				find = true;
			}
			if(!find) {
				ny = f[0];
				nx = f[1];
				idir = f[2];
			}
			newFish[0] = ny;
			newFish[1] = nx;
			newFish[2] = idir;
			temp[ny][nx].add(newFish);
			tempFish.add(newFish);
		}
		map = temp;
		fish = tempFish;
	}
	static void sharkMove() {
		maxFish = -1;
		res = new int[3];
		min = new int[3];
		dfs(0);
		smellDown();
		int y = sy;
		int x = sx;
		for(int dir : min) {
			y += sdy[dir];
			x += sdx[dir];
			if(map[y][x].size() > 0) {
				smell[y][x] = 2;
				map[y][x].clear();
			}
		}
		sy= y;
		sx= x;
	}
	static void dfs(int depth) {
		if(depth == 3) {
			int count =  getCount(res);
			if(count > maxFish){
				maxFish = count;
				min = res.clone();
			}else if(count == maxFish) {
				int a1 = 0;
				int a2 = 0;
				for(int i = 0;i<3;i++) {
					a1 += min[i] * Math.pow(10, 2-i);
					a2 += res[i] * Math.pow(10, 2-i);
				}
				if(a2 < a1) {
					min = res.clone();
				}
			}
			return;
		}
		for(int i = 1;i<=4;i++) {
			res[depth] = i;
			dfs(depth+1);
		}
	}
	static int getCount(int [] dirs) {
		int res = 0;
		int y = sy;
		int x = sx;
		boolean [][] v = new boolean[4][4];
		for(int dir : dirs) {
			int ny = y + sdy[dir];
			int nx = x + sdx[dir];
			if(ny >= 4 || ny < 0 || nx>= 4 || nx < 0) {
				return -1;
			}
			
			if(!v[ny][nx]) {
				v[ny][nx] = true;
				res += map[ny][nx].size();
			}
			y = ny;
			x = nx;
		}
		return res;
	}
	static void smellDown() {
		for(int i = 0;i<4;i++) {
			for(int j = 0;j<4;j++) {
				if(smell[i][j] > 0) {
					--smell[i][j];
				}
			}
		}
	}
	static void addFish(ArrayList<int[]> cp) {
		// todo : 복제된 물고기들을 맵에 풀어놓기
		for(int [] fish : cp) {
			int y = fish[0];
			int x = fish[1];
			map[y][x].add(fish);
		}
	}
	static void mapToArray() {
		// todo : 맵에 정보를 물고기 리스트에 갱신
		ArrayList<int []> tempFish = new ArrayList<>();
		for(int i = 0;i<4;i++) {
			for(int j = 0;j<4;j++) {
				if(map[i][j].size() > 0) {
					for(int [] fish : map[i][j]) {
						tempFish.add(fish);
					}
				}
			}
		}
		fish = tempFish;
	}
	static int getTotal() {
		int sum = 0;
		for(int i = 0;i < 4;i++) {
			for(int j =0;j<4;j++) {
				sum += map[i][j].size();
			}
		}
		return sum;
	}
	static void printMap() {
		for(int i = 0;i<4;i++) {
			for(int j = 0;j<4;j++) {
				System.out.println("i : "+i+" j : "+j);
				for(int [] fish : map[i][j]) {
					System.out.println(Arrays.toString(fish));
				}
			}
			System.out.println();
		}
	}
	static void printsMap() {
		for(int i = 0;i<4;i++) {
			for(int j = 0;j<4;j++) {
				System.out.print(smell[i][j]+" ");
			}
			System.out.println();
		}
	}
	
}