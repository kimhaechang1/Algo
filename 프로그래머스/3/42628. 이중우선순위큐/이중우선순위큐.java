import java.util.*;

class Solution {
   public int[] solution(String[] queries) {
        PriorityQueue<Integer> lower = new PriorityQueue<>((o1, o2)->{
            return o1 - o2;
        });
        PriorityQueue<Integer> higher = new PriorityQueue<>(Collections.reverseOrder());
        Queue<Integer> queue = new ArrayDeque<>();
        for(String query : queries){
            String [] q = query.split(" ");
            String order = q[0];
            char where = (q[1].toCharArray())[0];
            if(order.equals("I")){
                queue.add(Integer.parseInt(q[1]));
            }else if(order.equals("D")){
                
                if(queue.isEmpty()) continue;
                if(where == '-'){
                    while(!queue.isEmpty()){
                        lower.add(queue.poll());
                    }
                    lower.poll();
                    while(!lower.isEmpty()){
                        queue.add(lower.poll());
                    }
                }else{
                    while(!queue.isEmpty()){
                        higher.add(queue.poll());
                    }
                    
                    higher.poll();
                    
                    while(!higher.isEmpty()){
                        queue.add(higher.poll());
                    }
                }
            }
        }
        
        int[] answer = new int[2];
        if(queue.isEmpty()){
            answer[0] = 0;
            answer[1] = 0;
        }else{
            while(!queue.isEmpty()){
                int p = queue.poll();
                lower.add(p);
                higher.add(p);
            }
            answer[0] = higher.peek();
            answer[1] = lower.peek();
        }
        return answer;
    }
}