import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    static StringTokenizer stk;
    static int n;

    static int[] arr;
    static int cnt;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String numStr = bf.readLine();
        n = numStr.length();
        arr = new int[n];
        for(int i=0;i<n;i++) {
            arr[i] = numStr.charAt(i) - '0';
        }
        cnt = 0;
        dfs(0);
        System.out.print(cnt);
    }
    static void dfs(int depth) {

        if(depth == n) {
            cnt++;
            return;
        }

        if (arr[depth] <= 34 && arr[depth] > 0) {
            dfs(depth+1);
        }

        if (arr[depth] != 0 && depth+1 < n && arr[depth] * 10 + arr[depth+1] <= 34 && arr[depth] * 10 + arr[depth+1] > 0) {
            dfs(depth+2);
        }
    }
}