// start 19:23
// end
import java.util.*;

class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        String answer = "";
        boolean isCan = true;
        
        // 한카드에서 여러번 뽑을 수 있다.
        int idx1 = 0;
        int idx2 = 0;
        int next = 0;
        while(next < goal.length && (idx1 < cards1.length || idx2 < cards2.length)){
            while(idx1 < cards1.length && next < goal.length &&cards1[idx1].equals(goal[next])){
                next++;idx1++;
                
            } 
            int prev = next;
            while(idx2 < cards2.length && next < goal.length &&cards2[idx2].equals(goal[next])){
                next++;idx2++;   
            } 
            if(prev == next){
                break;
            }
        }
        System.out.println("idx1 : "+idx1+" idx2: "+idx2);
        if(next == goal.length){
            answer = "Yes";
        }else{
            answer = "No";
        }
        
        return answer;
        
    }
}