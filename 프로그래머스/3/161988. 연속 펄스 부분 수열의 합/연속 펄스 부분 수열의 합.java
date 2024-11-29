class Solution {
    public long solution(int[] sequence) {
        long answer = 0;
        // 어떠한 특수한 방법이 생각안남
        // 완탐해야할듯
        // 근데 개큼, dp
        // 자기 앞의 수를 포함하는 경우와 포함하지 않는 경우로 나뉘고, 자기 앞의 수까지 -1로 곱해서 더했는 경우와 +1을 곱해서 더했는 경우로 나뉨
        // 마지막에 전체 탐색으로 답을 구하는 이유는 만약 dp[i][0] = dp[i-1][0]을 할 경우 연속수열을 보장할 수 없기 때문에 중간중간에 최대합이 저장되어있으므로 찾아야함
        long[][] dp = new long[sequence.length][2];
        dp[0][0] = sequence[0];
        dp[0][1] = sequence[0] * -1;
        for(int i = 1;i<sequence.length;i++) {
            dp[i][0] = Math.max(dp[i-1][1] + sequence[i], dp[i][0]);
            dp[i][1] = Math.max(dp[i-1][0] + (sequence[i] * -1), dp[i][1]);
            dp[i][0] = Math.max(dp[i][0], sequence[i]);
            dp[i][1] = Math.max(dp[i][1], (-1 * sequence[i]));
        }
        long max = Long.MIN_VALUE;
        for(int i = 0;i<sequence.length;i++) {
            max = Math.max(dp[i][0],max);
            max = Math.max(dp[i][1],max);
        }
        return max;
    }
}