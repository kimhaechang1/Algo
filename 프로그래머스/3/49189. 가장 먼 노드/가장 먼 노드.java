// start 21:59
// end
import java.util.*;

class Solution {
    static int [] cost;
    static class Node implements Comparable<Node>{
        int e;
        int w;
        public Node(int e, int w){
            this.e = e;
            this.w = w;
        }
        public int compareTo(Node o){
            return this.w - o.w;
        }
    }
    static ArrayList<Node> [] g;
    static int n;
    public int solution(int n, int[][] edge) {
        int answer = 0;
        int maxNode = 1;
        for(int i= 0;i<edge.length;i++){
            maxNode = Math.max(Math.max(edge[i][0], edge[i][1]), maxNode);
        }
        n = maxNode;
        g = new ArrayList[n+1];
        for(int i= 1;i<n+1;i++) g[i] = new ArrayList<>();
        for(int i= 0;i<edge.length;i++){
            int start = edge[i][0];
            int end = edge[i][1];
            g[start].add(new Node(end, 1));
            g[end].add(new Node(start, 1));
        }
        cost = new int[n+1];
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b)->{
            return a.w - b.w;
        });
        Arrays.fill(cost, 555555);
        cost[1] = 0;
        pq.add(new Node(1, cost[1]));
        int cnt = 0;
        boolean [] v = new boolean[n+1];
        while(!pq.isEmpty()){
            Node now = pq.poll();
            if(v[now.e]) continue;
            v[now.e] = true;
            if(++cnt == n+1) break;
            if(cost[now.e] < now.w) continue;
            for(Node node : g[now.e]){
                if(!v[node.e] && cost[node.e] > now.w + node.w){
                    cost[node.e] = now.w + node.w;
                    pq.add(new Node(node.e, cost[node.e]));
                }
            }
        }
        int max = -1;
        int maxIdx = -1;
        for(int i= 0;i<cost.length;i++){
            if(cost[i] == 555555) continue;
            if(max < cost[i]){
                maxIdx = i;
                max = cost[i];
            }
        }
        System.out.println(Arrays.toString(cost));
        answer = 1;
        for(int i= n;i>1;i--){
            if(i != maxIdx && max == cost[i]) answer++;
        }
        return answer;
    }
}