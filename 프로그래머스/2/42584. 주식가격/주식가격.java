import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        Stack<Integer> stack = new Stack<>();
        int N = prices.length;
        for(int i= 0;i<N;i++){
            if(stack.isEmpty() || prices[stack.peek()] <= prices[i]){
                stack.push(i);
            }else{
                while(!stack.isEmpty() && prices[stack.peek()] > prices[i]){
                    int m = stack.pop();
                    answer[m] = i - m;
                }
                stack.push(i);
            }
        }
        while(!stack.isEmpty()){
            int t= stack.pop();
            answer[t] = N-1-t;
        }
        
        
        return answer;
    }
}