import java.util.*;
import java.io.*;

public class Main{
	static int N;
	static int M;
	static int K;
	static StringTokenizer stk;
	static int [] arr;
	static int [] p;
	static class Edge{
		int s;
		int e;
		int w;
		public Edge(int s, int e, int w) {
			this.s = s;
			this.e = e;
			this.w = w;
		}
		@Override
		public String toString() {
			return "Edge [s=" + s + ", e=" + e + ", w=" + w + "]";
		}
	}
	static ArrayList<Edge> edgelist;
	static void makeSet() {
		for(int i = 1;i<N+1;i++) {
			if(p[i] == -1) continue;
			p[i] = i;
		}
	}
	static int find(int a) {
		if(p[a] == -1) {;
			return -1;
		}else if(p[a] == a) {
			return a;
		}
		return p[a] = find(p[a]);
		
	}
	static boolean union(int a, int b) {
		int aRoot= find(a);
		int bRoot = find(b);
		if(aRoot == bRoot) return false;
		if(aRoot == -1 && bRoot != -1) {
			p[bRoot] = aRoot;
		}else if(aRoot != -1 && bRoot == -1) {
			p[aRoot] = bRoot;
		}else {
			p[bRoot] = aRoot;
		}
		return true;
	}
	public static void main(String [] args)throws Exception{
		BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
		stk = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		K = Integer.parseInt(stk.nextToken());
		arr = new int[K];
		p = new int[N+1];
		stk = new StringTokenizer(bf.readLine());
		for(int i = 0 ;i<K;i++) {
			int vtx = Integer.parseInt(stk.nextToken());
			p[vtx] = -1;
		}
		makeSet();
		edgelist = new ArrayList<>();
		for(int i = 0;i<M;i++) {
			stk = new StringTokenizer(bf.readLine());
			int s = Integer.parseInt(stk.nextToken());
			int e = Integer.parseInt(stk.nextToken());
			int w = Integer.parseInt(stk.nextToken());
			edgelist.add( new Edge(s, e, w));
			edgelist.add( new Edge(e, s, w));
		}
		int res = 0;
		// 최소비용 간선들을 골랏을 때, 발전소와 연결되어있으면 된다.
		// 즉, MST를 구성했을 때, 모든 노드의 대표자가 발전소일때의 최소비용을 출력한다.
		Collections.sort(edgelist, (o1, o2)->{
			return o1.w - o2.w;
		});
		int cnt = 0;
		for(Edge edge : edgelist) {
			if(union(edge.s, edge.e)) {
				res += edge.w;
				if(p[edge.e] == -1) {
					cnt++;
					if(cnt == (N-K)) break;
				}
			}
		}
		System.out.println(res);
	}
}