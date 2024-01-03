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
        // 문제 뜻 그대로 해석하면 MST로 해석하기 쉽다.
        // 왜냐하면 최단 거리로 모든 노드들에게 도착할때의 간선들을 고르라는 거라고 해석할 수도 있음
        // 하지만 MST는 최소비용이지 최단거리를 만들어 주진 않는다는점! 잊으면 안됨
        // 이 문제에서의 요구사항은
        // BFS로 최단거리로 이동하면서 다익으로 구한 1~특정정점까지의 최단거리와 동일해지는 간선들을 구하는 것
        // 즉 결국 다익스트라로 1에서 각 정점까지의 최단거리를 구할때 사용한 간선들을 중복없이 구하는 것
        
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
