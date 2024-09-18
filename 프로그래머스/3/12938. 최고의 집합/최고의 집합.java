class Solution {
    public int[] solution(int n, int s) {
        int[] answer = {};
        int mock = n / s;
        answer = new int[n];
        if (s < n) return new int[]{-1};
        if (s % n == 0) {
            for(int i = 0;i<n;i++) {
                answer[i] = s / n;
            }
        } else {
            // 반례
            // 33 33 33 35
            // 1,257,795
            // 33 33 34 34
            // 134 / 4 = 33
            // 1,258,884
            int namu = s % n;
            answer = new int[n];
            for(int i = 0;i<n- (s % n);i++) {
                answer[i] = (s / n);
            }
            for(int i = (n - (s % n)); i< n;i++) {
                answer[i] = (s / n) + 1;    
            }
            
        }
        return answer;
    }
}