import java.util.*;
import java.io.*;

public class Main{
	static int N;
	static int M;
	static int K;
	static int [][] map;
	static boolean [][][][] v;
	static StringTokenizer stk;
	public static void main(String [] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		stk = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		K = Integer.parseInt(stk.nextToken());
		v = new boolean [N][M][K+1][2]; // 맨 마지막에 낮/밤	
		map = new int[N][M];
		for(int i= 0;i<N;i++) {
			String str = bf.readLine();
			for(int j=0;j<M;j++) {
				map[i][j] = str.charAt(j)-'0';
			}
		}
		if(N == 1 && M == 1) {
			System.out.println(1);
		}else {
			int time = bfs();
			System.out.println(time);
		}
		
	}
	static int [] dy = {-1,1,0,0};
	static int [] dx = {0,0,-1,1};
	static int bfs() {
		Queue<int []> queue = new ArrayDeque<>();
		queue.add(new int[] {0,0,0,1,0}); // ny, nx, k, cnt, 0 : 낮, 1 : 밤
		// 같은 칸에 대하여 똑같은 칸수를 부순 상태에서 똑같은 낮 혹은 밤 시간대에 같은 칸에 있게 된다면 큐에 넣지 말아야 한다.
		v[0][0][0][0] = true;
		while(!queue.isEmpty()) {
			int [] now = queue.poll();
//			System.out.println(Arrays.toString(now));
			int cnt = now[3];
			int present = now[4];
			for(int k = 0;k<4;k++) {
				int ny = now[0]+dy[k];
				int nx = now[1]+dx[k];
				if(ny>=N || ny < 0 || nx>=M || nx< 0) continue;
				if(ny == N-1 && nx == M-1) return cnt+1;
				// 앞에 가야할 것이 벽이냐 아니냐로 나눈다.
				
				if(map[ny][nx] == 0) {
					if(present == 0 && !v[ny][nx][now[2]][1]) {
						v[ny][nx][now[2]][1] = true;
						queue.add(new int[] {ny, nx, now[2], cnt+1, 1});
					}
					else if(present == 1 && !v[ny][nx][now[2]][0]) {
						v[ny][nx][now[2]][0] = true;
						queue.add(new int[] {ny, nx, now[2], cnt+1, 0});
					}
				}else {
					if(present == 0 && now[2]+1 <=K && !v[ny][nx][now[2]+1][1]) {
						v[ny][nx][now[2]+1][1] = true;
						queue.add(new int[] {ny, nx, now[2]+1, cnt+1, 1});
					}else if(present == 1 && !v[now[0]][now[1]][now[2]][0]) {
						v[now[0]][now[1]][now[2]][0] = true;
						queue.add(new int[] {now[0],now[1],now[2],cnt+1,0});
					}
					
				}
			}
		}
		return -1;
	}
}