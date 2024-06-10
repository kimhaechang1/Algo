import java.util.*;
class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = {};
        // 열린구간, 합이 k, 길이가 짧으면서, 시작인덱스가 가장 작은
        int start = 1_000_001;
        int length = sequence.length; // id2 - id1 + 1 -> len
        int s = 0;
        int e = 0;
        int sum = sequence[s];
        while(s <= e){
            if(sum == k){
                if(e - s + 1 < length){
                    length = e - s + 1;
                    start = s;
                }else if(e - s + 1 == length && s < start){
                    start = s;
                }
                e++;
                if(e == sequence.length) break;
                sum += sequence[e];
            }else if(sum > k){
                sum -= sequence[s];
                s++;
            }else{
                e++;
                if(e == sequence.length) break;
                sum += sequence[e];
            }
        }
        answer = new int[]{start, start + (length - 1)};
        return answer;
    }
}