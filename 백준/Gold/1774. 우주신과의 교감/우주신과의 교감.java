import java.util.*;
import java.io.*;

public class Main{
	static int [] p;
	static int N;
	static int M;
	static int [][] map;
	static void makeSet() {
		p = new int[N+1];
		for(int i= 1;i<N+1;i++) {
			p[i] = i;
		}
	}
	static int find(int a) {
		if(a == p[a]) return a;
		return p[a] = find(p[a]);
	}
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot == bRoot) return false;
		p[bRoot] = aRoot;
		return true;
	}
	static class Edge{
		int s;
		int e;
		double w;
		public Edge(int s, int e, double w) {
			this.s = s;
			this.e = e;
			this.w = w;
		}
	}
	static ArrayList<Edge> edgeList;
	static StringTokenizer stk;
	public static void main(String [] args) throws Exception{
		// 간선하나를 연결시켜놓고 MST를 구성한다.
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		stk = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		edgeList = new ArrayList<>();
		map = new int[N+1][2];
		for(int i = 1;i<N+1;i++) {
			stk = new StringTokenizer(bf.readLine());
			int s= Integer.parseInt(stk.nextToken());
			int e= Integer.parseInt(stk.nextToken());
			map[i][0] =s;
			map[i][1] =e;
		}
		
		makeSet();
		
		double res = 0;
		for(int i = 0;i<M;i++) {
			stk = new StringTokenizer(bf.readLine());
			int s = Integer.parseInt(stk.nextToken());
			int e = Integer.parseInt(stk.nextToken());
			double w = Math.sqrt(Math.pow(map[s][0]-map[e][0], 2) + Math.pow(map[s][1]-map[e][1], 2));
			union(s, e);
		}
		int cnt = M;
		
		for(int i = 1;i<N+1;i++) {
			for(int j=1;j<N+1;j++) {
				if(i == j) continue;
				double w = Math.sqrt(Math.pow(map[i][0]-map[j][0], 2) + Math.pow(map[i][1]-map[j][1], 2));
				edgeList.add(new Edge(i, j, w));
			}
		}
		Collections.sort(edgeList, (o1, o2)->{
			return Double.compare(o1.w, o2.w);
		});
		/*
		 * 3.00 + 2.00 + 2.00
		 * */
		for(Edge edge : edgeList) {
			if(union(edge.s, edge.e)) {
				res += edge.w;
				if(++cnt == N) break;
			}
		}
		System.out.printf("%.2f", res);
		
	}
}