import java.io.*;
import java.util.*;

public class Main{

    static StringTokenizer stk;
    static int n;
    static int[][] dp;
    static final int MOD = 10_007;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        dp = new int[10][n+1];
        for(int i= 0;i<dp.length;i++){
            dp[i][1] = 1;
        }
        for(int i = 2;i<n+1;i++) {
            for(int j = 0;j<10;j++) {
                for(int k = 0; k<=j;k++) {
                    if(i-1 == 1 && k == 0) continue;
                    dp[j][i] = (dp[j][i] + dp[k][i-1]) % MOD;
                }
            }
        }
        int sum = 0;
        for(int i = 0;i<10;i++) {
            for(int j = 1;j<n+1;j++) {
                sum = (sum + dp[i][j]) % MOD;
            }

        }
        System.out.print(sum);

    }
}