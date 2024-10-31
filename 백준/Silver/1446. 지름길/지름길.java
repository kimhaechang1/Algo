import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    static StringTokenizer stk;
    static int n, d;
    static int[][] info;
    public static void solve() {
        // 모든 거리별 기본 거리값은 그냥 걸어가는 경우가 있고
        // 해당 거리에 도착하는 방법은 지름길을 타고 도착한 경우가 있다.
        // 지름길을 타는 횟수제한이 없으므로 최솟값 비교를 통해 누적해 나간다.
        int[] dp = new int[d+1];
        for(int i = 0;i<d+1;i++) {
            dp[i] = i;
        }
        for(int i = 1;i<d+1;i++) {
            // 이전의 값이 혹시나 지름길을 타고와서 짧을수도 있음
            dp[i] = Math.min(dp[i-1] + 1, dp[i]);

            for(int j = 0;j<n;j++) {
                if (i == info[j][1]) {
                    dp[info[j][1]] = Math.min(dp[info[j][1]], dp[info[j][0]] + info[j][2]);
                }
            }
        }
        System.out.print(dp[d]);
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