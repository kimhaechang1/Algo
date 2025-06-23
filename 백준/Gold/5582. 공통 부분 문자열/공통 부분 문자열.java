import java.util.*;
import java.io.*;


public class Main {

    static String str;
    static BufferedReader bf;
    static StringTokenizer stk;
    static String s1;
    static String s2;
    Main() {
        bf = new BufferedReader(new InputStreamReader(System.in));
    }


    public static void main(String[] args) throws Exception{
        Main m = new Main();
        m.input();
        m.solve();
    }

    void solve() {

        // LCS 와 조금 다른문제, LCS는 공통 부분 수열을 찾는것이므로 찾아넨 수열이 연속적일 필요는 없다.
        // 다만 공통부분 문자열은 비슷하면서도 서로 다른 문자를 검사할 때 끊겨야 한다.

        char[] ch1 = s1.toCharArray();
        char[] ch2 = s2.toCharArray();
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for(int i = 1 ; i < ch1.length + 1; i++) {
            for(int j = 1; j < ch2.length + 1; j++) {
                if (ch1[i - 1] == ch2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        int ans = 0;
        for(int i = 1; i < ch1.length + 1; i++) {
            for(int j = 1; j < ch2.length + 1; j++) {
                ans = Math.max(ans, dp[i][j]);
            }
        }
        System.out.print(ans);


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
        s1 = bf.readLine();
        s2 = bf.readLine();
    }
}