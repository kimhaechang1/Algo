// start 21:21
// end
import java.util.*;

public class Solution {
    public int solution(int n) {
        int ans = 0;
       // 한번에 k칸을 앞으로 점프하거나, 현재 온 거리 * 2
        // 현재 온 거리 * 2 의 위치로 순간이동임
        // 순간이동시 양이 줄지않음
        // k칸 점프시 k만큼 양이 줌
        // 5000 / 2 -> 2500 / 2 -> 1250 /2 -> (625-1) / 2 -> 312 / 2 ->
        // 156 /2 -> 78 -> (39 -1) -> (19-1) / 2-> (9-1) / 2 -> 4 
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