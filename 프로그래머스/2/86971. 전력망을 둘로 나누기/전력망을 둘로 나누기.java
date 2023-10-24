import java.util.*;

class Solution {
    static ArrayList<Integer> [] g;
    static boolean [] sel;
    static int N;
    static int min;
    public int solution(int n, int[][] wires) {
        int answer = -1;
        // 부분집합, 정점의 연결성 검사, 희소 그래프
        N = n;
        g = new ArrayList[N+1];
        for(int i =1;i<N+1;i++){
            g[i] = new ArrayList<>();
        }
        for(int i = 0;i<N-1;i++){
            int s = wires[i][0];
            int e = wires[i][1];
            g[s].add(e);
            g[e].add(s);
        }
        min = 987654321;
        for(int i = 0;i<N-1;i++){
            int s = wires[i][0];
            int e = wires[i][1];
            int t = bfs(s,e);
            int cha = Math.abs(t - (N-t));
            min = Math.min(min, cha);
        }
        
        answer = min;
        return answer;
    }
    
    static int bfs(int s, int e){
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(s);
        boolean [] v = new boolean[N+1];
        v[s] = true;
        int c = 1;
        while(!queue.isEmpty()){
            int now = queue.poll();
            for(int node : g[now]){
                if(!v[node] && node != s && node!=e){
                    v[node] = true;
                    c++;
                    queue.add(node);
                }
            }
        }
        return c;
    }
}