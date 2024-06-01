import java.util.*;

class Solution {
    static int n;
    static final int MAX_LENGTH = 10_000_001;
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        int [] cnt = new int[MAX_LENGTH];
        for(int t: tangerine){
            cnt[t]++;
        }
        Arrays.sort(cnt);
        int sum = 0;
        for(int i= cnt.length-1;i>-1;i--){
            if(cnt[i] == 0) continue;
            if(sum >= k){
                break;
            }
            sum += cnt[i];
            answer++;
        }
        
        
        return answer;
    }
}