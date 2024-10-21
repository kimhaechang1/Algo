import java.util.*;

class Solution {
    public int solution(int sticker[]) {
        int answer = 0;
        int len = sticker.length;
        // 특정 수를 뜯냐 안뜯냐 -> 부분집합 문제
        // 그런데 N크니까 dp를 해야함
        // 특정 idx를 뜯을려면 -> idx - 2번째 값에서 더할 수 있음
        // 안뜯는다면 이전까지의 최적값을 들고온다. idx-1
        // 그런데, 앞 뒤가 연결되어있기에 0번째를 선택한 경우와 1번째를 선택한 경우로 나눈다.
        if(len == 1) return sticker[0];
        if(len == 2) return Math.max(sticker[0], sticker[1]);
        
        int[] dp = new int[len-1]; // 0번 뜯으면서 시작 ~ 마지막 인덱스 - 1
        int[] dp2 = new int[len]; // 1번 뜯으면서 시작 ~ 마지막 인덱스
        
        dp[0] = sticker[0];
        dp[1] = sticker[0]; // 무조건 0번을 뜯엇기에 1번 인덱스까지의 최대값은 0번을 뜯은 경우이다.
        
        dp2[0] = 0;
        dp2[1] = sticker[1];
        
        for(int i = 2;i<len-1;i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + sticker[i]);
        }
        for(int i = 2;i<len;i++) {
            dp2[i] = Math.max(dp2[i-1], dp2[i-2] + sticker[i]);
        }
        return Math.max(dp[dp.length-1], dp2[dp2.length-1]);
    }
}