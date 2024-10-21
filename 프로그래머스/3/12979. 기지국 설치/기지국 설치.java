class Solution {
    static int[] arr;
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        
        int first = stations[0];
        int prev = 1;
        if (first - w <= 1) {
            prev = (first + w);
        } else {
            int l = first - w - prev;
            if (l < (w * 2 + 1)) {
                answer++;
            } else {
                if (l % (w * 2 + 1) == 0) answer += (l / (w * 2 + 1));
                else answer += ((l / (w * 2 + 1)) + 1);
            }
            prev = stations[0] + w;
        }
        
        for(int i= 1;i<stations.length;i++) {
            int mPos = stations[i] - w;
            if (mPos <= prev) {
                prev = stations[i] + w;
            } else {
                int interval = mPos - prev - 1;
                if (interval > 0) {
                    if (interval < (w * 2 + 1)) {
                        answer++;
                    } else {
                        if (interval % (w * 2 + 1) == 0) answer += (interval / (w * 2 + 1));
                        else answer += ((interval / (w * 2 + 1)) + 1);
                    }
                }
                prev = stations[i] + w;
            }
        }
        if (prev < n) {
            int len = n - prev;
            if (len > 0) {
                if (len < (w * 2 + 1)) {
                    answer++;
                } else {
                    if (len % (w * 2 + 1) == 0) answer += (len / (w * 2 + 1));
                    else answer += ((len / (w * 2 + 1)) + 1);
                }
            }
        }
        return answer;
    }
}