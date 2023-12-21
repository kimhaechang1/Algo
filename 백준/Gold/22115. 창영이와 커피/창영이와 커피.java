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
		// 커피의 종류와 카페인을 축으로 돌면서
		// 정확하게 해당 카페인을 얻을 수 있는 상황이라면 경우의수는 무조건 1이다.
		// 그리고 해당 카페인을 얻기위하여 지금 커피로 얻는 카페인과 다른 커피의 카페인을 섞을수도있다.
		// k 가 0이 주어질수도 있음에 주의하자.
		Arrays.sort(arr);
		int [][] dp = new int[n+1][k+1];
		
	
		for(int i = 1;i<n+1;i++) {
			dp[i][0] = 101;
		}
		for(int i = 1;i<k+1;i++) {
			dp[0][i] = 101;
		}
		
		
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