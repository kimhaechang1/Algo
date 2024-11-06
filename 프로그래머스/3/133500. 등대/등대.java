import java.util.*;

class Solution {
    // 등대와 등대 사이를 오가는 뱃길은 n개
    // 어느 등대에서 출발해도 모든 등대까지 이동가능 -> 즉 어느 노드에서 출발하던 모든 노드에 닿을 수 있음
    // 하나의 뱃길 양쪽 끝 등대 중 적어도 하나는 켜져 있도록 등대를 켜두어야 함 -> 즉 간선으로 이어진 두 노드중 하나는 불켜진 노드여야 함
    // 켜야하는 등대개수의 최솟값 -> 즉 최소한 켜두어야하는 등대들
    
    // 예를들어 1번 노드의 경우에는 2, 3, 4, 5 넷중에 하나만 켜져도 됨
    // 그렇지만 자신을 기준으로 간선이 하나만 있는 노드들은 무조건 둘 중 하나는 켜야함
    // 자신을 기준으로 간선이 하나만 있는 노드들 -> 더이상 뻗어갈 노드가 없는 노드로서 리프노드가 됨
    // 리프 노드 자체를 불켜게 되면 예제 1번같은 경우에서는 최소를 만족할 수 없음
    // 즉 리프 노드의 부모노드를 켜면 혹시나 해당 부모노드를 가진 자식노드들이 여럿 있을 수 있다.
    
    // 리프노드까지 타고 내려가는 깊이 우선 탐색을 시전하고
    // 타고 내려갔는데 리프노드면? 이전 노드의 불을 켠다. 
    // 그리고 깊이우선탐색에서 이전 노드로 되돌아 가면서 갔다온 노드쪽이 켜지지 않았으면 현재 되돌아온 노드는 반드시 켜야한다.
    
    // 그래프를 구성하고, 리프노드를 찾아간 다음, 이전노드를 키고 되돌아가면서 자기가 갔었던 노드가 안켜져있으면 되돌아온 노드를 켜야한다.
    
    static ArrayList<Integer>[] g;
    static boolean[] isLight;
    static boolean[] v;
    public int solution(int n, int[][] lighthouse) {
        int answer = 0;
        
        v = new boolean[n + 1];
        isLight = new boolean[n + 1];
        g = new ArrayList[n + 1];
        for(int i = 1;i<n+1;i++) g[i] = new ArrayList<>();
        for(int[] house: lighthouse) {
            g[house[0]].add(house[1]);
            g[house[1]].add(house[0]);
        }
        
        dfs(1, -1);
        
        for(int i = 1;i<isLight.length;i++) {
            answer += (isLight[i] ? 1 : 0);
        }
        
        return answer;
    }
    static void dfs(int node, int prev) {
        
        boolean isLeaf = true;
        
        for(int next: g[node]) {
            if (v[next]) continue;
            if (next == prev) continue;
            isLeaf = false;
            v[next] = true;
            dfs(next, node);
            v[next] = false;
            if (!isLight[next]) {
                isLight[node] = true;
            }
        }
        
        if(isLeaf) {
            isLight[prev] = true;
        }
    }
}