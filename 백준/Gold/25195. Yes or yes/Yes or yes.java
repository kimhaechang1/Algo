import java.util.*;
import java.io.*;


public class Main {

    static String str;
    static BufferedReader bf;
    static StringTokenizer stk;
    static int n, m;
    static int s;
    static int[] info;
    static ArrayList<Integer> []g;

    Main() {
        bf = new BufferedReader(new InputStreamReader(System.in));
    }


    public static void main(String[] args) throws Exception{
        Main m = new Main();
        m.input();
        m.solve();
    }

    void solve() {
        boolean[] v = new boolean[n + 1];
        for(int i = 0; i < s; i++) {
            v[info[i]] = true;
        }
        if (v[1]) {
            System.out.print("Yes");
            return;
        }

        Queue<Integer> q = new ArrayDeque<>();
        q.add(1);
        v[1] = true;
        while(!q.isEmpty()) {
            int now = q.poll();
            boolean canGo = false;
            for(int node: g[now]) {
                if (v[node]) continue;
                canGo = true;
                v[node] = true;
                q.add(node);
            }
            if (!canGo && g[now].size() == 0) {
                System.out.print("yes");
                return;
            }
        }
        System .out.print("Yes");

    }

    void input() throws Exception {
        stk = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        g = new ArrayList[n + 1];
        for(int i = 0; i < n + 1; i++) {
            g[i] = new ArrayList<>();
        }
        for(int i = 0; i < m; i++) {
            stk = new StringTokenizer(bf.readLine());
            int u = Integer.parseInt(stk.nextToken());
            int v = Integer.parseInt(stk.nextToken());
            g[u].add(v);
        }
        s = Integer.parseInt(bf.readLine());
        info = new int[s];
        stk = new StringTokenizer(bf.readLine());
        for(int i= 0; i < s; i ++) {

            info[i] = Integer.parseInt(stk.nextToken());
        }

    }
}