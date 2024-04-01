// start 19:37
// end
import java.util.*;
class Solution {
    public int[] solution(int k, int[] score) {
        // 출연 가수의점수 중 상위 k번째 이내이면 해당 가수의 점수를 기념
        // k일 다음부터는 k번째 점수보다 낮거나 높은가에 따라 달라짐
        // 최대 최소를 관리해야함 근데 중복 점수가 나오네?
        int[] answer = new int[score.length];
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i=0;i<score.length;i++){
            if (i >= k){
                if(pq.peek() < score[i]){
                    pq.poll();
                    pq.add(score[i]);
                }
            }else{
                pq.add(score[i]);
            }
            answer[i] = pq.peek();
        }
        return answer;
    }
}