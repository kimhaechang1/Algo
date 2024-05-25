import java.util.*;

class Solution {
    public int solution(int[] citations) {
        // n 편 중, h번 이상 인용된 논문이 h편 이상이고, 나머지가 h번 이하 인용되었다면
        // h의 최대값이 H-Index
        // 이분탐색
        Arrays.sort(citations);
        int s = 0;
        int e = 1000;
        int answer = 1000;
        while(s < e){
            int mid = (s + e) / 2;
            if(check(mid, citations)){
                // 현재 미드값은 되니까 mid+1
                s = mid+1;
            }else{
                e = mid;
                // 해당 상한선은 안되는거였으니까
                answer = Math.min(answer, mid-1);
            }
        }
        return answer;
    }
    static boolean check(int value, int [] arr){
        int cnt = 0;
        for(int i = 0;i<arr.length;i++){
            if(value <= arr[i]){
                cnt++;
            }
        }
        if(cnt >= value && arr.length - cnt <= value){
            return true;
        }
        return false;
    }
}