// start 19:22
// end 21:07
import java.util.*;

class Solution {
    public String solution(String p) {
        String answer = "";
        // ( 와 ) 개수가 같으면 일단 균형잡힌 괄호 문자열
        // 하지만 짝까지 맞아야 올바른 괄호문자열임
        // 빈 문자열 -> 빈 문자열
        // 균형잡힌 문자열의 경우는 배제하여 균형잡히지 않은 문자열을 균형잡아서 균형잡혀있는 문자열에 붙임
        // 앞 뒤 문자를 제거하고 나머지 문자의 괄호방향을 뒤집음
        // 그리고 앞 뒤에 () 를 감싸서 리턴함
        // 그냥 진짜 시키는데로 했으면 풀리는 문제....
        if(check(p)){
            answer = p;
        }else{
            answer = dfs(p);
        }
        return answer;
    }
    String dfs(String brackets){
        if(brackets.length() == 0) return brackets; // 빈 문자열의 경우 빈 문자열 반환
        int open = 0;
        int close = 0;
        int stopIdx = -1;
        for(int i = 0;i<brackets.length();i++){
            if(brackets.charAt(i) == '('){
                open++;
            }else{
                close++;
            }
            if(open == close){
                stopIdx = open+close; // -> 만났던 괄호 총 개수 = 멈추는 순간임
                break;
            }
        }
        String u = brackets.substring(0, stopIdx);
        String v = brackets.substring(stopIdx, brackets.length());
        if(check(u)){
            return u + dfs(v); // u가 올바른 문자열이면 v에 대해서 재귀
        }else{
            return "(" + dfs(v) + ")"+converter(u); // u가 올바른 문자열이 아니면 
            // u는 앞뒤 짜르고 v에 대해서 수행한거 앞 뒤에 괄호를 붙여서 뒤에 u를 붙임
        }        

    }
    boolean check(String brackets){
        if(brackets.length() == 0){
            return true;
        }
        Stack<Character> stack = new Stack();
        for(int i = 0;i<brackets.length();i++){
            if(stack.isEmpty()){
                if(brackets.charAt(i) == ')'){
                    return false;
                }
                stack.push(brackets.charAt(i));
            }else{
                if(brackets.charAt(i) == '('){
                    stack.push(brackets.charAt(i));
                }else{
                    if(stack.peek() == '('){
                        stack.pop();
                    }else{
                        return false;
                    }
                }
            }
        }
        if(!stack.isEmpty()){
            return false;
        }else{
            return true;
        }
    }
    String converter(String u){
        if(u.equals("")) return u;
        char [] chs = u.toCharArray();
        StringBuilder sb= new StringBuilder();
        for(int i= 1;i<chs.length-1;i++){
            if(chs[i] == '(') {
                sb.append(')');
            }
            else sb.append('(');
        }
        return sb.toString();
    }
}