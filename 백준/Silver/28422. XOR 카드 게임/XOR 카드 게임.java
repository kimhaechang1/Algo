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

        // XOR 카드게임이란, N장의 카드더미가 있을 때, 맨 위에서부터 카드를 두 장 혹은 세 장씩 가져가 점수를 획득함, 점수만 누적한다고 보면됨
        // 카드들에 있는 번호들을 XOR 연산하여, 이진수로 변환했을 때 1의 개수만큼이 점수가 된다.
        // 마지막에 카드 한장이 남는경우 모든 점수를 잃고 0점이 된다.
        // 이 게임에서 얻을 수 있는 최대점수는?

        // 카드의 개수는 10만개, 카드의 각 정수는 i를 인덱스로 두고 있으며 i가 작을수록 가장위에 놓인 카드이고, 그 값은 1 ~ 1024 이다.
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        if (n < 2) {
            System.out.print(0);
            return;
        }
        if (n >= 2) {
            int a = arr[0] ^ arr[1];
            dp[2] = getBits(a);
        }
        if (n >= 3) {
            int a = arr[0] ^ arr[1] ^ arr[2];
            dp[3] = getBits(a);
        }

        for(int i = 4; i <= n; i++) {
            int xor;
            if (dp[i - 2] != -1) {
                xor = arr[i - 2] ^ arr[i - 1];
                dp[i] = Math.max(dp[i], getBits(xor) + dp[i - 2]);
            }
            if (dp[i - 3] != -1) {
                xor = arr[i - 3] ^ arr[i - 2] ^ arr[i - 1];
                dp[i] = Math.max(dp[i], getBits(xor) + dp[i - 3]);
            }
        }
        System.out.print(dp[n]);
    }
    static int getBits(int number) {
        int bit = 1;
        int cnt = 0;
        for(int i = 0; i < 10; i++) {
            if ((number & bit) != 0) {
                cnt++;
            }
            bit <<= 1;
        }
        return cnt;
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