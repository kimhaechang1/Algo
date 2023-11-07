import java.util.*;

class Solution {
    static int N;
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        int [] wait = new int[bridge_length];
        int out = 0;
        N = truck_weights.length;
        int sum = 0; // 다리위 트럭의 누적합
        while(out != N){
            answer++;
            // 다리위 움직임, 0번째 차량은 항상 빠져나감
            if(wait[0] != 0){
                sum -= wait[0];    
                wait[0] = 0;
                out++;
            }
            
            for(int i = 0; i<wait.length-1;i++){
                wait[i] = wait[i+1];
            }
            // 마지막 차도 땡겨지므로 항상 비게됨
            wait[wait.length-1] = 0;
            // 대기 트럭에서 넣을 수 있는지 누적합 값을 통해 검사
            if(sum+truck_weights[0] <= weight){
                // 대기트럭에서 다리를 건너는 배열로 이동
                wait[wait.length-1] = truck_weights[0];
                sum += wait[wait.length-1];
                truck_weights[0] = 0;
                // 대기트럭 라인도 땡겨짐
                for(int i = 0;i<N-1;i++){
                    truck_weights[i] = truck_weights[i+1];
                }
            }
        }
        
        return answer;
    }
}