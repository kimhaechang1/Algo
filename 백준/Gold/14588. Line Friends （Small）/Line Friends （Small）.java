import java.util.*;
import java.io.*;


public class Main {

    static String str;
    static BufferedReader bf;
    static StringTokenizer stk;
    static int n, q;
    static int[][] lines;
    static int[][] queries;
    Main() {
        bf = new BufferedReader(new InputStreamReader(System.in));
    }


    public static void main(String[] args) throws Exception{
        Main m = new Main();
        m.input();
        m.solve();
    }

    void solve() {
        // 결국 선분을 하나 거칠때 마다 + 1씩이다.
        // 선분포함여부를 검사하려고 해봤는데 쉽지가 않다.
        // 누가 누구선분에 포함되어잇는지 검사하는게 말이지

        int[][] vals = new int[n][n];
        for(int i = 0 ;i < n ;i++) {
            Arrays.fill(vals[i], 987654321);
        }

        // 어짜피 300 개 밖에 없는데 브루트포스로 검사하면 어떨까
        for(int i = 0;i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                if (lines[i][0] > lines[j][1] || lines[i][1] < lines[j][0]) continue;
                vals[i][j] = 1;
                vals[j][i] = 1;
            }
        }

        // 결국 최대 노드가 300개인 서로의 연결정보가 주어졌고 플로이드 워셜을 통해 최단거리를 구할 수 있다ㅡ.
        for(int k = 0; k < n; k++) {
            for(int i = 0;i < n; i++) {
                for(int j = 0;j < n; j++) {
                    vals[i][j] = Math.min(vals[i][j], vals[i][k] + vals[k][j]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int[] q: queries) {
            if (vals[q[0] - 1][q[1] - 1] == 987654321) {
                sb.append(-1).append("\n");
            } else {
                sb.append(vals[q[0] - 1][q[1] - 1]).append("\n");
            }
        }
        System.out.print(sb);
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
        lines = new int[n][2];
        for(int i = 0; i < n; i++) {
            stk = new StringTokenizer(bf.readLine());
            lines[i][0] = Integer.parseInt(stk.nextToken());
            lines[i][1] = Integer.parseInt(stk.nextToken());
        }
        q = Integer.parseInt(bf.readLine());
        queries = new int[q][2];
        for(int i = 0; i < q; i++) {
            stk = new StringTokenizer(bf.readLine());
            queries[i][0] = Integer.parseInt(stk.nextToken());
            queries[i][1] = Integer.parseInt(stk.nextToken());
        }
    }
}