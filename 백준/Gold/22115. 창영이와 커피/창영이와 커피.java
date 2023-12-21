import java.util.*;
import java.io.*;

public class Main{
	static int n;
	static int k;
	static int [] arr;
	static StringTokenizer stk;
	public static void main(String [] args) throws Exception{
		BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
		stk =new StringTokenizer(bf.readLine());
		n = Integer.parseInt(stk.nextToken());
		k = Integer.parseInt(stk.nextToken());
		arr = new int[n+1]; // 카페인 함유량
		stk = new StringTokenizer(bf.readLine());
		for(int i = 1;i<n+1;i++) {
			arr[i] = Integer.parseInt(stk.nextToken());
		}
		
		// 각각의 커피를 마시냐 안마시냐로 나뉨 -> 부분집합
		Arrays.sort(arr);
		int [][] dp = new int[n+1][k+1];
		
		/*for(int i = 1 ;i<n+1;i++) {
			for(int j = 1;j<k+1;j++) {
				dp[i][j] = 101;
			}
		}*/
		for(int i = 1;i<n+1;i++) {
			dp[i][0] = 101;
		}
		for(int i = 1;i<k+1;i++) {
			dp[0][i] = 101;
		}
		
		/*for(int i = 0 ;i<n+1;i++) {
			for(int j = 0;j<k+1;j++) {
				System.out.print(dp[i][j]+" ");
			}
			System.out.println();
		}*/
		for(int i=1;i<n+1;i++) {
			for(int j = 0;j<k+1;j++) {
				dp[i][j] = dp[i-1][j];
				if(arr[i] == j) {
					dp[i][j] = 1;
				}
				if(arr[i] < j) {
					dp[i][j] = Math.min(dp[i][j], dp[i-1][j-arr[i]]+1);
				}
			}
		}
		System.out.println(dp[n][k] == 101 ? -1 : dp[n][k]);
		
	}
}