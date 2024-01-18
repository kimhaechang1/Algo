import java.util.*;
import java.io.*;

public class Main{
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
	static int n;
	static int p;
	static int k;
	public static void main(String [] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		stk = new StringTokenizer(bf.readLine());
		n = Integer.parseInt(stk.nextToken());
		p = Integer.parseInt(stk.nextToken());
		k = Integer.parseInt(stk.nextToken());
		g = new ArrayList[n+1];
		for(int i = 1;i<n+1;i++) g[i] = new ArrayList<>();
		for(int i = 0;i<p;i++) {
			stk = new StringTokenizer(bf.readLine());
			int s= Integer.parseInt(stk.nextToken());
			int e= Integer.parseInt(stk.nextToken());
			int v = Integer.parseInt(stk.nextToken());
			g[s].add(new Node(e, v));
			g[e].add(new Node(s, v));
		}
		// K 개의 선에서는 공짜로 연결해준다.
		// 
		long s = 0;
		long e = Integer.MAX_VALUE;
		long value = Integer.MAX_VALUE;
		while(s <= e) {
			long mid = (s + e) / 2;
			if(mid == 4) {
				
			}
			if(test(mid)) {
				e = mid -1;
				value = Math.min(mid, value);
			}else {
				s = mid+1;
			}
		}
		System.out.println(value == Integer.MAX_VALUE ? -1 : value);
	}
	static boolean test(long mid){
		//System.out.println("mid : "+mid);
		PriorityQueue<long []> pq = new PriorityQueue<>((a ,b)->{
			return Long.compare(a[2],b[2]);
		});
		long [] cost = new long[n+1];
		Arrays.fill(cost, Long.MAX_VALUE);
		cost[1] = 0;
		pq.add(new long[] {1, cost[1], 0});
		boolean [] v = new boolean[n+1];
		int cnt = 0;
		while(!pq.isEmpty()) {
			long [] now = pq.poll();
//			if(mid == 4) {
//				System.out.println(Arrays.toString(now));
//			}
			if((int)now[0] == n) {
				return true;
			}
			if(v[(int)now[0]]) continue;
			v[(int)now[0]] = true;
			if(++cnt == n+1) break;
			for(Node node : g[(int)now[0]]) {
				if(v[node.e]) continue;
				if(node.w > mid && k >= now[2] + 1) {
					pq.add(new long [] {node.e, now[1], now[2]+1});
				}else if(node.w <= mid && k >= now[2]) {
					pq.add(new long[] {node.e, now[1]+node.w, now[2]});
				}
			}
		}
		return false;
	}
	
}