import java.util.*;
class Solution {
    public int solution(int[] a) {
        int answer = 0;
        // 두 풍선 중 더작은 풍선을 터트려서 없애는짓은 한번만 가능
        // 그밖에는 더 큰 풍선만 터트려서 없앨 수 있음
        // 최후의 남을 수 있는것을 select
        // 결국 자기왼쪽 혹은 오른쪽 숫자가 자기보다 둘다 크면 -> 가능
        // 한쪽만 크면 -> 가능 왜냐하면 나머지한쪽은 더 작은 풍선을 없애는 짓으로 하면 됨
        // 둘다 자신보다 작으면 불가능 -> 자신보다 작은 풍선은 한번만 없앨 수 있음
        // 결국 특정 인덱스의 숫자가 살아남으려면, 한번도 자기보다 작은 풍선 터트리기 수를 안쓴 상태에서, 양옆에 살아남은 숫자가 자신보다 둘다 작으면 안됨
        int[] L = new int[a.length];
        int[] R = new int[a.length];
        
        L[0] = a[0];
        R[a.length-1] = a[a.length-1];
        
        for(int i=1;i<a.length;i++) {
            L[i] = Math.min(L[i-1], a[i]);
        }
        for(int i = a.length - 2;i>-1;i--) {
            R[i] = Math.min(R[i+1], a[i]);
        }
        
        for(int i = 0;i<a.length;i++) {
            if ((i - 1 >= 0 && L[i-1] < a[i]) && (i + 1 < a.length && R[i+1] < a[i])) {
                continue;
            }    
            answer++;
        }
            
        return answer;
    }
}