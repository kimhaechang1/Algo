import java.util.*;

class Solution {
    boolean solution(String s) {
        boolean answer = true;
        char [] gaul = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for(char ch : gaul){
            if(ch == '('){  
                stack.push(ch);    
            }else{
                if(stack.isEmpty()){ 
                    return false;
                }else{
                    if(stack.peek() == '('){
                        stack.pop();
                    }else{
                        return false;
                    }
                }
            }
        }
        answer = stack.isEmpty() ? true : false;
        return answer;
    }
}