import java.util.*;
import java.io.*;

public class Main{
    static int n;
    static int m;
    static int start;
    static int end;
    static class Node{
        int edge;
        long weight;
        public Node(int edge, long weight){
            this.edge = edge;
            this.weight = weight;
        }
    }
    static ArrayList<Node> [] g;
    static StringTokenizer stk;
    public static void main(String [] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        stk =new StringTokenizer(bf.readLine());
        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        stk = new StringTokenizer(bf.readLine());
        start = Integer.parseInt(stk.nextToken());
        end = Integer.parseInt(stk.nextToken());
        g = new ArrayList[n+1];
        for(int i= 0;i<n+1;i++){
            g[i] = new ArrayList<>();
        }
        while(m-- > 0){
            stk =new StringTokenizer(bf.readLine());
            int s = Integer.parseInt(stk.nextToken());
            int e = Integer.parseInt(stk.nextToken());
            int w = Integer.parseInt(stk.nextToken());
            g[s].add(new Node(e, w));
            g[e].add(new Node(s, w));
        }
        PriorityQueue<long []> pq = new PriorityQueue<>((a,b)->{
            return Long.compare(b[1], a[1]);
        });
        long [] cost =new long[n+1];
        Arrays.fill(cost, 0);
        cost[start] = Integer.MAX_VALUE;
        pq.add(new long[]{start, cost[start]});
        int cnt = 0;
        while(!pq.isEmpty()){
            long [] now = pq.poll();
            if(cost[(int)now[0]] > now[1]) continue;
            if(now[0] == end) break;
            if(++cnt == n+1) break;
            for(Node node : g[(int)now[0]]){
                if(cost[node.edge] < Math.min(node.weight, now[1])){
                    cost[node.edge] = Math.min(now[1], node.weight);
                    pq.add(new long[]{node.edge, cost[node.edge]});
                }
            }
        }
        System.out.print(cost[end]);
    }
}