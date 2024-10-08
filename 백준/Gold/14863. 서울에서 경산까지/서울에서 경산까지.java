import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    static StringTokenizer stk;
    static int n;
    static int k;
    static int[][] info;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        stk = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(stk.nextToken());
        k = Integer.parseInt(stk.nextToken());
        info = new int[n+1][4];
        for(int i = 1;i<n+1;i++) {
            stk = new StringTokenizer(bf.readLine());
            info[i][0] = Integer.parseInt(stk.nextToken());
            info[i][1] = Integer.parseInt(stk.nextToken());
            info[i][2] = Integer.parseInt(stk.nextToken());
            info[i][3] = Integer.parseInt(stk.nextToken());
        }

        // 특정 도시를 도보로 이동하던가, 자전거로 이동하던가로 나뉨
        int[][] dp = new int[n + 1][k + 1];
        for(int i = 1;i<n+1;i++) {
            for(int j = 0;j<= k;j++) {
                dp[i][j] = -987654321;
                if (j - info[i][0] >= 0) {
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j - info[i][0]] + info[i][1]);
                }
                if (j - info[i][2] >= 0) {
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j - info[i][2]] + info[i][3]);
                }
            }
        }
        System.out.print(dp[n][k]);
    }

}