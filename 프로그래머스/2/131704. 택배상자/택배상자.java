import java.util.*;

class Solution {
    public int solution(int[] order) {
        int answer = 0;
        // 1 ~ n까지 번호가 증가하는 순서대로 컨테이너 벨트에 놓여져있음
        // 하지만 벨트에 놓인 순서대로 택배 상자를 내려 바로 트럭에 싣게 되면
        // 배달하는 순서와 택배상자에 있는 순서가 맞지않아서 안된다.
        // 그래서 순번이 아니라면 보조 컨테이너 벨트에 두는데 이 벨트는 
        // 맨 앞에 상자만 뺄수 있음
        int maxOrder = order[0];
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> bojo = new Stack<>();
        for(int i = order.length;i>0;i--){
            stack.push(i);
        }
        int p = 0;
        while(p < order.length){
            boolean find = false;
            if(!bojo.isEmpty() && bojo.peek() == order[p]){
                bojo.pop();
                find = true;
            }
            while(!find && !stack.isEmpty()){
                if(stack.peek() == order[p]){
                    find = true;
                    stack.pop();
                    break;
                }
                bojo.push(stack.pop());
            }
            
            if(!find){
                break;
            }else{
                answer++;
            }
            p++;
        }
        return answer;
    }
}