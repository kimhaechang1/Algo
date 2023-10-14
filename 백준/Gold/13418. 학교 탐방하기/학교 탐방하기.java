import java.io.*;
import java.util.*;

public class Main{
	static StringTokenizer stk;
	static ArrayList<Node> [] g1;
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
	static PriorityQueue<Node> pq;
	static int [] cost;
	static boolean [] v;
	public static void main(String [] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		stk = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		g1 = new ArrayList[N+1];
		
		for(int i = 0;i<N+1;i++) {
			g1[i] = new ArrayList<>();
		}
		
		stk = new StringTokenizer(bf.readLine());
		int s = Integer.parseInt(stk.nextToken());
		int e = Integer.parseInt(stk.nextToken());
		int w = Integer.parseInt(stk.nextToken());
		int initW = w;
		g1[s].add(new Node(e,w));
		for(int i= 0;i<M;i++) {
			stk = new StringTokenizer(bf.readLine());
			s = Integer.parseInt(stk.nextToken());
			e = Integer.parseInt(stk.nextToken());
			w = Integer.parseInt(stk.nextToken());
			g1[s].add(new Node(e,w));
			g1[e].add(new Node(s,w));
		}
		// MST를 두번 구성하면 된다.
		cost = new int[N+1];
		pq =  new PriorityQueue<>((o1, o2)->{
			return o1.w - o2.w;
		});
		int res= 0;
		int cnt = 0;
		Arrays.fill(cost, 987654321);
		cost[1] = initW;
		pq.add(new Node(1, cost[1]));
		v = new boolean[N+1];
		// 0 은 오르막길, 1은 내리막길
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			if(v[now.e]) continue;
			v[now.e] = true;
			cost[now.e] = now.w;
			if(now.w == 0) res++;
			if(++cnt == N) break;
			for(Node node : g1[now.e]) {
				if(!v[node.e] && cost[node.e] > node.w) {
					cost[node.e] = node.w;
					pq.add(new Node(node.e, cost[node.e]));
				}
			}
		}
//		System.out.println(res);
		int max = res*res;
		//System.out.println(res*res);
		pq =  new PriorityQueue<>((o1, o2)->{
			return o2.w - o1.w;
		});
		res= 0;
		cnt = 0;
		Arrays.fill(cost, -987654321);
		cost[1] = initW;
		pq.add(new Node(1, cost[1]));
		v = new boolean[N+1];
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			if(v[now.e]) continue;
			v[now.e] = true;
			cost[now.e] = now.w;
			if(now.w == 0) res++;
			if(++cnt == N) break;
			for(Node node : g1[now.e]) {
				if(!v[node.e] && cost[node.e] < node.w) {
					cost[node.e] = node.w;
					pq.add(new Node(node.e, cost[node.e]));
				}
			}
		}
//		System.out.println(res);
		int min = res*res;
		System.out.println(max - min);
	}
}