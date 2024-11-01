import java.util.*;
class Solution {
    static boolean[] v;
    static int[] result;
    static int min;
    public int solution(int n, int[] weak, int[] dist) {
        int answer = 0;
        int[] ws = new int[weak.length * 2 - 1];
        for(int i = 0;i<ws.length;i++) {
            if (i > weak.length - 1) {
                ws[i] = weak[i % weak.length] + n;
            } else {
                ws[i] = weak[i];
            }
        }
        min = 987654321;
        v = new boolean[dist.length];
        result = new int[dist.length];
        for(int i = 0;i<weak.length;i++) {
            dfs(i, i + weak.length - 1, 0, ws, dist, n);
        }
        return min == 987654321 ? -1 : min;
    }
    static void dfs(int s, int e, int depth, int[] w, int[] d, int n) {
        if (depth == d.length) {
            go(s, e, w, d, n);
            return;
        }
        for(int i = 0;i<d.length;i++){
            if (v[i]) continue;
            v[i] = true;
            result[depth] = d[i];
            dfs(s, e, depth + 1, w, d, n);
            v[i] = false;
        }
    }
    static void go(int s, int e, int[] w, int[] d, int n) {
        int wIdx = s;
        int dIdx = 0;
        while(wIdx <= e && dIdx < d.length) {
            int end = w[wIdx] + result[dIdx];
            while(wIdx <= e && w[wIdx] <= end) {
                wIdx++;
            }
            dIdx++;
        }
        if (wIdx > e) {
            min = Math.min(min, dIdx);
        }
    }
}