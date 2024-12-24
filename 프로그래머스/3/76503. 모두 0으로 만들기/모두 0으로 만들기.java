// 트리의 모든점들의 가중치를 0으로 
// 임의의 연결된 두 점을 골라서 한쪽은 1 증가시키고 한쪽은 1감소 시키고

// 0   1   0 
// 0 - 1 - 2

// 어떤 정점이 음수 가중치 라면 0에 가까워질려면 증가해야한다.
// 양수 가중치 일려면 0에 가까워질려면 감소해야 한다.

// 위상정렬 시뮬레이션 아닌가?

// 리프노드에서는 무조건 자기와 연결된 단 하나의 노드와 씨름해야 한다.
// 리프노드에서 부터 가중치 0으로 만들어 나가다가, 마지막 루트에 닿을 때 까지도 모든 노드의 가중치 0을 만들 수 없다면
// 그땐 -1을 반환해야 할듯?

import java.util.*;

class Solution {
    static ArrayList<Integer>[] g;
    public long solution(int[] a, int[][] edges) {
        
        long answer = 0;
        long[] arr = new long[a.length];
        for(int i = 0; i < a.length; i++) arr[i] = a[i];
        int[] indegree = new int[300_001];
        g = new ArrayList[300_001];
        for(int i = 0;i < 300_001; i++) g[i] = new ArrayList<>();
        for(int i = 0;i < edges.length; i++) {
            int v1 = edges[i][0];
            int v2 = edges[i][1];
            g[v1].add(v2);
            g[v2].add(v1);
            indegree[v1]++;
            indegree[v2]++;
        }
        
        Queue<Integer> queue = new ArrayDeque<>();
        for(int i = 0; i < 300_001; i++) {
            if (indegree[i] == 1) queue.add(i);
        }
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-- > 0) {
                int node = queue.poll();
                for(int next: g[node]) {
                    if (indegree[next] == 1) continue;
                    indegree[next]--;
                    long cha = Math.abs(0 + arr[node]);
                    if (arr[node] < 0) {
                        arr[next] -= cha;
                        answer += cha;
                    } else if (arr[node] > 0) {
                        arr[next] += cha;
                        answer += cha;
                    } 
                    
                    arr[node] = 0;
                    if (indegree[next] == 1) {
                        queue.add(next);
                    }
                }
            }
        }
        // 마지막에 서로 인접 해 있는 부분 처리
        for(int i = 0;i < arr.length;i++) {
            if (arr[i] != 0) {
                boolean find = false;
                for(int node: g[i]) {
                    if (arr[i] + arr[node] == 0) {
                        answer += Math.abs(arr[node]);
                        arr[i] = 0;
                        arr[node] = 0;
                        find = true;
                        break;
                    }
                }
                if (!find) {
                    return -1;
                }
            }
        }
        return answer;
    }
}