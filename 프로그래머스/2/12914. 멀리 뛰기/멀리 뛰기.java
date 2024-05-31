class Solution {
    static long mod = 1234567;
    public long solution(int n) {
        long answer = 0;
        // 점진적으로 증가하고 앞에 사용된 값을 누적한다.
        long [] dp = new long[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2;i<n+1;i++){
            dp[i] = ((dp[i-2] % mod) + (dp[i-1] % mod)) % mod;
        }
        answer = dp[n];
        return answer;
    }
}