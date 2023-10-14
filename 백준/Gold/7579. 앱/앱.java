import java.util.*;
import java.io.*;

public class Main{
	static StringTokenizer stk;
	static int [] a;
	static int [] m;
	static int N;
	static int M;
	
	public static void main(String [] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		stk = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		stk = new StringTokenizer(bf.readLine());
		a = new int[N+1];
		int sum = 0;
		for(int i= 1;i<=N;i++) {
			a[i] = Integer.parseInt(stk.nextToken());
		}
		stk = new StringTokenizer(bf.readLine());
		m = new int[N+1];
		
		
		for(int i=1;i<=N;i++) {
			m[i] = Integer.parseInt(stk.nextToken());
			sum+=m[i];
		}
		// 여기서 단순히 부분집합을 돌리기엔 개수가 너무 많다.
		// 지금 끄냐 안끄냐로 나뉜다. -> 0/1 knapsack
		
		int [][] dp =new int[N+1][sum+1];
		
		// 처음에 가로가 메모리, 세로가 앱종류 기준으로 dp를 돌리니 답이잘 안나온다.
		// 구해야하는건 최소비용이지만, 종료시키는 앱에 따라 달라진다.
		// 비용의 제한을 증가시킬때 마다 포함시킬 수 있는 앱을 선택하고
		// 그거에 따라 모든 비용에 대한 dp테이블을 구성한다.
		// 그기서 값이 m이상이 처음 등장하는 j를 구한다.
		// i = 어떤 앱을 종료할지
		// j = 현재까지의 제한된 비용
		// dp[i][j] = 그 순간의 확보할 수 있는 최대 메모리
		
		// dp에서 문제가 잘 생각이 안난다면 기준점을 바꿔보도록 하자.
		for(int i= 1;i<=N;i++) {
			for(int j=1;j<=sum;j++) {
				if(m[i] > j) {
					dp[i][j] = dp[i-1][j];
				}else {
					dp[i][j] = Math.max(dp[i-1][j-m[i]]+a[i], dp[i-1][j]);
				}
			}
		}
		int res = 999999999;
		l : for(int i= 1;i<=N;i++) {
			for(int j=1;j<=sum;j++) {
				if(dp[i][j] >= M) {
					res = Math.min(res, j);
					
				}
			}
		}
		System.out.println(res);
		
		
		
		
	}
}