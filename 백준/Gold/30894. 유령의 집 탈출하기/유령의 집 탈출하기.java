import java.util.*;
import java.io.*;

public class Main{
	static int n;
	static int m;
	static int sy,sx,ty,tx;
	static int [] dy = {0,0,1,0,-1};
	static int [] dx = {0,1,0,-1,0};
	static char [][] map;
	static StringTokenizer stk;
	static ArrayList<int[]> state;
	static boolean [][][] isWatched;
	public static void main(String [] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		stk = new StringTokenizer(bf.readLine());
		n = Integer.parseInt(stk.nextToken());m = Integer.parseInt(stk.nextToken());
		stk = new StringTokenizer(bf.readLine());
		sy = Integer.parseInt(stk.nextToken())-1;sx = Integer.parseInt(stk.nextToken())-1;
		ty = Integer.parseInt(stk.nextToken())-1;tx = Integer.parseInt(stk.nextToken())-1;
		map = new char[n][m];
		state = new ArrayList<>();
		for(int i = 0;i<n;i++) {
			char [] temp = bf.readLine().toCharArray();
			for(int j = 0;j<m;j++) {
				map[i][j] = temp[j];
				if(map[i][j]-'0' > -1 && map[i][j]-'0' < 4 ) {
					state.add(new int[] {i, j});
				}
			}
		}
		
		// 4가지 모드의 지도를 만들기
		isWatched = new boolean[n][m][4];
		for(int k = 0;k<4;k++) {
			for(int i = 0;i<n;i++) {
				for(int j = 0;j<m;j++) {
					if(map[i][j] - '0'> -1 && map[i][j] - '0' < 4) {
						int dir = ((map[i][j] - '0'+k)%4)+1;
						//System.out.println("i : "+i +" j : "+j +" dir : "+dir);
						int ny = i;
						int nx = j;
						while(true) {
							isWatched[ny][nx][k] = true;
							ny += dy[dir];
							nx += dx[dir];
							if(OOB(ny, nx) || (map[ny][nx] - '0'> -1 && map[i][j] - '0' < 4) || map[ny][nx] == '#') break;
						}
					}
				}
			}
		}
		
		//printGhostMap(isWatched);
		
		
		int val = bfs();
		System.out.print(val == -1 ? "GG" : val);
	}
	static int bfs() {
		Queue<int []> queue = new ArrayDeque<>();
		int [] values = new int[4+state.size()];
		values[0] = sy;
		values[1] = sx;
		values[2] = 0;
		values[3] = 0; // dummy
		for(int i = 0;i<state.size();i++) {
			int [] pos = state.get(i);
			values[4+i] = map[pos[0]][pos[1]] - '0'+ 1;
		}
		int res = -1;
		boolean [][][] v = new boolean[n][m][4];
		// 해당 칸에 도착했을때 당시 4가지 유령의 모드에 따라 도착시간이 달라질 수 있다.
		queue.add(values);
		// 제자리 머무르기, 어떤 방향으로 해당 칸에 도착
		while(!queue.isEmpty()) {
			int [] now = queue.poll();
			//System.out.println(Arrays.toString(now));
			if(now[0] == ty && now[1] == tx) {
				res = now[2];
				break;
			}
			// 우 하 좌 상
			
			// depth 에 따라 유령들의 방향이 달리지고, 그 상황에 따라 지도도 달라진다.
			// 어떤 지도의 상황일때 도착한건지에 따라 달라진다.
			// 그말은 즉, 해당 하는 칸에 몇 뎁스로 도착했는지에 따라 달라진다.
			// 근데 유령이 보는 방향들은 총 4가지 이므로, 지도는 4가지 종류로 생길 수 있다.
			// 각 칸마다 유령이 어떤 상황일때 도착했는지를 선택한다.
			
			// 그밖에 방향으로 뻗어가는경우
			for(int k = 0;k<5;k++) {
				int ny = now[0] + dy[k];
				int nx = now[1] + dx[k];
				if(OOB(ny, nx) || map[ny][nx] == '#' || (map[ny][nx]-'0'> -1 && map[ny][nx]-'0' < 4) || isWatched[ny][nx][(now[2] + 1) % 4]) continue;
				if(!v[ny][nx][(now[2] + 1) % 4]) {
					int [] next = new int[now.length];
					next[0] = ny;
					next[1] = nx;
					v[ny][nx][(now[2] + 1) % 4] = true;
					next[2] = now[2]+1;
					next[3] = 0;
					// 유령 얼굴
					// 우 하 좌 상
					for(int i = 0;i<state.size();i++) {
						int sd = now[4+i];
						next[4+i] = sd;
					}
					queue.add(next);
				}
			}
			
		}
		
		return res;
	}
	static boolean OOB(int y, int x) {
		return y >= n || y < 0 || x >= m || x < 0;
	}
	static void printGhostMap(boolean [][][] map) {
		for(int k = 0;k<4;k++) {
			System.out.println("k : "+k);
			for(int i = 0;i<n;i++) {
				for(int j= 0;j<m;j++) {
					if(map[i][j][k]) System.out.print(1+" ");
					else System.out.print(0+" ");
				}
				System.out.println();
			}
			System.out.println("=====================");
		}
			
	}
	static void printState(int [][][] map, int y, int x) {
		
		for(int i = 0;i<n;i++) {
			for(int j= 0;j<m;j++) {
				if(map[i][j][0] == 9999999) System.out.print("n ");
				else {
					if(i == y && j == x) System.out.print("p ");
					else System.out.print(map[i][j][0]+" ");
				}
			}
			System.out.println();
		}
	}
}