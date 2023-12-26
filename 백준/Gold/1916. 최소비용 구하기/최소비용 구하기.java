import java.util.*;
import java.io.*;

public class Main{
	static int N;
	static int M;
	static long [] cost;
	static StringTokenizer stk;
	static ArrayList<Node> [] g;
	static class Node implements Comparable<Node>{
		int t;
		long w;
		public Node(int to, long weight) {
			this.t = to;
			this.w = weight;
		}
		public int compareTo(Node o2) {
			return Long.compare(this.w, o2.w);
		}
	}
	static boolean [] v;
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		M = Integer.parseInt(bf.readLine());
		cost = new long[N+1];
		g = new ArrayList[N+1];
		for(int i= 1;i<=N;i++) {
			g[i] = new ArrayList<>();
		}
		for(int i= 0;i<M;i++) {
			stk = new StringTokenizer(bf.readLine());
			int f = Integer.parseInt(stk.nextToken());
			int to = Integer.parseInt(stk.nextToken());
			long w = Long.parseLong(stk.nextToken());
			g[f].add(new Node(to, w));
		}
		
		stk = new StringTokenizer(bf.readLine());
		int s= Integer.parseInt(stk.nextToken());
		int e = Integer.parseInt(stk.nextToken());
		int cnt = 0;
		Arrays.fill(cost, 1, cost.length, Long.MAX_VALUE);
		cost[s] = 0;
		PriorityQueue<Node> pq = new PriorityQueue<>() ;
		pq.add(new Node(s, cost[s]));
		v = new boolean[N+1];
		
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			if(v[now.t]) continue;
			v[now.t] = true;
			cost[now.t] = now.w;
			if(now.t == e) break;
			if(++cnt == N+1) break;
			for(Node node : g[now.t]) {
				if(!v[node.t] && cost[node.t] > now.w+node.w) {
					cost[node.t] = now.w+node.w;
					pq.add(new Node(node.t, cost[node.t]));
				}
			}
		}
		System.out.println(cost[e]);
	}
}