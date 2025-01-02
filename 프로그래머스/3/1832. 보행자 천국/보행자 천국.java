
// 일부 교차로에서 좌회전이나 우회전이 금지되어있다.
// 오른쪽 또는 아래로 한칸씩 이동가능하다.
// 상태가 0이면 상관없음 1이면 진행 불가, 2이면 회전 불가

// 0이면 직진으로 도달한 경우, 1이면 좌회전 우회전으로 도달한 경우
// 그렇게 풀면 해당 칸에 자동차가 어떤 상태로 도달했는지 알 수가 없다.
// 그래서 내려오고있엇는지? 왼쪽에서 오고있엇는지 여부로 나누자
// 이전의 값에서 좌회전이나 우회전이 불가능한 경우
class Solution {
    int MOD = 20170805;
    public int solution(int m, int n, int[][] cityMap) {
        int answer = 0;
        
        int[][][] dp = new int[m][n][2]; 
        
        
        dp[0][0][0] = 1;
        dp[0][0][1] = 1;
        
        for(int i = 1; i < n; i++) {
            if (cityMap[0][i] == 1) {
                break;
            } else {
                dp[0][i][0] = (dp[0][i][0] + dp[0][i - 1][0]) % MOD;
            }
        }
        for(int i = 1; i < m; i++) {
            if (cityMap[i][0] == 1) {
                break;
            } else {
                dp[i][0][1] = (dp[i][0][1] + dp[i - 1][0][1]) % MOD;
            }
        }
        
        for(int i = 1; i < m; i++) {
            for(int j = 1;j < n; j ++) {
                if (cityMap[i][j] != 1) {
                    // 진행 가능하면서
                    // 위에서 오고있으면서 전진경우
                    dp[i][j][1] = (dp[i][j][1] + dp[i - 1][j][1]) % MOD;
                    // 왼쪽에서 오고있으면서 전진경우
                    dp[i][j][0] = (dp[i][j][0] + dp[i][j - 1][0]) % MOD;
                    if (cityMap[i - 1][j] != 2) {
                        // 왼쪽에서 전진했엇다가 이번에 꺾은경우
                        dp[i][j][1] = (dp[i][j][1] + dp[i - 1][j][0]) % MOD;    
                    }
                    if (cityMap[i][j - 1] != 2) {
                        // 위에서 전진했엇다가 오른쪽으로 꺾은경우
                        dp[i][j][0] = (dp[i][j][0] + dp[i][j - 1][1]) % MOD;
                    }
                }
                
            }
        }
        
        return (dp[m - 1][n - 1][0] + dp[m - 1][n - 1][1]) % MOD;
    }
}