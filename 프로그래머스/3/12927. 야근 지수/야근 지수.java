import java.util.*;
import java.io.*;

class Solution {
    static int len;
    public long solution(int n, int[] works) {
        long answer = 0;
        long sum = 0;
        len = works.length;
        for(int i= 0;i<len;i++){
            sum += works[i];
        }
        if(sum <= n){
            answer = 0;
        }else{
            PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
            for(int i = 0;i<len;i++){
                pq.add(works[i]);
            }
            int total = n;
            while(!pq.isEmpty() && total > 0){
                int now = pq.poll();
                total--;
                now--;
                if(now == 0) continue;
                pq.add(now);
            }
            while(!pq.isEmpty()){
                long value = pq.peek();
                answer += (long)(value) * (value);
                pq.poll();
            }
        }
        return answer;
    }
}