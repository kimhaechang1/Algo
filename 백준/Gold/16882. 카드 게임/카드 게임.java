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
        // 게임이론 문제다.
        // 게임이론 문제에서 가장 중요한것은 각 플레이어가 이길수 있는 경우의수가 존재하는 phase 인지 검사해야 한다.
        // 즉, 경우의수를 예시를 만들어보고 반드시 한쪽이 판가름이 한쪽으로 나는 경우만을 조합하여 분기문 처리를 하면 된다.

        // 현재 게임에서는 결국 카드내에 숫자의 중복가능여부가 가장 중요하다.
        // 어떠한 경우에서도 cubelover가 이기는 경우
        // 1. 카드내에 모든 수가 짝수개로 이루어졌을때

        // 로 정리할 수 있는데 이유는 아래와 같다.

        // 만약에 1 1 2 의 경우일 때, koosaga 가 이기는 경우가 생긴다.
        // 반대로 1 2 2 라고 하더라도 koosaga 가 이기는 경우가 생긴다.
        // 즉, 카드내에 모든 수가 짝수개로 이루어졌을 때 에만 cubelover가 이기고, 반대로 생각하면 어떠한 경우에서도 홀수개인 카드번호가 있는경우 반드시 koosaga가 이긴다.

        if (n == 1) {
            System.out.print("koosaga");
            return;
        }
        boolean flag = false;
        Arrays.sort(arr);
        int cnt = 1;
        for(int i = 1; i < n; i++) {
            if (arr[i - 1] == arr[i]) {
                cnt++;
            } else {
                if (cnt % 2 == 1) {
                    flag = true;
                    break;
                }
                cnt = 1;
            }
        }
        // 7 7 7 로 나온경우 숫자가 한번도 안바뀌는거라 마지막에도 검사해야함
        if (cnt % 2 == 1) {
            flag = true;
        }

        System.out.println(flag ? "koosaga" : "cubelover");

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
        arr = new int[n];
        stk = new StringTokenizer(bf.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
        }
    }
}