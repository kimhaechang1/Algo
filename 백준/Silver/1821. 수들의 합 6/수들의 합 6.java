import java.util.*;
import java.io.*;


public class Main {

    static String str;
    static BufferedReader bf;
    static StringTokenizer stk;
    static int n;
    static int f;
    static int[] result;
    static boolean[] v;
    static boolean flag;
    Main() {
        bf = new BufferedReader(new InputStreamReader(System.in));
    }


    public static void main(String[] args) throws Exception{
        Main m = new Main();
        m.input();
        m.solve();
    }

    void solve() {
        // 순열을 돌리고 시뮬레이션 돌려서 16이 나오는 경우를 찾는건 어떨까나
        // 어짜피 10까지니까
        int[] nums = new int[n];
        result = new int[n];
        v = new boolean[n];
        flag = false;
        for(int i = 0; i < n; i ++) {
            nums[i] = i + 1;
        }
        dfs(nums, 0);
    }
    void dfs(int[] arr, int depth) {
        if (flag) return;
        if (depth == n) {
            if (go(result)) {
                StringBuilder sb = new StringBuilder();
                for(int a: result) sb.append(a).append(" ");
                System.out.print(sb);
                flag = true;
            }
            return;
        }

        for(int i = 0;i < n; i++) {
            if (v[i]) continue;
            v[i] = true;
            result[depth] = arr[i];
            dfs(arr, depth + 1);
            if (flag) return;
            v[i] = false;
        }
    }
    boolean go(int[] arr) {
        int[] copy = Arrays.copyOf(arr, n);
        int k = n;
        while(k-- > 0) {
            for(int i = 0; i < k; i++) {
                copy[i] += copy[i + 1];
            }
        }
        return copy[0] == f;
    }

    void testCase() throws Exception {

        int T = Integer.parseInt(bf.readLine());
        StringBuilder sb = new StringBuilder();
        while(T -- > 0) {
            this.input();
            this.solve();
        }
        System.out.print(sb);
    }

    void input() throws Exception {

        stk = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(stk.nextToken());
        f = Integer.parseInt(stk.nextToken());
    }
}