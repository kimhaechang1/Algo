import java.util.*;

class Solution {
    static HashMap<String, Integer> result;
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = {};
        
        // 수익금의 10%는 자신의 부모노드에게 나눠주고
        // 그렇게 루트노드까지 계속 10%씩 나눠준다.
        // 1200원의 이득을 본 노드가 있다면, 1080원만 본인몫이고, 120원부터 10%씩 부모노드에게 간다.
        // 더이상 절사할 값이 없다면 그대로 갖는다.
        // 칫솔은 개당 100원임
        // 자신의 부모노드가 누구인지 담은 HashMap을 하나 만들어야 할듯
        
        result = new HashMap<>();
        HashMap<String, String> parent = new HashMap<>();
        for(int i = 0;i<enroll.length;i++) {
            if (referral[i].equals("-")) {
                parent.put(enroll[i], null);
            } else {
                parent.put(enroll[i], referral[i]);
            }
        }
        
        for(int i = 0;i<seller.length;i++) {
            int a = amount[i] * 100;
            String s = seller[i];
            while(true) {
                int gain = a < 10 ? a : (a - (int)(a * 0.1));
                result.put(s, result.getOrDefault(s, 0) + gain);
                if (a < 10 || parent.get(s) == null) break;
                a = (int)(a * 0.1);
                s = parent.get(s);
            }
        }
        
        
        answer = new int[enroll.length];
        for(int i = 0;i<enroll.length;i++) {
            answer[i] = result.getOrDefault(enroll[i], 0);
        }
        
        return answer;
    }
    
}