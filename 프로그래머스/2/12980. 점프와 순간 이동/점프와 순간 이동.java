// start 21:21
// end 21:55
import java.util.*;

public class Solution {
    public int solution(int n) {
        int ans = 0;
       // 한번에 k칸을 앞으로 점프하거나, 현재 온 거리 * 2
        // 현재 온 거리 * 2 의 위치로 순간이동임
        // 순간이동시 양이 줄지않음
        // k칸 점프시 k만큼 양이 줌
        // 완전탐색 -> 시간초과 
        // 수학적으로 접근: 결국 자기가 온만큼 더하는것 << 이걸 역으로 생각하면 2로 나눠지기만 하면됨
        while(n != 1){
            if(n % 2 == 0){
                n /= 2;
            }else{
                n--;
                ans++;
            }
        }        
        return ++ans;
    }
}