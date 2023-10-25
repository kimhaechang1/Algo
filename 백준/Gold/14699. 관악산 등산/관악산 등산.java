import java.util.*;
import java.io.*;


public class Main{
	static int [] arr;
	static int N;
	static int M;
	static class Node{
		int e;
		public Node(int e) {
			this.e = e;
		}
	}
	static int [] dp;
	static ArrayList<Node> [] g;
	static StringTokenizer stk;
	static int max;
	public static void main(String [] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		stk = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		g = new ArrayList[N+1];
		arr = new int[N+1];
		for(int i=1;i<N+1;i++) {
			g[i] = new ArrayList<>();
		}
		max = -987654321;
		stk = new StringTokenizer(bf.readLine());
		for(int i=1;i<N+1;i++) {
			arr[i] = Integer.parseInt(stk.nextToken());
			if(max < arr[i]) {
				max = arr[i];
			}
		}
		for(int i= 0;i<M;i++) {
			stk = new StringTokenizer(bf.readLine());
			int s= Integer.parseInt(stk.nextToken());
			int e= Integer.parseInt(stk.nextToken());
			g[s].add(new Node(e));
			g[e].add(new Node(s));
		}
		
		StringBuilder sb= new StringBuilder();
		dp = new int[N+1];
		Arrays.fill(dp, -1);
		for(int i = 1;i<N+1;i++) {
			sb.append(dfs(i, arr[i])).append("\n");
		}
		System.out.print(sb);
		
	}
	static int dfs(int start, int h) {
		if(dp[start] != -1) {
			return dp[start];
		}
		dp[start] = 1;
		for(Node node : g[start]) {
			if(h < arr[node.e]) {
				dp[start] = Math.max(dp[start], dfs(node.e, arr[node.e])+1);
			}
		}
		return dp[start];
	}
}