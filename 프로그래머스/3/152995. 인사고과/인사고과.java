import java.util.*;
// 어떤 사원이 다른 임의의 사원보다 두 점수가 모두 낮은 경우가 한번이라도 있다면 인센티브를 못받음
// 두 점수의 합이 높은순으로 석차를 냄
// 그러면 둘 중 하나만 높아도 상관없다는 것이니까
// 하나를 기준으로 내림차순, 반대쪽을 오름차순으로 정렬하면

// 인센티브를 못받는 직원을 제외시키기

class Solution {
    static class Data {
        int idx;
        int s1;
        int s2;
        
        public Data(int idx, int s1, int s2) {
            this.idx = idx;
            this.s1 = s1;
            this.s2 = s2;
        }
        public String toString() {
            return "[ idx: "+idx+", s1: "+s1+", s2: "+s2+" ]";
        }
    }
    static int n;
    public int solution(int[][] scores) {

        int answer = 0;
        n = scores.length;
        Data[] drr = new Data[n];
        for(int i =0;i<n;i++) {
            drr[i] = new Data(i, scores[i][0], scores[i][1]);
        }

        
        Arrays.sort(drr, (a, b) -> {
           if (a.s1 == b.s1) {
               return a.s2 - b.s2;
           }
            return b.s1 - a.s1;
        });
        
        PriorityQueue<Data> pq = new PriorityQueue<>((a, b) -> {
            return (b.s1 + b.s2) - (a.s1 + a.s2);
        });
        int minS2 = drr[0].s2;
        pq.add(drr[0]);
        for(int i  = 1;i<drr.length;i++) {
            if (minS2 > drr[i].s2) {
                continue;
            } else {
                // Math.min으로 하면 안되는게, 하나라도 작아지면 안됨.
                minS2 = Math.max(drr[i].s2, minS2);
                pq.add(drr[i]);
            }
        }
        int rank = 0;
        int max = 211111;
        int cnt = 1;
        while(!pq.isEmpty()) {
            Data now = pq.peek();
            int sum = now.s1 + now.s2;
            if (max == sum) {
                cnt++;
            } else if (max > sum) {
                rank += cnt;
                cnt = 1;
                max = sum;
            }
            
            if (now.idx == 0) {
                return rank;
            }
            pq.poll();
            
        }
        return -1;
    }
}