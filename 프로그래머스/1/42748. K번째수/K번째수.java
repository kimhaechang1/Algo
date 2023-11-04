import java.util.*;

class Solution {
    public int[] solution(int[] arr, int[][] commands) {
        int[] answer = new int[commands.length];
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int idx = 0;
        for(int [] command : commands){
           int s = command[0]-1;
           int e = command[1]-1;
           int k = command[2]-1;
           for(int i = s;i<=e;i++){
               pq.add(arr[i]);
           }
           
           while(k-- > 0){
               pq.poll();
           }
            answer[idx++] = pq.poll();
            if(pq.size() > 0) pq.clear();
        }
        return answer;
    }
}