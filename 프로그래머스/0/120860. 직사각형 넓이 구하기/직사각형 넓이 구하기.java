import java.util.*;
// start 19:55
// 
class Solution {
    public int solution(int[][] dots) {
        int answer  = 0;
        Arrays.sort(dots, (a, b)->{
           if(a[0] == b[0]){
               return a[1] - b[1];
           }
           return a[0] - b[0];
        });
        int width = dots[0][1] - dots[1][1];
        int height = dots[0][0] - dots[2][0];
        answer = Math.abs(width * height);
        
        return answer;
    }
}