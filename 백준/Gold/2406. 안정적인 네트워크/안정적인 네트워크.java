import java.util.*;
import java.io.*;

public class Main{
	static int n,m;
	static ArrayList<Edge> edgeList;
	static int [] p;
	static void makeSet() {
		for(int i = 2;i<n+1;i++) {
			p[i] = i;
		}
	}
	static int find(int x) {
		if(x == p[x]) return p[x];
		return p[x] = find(p[x]);
	}
	static boolean union(int a, int b) {
		a = find(a);
		b = find(b);
		if(a == b) return false;
		p[b] = a;
		return true;
	}
	static class Edge{
		int s;
		int e;
		int w;
		public Edge(int s, int e, int w) {
			this.s = s;
			this.e = e;
			this.w = w;
		}
		public String toString() {
			return "s : "+s+" e : "+e +" w : "+w;
		}
	}
	static int [][] map;
	
	static StringTokenizer stk;
	public static void main(String [] args) throws Exception{
		BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
		stk = new StringTokenizer(bf.readLine());
		n = Integer.parseInt(stk.nextToken());
		m = Integer.parseInt(stk.nextToken());
		boolean [] v = new boolean[n+1];
		edgeList = new ArrayList<>();
		p = new int[n+1];
		makeSet();
		for(int i = 0;i<m;i++) {
			stk = new StringTokenizer(bf.readLine());
			int v1 = Integer.parseInt(stk.nextToken());
			int v2 = Integer.parseInt(stk.nextToken());
			if(union(v1, v2)) {
				
			}
		}
		map = new int[n+1][n+1];
		for(int i = 1;i<n+1;i++) {
			stk = new StringTokenizer(bf.readLine());
			for(int j = 1;j<n+1;j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		for(int i = 2;i<n+1;i++) {
			for(int j = 2;j<n+1;j++) {
				if(i == j) continue;
				edgeList.add(new Edge(i, j, map[i][j]));
				edgeList.add(new Edge(j, i, map[i][j]));
			}
		}
		
		
		Collections.sort(edgeList, (a,b)->{
			return a.w - b.w;
		});
		int cnt = 0;
		int cost = 0;
		ArrayList<Edge> connected = new ArrayList<>();
		for(Edge edge : edgeList) {
			if(union(edge.s, edge.e)) {
				connected.add(edge);
				cost += edge.w;
				cnt++;
				if(cnt == n-1) {
					break;
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for(Edge edge : connected) {
			/*if(edge.s == 1 || edge.e == 1){
				cost -= edge.w;
				cnt--;*/
			//}else {
				sb.append(edge.e).append(" ").append(edge.s).append("\n");
			//}
		}
		System.out.println(cost +" "+cnt);
		System.out.println(sb);
	}
}