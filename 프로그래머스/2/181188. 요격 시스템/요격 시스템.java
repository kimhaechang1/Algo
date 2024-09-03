import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        // 미사일을 최소로 사용해서 모든 폭격 미사일을 요격할려함
        // 개구간 이라서 S, E 구간에 대하여 S혹은 E에 요격미사일 쏴도 의미가 없음
        // 이분탐색은 안될듯하고
        // 그리디하게 접근해야 할듯
        // 4 -> 5 와 4 ~ 8을 비교했을 때, 4 ~ 5 기준으로 생각해야 한다.
        Arrays.sort(targets, (a, b) ->{
            if (a[0] == b[0]) {
                return Integer.compare(a[1], b[1]);
            }
           return Integer.compare(a[0], b[0]); 
        });
        
        int s = targets[0][0];
        int e = targets[0][1];
        int cnt = 1;
        for(int i= 1;i<targets.length;i++) {
            if (e <= targets[i][0]) {
                cnt++;
                s = targets[i][0];
                e = targets[i][1];
            } else {
                // s는 더 크지만, e가 더 작은경우가 생길 수 있음, 즉 큰 직선이 작은직선을 포함하는경우임
                e = Math.min(e, targets[i][1]);
            }
        }
        
        return cnt;
    }
}