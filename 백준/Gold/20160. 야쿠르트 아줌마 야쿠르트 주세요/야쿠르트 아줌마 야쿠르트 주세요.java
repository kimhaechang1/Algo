import java.util.*;
import java.io.*;

public class Main{
	static int v;
	static int e;
	static class Node{
		int e;
		long w;
		public Node(int e, long w) {
			this.e = e;
			this.w = w;
		}
	}
	static ArrayList<Node> [] g;
	static int start;
	static int [] arr;
	static StringTokenizer stk;
	static long min;
	public static void main(String [] args) throws Exception{
		BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
		stk =new StringTokenizer(bf.readLine());
		v = Integer.parseInt(stk.nextToken());
		e = Integer.parseInt(stk.nextToken());
		g = new ArrayList[v+1];
		for(int i = 1;i<v+1;i++) {
			g[i] = new ArrayList<>();
		}
		for(int i = 0;i<e;i++) {
			stk = new StringTokenizer(bf.readLine());
			int v1= Integer.parseInt(stk.nextToken());
			int v2= Integer.parseInt(stk.nextToken());
			int w= Integer.parseInt(stk.nextToken());
			g[v1].add(new Node(v2, w));
			g[v2].add(new Node(v1, w));
		}
		arr = new int[10];
		stk = new StringTokenizer(bf.readLine());
		for(int i = 0;i<10;i++) {
			arr[i] = Integer.parseInt(stk.nextToken());
		}
		
		start = Integer.parseInt(bf.readLine());
		min = 987654321;
		if(start == arr[0]) {
			min = start;
		}else {
			min = 987654321;
		}
		int prev = arr[0];
		int end = 0;
		long presentTime = 0;
		while(true) {
			
			end++;
			if(end >= arr.length) {
				break;
			}
			int endVertex = arr[end];
			long [] res = dijkstra(prev, endVertex);
			if(res[endVertex] == Long.MAX_VALUE) {
				continue;
			}else {
				prev = endVertex;
				presentTime += res[endVertex];
				//System.out.println(presentTime);
				long [] me = dijkstra(start, endVertex);
				if(me[endVertex] == Long.MAX_VALUE) {
					continue;
				}else if(me[endVertex]!=Long.MAX_VALUE && presentTime >= me[endVertex]) {
					min = Math.min(min, endVertex);
				}
			}
		}
		System.out.println(min == 987654321 ? -1 : min);
		
	}
	static long [] dijkstra(int s, int e) {
		//System.out.println("s : "+s +" e : "+e);
		long [] cost = new long[v+1];
		Arrays.fill(cost, Long.MAX_VALUE);
		cost[s] = 0;
		PriorityQueue<Node> pq = new PriorityQueue<>((a, b)->{
			return Long.compare(a.w, b.w);
		});
		int cnt = 0;
		pq.add(new Node(s, cost[s]));
		boolean [] visit = new boolean[v+1];
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			if(visit[now.e]) continue;
			visit[now.e] = true;
			if(now.e == e) break;
			if(++cnt == v) break;
			for(Node node : g[now.e]) {
				if(!visit[node.e] && cost[node.e] > now.w + node.w) {
					cost[node.e] = now.w + node.w;
					pq.add(new Node(node.e, cost[node.e]));
				}
			}
		}
		//System.out.println(Arrays.toString(cost));
		return cost;
	}
}