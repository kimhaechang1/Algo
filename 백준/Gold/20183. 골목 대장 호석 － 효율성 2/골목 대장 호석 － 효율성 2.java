import java.util.*;
import java.io.*;

public class Main{
	static int N;
	static int M;
	static int A;
	static int B;
	static long C;
	static ArrayList<Node> [] g;
	static class Node{
		int e;
		long w;
		public Node(int e, long w) {
			this.e = e;
			this.w = w;
		}
	}
	static StringTokenizer stk;
	static boolean[] v;
	public static void main(String [] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		stk = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		A = Integer.parseInt(stk.nextToken());
		B = Integer.parseInt(stk.nextToken());
		C = Long.parseLong(stk.nextToken());
		g = new ArrayList[N+1];
		for(int i= 1;i<N+1;i++) {
			g[i] = new ArrayList<>();
		}
		for(int i = 0;i<M;i++) {
			stk = new StringTokenizer(bf.readLine());
			int a= Integer.parseInt(stk.nextToken());
			int b= Integer.parseInt(stk.nextToken());
			long c = Long.parseLong(stk.nextToken());
			g[a].add(new Node(b,c));
			g[b].add(new Node(a,c));
		}
		boolean [] v = new boolean[N+1];
		long [] cost = new long[N+1];
		Arrays.fill(cost, Long.MAX_VALUE);
		long [] edges = new long[N+1];
		Arrays.fill(edges, Long.MAX_VALUE);
		cost[A] = 0;
		edges[A] = 0;
		PriorityQueue<long []>pq = new PriorityQueue<>((a, b)->{
			return Long.compare(a[2], b[2]);
		});
		pq.add(new long [] {A, cost[A], edges[A]});
		int cnt = 0;
		while(!pq.isEmpty()) {
			long [] now = pq.poll();
			if(v[(int)now[0]]) continue;
			v[(int)now[0]] = true;
			if(++cnt == N) break;
			for(Node node : g[(int)now[0]]) {
				if(!v[node.e] && node.w+now[1] <= C && edges[node.e] > Math.max(node.w, now[2]) ) {
					cost[node.e] = node.w + now[1];
					edges[node.e] = Math.max(node.w, now[2]);
					pq.add(new long[] {node.e , cost[node.e], edges[node.e]});
				}
			}
		} 
		if(edges[B] == Long.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(edges[B]);
		}
		
	}
}