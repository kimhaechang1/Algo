// start 15:04
// end
import java.util.*;

class Solution {
    public int solution(int k, int m, int[] score) {
        int answer = 0;
        // k ~ 1점
        // 상자당 m개씩 담아서 포장함
        // 상자에 담긴 사과중 가장 낮은 점수가 p인 경우 p * m이 가격임
        // 사과 상자는 나누어떨어지지 않으면 버린다.
        // 이익이 나지않는경우는 하나의 상자조차 포장할 수 없는 경우이다.
        // 최대한 많이 포장하는것이 이득이다.
        Arrays.sort(score);
        if(score.length < m){
            return 0;
        }else{
            int n = score.length-1;
            while(n > -1){
                n -= m;
                if(n < -1){
                    return answer;
                }else{
                    answer += (m * score[n+1]);
                }
                
            }
        }
        return answer;
    }
}