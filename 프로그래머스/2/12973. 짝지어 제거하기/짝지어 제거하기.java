import java.util.*;

class Solution
{
    public int solution(String s)
    {
        int answer = 0;
        Stack<Character> stack = new Stack<>();
        char [] ch = s.toCharArray();        
        for(int i= 0;i<ch.length;i++){
            if(stack.isEmpty()){
                stack.push(ch[i]);
            }else{
                if(stack.peek() == ch[i]){
                    stack.pop();
                }else{
                    stack.push(ch[i]);
                }
            }
        }
        if(stack.isEmpty()){
            return 1;
        }

        return answer;
    }
}