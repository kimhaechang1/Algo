import java.util.*;
import java.io.*;

public class Main{
	static int N;
	static int M;
	static int R;
	static StringTokenizer stk;
	static int [][] arr;
	static int [] dy = {-1,1,0,0};
	static int [] dx = {0,0,-1,1};
	static char [][] map;
	public static void main(String [] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		stk = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		R = Integer.parseInt(stk.nextToken());
		arr = new int[N][M];
		for(int i= 0;i<N;i++) {
			stk = new StringTokenizer(bf.readLine());
			for(int j = 0;j<M;j++) {
				arr[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		// 매 라운드마다 공격수 먼저 그이후 수비수
		// 공격수는 너머뜨리고, 수비수는 도미노를 세운다.
		// K인 도미노를 넘어뜨리면, K-1개의 도미노가 넘어지고, 이미 넘어진 도미노의 칸을 공격한다면 아무일도 일어나지 않는다.
		// 수비수는 넘어져 있느 도미노들 중에서 원하는 것을 하나 세울 수 있는데, 넘어지지 않은 도미노를 세울려고 한다면 아무일도 일어나지 않는다.
		// 3, 4 번 과정이 반복된다., 매 라운드마다 공격수가 넘어뜨린 도미노의 개수를 세고 R라운드동안의 총합이 공격수의 점수가 된다.
		
		// 구현, 시뮬레이션
		int s = 0;
		map = new char[N][M];
		for(int i= 0;i<N;i++) {
			for(int j = 0;j<M;j++) {
				map[i][j] = 'S';
			}
		}
		while(R-- > 0) {
			stk = new StringTokenizer(bf.readLine());
			int sy = Integer.parseInt(stk.nextToken())-1;
			int sx = Integer.parseInt(stk.nextToken())-1;
			String D = stk.nextToken();
			if(D.equals("E")) {
				s+=attack(sy, sx, 3);
			}else if(D.equals("W")) {
				s+=attack(sy, sx, 2);
			}else if(D.equals("S")) {
				s+=attack(sy, sx, 1);
			}else {
				s+=attack(sy, sx, 0);
			}
			stk = new StringTokenizer(bf.readLine());
			int ry = Integer.parseInt(stk.nextToken())-1;
			int rx = Integer.parseInt(stk.nextToken())-1;
			map[ry][rx] = 'S';
		}
		System.out.println(s);
		StringBuilder sb = new StringBuilder();
		for(int i= 0;i<N;i++) {
			for(int j= 0;j<M;j++) {
				sb.append(map[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}
	static int attack(int y, int x, int d) {
		map[y][x] = 'F';
		int cnt = arr[y][x];
		int score = 1;
		Queue<int []> queue =new ArrayDeque<>();
		for(int i = 1;i<cnt;i++) {
			y += dy[d];
			x += dx[d];
			if(y >= N || y < 0 || x >= M || x < 0) break;
			if(map[y][x] == 'S') {
				map[y][x] = 'F';
				if(map[y][x] > 1) {
					queue.add(new int[] {y, x});
				}
				score++;
			}
		}
		while(!queue.isEmpty()) {
			int [] now = queue.poll();
			int c = arr[now[0]][now[1]];
			for(int i = 1;i<c;i++) {
				now[0] += dy[d];
				now[1] += dx[d];
				if(now[0] >= N || now[0] < 0 || now[1] >= M || now[1] < 0) break;
				if(map[now[0]][now[1]] == 'S') {
					map[now[0]][now[1]] = 'F';
					if(map[now[0]][now[1]] > 1) {
						queue.add(new int[] {now[0], now[1]});
					}
					score++;
				}
			}
		}
		return score;
		
	}
}