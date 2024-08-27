import java.util.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        // 최소중에 최대 -> 파라메트릭 서치
        int s = 1;
        int e = 1_000_000_001;
        Arrays.sort(rocks);
        int ans = e;
        while(s <= e) {
            int mid = (s + e) / 2;
            if(search(distance, rocks, n, mid)) {
                s = mid + 1;
            } else {
                e = mid - 1;
                ans = Math.min(ans, e);
            }
        }
        return ans;
    }
    static boolean search(int dis, int[] r, int n, int min) {
        int prev = 0;
        ArrayList<Integer> cha = new ArrayList<>();
        for(int i= 0;i<r.length;i++){ 
            cha.add(r[i]);
        }
        cha.add(dis);
        Iterator<Integer> iter = cha.iterator();
        while(iter.hasNext()) {
            int val = iter.next();
            if(val - prev < min) {
                iter.remove();
            } else {
                prev = val;
            }
        }
        // System.out.println(cha);
        if (r.length - (cha.size() - 1) > n) {
            return false;
        }
        return true;
    }
}