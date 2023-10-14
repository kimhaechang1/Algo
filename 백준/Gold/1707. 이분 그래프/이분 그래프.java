import java.util.*;
import java.io.*;


public class Main{
	static int N;
	static int M;
	static ArrayList<Integer> [] g;
	static StringTokenizer stk;
	static int [] v;
	public static void main(String [] args) throws Exception{
		// 이분 그래프란?
		// 어떤 정점에서 인접한 정점들은 another 색으로 색칠하고
		// 본인은 this 색으로 색칠했을 때 정확하게 두 집합으로 나누어지는 그래프라면
		// 해당 그래프는 이분그래프로 할 수 있다는 것
		// 즉 어떤정점에서 인접한 정점들을 another 색으로 분류하고 있을 때, 
		// 해당정점과 인접한 정점 중 같은 색을 가진 정점이 있다면 문제가 발생할 수 있다.
		// 이분그래프 판별은 싸이클 찾기로 할 수 없다.
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		for(int t= 1;t<=T;t++) {
			stk = new StringTokenizer(bf.readLine());
			N = Integer.parseInt(stk.nextToken());
			M = Integer.parseInt(stk.nextToken());
			g = new ArrayList[N+1];
			for(int i= 1;i<N+1;i++) {
				g[i] = new ArrayList<>();
			}
			for(int i= 0;i<M;i++) {
				stk= new StringTokenizer(bf.readLine());
				int to= Integer.parseInt(stk.nextToken());
				int from = Integer.parseInt(stk.nextToken());
				g[to].add(from);
				g[from].add(to);
			}
			v = new int[N+1];
			boolean isCan = true;
			for(int i= 1;i<=N;i++) {
				if(v[i] !=0) continue;
				isCan = bfs(i, 1);
				
				if(!isCan) break;
			}
			
			if(isCan) {
				System.out.println("YES");
			}else {
				System.out.println("NO");
			}
			
		}
	}
	static boolean bfs(int startNode, int mark) {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(startNode);
		v[startNode] = mark;
		while(!queue.isEmpty()) {			
			int vtx =queue.poll();			
			for(int node : g[vtx]) {				
				if(v[node] == v[vtx]) return false;
				if(v[node] == 0) {
					v[node] = v[vtx] * -1;
					queue.add(node);
				}
			}
		}
		return true;
	}
}