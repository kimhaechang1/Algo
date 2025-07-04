import java.util.*;
import java.io.*;


public class Main {

    static String str;
    static BufferedReader bf;
    static StringTokenizer stk;
    static int n;
    static int m, k;
    static int[][] ks;
    static int[] dy = {-1,1,0,0};
    static int[] dx = {0,0,-1,1};
    static int t;
    static int p;
    static int ujeong;
    static boolean flag;
    Main() {
        bf = new BufferedReader(new InputStreamReader(System.in));
    }


    public static void main(String[] args) throws Exception{
        Main m = new Main();
        m.input();
        m.solve();
    }

    void solve() {
        // 우정이와 아름이가 각각 최대로 잡을 수 있는 모기의 수
        // 우정이는 1초에 1칸씩 상하좌우 움직임
        // 모기가 있는 장소에 도착하면 우정이는 즉시 모기의 체력과 관계없이 잡는다.
        // 우정이는 체력이 안좋기 때문에 T초 동안만 움직일 수 있으며 T초에 도착한 장소나 0초에 출발한 장소의 모기도 잡을 수 있다.
        // 물론 그 장소에 여러 모기가 있더라도 전부 잡을 수 있다.

        // 우정이의 초기위치는 자유롭게 선택할 수 있다.

        // 아름이는 특정좌표를 지정해 세기 P 의 전기장을 형성
        // 해당 좌표에서부터 R,C 의 전기장의 새기는 택시거리 L 에 대하여 P/L 과 같음
        // 해당 위치의 전기장으 ㅣ세기가 그 위치에 있는 모기의 체력보다 크거나 같다면 모기를 잡을 수 있다.
        // 택시거리란 d = |y1 - y2| + |x2 - x1| 을 뜻함

        // 우정이의 경우와 아름이의 경우로 나눌 수 있을듯
        // 아름이는 구현으로 그냥 모든 좌표에 대해서 검사해보면 될듯함
        // 아름이 시간복잡도는 50 * 50 에 대해서 50 * 50 일듯함

        // 우정이는 최대치를 찾아야하므로 백트래킹을 해야할듯함
        // 문제는 복원인데, 복원이 쉬울지 모르겠음

        // 1초 제한이라서 50*50 에 매 순간 4방향에 그것을 최대 10번까지 가능하니까 4^10 으로 치면 시간초과일거다.
        // 차라리 모기위치를 순열로 조정시키고 이동거리가 T초안에 가능한곳까지만 계산해서 최대값을 추출해보는것도 방법이다.
        int arm = 0;
        ujeong = 0;
        for(int i = 0 ; i < n; i++) {
            for(int j = 0; j < m; j++) {
                int cnt = 0;
                for(int ki = 0; ki < k; ki++) {
                    if (i == ks[ki][0] && j == ks[ki][1]) {
                        cnt++;
                        continue;
                    }
                    int dis = Math.abs(i - ks[ki][0]) + Math.abs(j - ks[ki][1]);
                    if (ks[ki][2] <= (p / dis)) {
                        cnt++;
                    }
                }
                arm = Math.max(arm, cnt);
            }
        }

        // 어짜피 우정이는 좌표에 있는 모기가 몇마리든지간에 어떤 체력을 가지든지 간에 죽인다.
        // 그래서 좌표마다 몇마리의 모기가 있는지 미리 계산해두고, 중복되지않는 좌표의 개수도 셈한다.
        // 그리고 dfs 를 통해 백트래킹을 진행한다.
        int[][] cntMap = new int[n][m];
        ArrayList<int[]> pos = new ArrayList<>();

        for(int i = 0; i < k; i++) {
            cntMap[ks[i][0]][ks[i][1]]++;
            if (cntMap[ks[i][0]][ks[i][1]] == 1) {
                pos.add(new int[]{ks[i][0], ks[i][1]});
            }
        }
        boolean[] v = new boolean[pos.size()];
        for(int i = 0; i < pos.size(); i++) {
            if (ujeong == k) break;
            dfs(0, pos,v, i, cntMap[pos.get(0)[0]][pos.get(0)[1]], cntMap);
        }

        System.out.println(ujeong +" " + arm);
    }
    void dfs(int depth, ArrayList<int[]> pos, boolean[] isKill, int idx, int cnt, int[][] cntMap) {
        if (depth > t) {
            return;
        }

        ujeong = Math.max(ujeong, cnt);
        isKill[idx] = true;
        int[] now = pos.get(idx);
        for(int i = 0; i < pos.size(); i++) {
            if (isKill[i]) continue;
            int[]pp = pos.get(i);
            int time = Math.abs(now[0] - pp[0]) + Math.abs(now[1] - pp[1]);
            int dead = cntMap[pp[0]][pp[1]];
            dfs(depth + time, pos, isKill, i, cnt + dead, cntMap);
        }
        isKill[idx] = false;

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
        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        k = Integer.parseInt(stk.nextToken());
        t = Integer.parseInt(stk.nextToken());
        p = Integer.parseInt(stk.nextToken());
        ks = new int[k][3];
        for(int i = 0; i < k; i++) {
            stk = new StringTokenizer(bf.readLine());
            ks[i][0] = Integer.parseInt(stk.nextToken()) - 1;
            ks[i][1] = Integer.parseInt(stk.nextToken()) - 1;
            ks[i][2] = Integer.parseInt(stk.nextToken());
        }
    }
}