import java.util.*;

class Solution {
    static int[] p;
    
    static void init() {
        p = new int[101];
        for(int i = 1;i<p.length;i++) {
            p[i] = i;
        }
    }
    
    static int find(int a) {
        if (a == p[a]) return a;
        return p[a] = find(p[a]);
    }
    
    static boolean union(int a, int b){
        a = find(a);
        b = find(b);
        if (a == b) return false;
        p[b] = a;
        return true;
    }
    
    static class Node implements Comparable<Node>{
        int s;
        int e;
        int cost;
        
        public Node(int s, int e, int cost) {
            this.s = s;
            this.e = e;
            this.cost = cost;
        }
        
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
    static ArrayList<Node> nodes;
    public int solution(int n, int[][] costs) {
        // 크루스칼 아님?
        int answer = 0;
        nodes = new ArrayList<>();
        for(int i = 0;i<costs.length;i++) {
            nodes.add(new Node(costs[i][0], costs[i][1], costs[i][2]));
            nodes.add(new Node(costs[i][1], costs[i][0], costs[i][2]));
        }
        init();
        int cost = 0;
        int cnt = 0;
        Collections.sort(nodes);
        for(Node node: nodes) {
            if (union(node.s, node.e)) {
                cost += node.cost;
                cnt++;
                if (cnt == n-1) break;
            }
        }
        return cost;
    }
}