import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = 0;
        int cnt = s.length();
        char [] bs = s.toCharArray();
        Queue<Character> queue = new ArrayDeque<>();
        for(char b: bs){
            queue.add(b);
        }
        while(cnt-- > 0){
            queue.add(queue.poll());
            char [] brackets = make(queue);
            if(check(brackets)){
                answer++;
            }
            for(int i= 0;i<brackets.length;i++){
                queue.add(brackets[i]);
            }
            
        }
        return answer;
    }
    public boolean check(char [] brackets){
        Stack<Character> stack = new Stack<>();
        for(char bracket: brackets){
            if(stack.isEmpty()){
                if(!isOpen(bracket)) return false;
                stack.push(bracket);
            }else{
                if(!isOpen(bracket)){
                    // 닫는 괄호일 경우
                    char peek = stack.peek();
                    if(peek == '(' && bracket == ')'){
                        stack.pop();
                    }else if(peek == '{' && bracket == '}'){
                        stack.pop();
                    }else if(peek == '[' && bracket == ']'){
                        stack.pop();
                    }else{
                        return false;
                    }
                }else{
                    // 여는 괄호일 경우
                    stack.push(bracket);
                }
            }
        }
        return stack.isEmpty();
    }
    public boolean isOpen(char bracket){
        return bracket=='(' || bracket == '{' || bracket == '[';
    }
    public char [] make(Queue<Character> queue){
        char [] chs = new char[queue.size()];
        for(int i = 0;i<chs.length;i++){
            chs[i] = queue.poll();
        }
        return chs;
    }
}