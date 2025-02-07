
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
        for(int i = 1; i < n + 1; i++) {
            if (bellmanFord(i, edges)) {
                return "YES";
            }
        }
        return "NO";
    }

    boolean bellmanFord(int startNode, ArrayList<Edge> edges) {
        int[] cost = new int[n + 1];
        Arrays.fill(cost, 987654321);
        cost[startNode] = 0;
        boolean isUpdate = false;
        for(int i = 0; i < n - 1; i++) {
            isUpdate = false;
            for(Edge edge: edges) {
                if (cost[edge.s] != 987654321 && cost[edge.e] > cost[edge.s] + edge.c) {
                    cost[edge.e] = cost[edge.s] + edge.c;
                    isUpdate = true;
                }
            }
            if (!isUpdate) break;
        }
        // 마지막까지 업데이트가 발생했다면, 검사해봐야 하는것
        if (isUpdate) {
            for(Edge edge: edges) {
                if (cost[edge.s] != 987654321 && cost[edge.e] > cost[edge.s] + edge.c) {
                    return true;
                }
            }
        }

        return false;
    }

}