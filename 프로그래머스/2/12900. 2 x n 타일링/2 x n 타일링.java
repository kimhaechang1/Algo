import java.util.*;

class Solution {
    static final int MOD = 1_000_000_007;
    public int solution(int n) {
        int answer = 0;
        int [] dp = new int[60001];
        dp[0] = 1;
        dp[1] = 1;
        // dp[1] = 1;
        // dp[2] = 2;
        // dp[3] = 1 + 2 = 3
        // dp[4] = 5;
        for(int i = 2;i<n+1;i++){
            dp[i] = ((dp[i-2] % MOD) + (dp[i-1] % MOD)) % MOD;
        }
        return dp[n];
    }
}