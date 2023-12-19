import java.util.*;
import java.io.*;

public class Main{
	static int n;
	static int d;
	static int c;
	static ArrayList<Node> [] g;
	static class Node{
		int cost;
		int e;
		public Node(int e, int cost) {
			this.e = e;
			this.cost = cost;
		}
	}
	static StringTokenizer stk;
	public static void main(String [] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		// 자기자신으로 들어오는 간선들 중 비용이 싼 곳을 선택하여 누적
		// 숙주를 기준으로 나아갈 수 있는 정점을 나아감
		
		int T = Integer.parseInt(bf.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t =1;t<=T;t++) {
			stk = new StringTokenizer(bf.readLine());
			n = Integer.parseInt(stk.nextToken());
			d = Integer.parseInt(stk.nextToken());
			c = Integer.parseInt(stk.nextToken());
			g = new ArrayList[n+1];
			for(int i = 1;i<n+1;i++) {
				g[i] = new ArrayList<>();
			}
			
			for(int i = 0;i<d;i++) {
				stk = new StringTokenizer(bf.readLine());
				int a = Integer.parseInt(stk.nextToken());
				int b = Integer.parseInt(stk.nextToken());
				int cost = Integer.parseInt(stk.nextToken());
				g[b].add(new Node(a, cost));
			}
			int [] cost = new int[n+1];
			Arrays.fill(cost, Integer.MAX_VALUE);
			cost[c] = 0;
			PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2)->{
				return o1[1] -o2[1]; 
			});
			pq.add(new int[] {c, cost[c]});
			boolean [] v = new boolean[n+1];
			int cnt = 0;
			while(!pq.isEmpty()) {
				int [] now = pq.poll();
				if(v[now[0]]) continue;
				v[now[0]] = true;
				if(++cnt == n) break;
				for(Node node : g[now[0]]) {
					if(!v[node.e] && cost[node.e] > node.cost + now[1]) {
						cost[node.e] = node.cost + now[1];
						pq.add(new int[] {node.e, cost[node.e]});
					}
				}
			}
			int max = 0;
			int c = 0;
			for(int i = 1;i<cost.length;i++) {
				if(cost[i] == Integer.MAX_VALUE) {
					continue;
				}
				max = Math.max(cost[i], max);
				c++;
			}
			sb.append(c).append(" ").append(max).append("\n");
		}
		System.out.print(sb);
	}
}