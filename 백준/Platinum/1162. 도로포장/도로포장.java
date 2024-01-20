import java.util.*;
import java.io.*;

public class Main{
	static int n;
	static int m;
	static int k;
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
	public static void main(String [] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		stk =new StringTokenizer(bf.readLine());
		n = Integer.parseInt(stk.nextToken());
		m = Integer.parseInt(stk.nextToken());
		k = Integer.parseInt(stk.nextToken());
		g = new ArrayList[n+1];
		for(int i= 1;i<n+1;i++) {
			g[i] = new ArrayList<>();
		}
		for(int i= 0;i<m;i++) {
			stk =new StringTokenizer(bf.readLine());
			int s = Integer.parseInt(stk.nextToken());
			int e= Integer.parseInt(stk.nextToken());
			int w =Integer.parseInt(stk.nextToken());
			g[s].add(new Node(e, w));
			g[e].add(new Node(s, w));
		}
		System.out.println(bfs());
	}
	static long bfs() {
		PriorityQueue<long []> pq = new PriorityQueue<>((a, b)->{
			if(a[1] == b[1]) {
				return Long.compare(a[2], b[2]);
			}
			return Long.compare(a[1], b[1]);
		});
		long [][] cost = new long[n+1][k+1];
		for(int i = 1;i<n+1;i++) {
			Arrays.fill(cost[i], Long.MAX_VALUE);
		}
		cost[1][1] = 0;
		cost[1][0] = 0;
		pq.add(new long[] {1, cost[1][0], 0});
		int cnt = 0;
		while(!pq.isEmpty()) {
			long [] now = pq.poll();
			//System.out.println(Arrays.toString(now));
			//if(now[0] == n) break;
			if(cost[(int)now[0]][(int)now[2]] != now[1]) continue;
			for(Node node : g[(int)now[0]]) {
				if(now[2]+1 <= k && cost[node.e][(int)now[2]+1] > now[1]) {
					cost[node.e][(int)now[2]+1] = now[1];
					pq.add(new long [] {node.e, cost[node.e][(int)now[2]+1], now[2]+1});
				}
				if(now[2] <= k && cost[node.e][(int)now[2]] > now[1] + node.w) {
					cost[node.e][(int)now[2]] = now[1]+ node.w;
					pq.add(new long [] {node.e, cost[node.e][(int)now[2]], now[2]});
				}
			}
		}
		/*for(int i = 1;i<n+1;i++) {
			System.out.println(Arrays.toString(cost[i]));
		}*/
		Arrays.sort(cost[n]);
		return cost[n][0];
	}
}