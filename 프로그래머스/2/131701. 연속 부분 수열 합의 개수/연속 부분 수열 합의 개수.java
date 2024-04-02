// start 21:18
// end 
import java.util.*;

class Solution {
    public int solution(int[] elements) {
        HashSet<Integer> set = new HashSet<>();
        int cnt = 1;
        while(cnt <= elements.length){
            for(int i = 0;i<elements.length;i++){
                int idx = i;
                int c = 0;
                int sum = 0;
                while(c < cnt){
                    sum += elements[idx];
                    idx = (idx + 1) % elements.length;
                    c++;
                }
                set.add(sum);
            }   
            cnt++;
        }
        int answer = set.size();
        
        return answer;
    }
}