import java.util.*;
import java.io.*;

public class Main{
	static int N;
	static int K; // 시작 정점
	static int [][] map;
	static StringTokenizer stk;
	static ArrayList<Node> [] g;
	static class Node{
		int e;
		int w;
		public Node(int e, int w) {
			this.e = e;
			this.w = w;
		}
	}
	static boolean []v;
	static int min = 987654321;
	public static void main(String [] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		stk = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(stk.nextToken());
		K = Integer.parseInt(stk.nextToken());
		map = new int[N][N];
		g = new ArrayList[N];
		for(int i= 0;i<N;i++) {
			g[i] = new ArrayList<>();
		}
		for(int i= 0;i<N;i++) {
			stk =new StringTokenizer(bf.readLine());
			for(int j = 0;j<N;j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
				if(i == j) continue;
				g[i].add(new Node(j, map[i][j]));
			}
		}
		
		// 정점 얼마 없으니까 플로이드 워셜 돌리고
		for(int k= 0;k<N;k++) {
			for(int i = 0;i<N;i++) {
				if(i == k) continue;
				for(int j= 0;j<N;j++) {
					if( j== k) continue;
					if(j == i) continue;
					map[i][j] = Math.min(map[i][j], map[i][k]+map[k][j]);
				}
			}
		}
		// 백트래킹으로 최솟값 찾는다.
		v = new boolean[N];
		dfs(K, 1, 1 << K, 0);
		System.out.print(min);
	}
	static void dfs(int node, int depth, int v, int w) {
		if(depth == N) {
			min = Math.min(min, w);
			return;
		}
		
		for(int i= 0;i<N;i++) {
			if((v & (1 << i)) != 0) {
				continue;
			}
			dfs(i, depth+1, v | (1 << i), w + map[node][i]);
		}
	}
}