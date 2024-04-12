// start 12:02
// end 12:19
import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = new int[2];
        HashSet<String> set = new HashSet<>();
        int stopIdx = -1;
        set.add(words[0]);
        for(int i= 1;i<words.length;i++){
            if(set.contains(words[i])){
                stopIdx = i+1;
                break;
            }
            char prev = words[i-1].charAt(words[i-1].length()-1);
            char next = words[i].charAt(0);
            if(prev != next){
                stopIdx = i+1;
                break;
            }
            set.add(words[i]);
        }
        if(stopIdx != -1){
            int stage = stopIdx / n;
            int turn = stopIdx % n;
            if(turn == 0){
                turn = n;
            }else{
                stage++;
            }
            answer[0] = turn;
            answer[1] = stage;
        }
        return answer;
    }
}