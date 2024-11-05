// 한 간선에 양쪽 끝 중 하나라도 적어도 켜져있어야 한다.
// 2 -> 1
// 3 -> 1
// 4 -> 1

// 1 -> 2, 3, 4, 5
// 5 -> 1, 6, 7, 8

// 6 -> 5
// 7 -> 5
// 8 -> 5

// 자신을 기준으로 간선이 하나만 존재하는 리프노드의 경우 언제나 답에서 제외됨
// 그러면 리프노드로 갔었던 이전 노드는 적어도 불을 켜야함
import java.util.*;

class Solution {
    static ArrayList<Integer>[] g;
    static boolean[] v;
    static boolean[] isLight;
    public int solution(int n, int[][] lighthouse) {
        int answer = 0;
        
        g = new ArrayList[n + 1];
        isLight = new boolean[n + 1];
        for(int i = 1;i<n+1;i++) {
            g[i] = new ArrayList<>();
        }
        for(int[] lighth: lighthouse) {
            int nd1 = lighth[0];
            int nd2 = lighth[1];
            g[nd1].add(nd2);
            g[nd2].add(nd1);
        }
        v = new boolean[n + 1];
        
        // 2 -> 3 -> 4 -> 1 -> 5 -> 6 -> 7
        
        dfs(1, 1);
        int cnt = 0;
        for(int i = 1;i<n + 1;i++) 
            cnt += isLight[i] ? 1 : 0;
        
        return cnt;
    }
    static void dfs(int prev, int pres) {
        
        boolean isLeaf = true;
        for(int next: g[pres]) {
            if (next == prev) continue;
            if (v[next]) continue;
            isLeaf = false;
            v[next] = true;
            dfs(pres, next);
            v[next] = false;
            if (!isLight[next]) {
                isLight[pres] = true;
            }
        }
        
        if(isLeaf) {
            isLight[prev] = true;
        }
    
    }
}