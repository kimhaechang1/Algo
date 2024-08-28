import java.util.*;

class Solution {
    static int[] idxs = {0, 1, 2};
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
        // System.out.println("총 광물배열 길이: "+minerals.length);
        map = new HashMap<>();
        map.put("diamond", 0);
        map.put("iron", 1);
        map.put("stone", 2);
        dfs(0, minerals, picks, 0, 0);
        return min == Integer.MAX_VALUE ? 0 : min;
    }
    static void dfs(int depth, String[] minerals, int[] p, int mIdx, int sum) {
        // 백트래킹으로 변경
        if(depth == total || mIdx == minerals.length) {
            min = Math.min(sum, min);
            return;
        }
        
        if(p[0] > 0) {
            // 다이아 사용하기
            p[0]--;
            depth++;
            int i = 0;
            int s = 0;
            for(i = mIdx;i< mIdx + 5;i++) {
                if(i == minerals.length) break;
                s += need[0][map.get(minerals[i])];
            }
            dfs(depth, minerals, p, i, sum + s);
            depth--;
            p[0]++;
        }
        
        if(p[1] > 0) {
            // 철 사용하기
            p[1]--;
            depth++;
            int i= 0;
            int s = 0;
            for(i = mIdx;i< mIdx + 5;i++) {
                if(i == minerals.length) break;
                s += need[1][map.get(minerals[i])];
            }
            dfs(depth, minerals, p, i, sum + s);
            depth--;
            p[1]++;
        }
        
        if(p[2] > 0) {
            // 돌 사용하기
            p[2]--;
            depth++;
            int s = 0;
            int i = 0;
            for(i = mIdx;i< mIdx + 5;i++) {
                if(i == minerals.length) break;
                s += need[2][map.get(minerals[i])];
            }
            dfs(depth, minerals, p, i, sum + s);
            depth--;
            p[2]++;
        }
    }
}