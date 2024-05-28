import java.util.*;

class Solution {
    static class Data{
        int left;
        int right;
        int hap;
        int idx;
        public Data(int left, int right, int idx){
            this.left = left;
            this.right = right;
            this.hap = this.left + this.right;
            this.idx = idx;
        }
    }
    public int solution(int[][] scores) {
        int answer = 0;
        Data [] datas = new Data[scores.length];
        int find = scores[0][1] + scores[0][0];
        for(int i= 0;i<scores.length;i++){
            datas[i] = new Data(scores[i][0], scores[i][1], i);
        }
        // 결국 두 점수 보다 모두 낮아버리면 답도없음
        // 그렇지만 한쪽이 반대쪽보다 높은게 있으면 낮은것도 있어야함을 의미함
        // 즉, 인센티브 대상은 한쪽은 자기보다 높으면서 한쪾은 자기보다 낮아야함
        Arrays.sort(datas, (a, b)->{
           if(a.right == b.right){
               return a.left - b.left; // left에 오름차순
           } 
            return b.right - a.right; // right에 대해서 내림차순
        });
        // 내림차순 기준으로 순회했을때 left가 max 값보다 작다면, 그건 인센티브 대상에서 제외한다.
        // 왜냐하면 right가 이미 내림차순인데 left가 갱신되는 max보다 작다면 이것또한 문제이므로
        int maxLeft = datas[0].left;
        for(int i = 1;i<datas.length;i++){
            if(datas[i].left < maxLeft){
                if(datas[i].idx == 0){
                    return -1;
                }
                datas[i].hap = -1; // 등수계산에서 제외시키기
            }else{
                maxLeft = Math.max(maxLeft, datas[i].left);
            }
        }
        Arrays.sort(datas, (a, b)->{
           return b.hap - a.hap; 
        });
        answer = 1;
        for(int i = 0;i<datas.length;i++){
            if(datas[i].hap > find){
                answer++;
            }else{
                break;
            }
        }
        
        return answer;
    }
}