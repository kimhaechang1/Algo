// start 20:16
// end 
import java.util.*;

class Solution {
    public int solution(int[] numbers, int k) {
        int answer = 0;
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for(int number : numbers){
            deque.addLast(number);
        }
        while(k-- > 0){
            answer = deque.peekFirst();
            int p = 2;
            while(p-- > 0){
                deque.addLast(deque.pollFirst());
            }
        }
        return answer;
    }
}