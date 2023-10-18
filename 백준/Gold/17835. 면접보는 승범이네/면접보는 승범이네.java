import java.util.*;
import java.io.*;

public class Main{
	static int N;
	static int M;
	static int K;
	static class Node{
		int e;
		long w;
		public Node(int e, long w) {
			this.e =e ;
			this.w =w;
		}
	}
	static int [] arr;
	static ArrayList<Node> [] g;
	static StringTokenizer stk;
	
	public static void main(String [] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		stk = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		K = Integer.parseInt(stk.nextToken());
		// 다익스트라 문제인데
		// 모든 도시에서 정점들까지 다익을 돌리기엔 정점이 너무많다.
		// 당연지사 플로이드 워셜도 힘듬
		// 하나의 기준점을 잡고 다익스트라를 돌리면서
		// 그런데 단방향 도로이다. 면접장으로 들어가는 도로만 있을수도 있다.
		// 즉, 역인접 리스트를 만들어서 다익스트라로 구현해야한다.
		// 왜냐하면 면접장으로 들어가는데 소모하는 비용은 즉, 면접장에서 해당 도시로 가기위한 비용으로 볼 수 있다.
		// 어떤 정점이 면접장 번호 중 하나를 선택하였을때 마다 비용을 측정하여 갱신한다.
		// 그 비용이 동일하다면 리스트에 담고, 그 비용보다 더 크다면 리스트를 초기화한다.
		g = new ArrayList[N+1];
		for(int i= 1;i<N+1;i++){
			g[i] = new ArrayList<>();
		}
		for(int i = 0;i<M;i++) {
			stk = new StringTokenizer(bf.readLine());
			int s = Integer.parseInt(stk.nextToken());
			int e= Integer.parseInt(stk.nextToken());
			int w = Integer.parseInt(stk.nextToken());
			g[e].add(new Node(s,w));
		}
		long [] datas = new long[N+1];
		stk = new StringTokenizer(bf.readLine());
		PriorityQueue<Node> pq = new PriorityQueue<>((o1,o2)->{
			return Long.compare(o1.w,o2.w);
		});
		Arrays.fill(datas, Long.MAX_VALUE);
		for(int i = 0;i<K;i++) {
			int e = Integer.parseInt(stk.nextToken());
			datas[e] = 0;
			pq.add(new Node(e, datas[e]));
		}
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			if(datas[now.e] < now.w) continue;
			for(Node node : g[now.e]) {
				if(datas[node.e] > now.w+node.w) {
					datas[node.e] = now.w+node.w;
					pq.add(new Node(node.e, datas[node.e]));
				}
			}
		}
		long max = -1;
		int res = -1;
		for(int i= 1;i<N+1;i++) {
			if(max < datas[i]) {
				max= datas[i];
				res = i;
			}
		}
		System.out.println(res);
		System.out.println(max);
		
	}
}