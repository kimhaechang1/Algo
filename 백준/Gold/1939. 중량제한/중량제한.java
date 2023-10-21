import java.util.*;
import java.io.*;

public class Main{
	static StringTokenizer stk;
	static int N;
	static int M;
	static class Node{
		int e;
		int w;
		public Node(int e, int w) {
			this.e = e;
			this.w = w;
		}
	}
	static ArrayList<Node> [] g;
	
	public static void main(String [] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		stk = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		g = new ArrayList[N+1];
		for(int i = 1;i<N+1;i++) {
			g[i] = new ArrayList<>();
		}
		for(int i = 0;i<M;i++) {
			stk = new StringTokenizer(bf.readLine());
			int v1 = Integer.parseInt(stk.nextToken());
			int v2 = Integer.parseInt(stk.nextToken());
			int w = Integer.parseInt(stk.nextToken());
			g[v1].add(new Node(v2,w));
			g[v2].add(new Node(v1,w));
		}
		stk = new StringTokenizer(bf.readLine());
		int v1 = Integer.parseInt(stk.nextToken());
		int v2 = Integer.parseInt(stk.nextToken());
		// bfs + 이분탐색
		long s = 1;
		long e = Integer.MAX_VALUE-1;
		while(s < e) {
			long mid = ((long)(s+e))/2;
			if(search(mid, v1, v2)) {
				s = mid+1;
			}else {
				e = mid;
			}
		}
		System.out.println(e-1);
	}
	static boolean search (long max, int v1, int v2) { 
		Queue<Integer> queue = new ArrayDeque<>();
		boolean [] v = new boolean[N+1];
		v[v1] = true;
		queue.add(v1);
		while(!queue.isEmpty()) {
			int now = queue.poll();
			if(now == v2) return true;
			for(Node node : g[now]) {
				if(v[node.e]) continue;
				if(max > node.w) continue;
				v[node.e] = true;
				queue.add(node.e);
			}
		}
		return false;
	}
}