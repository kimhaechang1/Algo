import java.util.*;
import java.io.*;

public class Main{
	static StringTokenizer stk;
	static int [] dp;
	static int [] arr;
	static int N;
	static int t;
	public static void main(String [] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		StringBuilder sb = new StringBuilder();
		while(T-- > 0) {
			N = Integer.parseInt(bf.readLine());
			stk = new StringTokenizer(bf.readLine());
			arr = new int[N];
			for(int i = 0;i<N;i++) {
				arr[i] = Integer.parseInt(stk.nextToken());
			}
			t = Integer.parseInt(bf.readLine());
			dp = new int[t+1];
			dp[0] = 1;
			// 아무런 동전을 안쓰면 0원도 가짓수로 포함할 수 있다.
			for(int i = 0;i<N;i++) {
				for(int j = arr[i];j<=t;j++) {
					dp[j] += (dp[j-arr[i]]);
				}
			}
			sb.append(dp[t]).append("\n");
		}
		System.out.print(sb);
	}
}