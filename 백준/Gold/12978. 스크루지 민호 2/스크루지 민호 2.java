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
        // 특정 노드를 기준으로 경찰서를 설치했을 때, 자식노드는 경찰서를 설치하지 않아도 된다.
        // 특정 노드가 경찰서가 설치되어있지 않다면, 그 자식노드는 반드시 경찰서를 설치해야 한다.
        // 즉 노드하나에 가지고 있는 경우의수는 dp[경찰서를 세운경우][노드번호] = 경우의 수 가 된다.
        dfs(1);
        System.out.print(Math.min(dp[0][1], dp[1][1]));
    }
    static void dfs(int node) {
        v[node] = true;
        dp[1][node] = 1;

        for(int next: g[node]) {
            if(v[next]) continue;
            dfs(next);

            dp[1][node] += Math.min(dp[1][next], dp[0][next]);
            dp[0][node] += dp[1][next];
        }
    }

}