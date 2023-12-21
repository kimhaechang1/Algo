import java.util.*;
import java.io.*;

public class Main{
	static StringTokenizer stk;
	static int [] w;
	static int [] v;
	static int N;
	static int K;
	public static void main(String [] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		stk = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(stk.nextToken());
		K = Integer.parseInt(stk.nextToken());
		w = new int[N+1];
		v = new int[N+1];
		// 배낭 문제
		// 현재 제한된 무게보다 더 크다면? 이전의 최적의 해를 그대로 들고간다.
		// 현재 제한된 무게보다 작은 물품을 넣으려 한다면? 배낭에서 하나를 뺀 상황에서 지금 선택한 물품의 무게가 딱 들어갈 수 있는 최적의 해에
		// 현재 넣으려 하는 물품의 가치를 더한 것과 이전까지의 최적의 해를 비교한다.
        // 세로줄이 물건들, 가로줄이 현재까지의 무게제한
        // 해당 칸 : 무게제한에 따른 물건들이 들어있고 그것의 최적
		for(int i=1;i<=N;i++) {
			stk = new StringTokenizer(bf.readLine());
			w[i] = Integer.parseInt(stk.nextToken());
			v[i] = Integer.parseInt(stk.nextToken());
		}  
		int [][] dp = new int[N+1][K+1];
		for(int i= 1;i<=N;i++) {
			for(int j= 1;j<=K;j++) {
				if(w[i] > j) {
					dp[i][j] = dp[i-1][j];
				}else {
					dp[i][j] = Math.max(dp[i-1][j-w[i]]+v[i], dp[i-1][j]);
				}
			}
		}
		System.out.println(dp[N][K]);
	}
}