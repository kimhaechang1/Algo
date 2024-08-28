import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = 0;
        // 반복되지 않는 경우 (단, 하나만 ) 1은 생략함
        int len = 1;
        int min = s.length();
        while(len < s.length()) {
            min = Math.min(go(new String(s), len), min);
            len++;
        }
        return min;
    }
    static int go(String copy, int len) {
        int prev = 0;
        String sub = copy.substring(prev, prev + len);
        StringBuilder sb = new StringBuilder();
        int cnt = 1;
        prev += len;
        for(int i = prev;i<copy.length();i+=len) {
            String other = copy.substring(i, Math.min(i + len, copy.length()));
            // 인덱스 out of bound 예외 문제처리 Math.min(upper_bound, 원본 길이);
            
            if(other.length() < len) {
                sb.append(other);
                // 기준길이 미달이면 그냥 붙인다.
                continue;
            }
            if(sub.equals(other)) {
                cnt++;
                // 이전에 짜른 부분과 같으면 카운팅
            } else {
                sb.append(cnt == 1 ? "" : cnt).append(sub);
                // 한개짜리면 개수는 생략한다
                cnt = 1;
                sub = new String(other);
                // 이전에 짜른 부분 교환
            }
        }
        if(cnt > 0) {
            sb.append(cnt == 1 ? "" : cnt).append(sub);
            // 마지막 짜른게 남아있다면 넣어주기
        }
        return sb.length();
    }
}