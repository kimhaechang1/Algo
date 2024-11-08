// 순서번호가 붙어있는 과자들 -> 정렬을 하면 순서 흐뜨러짐
// 두 아들에게 줄 과자, i ~ m번까지와 m + 1 ~ r번까지의 바구니, -> 두 아들이 어떤 연속된 구간을 나눠가짐
// 두 아들이 받을 과자수는 같아야함
// 한명의 아들에게 줄 수 있는 가장 많은 과자수
// 즉 두사람에게 나눠줄 연속된 구간의 합이 같고, 그러한 합들 중 최대값? 을 찾는 것 같음
// 
// [1, 1, 2, 3]
// [0, 1, 2, 4, 7]
//     l     m  r  
import java.util.*;

class Solution {
    static int n;
    public int solution(int[] cookie) {
        int answer = 0;
        n = cookie.length;
        int[] S = new int[n + 1];
        for(int i = 1;i<n + 1;i++) {
            S[i] = S[i - 1] + cookie[i - 1];
        }
        
        for(int r = 2;r < n + 1; r++) {
            int l = 0;
            int m = l + 1;
            while(l < m) {
                int c1 = S[m] - S[l];
                int c2 = S[r] - S[m];
                if(c1 == c2) {
                    answer = Math.max(answer, c1);
                }
                if (c1 >= c2) {
                    l++;
                } else {
                    m++;
                }
            }
        }
    
        return answer;
    }
}