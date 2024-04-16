// start 09:00
// end 09:03

import java.util.*;

class Solution {
    public int solution(int n, int[] money) {
        long [] dp = new long[n+1];
        dp[0] = 1;
        for(int m: money){
            for(int i= 1;i<=n;i++){
                if(i >= m){
                    dp[i] = (dp[i] + dp[i-m]) % 1_000_000_007;    
                }
            }
        }
        int answer = (int)dp[n];
        return answer;
    }
}