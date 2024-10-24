import java.util.*;

class Solution {
    public int solution(int[] stones, int k) {
        // 디딤돌마다 숫자가 적혀있고, 밟을 때 마다 1씩 줄어듬
        // 만약 자기 앞 디딤돌의 체력이 0인경우 해당 디딤돌을 밟을 수 없다.
        // 그럴때에는 해당 디딤돌을 넘어서는 디딤돌을 건너야 하는데 그러한 디딤돌중 가장 가까운 디딤돌을 밟아야한다.
        // 이분탐색으로 풀이한다.
        int s = 1;
        int e = 200_000_001;
        int ans = 1;
        while(s <= e) {
            int mid = (s + e) / 2;
            if (check(mid, stones, k)) {
                s = mid + 1;
                ans = Math.max(mid, ans);
            } else {
                
                e = mid - 1;
                
            }   
        }
        return ans;
    }
    static boolean check(int fNum, int[] arr, int k) {
        int skip = 0;
        for(int i = 0;i<arr.length;i++) {
            if (arr[i] - fNum >= 0) {
                skip = 0;
            } else {
                skip++;
            }
            
            if (skip == k) return false;
        }
        return true;
    }
}