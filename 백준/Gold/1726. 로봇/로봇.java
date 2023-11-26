import java.util.*;
import java.io.*;

public class Main{
	static int N;
	static int M;
	static int [][] map;
	static StringTokenizer stk;
	static int [] dy = {0,0,1,-1};
	static int [] dx = {1,-1,0,0};
	static int [][][] v;
	static int sy;
	static int sx;
	static int sd;
	static int ty;
	static int tx;
	static int td;
	public static void main(String [] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		stk = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		map = new int[N][M];
		for(int i = 0;i<N;i++) {
			stk = new StringTokenizer(bf.readLine());
			for(int j =0;j<M;j++){
				map[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		// Go k 이 때 k = 1~3의 자연수, k칸 만큼 움직임
		// turn dir : left or right 왼쪽 90도 혹은 오른쪽 90도
		// 1은 갈수없는 지점
		// 즉, bfs로 최단거리 구하는건데
		// 각 칸마다 도달했을때의 로봇이 바라보는방향이 4가지가 존재한다.
		// 그리고 최소 명령으로 도달했는지 여부가 중요하기 때문에 해당 도달하는 칸에 몇번째 명령으로 도달했는지 기록해야한다.
		stk = new StringTokenizer(bf.readLine());
		sy = Integer.parseInt(stk.nextToken())-1;
		sx = Integer.parseInt(stk.nextToken())-1;
		sd = Integer.parseInt(stk.nextToken())-1;
		v = new int[N][M][4];
		for(int i = 0;i<N;i++) {
			for(int j = 0;j<M;j++) {
				for(int k= 0;k<4;k++) {
					v[i][j][k] = 987654321;
				}
			}
		}
		// 상 우 하 좌
		
		v[sy][sx][sd] = 0;
		stk = new StringTokenizer(bf.readLine());
		ty = Integer.parseInt(stk.nextToken())-1;
		tx = Integer.parseInt(stk.nextToken())-1;
		td = Integer.parseInt(stk.nextToken())-1;
		
		bfs();
		System.out.println(v[ty][tx][td]);
	}
	static void bfs() {
		Queue<int []> queue = new ArrayDeque<>();
		queue.add(new int[] {sy, sx, sd, 0});
		while(!queue.isEmpty()) {
			int [] now = queue.poll();
			if(now[0] == ty && now[1] == tx && now[2] == td) break;
			// 방향을 제자리에서 교체하는 경우
			int d = now[2];
			// 왼쪽 회전
			if(d == 0) {
				d = 3;
			}else if(d == 1) {
				d = 2;
			}else if(d == 2) {
				d = 0;
			}else {
				d = 1;
			}
			if(v[now[0]][now[1]][d] >= now[3]+1) {
				v[now[0]][now[1]][d] =now[3]+1;
				queue.add(new int[] {now[0], now[1], d, now[3]+1});
			}
			// 오른쪽 회전
			d = now[2];
			if(d == 0) {
				d = 2;
			}else if(d == 1) {
				d = 3;
			}else if(d == 2) {
				d = 1;
			}else {
				d = 0;
			}
			if(v[now[0]][now[1]][d] > now[3]+1) {
				v[now[0]][now[1]][d] =now[3]+1;
				queue.add(new int[] {now[0], now[1], d, now[3]+1});
			}
			// 현재 로봇이 바라보는 방향으로 이동하는 경우
			for(int i=0;i<3;i++) {
				now[0]+=dy[now[2]];
				now[1]+=dx[now[2]];
				if(now[0] < 0 || now[0] >= N || now[1] < 0 || now[1] >= M || map[now[0]][now[1]] == 1) break;
				if(v[now[0]][now[1]][now[2]] >= now[3]+1) {
					v[now[0]][now[1]][now[2]] = now[3]+1;
					queue.add(new int[] {now[0], now[1], now[2], now[3]+1});
				}
			}
		}
	}
}