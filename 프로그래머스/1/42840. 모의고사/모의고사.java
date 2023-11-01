import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int [] A = {1,2,3,4,5};
        int [] B = {2,1,2,3,2,4,2,5};
        int [] C = {3,3,1,1,2,2,4,4,5,5};
        int max = -1;
        // A 에 대해서 돌리기
        int n = answers.length;
        int c = 0;
        for(int i = 0;i<n;i++){
            if(A[i % 5] == answers[i]) c++;
        }
        pq.add(1);
        max = c;
        c = 0;
        // B 에 대해서 돌리기
        for(int i = 0;i<n;i++){
            if(B[i % 8] == answers[i]) c++;
        }
        if(max < c){
            pq.clear();
            pq.add(2);
            max = c;
        }else if(max == c){
            pq.add(2);
        }
        // C 에 대해서 돌리기
        c = 0;
        for(int i = 0;i<n;i++){
            if(C[i % 10] == answers[i]) c++;
        }
        if(max < c){
            pq.clear();
            pq.add(3);
            max = c;
        }else if(max == c){
            pq.add(3);
        }
        int [] answer = new int[pq.size()];
        int idx = 0;
        while(!pq.isEmpty()){
            answer[idx++] = pq.poll();
        }
        System.out.println(Arrays.toString(answer));
        return answer;
    }
}