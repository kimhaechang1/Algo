import java.util.*;
import java.io.*;

public class Main {
    static ArrayList<Node> [] g;

    static class Node{
        int e;
        int w;
        public Node(int e, int w){
            this.e = e;
            this.w = w;
        }
    }
    static int n;
    static int m;
    static StringTokenizer stk;
    static ArrayList<int []> list;
    public static void main(String [] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        stk = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        g = new ArrayList[n+1];
        for(int i = 1;i<n+1;i++){
            g[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(bf.readLine());
            int s = Integer.parseInt(stk.nextToken());
            int e = Integer.parseInt(stk.nextToken());
            int w = Integer.parseInt(stk.nextToken());
            g[s].add(new Node(e, w));
            g[e].add(new Node(s, w));
        }
        list = new ArrayList<>();
        int [] cost = dijkstra(1);
        bfs(cost);
        
        StringBuilder sb = new StringBuilder();
        sb.append(list.size()).append("\n");
        for(int [] edge : list) {
        	sb.append(edge[0]).append(" ").append(edge[1]).append("\n");
        }
        System.out.print(sb);
    }
    static void bfs(int [] dcost) {
    	Queue<int []> queue = new ArrayDeque<>();
    	int [] v= new int[n+1];
    	boolean [] sel = new boolean[n+1];
    	Arrays.fill(v, Integer.MAX_VALUE);
    	v[1] = 0;
    	queue.add(new int[] {1, 0});
    	while(!queue.isEmpty()) {
    		int [] now = queue.poll();
    		int v1 = now[0];
    		for(Node node : g[v1]) {
    			if(node.e == 1) continue;
    			int minCost = dcost[node.e];
    			v[node.e] = Math.min(v[node.e], now[1] + node.w);
    			if(!sel[node.e] && v[node.e] <= minCost){
    				v[node.e] = minCost;
    				sel[node.e] = true;
    				list.add(new int[] {now[0], node.e});
    				queue.add(new int[] {node.e, v[node.e]});
    			}
    		}
    	}
    }
    static int [] dijkstra(int s){
        PriorityQueue<Node> pq = new PriorityQueue<>((a,b)->{
        	return a.w - b.w;
        });
        int [] cost = new int[n+1];
        Arrays.fill(cost, 987654321);
        cost[s] = 0;
        pq.add(new Node(s, cost[s]));
        boolean [] v = new boolean[n+1];
        int cnt = 0;
        while(!pq.isEmpty()){
            Node now = pq.poll();
            if(v[now.e]) continue;
            v[now.e] = true;
            if(++cnt == n) break;
            for(Node node : g[now.e]){
                if(!v[node.e] && cost[node.e] >= node.w + now.w){
                    cost[node.e] = node.w + now.w;
                    pq.add(new Node(node.e, cost[node.e]));
                }
            }
        }
        return cost;
    }
}
