import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        // 백준에 오큰수 문제랑 거의비슷함
        // 스택으로 해결
        int[] answer = new int[numbers.length];
        Stack<Integer> stack = new Stack<>();
        // [3, 4, 5] -> ans: [4, 5, -1]
        // 작으면 스택에 넣고 // 같거나 크면 자기보다 큰 숫자 나올때까지 스택 뒤지고
        int p = numbers.length-1;
        stack.push(numbers[p]);
        answer[p] = -1;
        p--;
        while(p > -1){
            while(!stack.isEmpty() && stack.peek() <= numbers[p]){
                stack.pop();
            }
            if(stack.isEmpty()){
                answer[p] = -1;
            }else{
                answer[p] = stack.peek();
            }
            stack.push(numbers[p]);
            p--;
        }
        return answer;
    }
}