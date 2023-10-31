import java.util.*;
import java.io.*;

public class Main {
	static StringTokenizer stk;
	static ArrayList<Node> [] g;
	static class Node{
		int e;
		long w;
		public Node(int e, long w) {
			this.e = e;
			this.w = w;
		}
	}
	static int N;
	static int M;
	static int D;
	static int E;
	static int [] arr;
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		stk = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		D = Integer.parseInt(stk.nextToken()); // 거리 당 D 체력 소모
		E = Integer.parseInt(stk.nextToken()); // 높이당 E의 성취감
		
		stk = new StringTokenizer(bf.readLine());
		arr =new int[N+1];
		for(int i= 1;i<N+1;i++) {
			arr[i] = Integer.parseInt(stk.nextToken());
		}
		g = new ArrayList[N+1];
		for(int i = 1;i<=N;i++) {
			g[i] = new ArrayList<>();
		}
		for(int i =0;i<M;i++) {
			stk= new StringTokenizer(bf.readLine());
			int s = Integer.parseInt(stk.nextToken());
			int e= Integer.parseInt(stk.nextToken());
			int w = Integer.parseInt(stk.nextToken());
			g[s].add(new Node(e, w));
			g[e].add(new Node(s, w));
		}
		// 특정 높이를 기준점으로 삼아서
		// 올라가는길 내려가는길을 선택한다.
		// 양방향 그래프이므로 출발점에서 다익스트라 돌리고
		// 도착지점에서 다익스트라 돌려서(도착하는 지점에서 언덕을 본다고 하면 어짜피 올라가는길이다.) 성취괌 최소가 되는 것을 찾는다.
		long max = Long.MIN_VALUE;
		long [] up = dijkstra(1, N);
		long [] down = dijkstra(N, 1);
		
		for(int i = 1;i<=N;i++) {
			if(up[i] == Long.MAX_VALUE || down[i] == Long.MAX_VALUE) {
				continue;
			}
			long res = E*arr[i] - ((up[i]+down[i]) * D);
			if( max < res ) {
				max = res;
			}
		}
		if(max == Long.MIN_VALUE) {
			System.out.println("Impossible");
		}else {
			System.out.println(max);
		}
		
//		System.out.println(Arrays.toString(up));
//		System.out.println(Arrays.toString(down));
//		System.out.println(max);
		
		
	}
	static long [] dijkstra(int s, int target) {
		long [] cost = new long[N+1];
		Arrays.fill(cost, Long.MAX_VALUE);
		cost[s] = 0;
		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2)->{
			return Long.compare(o1.w, o2.w);
		});
		boolean [] v = new boolean[N+1];
		pq.add(new Node(s, cost[s]));
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			if(v[now.e]) continue;
			v[now.e] = true;
			if(cost[now.e] > now.w) continue;
			cost[now.e] = now.w;
			if(now.e == target) break;
			for(Node node : g[now.e]) {
				if(arr[now.e] >= arr[node.e]) continue;
				if(!v[node.e] && cost[node.e] > now.w + node.w) {
					cost[node.e] = now.w + node.w;
					pq.add(new Node(node.e, cost[node.e]));
				}
			}
		}
		
		
		return cost;
	}

}
