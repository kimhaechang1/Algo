import java.util.*;
import java.io.*;

public class Main{
	static StringTokenizer stk;
	static class Node{
		int e;
		int w;
		public Node(int e, int w) {
			this.e = e;
			this.w = w;
		}
	}
	static int N;
	static int M;
	static ArrayList<Node> [] g;
	public static void main(String [] args) throws Exception{
		// MST
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		while(true) {
			stk = new StringTokenizer(bf.readLine());
			N = Integer.parseInt(stk.nextToken());
			M = Integer.parseInt(stk.nextToken());
			if(N == 0 && M == 0) break;
			g = new ArrayList[N];
			for(int i = 0;i<N;i++) {
				g[i] = new ArrayList<>();
			}
			int sum = 0;
			for(int i = 0 ;i<M;i++) {
				stk = new StringTokenizer(bf.readLine());
				int s= Integer.parseInt(stk.nextToken());
				int e= Integer.parseInt(stk.nextToken());
				int w= Integer.parseInt(stk.nextToken());
				g[s].add(new Node(e,w));
				g[e].add(new Node(s,w));
				sum += w;
			}
			int [] cost = new int[N];
			int res=  0;
			boolean [] v =new boolean[N];
			int cnt = 0;
			PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2)->{
				return o1.w - o2.w;
			});
			Arrays.fill(cost, 987654321);
			cost[0] = 0;
			pq.add(new Node(0, cost[0]));
			while(!pq.isEmpty()) {
				Node now = pq.poll();
				if(v[now.e]) continue;
				v[now.e] = true;
				res += now.w;
				cost[now.e] = now.w;
				if(++cnt == N) break;
				for(Node node : g[now.e]) {
					if(!v[node.e] && cost[node.e] > node.w) {
						cost[node.e] = node.w;
						pq.add(new Node(node.e, cost[node.e]));
					}
				}
			}
			sb.append(sum-res).append("\n");
		}
		System.out.println(sb);
				
	}
}