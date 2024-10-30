import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    static StringTokenizer stk;
    static int n;
    static int[] arr;

    public static void solve() {
        int ans = 0;
        int[][] dp = new int[n][2];
        // 0 건너뜀을 안썻음, 1 건너뜀을 썻음
        // idx = 1번을 기준으로 -4를 10을 선택한 결과에서 더하냐, 아니면 10을 포기한 상태에서 더하냐를 갈라야함
        dp[0][0] = arr[0];
        int max1 = dp[0][0];
        int max2 = dp[0][0];
        for(int i = 1;i<n;i++) {
            // 원래 연속 최대구간 찾는 dp
            dp[i][0] = Math.max(dp[i - 1][0] + arr[i], arr[i]);
            max1 = Math.max(max1, dp[i][0]);
        }
        for(int i = 1;i<n;i++) {
            // 자기 앞의 원소를 무시하고 얻는 최적의 값에서 현재값을 더하는것과, 그냥 연속 최대구간 찾는 dp값과의 비교
            dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][1] + arr[i]);
            max2 = Math.max(max2, dp[i][1]);
        }

        System.out.println(Math.max(max1, max2));

    }
    public static void input() throws Exception {

        BufferedReader bf =new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        arr = new int[n];
        stk = new StringTokenizer(bf.readLine());
        for(int i = 0;i<n;i++) arr[i] = Integer.parseInt(stk.nextToken());
    }

    public static void main(String[] args) throws Exception {
        input();
        solve();
    }


}