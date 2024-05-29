import java.util.*;

class Solution {
    static int n;
    public int solution(int[] cookie) {
        // 두 사람한테 줄 과자를 산다.
        // l~m, m+1 ~ r
        // 연속적인 데이터들의 합이 기준점을 기준으로 좌우가 같아야한다.
        int answer = 0;
        n = cookie.length;
        int [] S = new int[n+1];
        for(int i=0;i<n;i++){
            S[i+1] = S[i] + cookie[i]; // 누적합
        }
        // System.out.println(Arrays.toString(S));
        for(int i = 1;i<S.length-1;i++){
            // 기준점 잡고 투포인터
            int gi = i;
            int s = 0;
            int e = S.length - 1;
            // 	[0, 1, 2, 4, 7]
            //      s  g  r 
            while(s < gi && e > gi){
                int left = S[gi] - S[s];
                int right = S[e] - S[gi];
                // System.out.println("g: "+gi+" s: "+s+" e: "+e);
                // System.out.println(left+" : "+right);
                if(left == right){
                    answer = Math.max(answer, right);
                    s++;
                }else if(left > right){
                    s++;
                }else{
                    e--;
                }
            }
        }
        
        return answer;
    }
}