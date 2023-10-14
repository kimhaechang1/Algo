import java.util.*;
import java.io.*;

public class Main{
	static char [][] map;
	static int N;
	static int M;
	static int time;
	static StringTokenizer stk;
	static int [] dy = {-1,1,0,0};
	static int [] dx = {0,0,-1,1};
	static boolean [][][] v;
	static int [] keys;
	static ArrayList<int []> [] list;
	static int sy;
	static int sx;
	public static void main(String [] args ) throws Exception {
		BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
		stk = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		map = new char[N][M];
		for(int i= 0;i<N;i++) {
			String str = bf.readLine();
			for(int j = 0;j<M;j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == '0') {
					sy = i;
					sx = j;
				}
			}
		}
		
		
		// 소문자를 만난경우
		// 시작 지점에서 해당 열쇠까지 걸린 최소 시간(칸수)를 열쇠 배열에 저장
		// 비트마스킹으로 방문체크를 할 시간이다.
		// 왜냐하면 지금 내가 들고있는 열쇠의 종류에 따라 달라지기 때문이다.
		// 어떤어떤 열쇠들을 들고서 지나다녔는지가 중요하기 때문에 
		// 비트부호를 하나 씌운다.
		// abcdef
		// 000000
		// 111111
		// 0~ (32+16+8+4+2+1) = 0 ~ 63 으로 어떤 열쇠들을 소지하고 다녔는지 표시 할 수 있다.
		// 단순히 탈출여부를 묻는 문제였으면 굳이 이럴필요는 없지만 --> 열쇠문제
		// 최소 시간을 구해야하므로 내가 어떤상태로 지나갔는지가 중요해진다.
		
		v = new boolean[N][M][64];
		int time = bfs();
		System.out.println(time);
	}
	static int bfs() {
		Queue<int []> queue  = new ArrayDeque<>();
		v[sy][sx][0] = true;
		queue.add(new int[] {sy, sx, 0, 0}); 
		while(!queue.isEmpty()) {
			int [] now = queue.poll();
			if(map[now[0]][now[1]] == '1') return now[2];
			int nowCnt =  now[2];
			int nowKeys = now[3];
			for(int k = 0;k<4;k++) {
				int ny = now[0]+dy[k];
				int nx = now[1]+dx[k];
				if(ny >= N || ny < 0 || nx >=M || nx<0 || v[ny][nx][nowKeys] || map[ny][nx] == '#') continue;
				
				if(map[ny][nx] >= 65 && map[ny][nx] <= 90) {
					// 문의 경우
					// 즉 현재 문에 해당하는 알파벳 입장에서 필요한 key가 현재까지 들고다니는 key들 중에 있는지 확인
					// 그말은 즉, 000001 이면 A에 해당하는 key가 필요한 것
					int canOpen = ((1 << (map[ny][nx] -'A')) & nowKeys);
					if(canOpen != 0) {
						v[ny][nx][nowKeys] = true;
						queue.add(new int[] {ny, nx, nowCnt+1, nowKeys});
					}
					
				}else if(map[ny][nx] >= 97 && map[ny][nx] <= 122) {
					// 열쇠의 경우
					// 해당열쇠를 먹은 마스킹을 처리하고
					// 그 상태를 visited 처리 하고 큐에 담는다.
					int nK = (1 << (map[ny][nx]-'a')) | nowKeys;
					v[ny][nx][nowKeys] = true;
					queue.add(new int[] {ny, nx, nowCnt+1, nK});
				}else {
					v[ny][nx][nowKeys] = true;
					queue.add(new int[] {ny, nx, nowCnt+1, nowKeys});
				}
			}
		}
		return -1;
	}
}