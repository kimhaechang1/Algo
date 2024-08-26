import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        
        // 입국심사를 기다리는 사람수 n, 각 심사관이 한 명을 심사하는데 걸리느 ㄴ시간이 담긴 배열
        // 모든 사람이 심사를 받는데 걸리는 시간의 최솟값
        // 정답의 범위가 정해져잇고, 그기서 최소, 최대를 정해야한다면 파라메트릭 or 이분탐색
        // 최솟값을 정해놓고 search하는건 어떨까
        Arrays.sort(times);
        long s = 1;
        long e = (long)1e18; 
        // 결국 가장 오래걸리는건 가장 늦게 처리되는녀석이 많이 처리하는경우이다.
        // 나머지 뭐 10억 * 10만이 안된경우는 아마 오바해서 그런듯 중간에
        answer = e;
        
        while(s <= e) {
            long mid = (s + e) / 2;
            if(search(n, times, mid)) {
                e = mid - 1;
                answer = Math.min(mid, answer);
            } else {
                
                s = mid + 1;
            }
        }
        return answer;
    }
    static boolean search(int n, int[] times, long min) {
        long sum = 0;
        for(int i = 0;i<times.length;i++) {
            sum += (min / (long)times[i]);
        }
        return sum >= n;
    }
}