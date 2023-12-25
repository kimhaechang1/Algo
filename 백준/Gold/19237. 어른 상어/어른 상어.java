import java.util.*;
import java.io.*;

public class Main{
	static StringTokenizer stk;
	static int [][] map;
	static int n;
	static int m;
	static int k;
	static int [][] shark;
	static int [][][] sharkPriority;
	static int [] dy = {0,-1,1,0,0};
	static int [] dx = {0,0,0,-1,1};
	static PriorityQueue<Integer>[][] pq;
	static int [][][] smell;
	public static void main(String [] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		stk = new StringTokenizer(bf.readLine());
		n = Integer.parseInt(stk.nextToken());
		m = Integer.parseInt(stk.nextToken());
		k = Integer.parseInt(stk.nextToken());
		map = new int[n][n];
		shark = new int[m+1][3];
		for(int i = 0;i<n;i++) {
			stk = new StringTokenizer(bf.readLine());
			for(int j = 0;j<n;j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
				if(map[i][j] > 0) {
					shark[map[i][j]][0] = i;
					shark[map[i][j]][1] = j;
				}
			}
		}
		stk = new StringTokenizer(bf.readLine());
		sharkPriority = new int[m+1][5][4]; // 상어번호, dir, dir별 우선순위
		for(int i = 1;i<m+1;i++) {
			shark[i][2] = Integer.parseInt(stk.nextToken());
		}
		for(int i = 1;i<m+1;i++) {
			int dir = 1;
			while(dir < 5) {
				stk = new StringTokenizer(bf.readLine());
				for(int j = 0;j<4;j++) {
					sharkPriority[i][dir][j] = Integer.parseInt(stk.nextToken());
				}
				dir++;
			}
		}
		smell = new int[n][n][m+1];
		
		// 1은 가장 강력해서 나머지 모두 쫒아내기 가능
		// 모든 상어가 이동한 후, 한가지 칸에 여러 상어가 존재할 수 있는데
		// 가장 작은 번호를 가진 상어를 제외하고 모두 쫒아냄
		// 상어의 이동방향 우선순위
		// 인접한 칸 중 아무 냄세가 없는 칸이 1순위
		// 이런 칸이 하나도 없으면, 자기 냄세가 남아있는 인접한칸
		// 아무 냄세가 없는 칸이 여럿 혹은 자기 냄세가 남아있는 칸이 여럿이라면
		// 특정한 우선순위를 따름
		int time = 0;
		pq = new PriorityQueue[n][n];
		init();
		while(time < 1000) {
			if(isOne()) break;
			time++;
			sharkMove();
			smellMark();
			mark();
			smellDown();
		}
		System.out.println(isOne() ? time : -1);
		
	}
	static void init() {
		for(int i = 0;i<n;i++) {
			for(int j = 0;j<n;j++) {
				pq[i][j] = new PriorityQueue<>();
			}
		}
	}
	static void sharkMove() {
		// todo : 우선순위의 결과에따른 shark 이동 후 위치를 큐에 담음
		boolean flag = true;
		for(int i = 1;i<m+1;i++) {
			if(shark[i][2] == -1) continue;
			if(i > 1) flag = false;
			int ty = -1, tx = -1;
			int sy = shark[i][0];
			int sx = shark[i][1];
			int presDir = shark[i][2];
			int nd = -1;
			int [] pDir = sharkPriority[i][presDir];
			for(int dir : pDir) {
				// 냄세가 없는칸
				int ny = sy + dy[dir];
				int nx = sx + dx[dir];
				if(ny >= n || ny < 0 || nx >= n || nx < 0) continue;
				boolean isSmell = false;
				for(int k = 1;k<m+1;k++) {
					if(smell[ny][nx][k] > 0) {
						isSmell = true;
						break;
					}
				}
				if(isSmell) continue;
				ty = ny;
				tx = nx;
				nd = dir;
				break;
			}
			if(ty == -1 && tx == -1) {
				for(int dir : pDir) {
					int ny = sy + dy[dir];
					int nx = sx + dx[dir];
					if(ny >= n || ny < 0 || nx >= n || nx < 0) continue;
					if(smell[ny][nx][i] > 0) {
						ty = ny;
						tx = nx;
						nd = dir;
						break;
					}
				}
			}
			shark[i][2] = nd;
			pq[ty][tx].add(i);
		}
	}
	static void smellMark() {
		// todo : 냄세 시간과 어떤 상어가 냄세를 남겼는지를 마킹 : k로 초기화
		for(int i = 1;i<m+1;i++) {
			if(shark[i][2] == -1) continue;
			smell[shark[i][0]][shark[i][1]][i] = k;
		}
	}
	static void mark() {
		// todo : 상어의 결정된 위치로 지도에 갱신
		// 이 때 두 상어가 같이 있는경우 pq에서 나오는 최초상어만 꺼내고 나머지는 죽음 처리
		// 죽은 상어는 지도상에 남기지 않고, sharkDir을 -1로 처리
		// 추가적으로 지도에 갱신하면서 1번 상어를 제외하고 다른상어도 이동에 성공하였다면 flg를 건드리지 않음
		int [][] temp = new int[n][n];
		for(int i = 0;i<n;i++) {
			for(int j = 0;j<n;j++) {
				if(pq[i][j].size() > 1) {
					int sharkNumber = pq[i][j].poll();
					temp[i][j] = sharkNumber;
					shark[sharkNumber][0] = i;
					shark[sharkNumber][1] = j;
					while(!pq[i][j].isEmpty()) {
						sharkNumber = pq[i][j].poll();
						shark[sharkNumber][2] = -1;
					}
				}else {
					if(pq[i][j].isEmpty()) continue;
					int sharkNumber = pq[i][j].poll();
					temp[i][j] = sharkNumber;
					shark[sharkNumber][0] = i;
					shark[sharkNumber][1] = j;
				}
			}
		}
		map = temp;
	}
	static void smellDown() {
		// todo : 상어의 이동이 1번 일어났으므로 냄세를 모두 1씩 감소
		for(int k = 1;k<m+1;k++) {
			for(int i = 0;i<n;i++) {
				for(int j=0;j<n;j++) {
					if(smell[i][j][k] > 0) {
						--smell[i][j][k];
					}
				}
			}
		}
	}
	static boolean isOne() {
		for(int i = 2;i<m+1;i++) {
			if(shark[i][2] != -1) {
				return false;
			}
		}
		return true;
	}
	static void printMap(int [][] map) {
		for(int i = 0;i<n;i++) {
			for(int j= 0;j<n;j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}
	static void printShark() {
		for(int i= 1;i<m+1;i++) {
			System.out.println(i+" 번째 상어 정보 : "+Arrays.toString(shark[i]));
		}
	}
}