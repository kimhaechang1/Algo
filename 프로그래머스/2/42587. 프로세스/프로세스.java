import java.util.*;
import java.io.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        /*
        ['A','B','C','D']
        [2, 1, 3 , 2] => 중요도
        1. A 꺼내고
        2. 큐 안에 A의 우선순위보다 높은 C가 있기에 다시A를 큐에 넣는다.
        [ B C D A ]
        3. B 꺼내고
        4. 큐 안에 B보다 우선순위가 높은 C가 있기에 다시B를 큐에 넣는다.
        [C D A B]
        5. C 꺼내고 큐안에 C보다 우선순위가 높은건 없은건 없기에 C 실행
        [D A B]
        */
        boolean [] v = new boolean[priorities.length];
        Queue<int[]> q = new ArrayDeque<>();
        int max = -1;
        int [] desc = new int[priorities.length];
        for(int i = 0;i<priorities.length;i++){
            q.add(new int[]{i, priorities[i]});  
            desc[i]= priorities[i];
        }
        Arrays.sort(desc);
        System.out.println("max : "+desc[desc.length-1]);
        int idx = 1;
        for(int i= priorities.length-1;i>-1;i--){
            while(true){
                int [] peek = q.peek();
                if(!v[peek[0]] && desc[i] == q.peek()[1]){
                    break;    
                }
                q.add(q.poll());
            }
            if(location ==q.peek()[0]){
                break;
            }else{
                int [] process = q.poll();
                v[process[0]] = true;
                idx++;
            }
            
        }
        return idx;
    }
}