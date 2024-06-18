import java.util.*;

class Solution {
    static boolean [] v;
    static int [] cost;
    static class Node implements Comparable<Node>{
        int cost;
        int edge;
        public Node(int edge, int cost){
            this.edge = edge;
            this.cost = cost;
        }
        
        public int compareTo(Node o){
            return this.cost - o.cost;
        }
    }
    static final int INF = 987654321;
    static ArrayList<Node> [] g;
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        // 다익스트라
        g = new ArrayList[51];
        for(int i= 1;i<51;i++){
            g[i] = new ArrayList<>();
        }
        for(int i = 0;i<road.length;i++){
            int s = road[i][0];
            int e = road[i][1];
            int c = road[i][2];
            g[s].add(new Node(e, c));
            g[e].add(new Node(s, c));
        }
        dijkstra();
        for(int i= 1;i<51;i++){
            if(cost[i] > K) continue;
            answer++;
        }
        return answer;
    }
    static void dijkstra(){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        cost = new int[51];
        Arrays.fill(cost, INF);
        cost[1] = 0;
        v = new boolean[51];
        pq.add(new Node(1, cost[1]));
        while(!pq.isEmpty()){
            Node now = pq.poll();
            if(v[now.edge]) continue;
            if(cost[now.edge] < now.cost) continue;
            v[now.edge] = true;
            for(Node node: g[now.edge]){
                if(!v[node.edge] && cost[node.edge] > node.cost + now.cost){
                    cost[node.edge] = node.cost + now.cost;
                    pq.add(new Node(node.edge, cost[node.edge]));
                }
            }
        }
    }
}