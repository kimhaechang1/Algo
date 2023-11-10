import java.util.*;

public class Solution {
    public static int[] solution(int []arr){
        // 자신의 바로 앞 원소와 동일한지 검사
        int [] answer = {};
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for(int i= 0;i<arr.length;i++){
            if(deque.isEmpty() || deque.peekLast() != arr[i]) deque.addLast(arr[i]);
        }
        answer = new int[deque.size()];
        int idx = 0;
        while(!deque.isEmpty()){
            answer[idx++] = deque.pollFirst();
        }
        return answer;
    }
}