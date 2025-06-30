import java.util.*;
import java.io.*;


public class Main {

    static String str;
    static BufferedReader bf;
    static StringTokenizer stk;
    static int ff, fs, sf, ss;
    Main() {
        bf = new BufferedReader(new InputStreamReader(System.in));
    }


    public static void main(String[] args) throws Exception{
        Main m = new Main();
        m.input();
        m.solve();


    }

    void solve() {
        // 노래들중 몇개를 새로운 앨범에 넣어야할지 결정
        // f로 시작하는 노래는 f로 끝나는 노래 뒤에 와야한다.
        // s로 시작하는 노래는 s로 끝나는 노래 바로 다음에 와야한다.
        // f로 시작하는 노래가 하나라도 있다면, 앨범의 가장 첫 곡은 f로 시작하는 곡이어야한다.

        // 이러한 조건을 어기지않은체로 최대 몇곡을 실을 수 있는가?

        // 먼저 FF, FS 가 모두 0인경우
        // sf 아니면 ss 만 올수있는데, 이경우 ss 만 쭉 이어붙이고 마지막에 sf 하나만 붙이는게 항상 최대다.
        if (ff == 0 && fs == 0) {
            System.out.print(ss + Math.min(sf, 1));
            return;
        }
        // 그리고 생각해볼건, 만약 빠르게 시작하는것과 느리게 시작하는것 사이의 중간다리가 없는 경우인데
        // sf 나 fs 가 없는 경우보다도 먼저 거를 수 있는게 fs 가 없는 경우다.
        // 이럴경우 ff 는 있기에 ff 는 3번조건에 의해 반드시 먼저오게 되고 그 이후 sf 와 ss 로의 연결이 불가능해진다.
        if (ff > 0 && fs == 0) {
            System.out.print(ff);
            return;
        }

        // 그외의 경우에서는 사실상 fs는 무조건 0보다 클 것이다.
        // 그리고 fs sf fs sf 이런식으로 쭊 나아갈 수 있고 앞 혹은 뒤에 ff 나 ss 를 붙일 뿐이다.
        // 왜냐하면 fs sf 를 함으로서 1번 2번 3번 조건 모두를 만족할 수 있기 때문이다.
        if (fs > sf) {
            System.out.print(ss + ff + (2 * sf) + 1); // fs sf fs sf ... 마지막 fs 를 하나 더 추가할 수 있다.
        } else {
            System.out.print(ss + ff + (2 * fs)); // fs <= sf 인 관계이기에 fs 를 더 추가할 수는 없고 fs 개수만큼만 만들어진다.
        }

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
        stk = new StringTokenizer(bf.readLine());
        ff = Integer.parseInt(stk.nextToken());
        fs = Integer.parseInt(stk.nextToken());
        sf = Integer.parseInt(stk.nextToken());
        ss = Integer.parseInt(stk.nextToken());
    }
}