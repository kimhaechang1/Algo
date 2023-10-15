import java.util.*;
import java.io.*;

public class Main{
	static int N;
	static int [] arr;
	static int [][] map;
	static StringTokenizer stk;
	static class Edge{
		int s;
		int e;
		int w;
		public Edge(int s, int e, int w) {
			this.s = s;
			this.e = e;
			this.w = w;
		}
	}
	static ArrayList<Edge> edgeList ;
	static int [] p;
	static void makeSet() {
		p = new int[N+1];
		for(int i=0;i<N+1;i++) {
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
	public static void main(String [] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		arr = new int[N+1];
		edgeList = new ArrayList<>();
		int idx = 0;
		for(int i=1;i<N+1;i++) {
			arr[i] = Integer.parseInt(bf.readLine());
			edgeList.add(new Edge(i, 0, arr[i]));
			edgeList.add(new Edge(0, i, arr[i]));
		}
		
		// 모든 논밭에 대한 우물이 있다고 가정하고 해당 논밭을 우물로 바꾸었을 때 사용되는 비용을 0번줄에 추가한다.
		for(int i = 1;i<N+1;i++) {
			stk = new StringTokenizer(bf.readLine());
			for(int j = 1;j<N+1;j++) {
				if(i == j) stk.nextToken();
				else edgeList.add(new Edge(i,j, Integer.parseInt(stk.nextToken())));
			}
		}
		
		// 우물을 무조건 하나만 만든다는건 답이 아닐수도있음
		// 현재 그 정점에대하여 연결하는것이 아니라 그 정점을 우물로 만드는게 최소가 될 수 도 있음
		int res = 0;
		int cnt = 0;
		makeSet();
		Collections.sort(edgeList, (o1, o2)->{
			return o1.w - o2.w;
		});
	
		
		for(Edge edge : edgeList) {
			if(union(edge.s, edge.e)) {
				res+= edge.w;
				if(++cnt == N+1) break;
			}
		}
		System.out.println(res);
		
	}
}