import java.util.*;

class Solution {
    public String solution(int n) {
        // 자연수만 존재 1~ n
        // 모든 수를 표현할 때 1, 2, 4 만 사용
        String answer = "";
        Stack<String> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        while(n > 0){
            int result = n % 3;
            if(result == 0){
                stack.push("4");
                n = n/3 - 1;
            }else{
                stack.push(String.valueOf(result));
                n = n/3;
            }
        }
        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }
        return sb.toString();
    }
    
}