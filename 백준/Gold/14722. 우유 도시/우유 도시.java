import java.util.*;
import java.io.*;


public class Main{
	static int n;
	static int [][] map;
	static StringTokenizer stk;
	static int [][] dp;
	public static void main(String [] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(bf.readLine());
		map = new int[n][n];
		for(int i = 0;i<n;i++) {
			stk = new StringTokenizer(bf.readLine());
			for(int j = 0;j<n;j++) {
				map[i][j] = Integer.parseInt(stk.nextToken());
			}
		}
		// 0 -> 1 -> 2 -> 0
		// 오른쪽 아니면 아래쪽으로만 움직임
		int [][] type = new int[n][n];
		for(int i = 0;i<n;i++) {
			Arrays.fill(type[i],-1);
		}
		dp = new int[n][n];
		if(map[0][0] == 0) {
			dp[0][0] = 1;
			type[0][0] = 0;
		}
		for(int i = 1;i<n;i++) {
			if(type[0][i-1] == -1 && map[0][i] == 0) {
				dp[0][i] = 1;
				type[0][i] = 0; 
			}else {
				// 가로줄 젤 윗쪽은 오른쪽으로만 도달할 수있음
				if(type[0][i-1] == 0 && map[0][i] == 1) {
					dp[0][i] = dp[0][i-1]+1;
					type[0][i] = map[0][i];
				}else if(type[0][i-1] == 1 && map[0][i] == 2) {
					dp[0][i] = dp[0][i-1]+1;
					type[0][i] = map[0][i];
				}else if(type[0][i-1] == 2 && map[0][i] == 0) {
					dp[0][i] = dp[0][i-1]+1;
					type[0][i] = map[0][i];
				}else {
					dp[0][i] = dp[0][i-1];
					type[0][i] = type[0][i-1];
				}
			}
		}
		for(int i = 1;i<n;i++) {
			// 세로줄 젤 윗쪽은 위에서만 도달할 수있음
			if(type[i-1][0] == -1 && map[i][0] == 0) {
				dp[i][0] = 1;
				type[i][0] = 0;
			}else {
				if(type[i-1][0] == 0 && map[i][0] == 1) {
					dp[i][0] = dp[i-1][0]+1;
					type[i][0] = map[i][0];
				}else if(type[i-1][0] == 1 && map[i][0] == 2) {
					dp[i][0] = dp[i-1][0]+1;
					type[i][0] = map[i][0];
				}else if(type[i-1][0] == 2 && map[i][0] == 0) {
					dp[i][0] = dp[i-1][0]+1;
					type[i][0] = map[i][0];
				}else {
					dp[i][0] = dp[i-1][0];
					type[i][0] = type[i-1][0];
				}
			}
			
		}
		
		for(int i = 1;i<n;i++) {
			for(int j = 1;j<n;j++) {
				if(type[i-1][j] == -1) {
					if(type[i-1][j] == -1 && map[i][j] == 0) {
						dp[i][j] = 1;
						type[i][j] = 0;
					}
				}else {
					if(dp[i][j] < dp[i-1][j]) {
						dp[i][j] = dp[i-1][j];
						type[i][j] = type[i-1][j];
					}
				}
				if(type[i][j-1] == -1) {
					if(type[i][j-1] == -1 && map[i][j] == 0) {
						dp[i][j] = 1;
						type[i][j] = 0;
					}
				}else {
					if(dp[i][j] < dp[i][j-1]) {
						dp[i][j] = dp[i][j-1];
						type[i][j] = type[i][j-1];
					}
				}
				if(type[i][j-1] == 0 && map[i][j] == 1 && dp[i][j-1]+1 > dp[i][j]) {
					dp[i][j] = dp[i][j-1]+1;
					type[i][j] = map[i][j];
				}else if(type[i][j-1] == 1 && map[i][j] == 2 && dp[i][j-1]+1 > dp[i][j]) {
					dp[i][j] = dp[i][j-1]+1;
					type[i][j] = map[i][j];
				}else if(type[i][j-1] == 2 && map[i][j] == 0 && dp[i][j-1]+1 > dp[i][j]) {
					dp[i][j] = dp[i][j-1]+1;
					type[i][j] = map[i][j];
				}
				if(type[i-1][j] == 0 && map[i][j] == 1 && dp[i-1][j]+1 > dp[i][j]) {
					dp[i][j] = dp[i-1][j]+1;
					type[i][j] = map[i][j];
				}else if(type[i-1][j] == 1 && map[i][j] == 2 && dp[i-1][j]+1 > dp[i][j]) {
					dp[i][j] = dp[i-1][j]+1;
					type[i][j] = map[i][j];
				}else if(type[i-1][j] == 2 && map[i][j] == 0 && dp[i-1][j]+1 > dp[i][j]) {
					dp[i][j] = dp[i-1][j]+1;
					type[i][j] = map[i][j];
				}
			}
		}
		/*for(int i = 0;i<n;i++) {
			System.out.println(Arrays.toString(dp[i]));
		}
		for(int i = 0;i<n;i++) {
			System.out.println(Arrays.toString(type[i]));
		}*/
		
		
		System.out.println(dp[n-1][n-1]);
	}
}