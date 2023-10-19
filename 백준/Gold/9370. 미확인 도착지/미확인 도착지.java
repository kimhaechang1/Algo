import java.util.*;
import java.io.*;

public class Main{
	static int N;
	static int M;
	static int t;
	static int S;
	static int G;
	static int H;
	static ArrayList<Node> [] g;
	static class Node{
		int e;
		int w;
		public Node(int e, int w) {
			this.e = e;
			this.w = w;
		}
	}
	static StringTokenizer stk;
	public static void main(String [] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		StringBuilder sb = new StringBuilder();
		while(T-->0) {
			stk = new StringTokenizer(bf.readLine());
			N = Integer.parseInt(stk.nextToken());
			M = Integer.parseInt(stk.nextToken());
			t = Integer.parseInt(stk.nextToken());
			g = new ArrayList[N+1];
			for(int i= 1;i<N+1;i++) {
				g[i] = new ArrayList<>();
			}
			Node [] nodes = new Node[t+1];
			stk = new StringTokenizer(bf.readLine());
			S = Integer.parseInt(stk.nextToken());
			G = Integer.parseInt(stk.nextToken());
			H = Integer.parseInt(stk.nextToken());
			int [] cost = new int[N+1];
			for(int i= 0;i<M;i++) {
				stk = new StringTokenizer(bf.readLine());
				int a= Integer.parseInt(stk.nextToken());
				int b = Integer.parseInt(stk.nextToken());
				int d = Integer.parseInt(stk.nextToken());
				g[a].add(new Node(b, d));
				g[b].add(new Node(a, d));
			}
			int [] dest = new int[t];
			for(int i= 0;i<t;i++) {
				dest[i] = Integer.parseInt(bf.readLine());
				//System.out.println(dest[i]);
			}
			// 다익스트라에서 최단거리에 특정 간선이 포함되어있는지 여부를 검사하려면
			// 모든 경우의 수를 다 따져야한다.
			// S -> H -> G -> dest
			// S -> G -> H -> dest
			// 이 둘 중 더 작은값이 
			// S -> dest 와 같으면, 적어도 H->G 혹은 G->H를 사용한 것이 된다.
			PriorityQueue<Integer> pq = new PriorityQueue<>();
			for(int d : dest) {
				int res1 = dijkstra(S, H) + dijkstra(H,G) + dijkstra(G,d);
				int res2 = dijkstra(S, G) + dijkstra(G,H) + dijkstra(H,d);
				int res3 = dijkstra(S, d);
				if(Math.min(res1,res2) == res3) {
					pq.add(d);
				}
			}
			while(!pq.isEmpty()) {
				sb.append(pq.poll()).append(" ");
			}
			sb.append("\n");
		}
		System.out.print(sb);
		
	}
	static int dijkstra(int s, int e) {
		PriorityQueue<Node> pq = new PriorityQueue<>((o1,o2)->{
			return o1.w - o2.w;
		});
		int [] cost = new int[N+1];
		Arrays.fill(cost, 987654321);
		cost[s] = 0;
		pq.add(new Node(s, cost[s]));
		int cnt = 0;
		boolean [] v = new boolean[N+1];
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			if(v[now.e]) continue;
			v[now.e] = true;
			cost[now.e] = now.w;
			if(now.e == e) break;
			if(++cnt == N+1) break;
			for(Node node : g[now.e]) {
				if(!v[node.e] && cost[node.e] > now.w + node.w) {
					cost[node.e] = now.w + node.w;
					pq.add(new Node(node.e, cost[node.e]));
				}
			}
		}
		return cost[e];
	}
}