import java.util.*;
import java.io.*;

public class Main {
	static StringTokenizer stk;
	static int [][] map;
	static long [][] dp;
	static int N;
	static long max;
	static StringBuilder sb;
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		sb = new StringBuilder();
		for(int t= 1;t<=T;t++) {
			N = Integer.parseInt(bf.readLine());
			map = new int[2][N];
			max = -1;
			for(int i = 0;i<2;i++) {
				stk = new StringTokenizer(bf.readLine());
				for(int j= 0;j<N;j++) {
					map[i][j] = Integer.parseInt(stk.nextToken());
				}
			}
			// (N <=2 )1번째 열까지도 아래위 비교만 하면 되지만
			// (N == 3)2번째 열부터는 0층의 경우 자기자신 왼쪽아래(j-1)까지의 누적합과 (j-2)까지의 누적합에서도 올 수 있으므로 
			// 점화식이 달라지게 된다.
			// 앞에서 민단 생각보다 뒤에서 땡겨져오는 점화식을 더 생각해보면 된다.
			dp = new long[2][N+1];
			dp[0][0] = map[0][0];
			dp[1][0] = map[1][0];
			if(N == 1) {
				max = Math.max(dp[0][0], dp[1][0]);
			}else if(N == 2) {
				dp[1][1] = map[1][1]+dp[0][0];
				dp[0][1] = map[0][1]+dp[1][0];
				max = Math.max(dp[1][1],dp[0][1]);
			}else {
				dp[1][1] = map[1][1]+dp[0][0];
				dp[0][1] = map[0][1]+dp[1][0];
				for(int i = 2;i<N;i++) {
					dp[0][i] = Math.max(dp[1][i-1], dp[1][i-2]) + map[0][i];
					dp[1][i] = Math.max(dp[0][i-1], dp[0][i-2]) + map[1][i];
				}
				max = Math.max(dp[0][N-1], dp[1][N-1]);
			}
			sb.append(max).append("\n");
		}
		System.out.print(sb);
	}

}
