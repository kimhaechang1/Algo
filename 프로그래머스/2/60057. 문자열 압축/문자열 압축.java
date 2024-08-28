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
        // System.out.println("sub: "+sub);
        for(int i = prev;i<copy.length();i+=len) {
            String other = copy.substring(i, Math.min(i + len, copy.length()));
            
            if(other.length() < len) {
                sb.append(other);
                continue;
            }
            if(sub.equals(other)) {
                cnt++;
            } else {
                sb.append(cnt == 1 ? "" : cnt).append(sub);
                cnt = 1;
                sub = new String(other);
                /*if(other.equals("c")) {
                    System.out.println("start c: "+i);
                }*/
                
            }
        }
        if(cnt > 0) {
            sb.append(cnt == 1 ? "" : cnt).append(sub);
        }
        // System.out.println(sb);
        return sb.length();
    }
}