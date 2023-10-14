import java.util.*;
import java.io.*;

public class Main{
	static int N;
	static int E;
	static StringTokenizer stk;
	static ArrayList<Node> [] g;
	static class Node{
		int f;
		int w;
		public Node(int f, int w) {
			this.f = f;
			this.w = w;
		}
	}
	static int [] cost;
	static int v1;
	static int v2;
	
	public static void main(String [] args ) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		stk = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(stk.nextToken());
		E = Integer.parseInt(stk.nextToken());
		g = new ArrayList[N+1];
		for(int i = 1;i<N+1;i++) {
			g[i] = new ArrayList<>();
		}
		for(int i = 0;i<E;i++) {
			stk = new StringTokenizer(bf.readLine());
			int t = Integer.parseInt(stk.nextToken());
			int f = Integer.parseInt(stk.nextToken());
			int w = Integer.parseInt(stk.nextToken());
			g[t].add(new Node(f, w));
			g[f].add(new Node(t, w));
		}
		stk = new StringTokenizer(bf.readLine());
		v1 = Integer.parseInt(stk.nextToken());
		v2 = Integer.parseInt(stk.nextToken());

		int s = v1;
		int e = v2;
		
		cost = new int[N+1];
		int res1 = 0;// 1-> v1 -> v2 -> N
		int res2 = 0;// 1 -> v2 -> v1 -> N 두가지 경우를 비교한다.
		
		
		res1 += dijkstra(1, v1);
		res1 += dijkstra(v1, v2);
		res1 += dijkstra(v2, N);
		cost = new int[N+1];
		res2 += dijkstra(1, v2);
		res2 += dijkstra(v2, v1);
		res2 += dijkstra(v1, N);
		if(res1 >= 200000000 && res2>=200000000) {
			System.out.println(-1);
		}else {
			System.out.println(Math.min(res1, res2));
		}
				
	}
	static int dijkstra(int s, int e) {
		final int INF = 200000000;
		Arrays.fill(cost, INF);
		boolean [] v = new boolean[N+1];
		cost[s] = 0;

		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2)->{
			return o1.w-o2.w;
		});
		
		int cnt = 0;
		pq.add(new Node(s, cost[s]));
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			if(v[now.f]) continue;
			v[now.f] = true;
			cost[now.f] = now.w;
			if(now.f == e) break;
			if(++cnt == N) break;
			for(Node node : g[now.f]) {
				if(!v[node.f] && cost[node.f] > now.w + node.w) {
					cost[node.f] = now.w + node.w;
					pq.add(new Node(node.f, cost[node.f]));
				}
			}
		}
		return cost[e];
	}
}