import java.util.*;
import java.io.*;

public class Main{
    static int n;
    static int m;
    static int k;
    static int s;
    static int p;
    static int q;
    static boolean [] infected;
    static boolean [] danger;
    static ArrayList<Node> [] g;
    static class Node{
        int edge;
        int weight;
        public Node(int edge, int weight){
            this.edge = edge;
            this.weight = weight;
        }
    }
    static Queue<int[]> queue;
    static StringTokenizer stk;
    public static void main(String [] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        stk = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        k = Integer.parseInt(stk.nextToken());
        s = Integer.parseInt(stk.nextToken());
        stk = new StringTokenizer(bf.readLine());
        p = Integer.parseInt(stk.nextToken());
        q = Integer.parseInt(stk.nextToken());
        infected = new boolean[n+1];

        danger = new boolean[n+1];
        queue = new ArrayDeque<>();
        for(int i= 0;i<k;i++){
            int node = Integer.parseInt(bf.readLine());
            infected[node] = true;
            queue.add(new int[]{node, s});
        }
        g = new ArrayList[n+1];
        for(int i= 1;i<n+1;i++){
            g[i] = new ArrayList<>();
        }
        for(int i= 0;i<m;i++){
            stk = new StringTokenizer(bf.readLine());
            int s = Integer.parseInt(stk.nextToken());
            int e = Integer.parseInt(stk.nextToken());
            g[s].add(new Node(e, p));
            g[e].add(new Node(s, p));
        }
        // 위험한 도시 or 감염된 도시
        // 위험한 도시이면서 감염된 도시라면? 감염된 도시임
        // 둘 이상의 감염된 도시가 서로의 범위내에 존재할 수 있음
        // 일단 그냥 구현때려보자
        infect();
        //print();
        long [] cost = new long[n+1];
        int cnt = 0;
        Arrays.fill(cost, Long.MAX_VALUE);
        cost[1] = 0;
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b)->{
            return Long.compare(a[1], b[1]);
        });
        pq.add(new long[]{1, cost[1]});
        boolean [] v = new boolean[n+1];
        while(!pq.isEmpty()){
            long [] now = pq.poll();
            if(v[(int)now[0]]) continue;
            v[(int)now[0]] = true;
            if((int)now[0] == n) break;
            if(++cnt == n+1) break;
            if(cost[(int)now[0]] < now[1]) continue;
            for(Node node : g[(int)now[0]]){
                if(infected[node.edge]) continue;
                if(!v[node.edge]){
                    long variableCost = node.weight;
                    if(danger[node.edge]){
                        variableCost = q;
                    }
                    if(cost[node.edge] > now[1] + variableCost){
                        cost[node.edge] = now[1] + variableCost;
                        pq.add(new long[]{node.edge, cost[node.edge]});
                    }

                }
            }
        }
        // 마지막 노드는 숙박이 필요한 것이 아니다.
        if(danger[n]){
            System.out.print(cost[n] - q);
        }else{
            System.out.print(cost[n] - p);
        }



    }
    static void infect(){
        while(!queue.isEmpty()){
            int [] now = queue.poll();
            for(Node node : g[now[0]]) {
                if (!danger[node.edge]) {
                    danger[node.edge] = true;
                    if (now[1]-1 > 0) {
                        queue.add(new int[]{node.edge, now[1] - 1});
                    }
                }

            }
        }
    }
}