import java.util.*;
import java.io.*;


public class Main{
	static int N;
	static int K;
	static int [][] map;
	static StringTokenizer stk;
	static int S;
	static int Y,X;
	static PriorityQueue<int[]> pq;
	public static void main(String [] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		stk = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(stk.nextToken());
		K = Integer.parseInt(stk.nextToken());
		map = new int[N][N];
		pq = new PriorityQueue<>((o1, o2)->{
			return o1[2] - o2[2];
		});
		for(int i= 0;i<N;i++) {
			stk = new StringTokenizer(bf.readLine());
			for(int j= 0;j<N;j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
				if(map[i][j] > 0) {
					pq.add(new int[] {i, j, map[i][j]});
				}
			}
		}
		stk = new StringTokenizer(bf.readLine());
		S = Integer.parseInt(stk.nextToken());
		Y = Integer.parseInt(stk.nextToken());
		X = Integer.parseInt(stk.nextToken());
		int []  dy = {-1,1,0,0};
		int [] dx = {0,0,-1,1};
		Queue<int []> temp = new ArrayDeque<>();
		while(S-- > 0 && !pq.isEmpty()) {
			int size = pq.size();
			for(int i = 0;i<size;i++) {
				int [] now =  pq.poll();
				for(int k = 0;k<4;k++) {
					int ny  = now[0] + dy[k];
					int nx = now[1] + dx[k];
					if(ny >=N || ny < 0 || nx >=N || nx < 0 || map[ny][nx] !=0) continue;
					map[ny][nx] = now[2];
					temp.add(new int[] {ny, nx, now[2]});
				}
			}
			while(!temp.isEmpty()) {
				pq.add(temp.poll());
			}
		}
		System.out.println(map[Y-1][X-1]);
	}
}