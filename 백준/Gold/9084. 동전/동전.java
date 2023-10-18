import java.util.*;
import java.io.*;

public class Main{
	static int [] coin;
	static int N;
	static StringTokenizer stk;
	public static void main(String [] args) throws Exception{
		// y축, 동전 종류, x축 금액
		// 냅섹 테이블 쭉 돌리기
		// 각 동전은 하나씩만 쓸 수 있다 -> 0/1 냅섹
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		for(int t= 1;t<=T;t++) {
			N = Integer.parseInt(bf.readLine());
			// 동전개수가 의미가 크게 가지는건 아니므로 금액 제한을 중점으로 생각한다.
			int []dp = new int[10001];
			coin = new int[N+1];
			stk = new StringTokenizer(bf.readLine());
			for(int i=1;i<=N;i++) {
				coin[i] = Integer.parseInt(stk.nextToken());
			}
			int target = Integer.parseInt(bf.readLine());
			dp[0] = 1;
			/*
			 * dp[1] = 1; 1원 한개
			 * dp[2] = 1원 2개, 2원 1개 = 2
			 * dp[3] = 1원 3개, 1원2개 2원 1개 = 2
			 * */
			for(int i= 1;i<=N;i++) {
				for(int j=coin[i];j<=10000;j++) {
					dp[j] += dp[j - coin[i]];
				}
			}
			
			System.out.println(dp[target]);
		}
		
	}
}