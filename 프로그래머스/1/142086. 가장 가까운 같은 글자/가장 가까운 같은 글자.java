// start 19:25
// end
import java.util.*;

class Solution {
    public int[] solution(String s) {
        int n = s.length();
        int[] answer = new int[n];
        HashMap<Character, Integer> map = new HashMap<>();
        char [] frags = s.toCharArray();
        for(int i = 1;i<n+1;i++){
            if(map.containsKey(frags[i-1])){
                int result = i - map.get(frags[i-1]);
                answer[i-1] = result;
            }else{
                answer[i-1] = -1;
            }
            map.put(frags[i-1], i);
        }
        return answer;
    }
}