import java.util.*;
import java.io.*;


public class Main {

    static String str;
    static BufferedReader bf;
    static StringTokenizer stk;
    static int n, m, f;
    static char[][] map;
    static int[][][] dp;
    static int[] dy= {1, 0};
    static int[] dx={0,1};
    Main() {
        bf = new BufferedReader(new InputStreamReader(System.in));
    }


    public static void main(String[] args) throws Exception{
        Main m = new Main();
        m.input();
        m.solve();


    }

    void solve() {
        // 격자에서 아래 혹은 오른쪽으로만 움직여야 하며,
        // 왼쪽위에서 오른쪽 아래로 가야하고, 도착한 칸의 정보는 반드시 수집해야 한다면
        // DP 에 가깝다.
        dp = new int[n][n][4]; // 특정칸까지 MOL 혹은 MO 혹은 M 까지 중 어느정도로 완성된 스트링이 있는지
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                for(int k = 0; k < 4; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }
        String targetString = "MOLA";

        // 특정칸까지 MOLA 가 부분문자열로 가지고있는 개수

        // M 은 언제나 시작점이고, O는 L보다 앞에 L은 A보다 앞에 와야한다.
        // 그리고 특정칸에 도달했을 때 윗칸 아니면 왼쪽칸에서 어디까지 진행되었는지를 알아야한다.
        // 물론 윗칸 아니면 왼쪽칸만 선택가능하니까 이전 알파벳은 알 수 있지만, 그기까지 최적의 수로 어떤 문자열을 이루면서 도착했는지가 중요하다.

        // 현재 칸을 기준으로 왼쪽에서 MOLA 중 하나를 뽑는경우와 위에서 MOLA 중 하나를 뽑는 경우로 볼 수 있다.
        // 어짜피 연속적으로 완성되어야 하기에 M 다음에 갑자기 L이 오는건 실패라고 볼 수 있다.
        // 그래서 연속적으로 MOLA가 만들어지는지를 잘 찾아보자.
        // 실패했다고 해서 더 나아가지 않는것은 아니다.
        // 0단계: 아무것도 못만듬, 1단계: M, 2단계: MO, 3단계: MOL, 4단계: MOLA 로 완성하고 0단계로 돌아감

        dfs(0, 0, map[0][0] == 'M' ? 1 : 0);
        int answer = 0;
        for(int i = 0; i < 4; i++) {
            answer = Math.max(answer, dp[0][0][i]);
        }
        System.out.print(answer);
    }
    int dfs(int y, int x, int phase) {
        if (y == n - 1 && x == n - 1) {
            return 0;
        }
        if (dp[y][x][phase] != -1) {
            return dp[y][x][phase];
        }

        dp[y][x][phase] = 0;

        for(int dir = 0; dir < 2; dir++) {
            int ny = y + dy[dir];
            int nx = x + dx[dir];
            if (ny < 0 || ny >= n || nx < 0 || nx >= n) continue;

            if (map[ny][nx] == 'M') {
                dp[y][x][phase] = Math.max(dp[y][x][phase], dfs(ny, nx, 1)); // M이 등장하면 무조건 새로운시작이다.=> 1단계
            } else if (phase == 1 && map[ny][nx] == 'O') {
                dp[y][x][phase] = Math.max(dp[y][x][phase], dfs(ny, nx, phase + 1)); // O 이면서 앞에 M이 있는경우 => 2단계
            } else if (phase == 2 && map[ny][nx] == 'L') {
                dp[y][x][phase] = Math.max(dp[y][x][phase], dfs(ny, nx, phase + 1)); // L 이면서 MO 까지 완성한 경우 => 3단계
            } else if (phase == 3 && map[ny][nx] == 'A') {
                dp[y][x][phase] = Math.max(dp[y][x][phase], dfs(ny, nx, 0) + 1); // A 이면서 MOL 까지 완성한 경우 => 4단계 이후 완성 처리 및 0단계로 변경
            } else {
                dp[y][x][phase] = Math.max(dp[y][x][phase], dfs(ny, nx, 0)); // 아무런 관련없는 상태인 경우 0단계로 변경
            }
        }
        return dp[y][x][phase];
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
        map = new char[n][n];
        for(int i= 0; i < n; i++) {
            map[i] = bf.readLine().toCharArray();
        }
    }
}