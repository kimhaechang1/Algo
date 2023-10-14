import java.util.*;
import java.io.*;
public class Main {
	static int N;
	static int [][]map;
	static StringTokenizer stk;
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		map = new int[N][N];
		for(int i= 0;i<N;i++) {
			stk = new StringTokenizer(bf.readLine());
			for(int j = 0;j<N;j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		// 1은 벽
		// 초기상태는 0,1, 0,2를 차지하고 있다.
		// 가로로 누워있는 경우 오른쪽, 오른쪽-아래(대각선)을 가리킬 수 있다.
		// 세로로 누워있는 경우 아래, 오른쪽-아래를 가리킬수 있다.
		// 오른쪽-아래의 경우 다음 모양이 다 가능하다.
		// N,N까지 이동하는 경우의 수는 총 3가지 모양이 가능하다.
		// 즉 N,N까지 이동하는 3가지 경우의수의 합이 답이 될 것이다.
		// 어떻게든 오른쪽 아래로 이동할 수 밖에 없다.
		// N,N에 대각선 모양으로 도착하는 경우, 세로모양으로 도착하는경우, 가로모양으로 도착하는 경우가 있다.
		// 각 칸마다 모두 세로 파이프모양, 가로 파이프모양, 대각선 파이프모양이 도착하는 경우들을 누적한다.
		long [][][] dp = new long[N+1][N+1][3];
		// 가로모양 0, 세로모양 1, 대각선 모양 2
		//첫 모양은 가로로 주어지므로 0,1에는 가로모양 단 하나만 올 수 있다. 
		dp[0][1][0] = 1;
		dp[0][1][1] = 0;
		dp[0][1][2] = 0;
		for(int i= 0;i<N;i++) {
			for(int j = 2;j<N;j++) {
				// 칸에 가로로 오게 될 경우
				if(j-1 > -1 && map[i][j] != 1) {
					dp[i][j][0] += (dp[i][j-1][0] + dp[i][j-1][2]);
				}
				// 칸에 세로로 올 경우
				if(i-1 > -1 && map[i][j] != 1) {
					dp[i][j][1] += (dp[i-1][j][1]+dp[i-1][j][2]);
				}
				// 칸에 대각선으로 올 경우
				if(i-1> -1 && j-1> -1 && map[i-1][j] != 1 && map[i][j-1] != 1 && map[i][j] != 1) {
					dp[i][j][2] += (dp[i-1][j-1][0]+dp[i-1][j-1][1]+dp[i-1][j-1][2]);
				}
			}
		}
		
		System.out.println(dp[N-1][N-1][0]+dp[N-1][N-1][1]+dp[N-1][N-1][2]);
		
	}

}
