import java.util.*;
import java.io.*;

public class Main{
	static StringTokenizer stk;
	static int[] w;
	static int [] v;
	public static void main(String [] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(bf.readLine());
		w = new int[n+1];
		v = new int[n+1];
		stk = new StringTokenizer(bf.readLine());
		for(int i = 1;i<n+1;i++) {
			w[i] = Integer.parseInt(stk.nextToken());
		}
		stk = new StringTokenizer(bf.readLine());
		for(int i =1;i<n+1;i++) {
			v[i] = Integer.parseInt(stk.nextToken());
		}
		
		int [][] dp = new int[n+1][101];
		
		for(int i =1;i<=n;i++) {
			for(int j = 1;j<100;j++) {
				// 남은 체력이 있어야 하므로... 99까지 돌려야한다.
				// 이전 사람까지의 현재 제한된 체력일때의 최적의 해를 들고온다.
				dp[i][j] = dp[i-1][j];
				if(j >= w[i]) {
					// 현재 선택한 사람을 넣을 수 있다면, 넣었을때의 해와 비교한다.
					dp[i][j] = Math.max(dp[i][j], dp[i-1][j-w[i]]+v[i]);
				}
			}
		}
		System.out.print(dp[n][99]);
		 
	}
}