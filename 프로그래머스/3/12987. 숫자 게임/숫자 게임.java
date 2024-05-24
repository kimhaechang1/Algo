import java.util.*;
import java.io.*;

class Solution {

    public int solution(int[] A, int[] B) {
        // A가 건넨 카드에서 B가 이길 수 있는 카드가 있는가? 가 중요함
        // 순서가 정해졌다고 해서 건드리면 위험하다는건 아님
        Arrays.sort(A);
        Arrays.sort(B);
        int answer = 0;
        int p1 = 0;
        int p2 = 0;
        int n = A.length;
        while(p1 < n && p2 < n){
            if(A[p1] < B[p2]){
                p1++;
                p2++;
                answer++;
            }else{
                p2++;
            }
        }
        

        return answer;
    }
}