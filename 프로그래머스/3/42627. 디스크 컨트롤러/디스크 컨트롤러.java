import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        // 사건의 흐름대로 다시 풀어보자
        
        int answer = 0;
        Arrays.sort(jobs, (o1, o2)->{
           return o1[0] - o2[0]; 
        });
        PriorityQueue<int []> pq = new PriorityQueue<>((o1, o2)->{
            return o1[1] - o2[1];
        });
        int idx = 0;
        int pres = 0;
        int cnt = 0;
        int sum = 0;
        while(cnt != jobs.length){
            // 현재 기록된 작업 끝나는 시간 도중에 들어온 요청들을 대기큐에 넣음 
            while(idx!= jobs.length && jobs[idx][0] <= pres){
                pq.add(jobs[idx++]);
            }
            
            if(pq.isEmpty()){
                // pq가 비어있다는 뜻은 현재 마지막으로 작업끝난 시간보다
                // 더 뒤에 작업 요청이 들어오는 작업이 있다는 의미
                pres = jobs[idx][0];
            }else{
                // 비어있지 않다는것은 도중에 들어온것들에 대하여
                // 수행시간이 적은것 부터 처리
                int [] now = pq.poll();
                sum += pres - now[0] + now[1];
                pres += now[1];
                cnt++;
            }
            
            
        }
        answer = sum/ jobs.length;
        return answer;
    }
}