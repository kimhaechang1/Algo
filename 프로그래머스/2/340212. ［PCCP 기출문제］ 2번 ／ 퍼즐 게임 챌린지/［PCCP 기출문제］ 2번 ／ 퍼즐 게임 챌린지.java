import java.util.*;

class Solution {
    static int n;
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;
        n = diffs.length;
        // 숙련도의 최솟값을 정수로 return
        long s = diffs[0];
        long e = 0;
        for(int i =0;i<diffs.length;i++) {
            e = (int)Math.max(e, diffs[i]);
        }
        
        
        answer = Integer.MAX_VALUE;
        while(s <= e) {
            long mid = (s + e) / 2;
            if(check(mid, diffs, times, limit)) {
                e = mid - 1;
                answer = (int) Math.min(mid, answer);
            } else {
                s = mid + 1;
            }
        }
        return answer;
    }
    static boolean check(long value, int[] d, int[] t, long l) {
        long sum = 0;
        for(int i = 0;i<n;i++) {
            if(d[i] <= value) {
                sum += t[i];
            } else {
                sum += ((t[i-1] + t[i]) * (d[i] - value) + t[i]);
            }
        }
        return sum <= l;
    }
}