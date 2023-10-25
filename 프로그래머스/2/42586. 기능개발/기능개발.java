import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        // 먼저 들어온 작업에 대하여 먼저 나가야 한다.
        // 같은 날짜에 배포되는 개수를 구한다.
        
        Queue<int []> queue = new ArrayDeque<>();
        for(int i = 0;i<progresses.length;i++){
            queue.add(new int[]{progresses[i], speeds[i]});
        }
        Queue<int []> temp = new ArrayDeque<>();
        ArrayList<Integer> list = new ArrayList<>();
        while(true){
            int size = queue.size();
            for(int i = 0;i<size;i++){
                int [] now = queue.poll();
                int np = now[0] + now[1];
                temp.add(new int[]{np, now[1]});
            }
            boolean isBefo = false;
            if(!temp.isEmpty() && temp.peek()[0] >= 100){
                int c = 0;
                while(!temp.isEmpty() && temp.peek()[0] >= 100){
                    temp.poll();
                    c++;
                }    
                list.add(c);
            }
            if(temp.isEmpty()){
                break;
            }
            while(!temp.isEmpty()){
                queue.add(temp.poll());
            }
        }
        int [] answer = new int[list.size()];
        for(int i = 0;i<list.size();i++){
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}