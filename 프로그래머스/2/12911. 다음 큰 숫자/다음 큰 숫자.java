import java.util.*;

class Solution {
    public int solution(int n) {
        int answer = n+1;
        // n 보다 크면서, 2진수 변환시 1의 개수와 같으면서, 가장 작은수
        String nB = Integer.toBinaryString(n);
        int cnt = 0;
        for(char ch: nB.toCharArray()){
            if(ch == '1') cnt++;
        }
        while(true){
            String current = Integer.toBinaryString(answer);
            int p = 0;
            for(char c: current.toCharArray()){
                if(c == '1') p++;
            }
            if(cnt == p) break;
            answer++;
        }
        return answer;
    }
}