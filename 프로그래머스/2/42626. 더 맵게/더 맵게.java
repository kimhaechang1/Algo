import java.util.*;


class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer>  pq = new PriorityQueue<>((o1, o2)->{
            return o1-o2;
        });
        for(int s : scoville){
            pq.add(s);
        }
        boolean canGo = true;
        int cnt = 0;
        while(!pq.isEmpty()){
            //System.out.println(pq.peek());
            if(pq.peek() >= K) break;
            int l = pq.poll();
            
            if(pq.isEmpty()){
                canGo = false;
                break;
            }
            if(pq.peek() >= K){ 
                cnt++;
                break;
            }
            int h = pq.poll();
            int n = l + ( h*2 );
            cnt++;
            pq.add(n);
        }
        if(!canGo) {
            return -1;
        }
        return cnt;
    }
}