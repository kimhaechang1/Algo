import java.util.*;
class Solution {
    public String solution(String number, int k) {
        // 각 자리수에서 뒤로 만들수 있는 숫자가 남아있다면 교체할 수 있음
        int start = 0;
        StringBuilder sb = new StringBuilder();
        int max = 0;
        for(int i = 0;i<number.length() - k;i++){
            // 각 자리수가 정해지기 위해서는
            // 최소한 뒤에 후보숫자 개수들이 맞춰져야 한다.
            // 즉, start에서부터 n.length - 지금 선택한 개수 만큼이 남겨놔야 정답 후보가 될 수 있다.
            max = 0;
            for(int j = start;j<=k+i;j++){
                if(max < number.charAt(j)-'0'){
                    max = number.charAt(j)-'0';
                    start = j+1;
                }
            }
            sb.append(max);
            
        }
        return sb.toString();
    }
}