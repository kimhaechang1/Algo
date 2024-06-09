import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        // 한쪽 큐에서 pop과 동시에 insert를 수행하고 여기까지가 1회
        // 어떠한 짓을 해도 두 큐의 합이 같아질 수 없으면 -1
        long sum = 0;
        long left = 0;
        long right = 0;
        Queue<Integer> lq = new ArrayDeque<>();
        Queue<Integer> rq = new ArrayDeque<>();
        for(int i = 0;i<queue1.length;i++){
            sum += queue1[i];
            left += queue1[i];
            lq.add(queue1[i]);
        }
        for(int i = 0;i<queue2.length;i++){
            sum += queue2[i];
            right += queue2[i];
            rq.add(queue2[i]);
        }
        int n = lq.size() + rq.size();
        if(sum % 2 != 0) return -1; // 총 합이 홀수면 두개로 못나눔
        long target = sum/2;
        boolean flag = false;
        // 최대 반복가능한 횟수를 지정해야 한다.
        // 왜냐하면 적절치 못한 차이 때문에 좌우 왔다갔다하는 수가 존재할 수 있기 때문
        while(answer <= 2*n){
            if(left == target && right == target){
                flag = true;
                break;
            }
            answer++;
            if(left > target){
                int temp = lq.peek();
                rq.add(lq.poll());
                left -= temp;
                right += temp;
            }else if(right > target){
                int temp = rq.peek();
                lq.add(rq.poll());
                right -= temp;
                left += temp;
            }else{
                return -1;
            }
        }
        return flag ? answer : -1;
    }
}