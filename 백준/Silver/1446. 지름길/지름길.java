import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    static StringTokenizer stk;
    static int n, d;
    static int[][] info;
    static int min;
    static boolean[] select;
    public static void solve() {
        min = Integer.MAX_VALUE;
        dfs(0, 0);
        System.out.print(min);
    }
    static void dfs(int pd, int sum) {
        if (pd == d) {
            min = Math.min(min, sum);
            return;
        }
        for(int i = 0;i<n;i++) {
            if (pd > info[i][0]) continue;
            if (info[i][1] > d) continue;

            dfs(info[i][1], sum + (info[i][0] - pd + info[i][2]));
        }
        dfs(d, sum + (d - pd));
    }

    public static void input() throws Exception {

        BufferedReader bf =new BufferedReader(new InputStreamReader(System.in));
        stk = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(stk.nextToken());
        d = Integer.parseInt(stk.nextToken());
        info = new int[n][3];
        for(int i = 0;i<n;i++) {
            stk = new StringTokenizer(bf.readLine());
            info[i][0] = Integer.parseInt(stk.nextToken());
            info[i][1] = Integer.parseInt(stk.nextToken());
            info[i][2] = Integer.parseInt(stk.nextToken());
        }

    }


    public static void main(String[] args) throws Exception {
        input();
        solve();
    }


}