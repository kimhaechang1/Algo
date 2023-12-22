import java.util.*;
import java.io.*;

public class Solution{
	static int n;
	static int l;
	static int [] w;
	static int [] v;
	static StringTokenizer stk;
	public static void main(String [] args) throws Exception{
		BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		StringBuilder sb= new StringBuilder();
		for(int t=1;t<=T;t++) {
			stk = new StringTokenizer(bf.readLine());
			n = Integer.parseInt(stk.nextToken());
			l = Integer.parseInt(stk.nextToken());
			// 재료를 넣냐 마냐 -> 부분집합
			w = new int[n+1];
			v = new int[n+1];
			for(int i = 1;i<n+1;i++) {
				stk = new StringTokenizer(bf.readLine());
				v[i] = Integer.parseInt(stk.nextToken());
				w[i] = Integer.parseInt(stk.nextToken());
			}
			long [][] dp = new long[n+1][l+1];
			
			for(int i = 1;i<n+1;i++) {
				for(int j = 1;j<l+1;j++) {
					dp[i][j] = dp[i-1][j];
					if(w[i] <= j) {
						dp[i][j] = Math.max(dp[i][j],dp[i-1][j-w[i]]+v[i]);
					}
				}
			}
			sb.append("#").append(t).append(" ").append(dp[n][l]).append("\n");
		}
		System.out.print(sb);
	}
}