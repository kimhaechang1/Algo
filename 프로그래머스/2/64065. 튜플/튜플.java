// start 20:41
// end 21:15
import java.util.*;

class Solution {
    public int[] solution(String s) {
        HashSet<String> set = new HashSet<>();
        // 길이순으로 정렬하고 처음 나오는 원소가 나온다면 s[idx++] = 원소
        s = s.substring(2, s.length()-2);
        String [] sets = s.split("\\}\\,\\{");
        Arrays.sort(sets, (a,b)->{
            return a.length() - b.length();
        });
        int[] answer = new int[sets.length];
        int idx = 0;
        boolean [] isUsed = new boolean[1000001];
        for(int i = 0;i<sets.length;i++){
            String [] frags = sets[i].split(",");
            for(int j = 0;j<frags.length;j++){
                int number = Integer.parseInt(frags[j]);
                if(isUsed[number]){
                    continue;
                }
                isUsed[number] = true;
                answer[idx++] = number;
            }
        }
        
        return answer;
    }
}