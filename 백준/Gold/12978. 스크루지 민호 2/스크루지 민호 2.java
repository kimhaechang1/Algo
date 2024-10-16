import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    static StringTokenizer stk;
    static int n;
    static ArrayList<Integer>[] g;
    static int[][] dp;
    static boolean[] v;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        g = new ArrayList[n+1];
        for(int i= 1;i<n+1;i++) {
            g[i] = new ArrayList<>();
        }
        for(int i= 0;i<n-1;i++) {
            stk = new StringTokenizer(bf.readLine());
            int u = Integer.parseInt(stk.nextToken());
            int v = Integer.parseInt(stk.nextToken());
            g[u].add(v);
            g[v].add(u);
        }
        v = new boolean[n+1];
        dp = new int[2][n+1];
        for(int i = 0;i<2;i++) {
            Arrays.fill(dp[i], -1);
        }
        // 특정 노드를 기준으로 경찰서를 설치했을 때, 자식노드는 경찰서를 설치하거나 설치 안할 수 있다.
        // 특정 노드가 경찰서가 설치되어있지 않다면, 그 자식노드는 반드시 경찰서를 설치해야만 한다.
        // 즉 노드하나에 가지고 있는 경우의수는 dp[경찰서를 세운경우][노드번호] = 경우의 수 가 된다.
        // dfs dp문제이다.
        // 이문제를 봤을 때, 무조건적인 결정적인 부분이 보여지지않으면 억지로 그렇게 하면 안되고, 완탐부터 생각한다.
        // 근데 노드수가 많으니까, 메모할 부분이 있는지 생각한다.

        dfs(1, 0 , 1); // 첫 노드에 대하여 경찰서를 설치 하는경우
        dfs(1, 0, 0); // 첫 노드에 대하여 경찰서를 설치 안하는 경우
        System.out.print(Math.min(dp[1][1], dp[0][1]));
    }
    static int dfs(int node, int parent, int flag) {
        if (dp[flag][node] != -1) return dp[flag][node];

        dp[flag][node] = flag; // 초기값 넣기, 왜냐하면 도달하지 못하는 경우를 Arrays.fill로 채웠고 해당 노드에 경찰서를 세우지 않은경우에는 초기값이 0, 경찰서를 세운경우에는 초기값이 1이기 때문
        for(int next: g[node]) {
            if(next == parent) continue;
            if(flag == 1) {

                dp[flag][node] += Math.min(dfs(next, node, 0), dfs(next, node, 1));
            } else {
                dp[flag][node] += dfs(next, node, 1);
            }
        }
        return dp[flag][node];
    }

}
