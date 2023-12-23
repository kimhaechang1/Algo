import java.util.*;
import java.io.*;

public class Main{
	static int [] dy = {0,-1,-1,0,1,1,1,0,-1};
	static int [] dx = {0,0,-1,-1,-1,0,1,1,1};
	static StringTokenizer stk;
	static int max;
	public static void main(String [] args) throws Exception{
		int [][] fish = new int[17][3];
		int [][] map = new int[4][4];
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int idx = 1;
		for(int i = 0;i<4;i++) {
			stk = new StringTokenizer(bf.readLine());
			for(int j = 0;j<4;j++) {
				int fn = Integer.parseInt(stk.nextToken());
				int fd = Integer.parseInt(stk.nextToken());
				map[i][j] = fn;
				fish[fn] = new int[] {i, j, fd};
			}
		}
		max = 0;
		// 상어가 위치에 이동하면 해당 물고기를 먹고 상어의 이동방향은 먹은 물고기로 진행됨
		// 그다음에 물고기들이 이동함
		// 물고기이동은 1번부터 순서대로이며, 다른 물고기가 있으면 서로 위치를 스왑
		// 상어가 있는 칸도 이동 못함
		
		// 1. 원복을 시키기 힘들다면, 복사하는게 좋음
		// 2. 물고기의 위치만 교환되는것이지 방향은 교환시키지 않음!
		// 3. map과 fish정보의 싱크를 맞춰야함
		
		dfs(0, 0, map, fish, 0);
		System.out.println(max);
	}
	static void dfs(int sharky, int sharkx, int [][] map, int [][] fish, int sum) {
		int [][] copiedFish = copyFish(fish);
		int [][] copiedMap = copyMap(map);
		int fn = copiedMap[sharky][sharkx];
		sum += fn;
		max = Math.max(sum, max);
		copiedMap[sharky][sharkx] = -1;
		int sharkDir = copiedFish[fn][2];
		copiedFish[fn][2] = -1;
		fishMove(sharky, sharkx, copiedFish, copiedMap);
		for(int step = 1;step<=3;step++) {
			int ny = sharky + dy[sharkDir] * step;
			int nx = sharkx + dx[sharkDir] * step;
			if(ny >= 4 || ny < 0 || nx >= 4 || nx < 0 || copiedMap[ny][nx]== -1 || copiedFish[copiedMap[ny][nx]][2] == -1) continue;
			dfs(ny, nx, copiedMap, copiedFish, sum);
		}
		
	}
	static void fishMove(int sharky, int sharkx, int [][] fish, int [][] map) {
		// todo : 물고기 이동시키기
		for(int i = 1;i<17;i++) {
			if(fish[i][2] == -1) continue;
			int ny = fish[i][0] + dy[fish[i][2]];
			int nx = fish[i][1] + dx[fish[i][2]];
			if(ny >= 4 || ny < 0 || nx >= 4 || nx < 0 || (sharky == ny && sharkx == nx)) {
				// todo : 반시계로 회전하면서 이동할 수 있는곳 찾기
				// ↑, ↖, ←, ↙, ↓, ↘, →, ↗
				int idir = fish[i][2];
				int nd = idir + 1;
				boolean flg = false;
				while(nd != idir) {
					if(nd == 9) nd = 1;
					ny = fish[i][0] + dy[nd];
					nx = fish[i][1] + dx[nd];
					if(ny >= 4 || ny < 0 || nx >= 4 || nx < 0 || (sharky == ny && sharkx == nx)) {
						nd++;
					}else {
						flg = true;
						break;
					}
				}
				if(flg) {
					// todo : 다른 물고기와 위치 교환
					fish[i][2] = nd;
					int target = map[ny][nx];
					map[ny][nx] = map[fish[i][0]][fish[i][1]];
					map[fish[i][0]][fish[i][1]] = target;
					int ty = fish[i][0];
					int tx = fish[i][1];
					fish[i][0] = fish[target][0];
					fish[i][1] = fish[target][1];
					fish[target][0] = ty;
					fish[target][1] = tx;
				}
			}
			else {
				// todo : 다른 물고기와 위치 교환
				// 방향은 교환이 되면 안된다.
				int target = map[ny][nx];
				map[ny][nx] = map[fish[i][0]][fish[i][1]];
				map[fish[i][0]][fish[i][1]] = target;
				int ty = fish[i][0];
				int tx = fish[i][1];
				fish[i][0] = fish[target][0];
				fish[i][1] = fish[target][1];
				fish[target][0] = ty;
				fish[target][1] = tx;
			}
		}
		for(int i = 1;i<17;i++) {
			// todo : 맵에 이동된 물고기들 반영하기
			int [] fishInfo = fish[i];
			map[fishInfo[0]][fishInfo[1]] = i;
		}
		
	}
	static int[][] copyFish(int [][] fish) {
		int [][] cp = new int[17][3];
		for(int i = 1;i<17;i++) {
			cp[i] = fish[i].clone();
		}
		return cp;
	}
	static int[][] copyMap(int [][] map){
		int [][] cp = new int[4][4];
		for(int i = 0;i<4;i++) {
			cp[i] = map[i].clone();
		}
		return cp;
	}
}