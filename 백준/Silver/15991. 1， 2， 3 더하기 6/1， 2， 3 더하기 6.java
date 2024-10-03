import java.io.*;
import java.util.*;

public class Main{

    static StringTokenizer stk;
    static int n;
    static final int MOD = 1_000_000_009;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());
        int[] dp = new int[100_001];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 2;
        dp[4] = 3;
        dp[5] = 3;
        // 대칭을 이루어야 하므로 가운데 집합 양옆에 같은숫자를 더해야 한다.
        // 그 같은숫자가 1인경우, 2인 경우, 3인 경우로 나눠서 해결한다.
        StringBuilder sb = new StringBuilder();
        for(int i = 6;i <= 100_000;i++) {
            dp[i] = ((dp[i - 2] + dp[i - 4]) % MOD + dp[i - 6]) % MOD;
        }
        while(T-- > 0) {
            n = Integer.parseInt(bf.readLine());
            sb.append(dp[n]).append("\n");
        }
        System.out.print(sb);



    }
}