import java.util.*;
import java.io.*;


public class Main {

    static String str;
    static BufferedReader bf;
    static StringTokenizer stk;
    static int a, b;

    Main() {
        bf = new BufferedReader(new InputStreamReader(System.in));
    }


    public static void main(String[] args) throws Exception{
        Main m = new Main();
        m.input();
        m.solve();
    }

    void solve() {
        // 금민수는 4와 7로만 이루어진 수를 말한다.
        // A <= B, 1 <= A,B <= 10^9
        // 4 와 7로만 이루어질려면, 우선 A가 몇자리 수인지 파악해야한다.
        // A의 자리수에서 표현가능한 4와 7의 조합을 먼저 생각하고, 각 10을 곱하여 자리수를 늘린다음 4와 7의 경우로 계속 생각한다.
        ArrayList<Integer> list = new ArrayList<>();
        list.add(4); list.add(7);
        int prev = 0;
        int bit = 1;
        // 2 + 4
        for(int i = 0; i < 8; i++) {
            bit <<= 1;
            int limit = list.size();
            for(int j = prev; j < limit; j++) {
                int val = list.get(j);
                list.add(val * 10 + 4);
                list.add(val * 10 + 7);
            }
            prev += bit;
        }
        int cnt = 0;
        for(int i = 0; i < list.size(); i++) {
            int val = list.get(i);
            if (a <= val && val <= b) cnt++;
            if (val > b) break;
        }

        System.out.print(cnt);
    }

    void input() throws Exception {

        stk = new StringTokenizer(bf.readLine());
        a = Integer.parseInt(stk.nextToken());
        b = Integer.parseInt(stk.nextToken());
    }
}