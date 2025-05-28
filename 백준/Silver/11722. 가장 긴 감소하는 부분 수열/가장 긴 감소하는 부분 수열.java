import java.util.*;
import java.io.*;


public class Main {

    static String str;
    static BufferedReader bf;
    static StringTokenizer stk;
    static int n;
    static int[] arr;

    Main() {
        bf = new BufferedReader(new InputStreamReader(System.in));
    }


    public static void main(String[] args) throws Exception{
        Main m = new Main();
        m.input();
        m.solve();
    }

    void solve() {

        int[] dp = new int[n + 1];
        int length = 1;
        dp[0] = arr[0];
        for(int i = 1; i < n; i++) {

            if (dp[length - 1] > arr[i]) {
                dp[length] = arr[i];
                length++;
            } else {
                int index = find(dp, length, arr[i]);
                dp[index] = arr[i];
            }
        }

        System.out.print(length);
    }
    int find(int[] arr, int length, int target) {

        int s = length;
        int e = 0;
        int ans = length - 1;
        // 1 >= 0
        // mid = 0;
        // arr[mid] >= target

        // 2 ~ 0
        while(s >= e) {

            int mid = (s + e) / 2;
            if (arr[mid] <= target) {

                ans = Math.min(ans, mid);
                s = mid - 1;
            } else {
                e = mid + 1;
            }
        }
        return ans;
    }

    void input() throws Exception {

        n = Integer.parseInt(bf.readLine());
        arr = new int[n];
        stk = new StringTokenizer(bf.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
        }
    }
}