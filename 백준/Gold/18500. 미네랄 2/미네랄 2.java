import java.util.*;
import java.io.*;

public class Main{
	static char [][] map;
	static int R;
	static int C;
	static int N;
	static int [] arr;
	static StringTokenizer stk;
	static int [] dy = {-1,1,0,0};
	static int [] dx = {0,0,-1,1};
	static int c [] = {R-1,0};
	static int s [] = {R-1,C-1};
	static int cnt;
	static boolean [][] visited;
	static Queue<int []> queue;
	public static void main(String [] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		stk = new StringTokenizer(bf.readLine());
		R = Integer.parseInt(stk.nextToken());
		C = Integer.parseInt(stk.nextToken());
		map = new char[R][C];
		cnt = 0;
		for(int i= 0;i<R;i++) {
			String str = bf.readLine();
			for(int j= 0;j<C;j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == 'x') {
					cnt++;
				}
			}
		}
		N = Integer.parseInt(bf.readLine());
		arr = new int[N];
		stk = new StringTokenizer(bf.readLine());
		for(int i=0;i<N;i++) {
			arr[i] = R - Integer.parseInt(stk.nextToken());
		}
		// 두 사람은 양끝에 서있다.
		// 수평 높이로 창을 던져서 미네랄을 파괴한다.
		// 왼쪽의 사람은 오른쪽으로 던지고, 오른쪽의 사람은 왼쪽으로 던진다.
		int turn = 0;
		while(turn < N) {
			boolean isR = false;
			if((turn+1)%2 == 0) {
				isR = true;
			}
			int [] mine = find(isR ,arr[turn]);
			turn++;
			if(mine[0] == -1 && mine[1] == -1) continue;
			map[mine[0]][mine[1]] = '.';
			cnt--;
			queue= new ArrayDeque<>();
			visited = new boolean[R][C];
			int c = 0;
			for(int i = 0;i<C;i++) {
				if(!visited[R-1][i] && map[R-1][i] == 'x') {
					c += bfs(R-1,i);					
				}
			}
			if(c != cnt) {
				// 클러스터가 분리됬음을 의미함
				go();
			}
			
		}
		for(int i =0;i<R;i++) {
			for(int j = 0;j<C;j++) {
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}

	static int [] find(boolean isR, int h) {
		if(!isR) {
			for(int i= 0;i<C;i++) {
				if(map[h][i] == 'x') {
					return new int[] {h, i};
				}
			}
		}else {
			for(int i= C-1;i>-1;i--) {
				if(map[h][i] == 'x') {
					return new int[] {h, i};
				}
			}
		}
		return new int[] {-1,-1};
	}
	static int bfs(int sy, int sx) {
		// 클러스터 찾기
		Queue<int[]> queue = new ArrayDeque<>();
		int cnt = 1;
		queue.add(new int[] {sy, sx});
		visited[sy][sx] = true;
		while(!queue.isEmpty()) {
			int [] now = queue.poll();
			for(int k = 0;k<4;k++) {
				int ny= now[0] + dy[k];
				int nx = now[1] + dx[k];
				if(ny>=R || ny < 0 || nx>=C || nx< 0 || visited[ny][nx] || map[ny][nx] == '.') continue;
				cnt++;
				visited[ny][nx] = true;
				queue.add(new int[] {ny, nx});
			}
		}
		return cnt;
	}
	static void go() {
		// 중력 작용
		Queue<int []> queue = new ArrayDeque<>();
		Queue<int []> temp = new ArrayDeque<>();
		for(int i= R-2;i>-1;i--) {
			for(int j = 0;j<C;j++) {
				if(!visited[i][j] && map[i][j] == 'x') {
					queue.add(new int[] {i,j});
				}
			}
		}
		while(true) {
			boolean canGo = true;
			int size = queue.size();
			for(int i= 0;i<size;i++) {
				int [] now = queue.poll();
				int ny = now[0]+dy[1];
				if(ny==R) {
					canGo = false;
					break;
				}
				else if(visited[ny][now[1]] && map[ny][now[1]] == 'x') {
					canGo = false;
					break;
				}
				temp.add(new int[] {ny, now[1]});
			}			
			if(!canGo) break;
			while(!temp.isEmpty()) {
				int [] nyx = temp.poll();
				map[nyx[0]][nyx[1]] = map[nyx[0]-1][nyx[1]];
				map[nyx[0]-1][nyx[1]] = '.';
				queue.add(new int[] {nyx[0],nyx[1]});
				
			}
		}
	}
}