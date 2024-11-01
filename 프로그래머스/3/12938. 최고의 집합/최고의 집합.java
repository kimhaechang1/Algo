class Solution {
    public int[] solution(int n, int s) {
        // 원소의 합이 S가 되는 수의 집합
        // 위 조건으 ㄹ만족하면서 가 원소의 곱이 최대가 되는 집합
        // 애초에 못만든다면? -1 드랍
        // 수학 그리디 일듯
        // 그냥 대충 생각했을 때? 가운데 값 두개 곱한개 항상 제일 크지않나?
        // 못만드는 경우라면 타겟 숫자보다 n이 크면 못만드는게 아닐까 싶음
        if (n > s) return new int[]{-1};
        int[] answer = new int[n];
        int idx = 0; 
        int mock = s / n;
        for(int i = 0;i<n - (s % n);i++){
            answer[i] = (s / n);
        }
        for(int i = n - (s % n);i < n;i++) {
            answer[i] = (s / n) + 1;
        }
        // s = 11 이고 n = 4 인경우를 생각해보면
        // 2 3 3 3
        // s = 8 이고 n = 4 인 경우면 
        // 2 2 2 2
        return answer;
    }
}