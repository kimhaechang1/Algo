import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static int m;
    static int[][] info;
    static StringTokenizer stk;
    static BufferedReader bf;


    Main() {
        bf = new BufferedReader(new InputStreamReader(System.in));
    }


    public static void main(String[] args) throws Exception{
        Main m = new Main();
        m.input();
        m.solve();
    }

    void testCase() throws Exception {
        StringBuilder sb = new StringBuilder();
        while(true) {
            input();
            if (n == 0) break;
            sb.append(TC()).append("\n");
        }
        System.out.print(sb);
    }

    void input() throws Exception {
        stk = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        info = new int[m][2];
        for(int i = 0 ;i < m; i++) {
            stk = new StringTokenizer(bf.readLine());
            info[i][0] = Integer.parseInt(stk.nextToken());
            info[i][1] = Integer.parseInt(stk.nextToken());
        }
    }

    int TC() {
       return 0;
    }

    void solve() {

        // 학생들을 순서에 따라 줄을 세워야 한다.
        // 일부 순서만을 주어졌을 때 전체 줄을 알려줘야한다.

        // 무슨말이냐? 방향대로 줄을 세우라는 것이다.
        // 같은 순서인 노드가 있을 수 있으니 스페셜 저지 문제이다.
        // 학생들 사이에 순서가 있으니, 방향이 있는 그래프이다.

        ArrayList<Integer>[] g = new ArrayList[n + 1];
        int[] indegree = new int[n + 1];
        for(int i = 1;i < n + 1; i++) g[i] = new ArrayList<>();
        for(int i = 0;i < m; i++) {
            g[info[i][0]].add(info[i][1]);
            indegree[info[i][1]]++;
        }

        ArrayList<Integer> answer = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        Queue<Integer> queue = new ArrayDeque<>();
        for(int i = 1;i < n + 1; i++) {
            if (indegree[i] == 0) queue.add(i);
        }

        while(!queue.isEmpty()) {

            int node = queue.poll();
            answer.add(node);
            for (int next: g[node]) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    queue.add(next);
                }
            }
        }


        for(int i = 0;i < answer.size(); i++) {
            sb.append(answer.get(i)).append(" ");
        }
        System.out.print(sb);

    }

}