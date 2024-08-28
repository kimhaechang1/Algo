import java.util.*;

class Solution {
    static int[] idxs = {0, 1, 2};
    static int[] result;
    static int min;
    static HashMap<String, Integer> map;
    static int[][] need;
    static int total;
    static int[] arr;
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        // 착각했다. 이거 모든 곡괭이를 순열로 돌려야한다....
        // 특정 곡괭이 종류를 다쓸때까지가 아닌, 곡괭이 하나를 다쓸때까지다.
        
        
        min = Integer.MAX_VALUE;
        need = new int[][]{  
            {1, 1, 1},
            {5, 1, 1},
            {25,5, 1}
        };
        total= 0;
       
        for(int i= 0;i<picks.length;i++) {
            total += picks[i];
        }
        result = new int[total];
        boolean[] v = new boolean[total];
        arr = new int[total];
        int aIdx = 0;
        for(int i= 0;i<picks.length;i++) {
            int sCnt = picks[i];
            while(sCnt-- > 0) {
                arr[aIdx] = i;
                aIdx++;
            }
        }
        // System.out.println("총 광물배열 길이: "+minerals.length);
        map = new HashMap<>();
        map.put("diamond", 0);
        map.put("iron", 1);
        map.put("stone", 2);
        dfs(0, v, minerals, picks);
        return min == Integer.MAX_VALUE ? 0 : min;
    }
    static void dfs(int depth, boolean[] v, String[] minerals, int[] p) {
        if(depth == total) {
            int sum = 0;
            int midx = 0;
            for(int i = 0;i<total;i++) {
                int select = arr[i];
                int cnt = 0;
                while(cnt < 5 && midx < minerals.length) {
                    sum += need[select][map.get(minerals[midx])];
                    cnt++;
                    midx++;
                }
            }
            min = Math.min(min, sum);
            return;
        }
        
        for(int i =0;i<total;i++) {
            if (v[i]) continue;
            v[i] = true;
            result[depth] = arr[i];
            dfs(depth + 1, v, minerals, p);
            v[i] = false;
        }
    }
}