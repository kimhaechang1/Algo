import java.util.*;

class Solution {
    // 싱글, 더블, 트리플이면 1 ~ 20의 숫자에 배율을 가해짐
    // 0점이 되는데까지 걸리는 횟수가 가장 적고, 같으면 싱글 또는 불의 합이 큰 사람이 이김
    // 특정 숫자가 되기까지 걸리는 최소한의 다트합과 싱글 또는 불의 합을 메모하면 되지않을까/
    static int[][] dp;
    public int[] solution(int target) {
        int[] answer = {};
        dp = new int[2][target + 1]; // 0: 총 다트의 합, 1: 싱글 또는 불 다트의 개수
        Arrays.fill(dp[0], 987654321);
        Arrays.fill(dp[1], -987654321);
        dfs(target, 0, 0);
        
        return new int[]{dp[0][target], dp[1][target]};
    }
    static int[] dfs(int num, int total, int sbCnt) {
        if (dp[0][num] != 987654321 && dp[1][num] != -987654321) {
            return new int[]{dp[0][num], dp[1][num]};
        }
        
        if (num == 0) {
            return new int[]{0, 0};
        }
        
        if (num - 50 >= 0) {
            int[] result = dfs(num - 50, total + 1, sbCnt + 1);
            if (result[0] + 1 < dp[0][num]) {
                dp[0][num] = result[0] + 1;
                dp[1][num] = result[1] + 1;
            } else if (result[0] + 1 == dp[0][num] && dp[1][num] < result[1] + 1) {
                dp[1][num] = result[1] + 1;
            }
        }
        
        for(int i = 20;i > 0; i--) {
            for(int j = 3;j > 0;j--) {
                int t = i * j;
                if (num < t) continue;
                int[] result = dfs(num - t, total + 1, sbCnt + (j == 1 ? 1 : 0));
                if (result[0] + 1 < dp[0][num]) {
                    dp[0][num] = result[0] + 1;
                    dp[1][num] = result[1] + (j == 1 ? 1 : 0);
                } else if (result[0] + 1 == dp[0][num] && dp[1][num] < result[1] + (j == 1 ? 1 : 0)) {
                    dp[1][num] = result[1] + (j == 1 ? 1 : 0);
                }
            }
        }
        return new int[]{dp[0][num], dp[1][num]};
        
    }
}