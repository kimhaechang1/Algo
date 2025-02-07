
import java.util.*;
import java.io.*;

public class Main {

    static int n, m, w;
    static int[][] arr;
    static int[][] arr2;
    static BufferedReader bf;
    static StringTokenizer stk;

    Main() {
        bf = new BufferedReader(new InputStreamReader(System.in));
    }


    public static void main(String[] args) throws Exception{
        Main m = new Main();
        int t = Integer.parseInt(bf.readLine());
        StringBuilder sb = new StringBuilder();
        while(t-- > 0) {
            m.input();
            sb.append(m.solve()).append("\n");
        }
        System.out.print(sb);
    }

    void input() throws Exception {
        stk = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        w = Integer.parseInt(stk.nextToken());
        arr = new int[m][3];
        for(int i = 0; i < m; i++) {
            stk = new StringTokenizer(bf.readLine());
            arr[i][0] = Integer.parseInt(stk.nextToken());
            arr[i][1] = Integer.parseInt(stk.nextToken());
            arr[i][2] = Integer.parseInt(stk.nextToken());
        }
        arr2 = new int[w][3];
        for(int i = 0; i < w; i++) {
            stk = new StringTokenizer(bf.readLine());
            arr2[i][0] = Integer.parseInt(stk.nextToken());
            arr2[i][1] = Integer.parseInt(stk.nextToken());
            arr2[i][2] = Integer.parseInt(stk.nextToken());
        }
    }
    static class Edge {
        int s;
        int e;
        int c;

        public Edge(int s, int e, int c) {
            this.s = s;
            this.e = e;
            this.c = c;
        }
    }
    String solve() {
        // 벨만 - 포드 알고리즘

        // 정점 -1 번만큼 모든 간선에 대해서 최단 경로를 구한다.
        // 그리고 모든 간선에 대해서 1회 더 최단경로를 구한다.
        // 이 때 최단경로가 추가적으로 수행되는 경우 음수 사이클이 있다고 판단한다.
        ArrayList<Edge> edges = new ArrayList<>();
        for(int i = 0; i < m; i++) {
            edges.add(new Edge(arr[i][0], arr[i][1], arr[i][2]));
            edges.add(new Edge(arr[i][1], arr[i][0], arr[i][2]));
        }
        for(int i = 0; i < w; i++) {
            edges.add(new Edge(arr2[i][0], arr2[i][1], -arr2[i][2]));
        }
        if (bellmanFord(1, edges)) {
            return "YES";
        }
        return "NO";
    }

    boolean bellmanFord(int startNode, ArrayList<Edge> edges) {
        int[] cost = new int[n + 1];
        Arrays.fill(cost, 987654321);
        // 벨만 - 포드는 어떤 시작점에서 부터 시작하는 음수사이클을 찾는다.
        // 어떤 노드로부터 음수 사이클이 존재하는지 여부는 시작노드가 달라야하지만
        // 그래프 내에 음수사이클이 존재하는지 여부를 검사하는건 벨만포드 한번이면 될거다.
        // 주의 할 점으로, 모든 노드가 연결되어있다는 보장이 없다면, 분명히 시작점으로 잡은것이 끊겨 있을수도 있다.
        // 대신 나머지 간선들은 충분히 벨만포드를 수행하여야 한다. 따라서 음수 사이클 존재판정만 하려면 INF 검사를 빼야 한다.

        boolean isUpdate = false;
        for(int i = 0; i < n - 1; i++) {
            isUpdate = false;
            for(Edge edge: edges) {
                if (cost[edge.e] > cost[edge.s] + edge.c) {
                    cost[edge.e] = cost[edge.s] + edge.c;
                    isUpdate = true;
                }
            }
            if (!isUpdate) break;
        }
        // 마지막까지 업데이트가 발생했다면, 검사해봐야 하는것
        if (isUpdate) {
            for(Edge edge: edges) {
                if (cost[edge.e] > cost[edge.s] + edge.c) {
                    return true;
                }
            }
        }

        return false;
    }

}