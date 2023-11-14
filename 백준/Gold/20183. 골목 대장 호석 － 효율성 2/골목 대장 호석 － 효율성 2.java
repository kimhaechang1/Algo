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
	static int max = 987654321;
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
		cost[A] = 0;
		PriorityQueue<long []>pq = new PriorityQueue<>((a, b)->{
			if(a[1] == b[1]) {
				return Long.compare(a[1],b[1]);
			}
			return Long.compare(a[2], b[2]);
		});
		pq.add(new long [] {A, cost[A], Integer.MIN_VALUE});
		long min = Long.MAX_VALUE;
		while(!pq.isEmpty()) {
			long [] now = pq.poll();
			if(v[(int)now[0]]) continue;
			v[(int)now[0]] = true;
			cost[(int)now[0]] = now[1];
			if((int)now[0] == B) {
				min = Math.min(min,now[2]);
				break;
			}
			for(Node node : g[(int)now[0]]) {
				if(!v[node.e] && cost[node.e] > node.w + now[1]) {
					if(node.w + now[1] > C) continue;
					cost[node.e] = node.w + now[1];
					pq.add(new long[] {node.e , cost[node.e], Math.max(now[2], node.w)});
				}
			}
		}
		if(cost[B] == Long.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(min);
		}
		
	}
}