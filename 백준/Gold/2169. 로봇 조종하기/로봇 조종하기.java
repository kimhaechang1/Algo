import java.util.*;
import java.io.*;

public class Main{
	static int N;
	static int M;
	static int [][] map;
	static int [][] dp;
	static StringTokenizer stk;
	static int [] dy = {0,0,1};
	static int [] dx = {-1,1,0};
	static boolean [][] v;
	public static void main(String [] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		stk = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		map = new int[N][M];
		for(int i = 0;i<N;i++) {
			stk = new StringTokenizer(bf.readLine());
			for(int j= 0;j<M;j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		dp = new int[N][M];
		dp[0][0] = map[0][0];
		// 갈 수 있는 모든 가짓수를 원하는것이 아닌 값들중 최대를 원하므로 3차원 할필요도 없다.
		if(N == 1 && M == 1) {
			System.out.println(map[0][0]);
		}else {
			// 맨 윗줄은 왼쪽에서만 올 수 있다.
			for(int i= 1;i<M;i++) {
				dp[0][i] = map[0][i] + dp[0][i-1];
			}
			// 1번째 인덱스 층부터 내려가면서
			// 각 층에 칸에 대하여 왼쪽 또는 위 그리고 오른쪽 또는 위에서 오는경우로 나누어 생각한다.
			for(int i = 1;i<N;i++) {
				int [] left = new int[M];
				int [] right = new int[M];
				// 왼쪽 첫번째는 항상 위에서만 내려올 수 있다.
				left[0] = dp[i-1][0]+map[i][0];
				for(int j = 1;j<M;j++) {
					left[j] = Math.max(left[j-1], dp[i-1][j]) + map[i][j]; 
				}
				// 오른쪽에서 오는경우
				// 오른쪽 끝은 항상 위에서 내려온다.
				right[M-1] = dp[i-1][M-1] + map[i][M-1];
				for(int j = M-2;j>-1;j--) {
					right[j] = Math.max(right[j+1], dp[i-1][j]) + map[i][j]; 
				}
				
				// 왼 위 와 오 위 중에 더 큰값으로 해당층에 테이블을 채운다.
				for(int j = 0;j<M;j++) {
					dp[i][j] = Math.max(left[j], right[j]); 
				}
			}
			System.out.println(dp[N-1][M-1]);
		}
		
	}
	
}