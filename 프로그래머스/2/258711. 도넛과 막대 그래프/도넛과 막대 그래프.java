import java.util.*;

class Solution {
    static HashSet<Integer> all;
    static HashMap<Integer, Integer> out; // key 노드에서 나가는 간선의 개수
    static HashMap<Integer, Integer> in;  // key 노드로 들어오는 간선의 개수
    public int[] solution(int[][] edges) {
        int[] answer = {};
        // 도넛모양은 출발정점으로 돌아오는데 간선의 개수와 정점의 개수가 모두 n개
        // 막대모양은 n개 간선 n-1개
        // 8자모양은 2개의 도넛모양을 합침 즉, 2번 방문하게되는 중점노드가 반드시 존재함
        // 그냥 정점으로 향하는것도 있음
        all = new HashSet<>();
        out = new HashMap<>();
        in = new HashMap<>();
        // 생성된 정점의 특징은 들어오는 정점이 없음, 다 나가는 간선 뿐임
        // 8 자모양의 특징은 나가는 들어오는 간선2개, 나가는 간선2개인 정점이 무조건 하나 존재함
        // 막대모양의 특징은 나가는 간선이 없는 정점이 무조건 하나 존재함
        init(edges);
        int newVertex = findNew();
        int allCount = out.getOrDefault(newVertex, 0);
        int eightCount = findEight();
        int lineCount = 0;
        for(int vertex: all) {
            if (out.getOrDefault(vertex, 0) == 0) {
                lineCount++;
            }
        }
        answer = new int[]{newVertex, allCount - (eightCount + lineCount), lineCount, eightCount};
        return answer;
    }
    static void init(int[][] edges) {
        for(int i= 0;i<edges.length;i++) {
            int s = edges[i][0];
            int e = edges[i][1];
            // in 처리
            int inCount = in.getOrDefault(e, 0);
            inCount += 1;
            in.put(e, inCount);
            // out 처리
            int outCount = out.getOrDefault(s, 0);
            out.put(s, outCount+1);
            all.add(s);
            all.add(e);
        }
    }   
    static int findNew() {
        // in이 없으면서 나가는 정점이 2개 이상인 정점
        // 왜냐하면 2개이상인 이유는 최소 도넛 + 막대 + 8자 합이 2이상이므로
        int find = 0;
        for(int vertex: all) {
            if(in.getOrDefault(vertex, 0) == 0 && out.getOrDefault(vertex, 0) >= 2) {
                find = vertex;
                break;
            }
        }
        return find;
    }
    static int findEight() {
        // 8자 그래프는 들어오는 간선 2개, 나가는 간선 2개인 그래프임
        // 근데 시작정점으로 부터 들어오는 간선 포함하면 2개 이상일 수 있음
        int cnt = 0;
        for(int vertex: all) {
            if(in.getOrDefault(vertex, 0) >= 2 && out.getOrDefault(vertex, 0) == 2){
                cnt++;
            }
        }
        return cnt;
    }
    
}