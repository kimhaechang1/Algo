import java.util.*;

class Solution {
    public int solution(int n, int[] cores) {
        int answer = 0;

        if (cores.length >= n) return n;

        long s = 1;
        long e = 10000 * 50000;
        long time = e;

        long jobs = 0; // 처리량 n 개 이상을 유지하는데 필요한 최소시각에 따른 총 처리량
        // 딱 n개를 처리하는데 걸리는 최소시각은 찾기 힘드니까 그것 이상을 유지하는데 걸리는 시각을 찾으면
        // n이상의 무언가 값이 나오는데 이것이 최소시각에 따른 처리량이고, 여기서부터 n번째로 내려가면 된다.
        // 이 내려가는 방법은 처리된 작업을 살리는건데, 특이한점은 특정 k번째를 처리한 코어를 찾는방법은 시각으로 나누어 떨어지는지 여부이다.
        // 따라서 나누어떨어진다면 본인 코어가 작업한 것이 된다.
        while(s <= e) {
            long mid = (s + e) / 2;
            long sum = 0;
            for(int i = 0;i<cores.length;i++) {
                sum += ((mid / cores[i]) + 1);
            }
            if (sum >= n) {
                time = Math.min(mid, time);
                // jobs = sum; jobs를 여기서 처리했다면, n 이상의 job이므로 뒤에서부터 처리해야한다.
                e = mid - 1;
            } else {
                jobs = sum; // 최소시각 직전까지의 작업 처리량이므로 여기서부터 올라가도 된다.
                s = mid + 1;
            }
        }
        long remain = n - jobs;
        for(int i = 0;i < cores.length; i++) {
            if (time % cores[i] == 0) {
                remain--;
                if (remain == 0) {
                    answer = i + 1;
                    break;
                }
            }
        }

        return answer;

    }
}