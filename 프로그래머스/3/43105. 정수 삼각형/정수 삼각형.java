// start 19:27
// end
import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        int n = triangle.length;
        int [][] dp = new int[n][n];
        dp[0][0] = triangle[0][0];
        for(int i = 1;i<n;i++){
            dp[i][0] = dp[i-1][0] + triangle[i][0];
            dp[i][triangle[i].length-1] = dp[i-1][triangle[i-1].length-1] + triangle[i][triangle[i].length-1];
            for(int j = 1;j<triangle[i].length-1;j++){
                
                dp[i][j] += Math.max(dp[i-1][j-1], dp[i-1][j]) + triangle[i][j];
                
            }
        }
        Arrays.sort(dp[n-1]);
        int answer = dp[n-1][triangle[n-1].length-1];
        return answer;
    }
}