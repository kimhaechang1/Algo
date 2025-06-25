import java.util.*;
import java.io.*;


public class Main {

    static String str;
    static BufferedReader bf;
    static StringTokenizer stk;
    static int n;
    Main() {
        bf = new BufferedReader(new InputStreamReader(System.in));
    }


    public static void main(String[] args) throws Exception{
        Main m = new Main();
        m.input();
        m.solve();


    }

    void solve() {
        // 정방향 삼각형과 역방향 삼각형을 따로 계산하자.
        long[] dp = new long[n + 1];
        dp[1] = 1;
        for(int i = 2; i < n + 1; i++) {
            dp[i] += dp[i - 1] + i;
        }

        for(int i = 2; i < n + 1; i++) {
            for(int j = 1 ; j < i; j++) {
                dp[n] += j;
            }
        }
        // System.out.println(dp[n]);
        // 정방향 삼각형이 짝수일 때에는 크기가 더 큰 역방향 삼각형을 고려해볼 수 있다.
        // 즉, 4층일때를 생각해보면 기존까지는 역방향 삼각형이 크기가 1이었다가 2짜리를 한번 고려해야 한다.
        // 2층: 크기가 0이었다가 1인 역방향 삼각형이 생겼다고 볼 수 있다.
        // 3층: 홀수이기 때문에 크기가 1인 역방향 삼각형만 고려하면 되고, 그것은 항상 층 -1개가 된다.
        // 4층: 짝수이기 때문에 크기가 2인 역방향 삼각형이 고려대상이 되고, 크기가 1인 역삼은 3개가 된다.
        // 5층: 홀수이기 때문에 크키가 2인 역삼과 1인 역삼만 고려대상이 되고, 크기가 2라는 뜻은 5 - 2 에서 밑변이 생긴단 뜻이기에 2로 나눠떨어지는지 검사하면 된다.
        // 또한 크기가 1인 역삼은 층 -1개이다.

        long[] dp2 = new long[10001];
        dp2[2] = 1;
        for(int i = 3; i < n + 1; i++) {
             dp2[i] = dp2[i - 1] + (i - 1);
        }
        for(int i = 2; i < n + 1; i++) {
            int s = 1;
            for(int j = n - i; j >= i; j--) {
                dp2[n] += s;
                s++;
            }
        }
        //System.out.println(dp2[n] + dp[n]);
        System.out.println(dp2[n] + dp[n]);
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
        n = Integer.parseInt(bf.readLine());
    }
}