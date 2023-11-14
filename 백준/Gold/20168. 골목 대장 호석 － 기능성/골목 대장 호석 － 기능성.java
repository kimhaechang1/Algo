import java.util.*;
import java.io.*;

public class Main{
	static int N;
	static int M;
	static int A;
	static int B;
	static int C;
	static ArrayList<Node> [] g;
	static class Node{
		int e;
		int w;
		public Node(int e, int w) {
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
		C = Integer.parseInt(stk.nextToken());
		g = new ArrayList[N+1];
		for(int i= 1;i<N+1;i++) {
			g[i] = new ArrayList<>();
		}
		for(int i = 0;i<M;i++) {
			stk = new StringTokenizer(bf.readLine());
			int a= Integer.parseInt(stk.nextToken());
			int b= Integer.parseInt(stk.nextToken());
			int c = Integer.parseInt(stk.nextToken());
			g[a].add(new Node(b,c));
			g[b].add(new Node(a,c));
		}
		v = new boolean[N+1];
		dfs(A, B,0,0);
		System.out.println(max == 987654321 ? -1 : max);
	}
	static void dfs(int start, int end,int sum, int m) {
		if(sum > C) {
			return;
		}
		if(start == end) {
			max = Math.min(max, m);
			return;
		}
		
		for(Node node : g[start]) {
			if(!v[node.e]) {
				v[node.e] = true;
				dfs(node.e, end, sum+node.w, Math.max(m, node.w));
				v[node.e] = false;
			}
		}
	}
}