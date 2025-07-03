import java.util.*;
import java.io.*;


public class Main {

    static String str;
    static BufferedReader bf;
    static StringTokenizer stk;
    static int n;
    static int m;
    static int[] indexs;
    String plusMinus;
    Main() {
        bf = new BufferedReader(new InputStreamReader(System.in));
    }


    public static void main(String[] args) throws Exception{
        Main m = new Main();
        m.input();
        m.solve();


    }

    void solve() {
        // 우주의 각 점은 좌표 N 개로 이루어지며, 각 좌표는 1 ~ N 으로 인덱스가 매겨져있다.

        // 움직일 좌표의 인덱스를 고른다. 1 ~ N 중 하나.
        // 좌표의 값을 1만큼 증가시킨 곳이나 감소시킨곳으로 이동한다.
        // N = 1 이라면 점마다 좌표는 (1) ... (N) 이다.
        // N = 2 이라면 점마다 좌표는 (1, 1) ... (N, N) 이다.
        // N = 3 이라면 점마다 좌표는 (1, 1, 1) ... (N, N, N) 이다.
        // 그리고 각 좌표에는 인덱스가 매겨져 있다.
        // N = 3 이라면, (1, 1, 1) 일때 1번 인덱스 2번 인덱스 3번 인덱스가 있는 셈이다.

        // M 이 별로 안크다.
        // 문득 드는 생각인데,,
        // 어짜피 M 개의 정수라면 M 개 이상의 정수의 종류가 들어오진 않을것이다.
        // 그러면 M 개의 인덱스로 각 좌표들을 나타낼 수 잇을 것이다.

        // 좌표값 압축 들어가자
        TreeSet<Integer> set = new TreeSet<>();
        for(int i = 0; i < m; i++) {
            set.add(indexs[i]);
        }
        HashMap<Integer, Integer> compression = new HashMap<>();
        int idx = 0;
        for(Integer val: set) {
            compression.put(val, idx++);
        }

        int[][] histories = new int[m + 1][m + 1]; // (phase, compressed Coordination)
        Set<String> duplicated = new HashSet<>();
        duplicated.add(Arrays.toString(histories[0]));


        for(int i = 1; i <= m; i++) {
            for(int j = 0; j < m; j++) {
                histories[i][j] = histories[i - 1][j];
            }

            char flag = plusMinus.charAt(i - 1);
            int pos = indexs[i - 1];
            int compressed = compression.get(pos);
            if (flag == '-') histories[i][compressed]--;
            else histories[i][compressed]++;

            String state = Arrays.toString(histories[i]);
            if (duplicated.contains(state)) {
                System.out.print(0);
                return;
            }
            duplicated.add(state);
        }
        System.out.print(1);
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
        m = Integer.parseInt(bf.readLine());
        stk = new StringTokenizer(bf.readLine());
        indexs = new int[m];
        for(int i = 0; i < m; i++) {
            indexs[i] = Integer.parseInt(stk.nextToken());
        }
        plusMinus = bf.readLine();
    }
}