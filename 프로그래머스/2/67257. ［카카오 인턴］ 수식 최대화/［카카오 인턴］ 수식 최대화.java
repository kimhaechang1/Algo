import java.util.*;

class Solution {
    static char [][] opts = {
            {'+','-','*'}, {'+','*','-'}, {'-','*','+'},
            {'-','+','*'}, {'*','-','+'}, {'*','+','-'}
    };
    public long solution(String expression) {
        long answer = 0;
        String expr = new String(expression);
        // 자료구조를 사용하여 해결
        for(char [] opt: opts){
            Queue<Long> operand = new ArrayDeque<>();
            Queue<Character> operator = new ArrayDeque<>();
            addInt(operand, expr);
            addOpt(operator, expr);
            // left, right, opt인데
            // 이게 만약 수식이 안맞다면, right -> left로 가서 right, opt를 새로뽑음
            // 수식이 맞다면 연산후 결과를 left에 놓고 -> right, opt를 새로 뽑음
            // left 하나를 미리 뽑아놓고 시작
            for(int i = 0;i<3;i++){
                long left = operand.poll();
                long right;
                char o;
                char selected = opt[i];
                int size = operator.size();
                if(size == 0) {
                    operand.add(left);
                    break;
                }
                for(int j = 0;j<size;j++){
                    o = operator.poll();
                    right = operand.poll();
                    if(o == selected){
                        left = ops(left, right, o);
                        if(j == size-1){
                            operand.add(left);
                            // 끝 연산인데 연산을 해야했다면? 연산결과를 마지막으로 넣어줘야함
                        }
                    }else{
                        operator.add(o);
                        operand.add(left);
                        if(j == size-1){
                            operand.add(right);
                            // 끝 연산인데 연산을 안했다면?
                            // 연산 결과가 항상 담기는 left, 피연산자 right, 연산자 opt를 모두 넣어야한다.
                        }
                        left = right;
                    }
                }
            }
            answer = Math.max(answer, Math.abs(operand.poll()));
        }
        return answer;
    }
    static void addInt(Queue<Long> queue, String e){
        String str = new String(e);
        for(char opt: opts[0]){
           str = str.replaceAll("\\"+opt, "\\|");
        }
        String [] nums = str.split("\\|");
        for(String num: nums){
            queue.add(Long.parseLong(num));
        }
    }
    static void addOpt(Queue<Character> queue, String e){
        for(int i =0;i<e.length();i++){
            char p = e.charAt(i);
            if(isNumber(p)){
                continue;
            }
            queue.add(p);
        }
    }
    static boolean isNumber(char ch){
        return ch-'0' >= 0 && ch-'0' <=9;
    }
    static long ops(long num1, long num2, char opt){
        switch(opt){
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            default:
                return num1 * num2;
        }
    }
}