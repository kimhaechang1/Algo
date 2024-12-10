import java.util.*;

class Solution {
    static int[] dp1;
    static int[] dp2;
    static int n;
    public int solution(int[] money) {
        int answer = 0;
        n = money.length;
        dp1 = new int[n]; // i번째 까지 조건에 부합하는 최대값
        dp2 = new int[n];
        dp1[0] = money[0];
        dp1[1] = money[0]; // 두번째집은 무조건 못텀
        // 첫 집을 턴 경우
        
        dp2[0] = 0;
        dp2[1] = money[1];
        // 첫 집을 털지않은 경우
        for(int i = 2;i < n ;i++) {
            dp1[i] = Math.max(dp1[i - 2] + money[i], dp1[i - 1]);
            dp2[i] = Math.max(dp2[i - 2] + money[i], dp2[i - 1]);
        }
        // 첫번째 집을 턴 경우, 무조건 dp[n - 2] 가 정답이 된다, 왜냐하면 실제 인덱스는 짝수로 돌기 때문
        // 두번째 집을 턴 경우는 dp[n - 1] 이 정답이 된다, 왜냐하면 실제 인덱스는 홀수로 돌기 때문

        return Math.max(dp1[n - 2], dp2[n - 1]);
    }  
}