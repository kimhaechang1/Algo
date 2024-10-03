import java.io.*;
import java.util.*;

public class Main{

    static StringTokenizer stk;
    static int n;
    static int k;
    static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        stk = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(stk.nextToken());
        k = Integer.parseInt(stk.nextToken());

        arr = new int[n];
        stk = new StringTokenizer(bf.readLine());
        for(int i = 0;i<n;i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
        }
        int[] S = new int[n];
        // i 까지의 계단수열 개수
        // 해시맵을 사용해서 딱 K개일때 끊었는데 이러면 19퍼에서 틀린다.
        // 왜냐하면 i-k의 start를 가지면서 k개의 계단수열만 되면 상관없기에, k개 이상이어도 상관없다.
        S[0] = 1;
        int prev = 0;
        for(int i = 1;i<n;i++) {
            if(Math.abs(arr[i-1]- arr[i]) != 1) prev = i;
            S[i] = i - prev + 1;
        }

        int[] dp = new int[n+1];
        dp[0] = 0;
        for(int i = 1;i<=n;i++) {
            dp[i] = 987654321;
            for(int j = 1;j<=3;j++) {
                if (i - j < 0) {
                    break;
                }
                dp[i] = Math.min(dp[i], dp[i - j] + 1);
            }
            if (i - k  >= 0 && S[i - 1] >= k) {
                dp[i] = Math.min(dp[i], dp[i - k] + 1);
            }
        }
        System.out.print(dp[n]);

    }
}