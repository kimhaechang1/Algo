import java.util.*;
import java.io.*;

public class Main{
	static int n;
	static int k;
	static int [] w;
	static int [] v;
	static StringTokenizer stk;
	public static void main(String [] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		stk = new StringTokenizer(bf.readLine());
		n = Integer.parseInt(stk.nextToken());
		k = Integer.parseInt(stk.nextToken());
		
		// 지금 해당하는 과목을 선택하냐 안하냐로 나뉨 -> 부분집합
		w = new int[k+1];
		v = new int[k+1];
		for(int i = 1;i<k+1;i++) {
			stk = new StringTokenizer(bf.readLine());
			v[i] = Integer.parseInt(stk.nextToken());
			w[i] = Integer.parseInt(stk.nextToken());
		}
		long [][] dp = new long[k+1][n+1];
		
		for(int i = 1;i<k+1;i++) {
			for(int j = 1;j<n+1;j++) {
				dp[i][j] = dp[i-1][j];
				if(w[i] <= j) {
					dp[i][j] = Math.max(dp[i][j],dp[i-1][j-w[i]]+v[i]);
				}
			}
		}
		System.out.print(dp[k][n]);
	}
}