import java.util.*;
import java.io.*;

public class Main{
	static int n;
	static int t;
	static int [] w;
	static int [] v;
	static StringTokenizer stk;
	public static void main(String [] args) throws Exception{
		BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
		stk = new StringTokenizer(bf.readLine());
		n = Integer.parseInt(stk.nextToken());
		t = Integer.parseInt(stk.nextToken());
		w = new int[n+1];
		v = new int[n+1];
		
		// 한단원에서는 한번만 사용
		// 그 단원에 모든 내용을 알아야함  -> 몇 페이지만 공부하는건 안됨
		// 결국 단원을 공부 하냐 안하냐 -> subset 문제, 단원 종류가 100? 이건 냅섹으로 접근
		for(int i = 1;i<n+1;i++) {
			stk = new StringTokenizer(bf.readLine());
			int weight = Integer.parseInt(stk.nextToken());
			int value = Integer.parseInt(stk.nextToken());
			w[i] = weight;
			v[i] = value;
		}
		
		int [][] dp = new int[n+1][t+1];
		for(int i = 1;i<n+1;i++) {
			for(int j = 1;j<t+1;j++) {
				// 현재 제한된 시간 j와 제한된 단원 i에서 얻을 수 있는 최대 점수
				// 똑같은 제한시간을 가지고 이전 단계에서 최대점수를 들고옴
				dp[i][j] = dp[i-1][j];
				if(w[i] <= j) {
					// 이번 선택한 단원을 넣을 수 있는 시간에서
					// 해당 단원을 넣기전 현재 제한된 시간으로의 최대 이익과 비교
					dp[i][j] = Math.max(dp[i][j], dp[i-1][j-w[i]]+v[i]);
				}
			}
		}
		
		System.out.print(dp[n][t]);
		
	}
}