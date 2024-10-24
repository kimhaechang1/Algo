import java.util.*;

class Solution {
    public int solution(int[] stones, int k) {
        // 디딤돌마다 숫자가 적혀있고, 밟을 때 마다 1씩 줄어듬
        // 만약 자기 앞 디딤돌의 체력이 0인경우 해당 디딤돌을 밟을 수 없다.
        // 그럴때에는 해당 디딤돌을 넘어서는 디딤돌을 건너야 하는데 그러한 디딤돌중 가장 가까운 디딤돌을 밟아야한다.
        // 이분탐색으로 풀이한다.
        // 이분탐색인 이유는 젤 중요한 판단 기준이 명확하다는 것인데, 이 문제에서 판단기준은 결국 프렌즈 수에 해당되고
        // 모든 프렌즈들이 밟을 수 있는 디딤돌을 밟는다고 가정했을 때, 못밟는 디딤돌의 연속된 개수가 k개라면 현재 프렌즈수는 통과할 수 없다는 것이 명확하다
        // 결국 프렌즈 수에따라 명확하게 프렌즈 수를 줄여야한다와 늘려야한다가 갈린다.
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