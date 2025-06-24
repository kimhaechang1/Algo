import java.util.*;
import java.io.*;


public class Main {

    static String str;
    static BufferedReader bf;
    static StringTokenizer stk;
    static int n, m;
    static int[][] map;
    static int[] dy = {0, -1, 0, 1};
    static int[] dx = {-1, 0, 1, 0};
    static ArrayList<Integer> sizes = new ArrayList<>();
    static ArrayList<ArrayList<int[]>> poses = new ArrayList<>();
    static int[][] cMap;
    Main() {
        bf = new BufferedReader(new InputStreamReader(System.in));
    }


    public static void main(String[] args) throws Exception{
        Main m = new Main();
        m.input();
        m.solve();
    }

    void solve() {
        // 굵은 선은 벽을 나타내고 점선은 벽없이 지나다닐 수 있는 통로
        // 이 성에 있는 방의 개수
        // 가장 넓은 방의 넒이
        // 하나의 벽을 제거하여 얻을 수 있는 가장 넓은 방의 크기

        // 2진수로 계산해서 서쪽은 1, 북쪽은 2, 동쪽은 4, 남쪽은 8을 더하면 된다.
        // 즉, 서 남 동에 벽이 있다면 1 + 4 + 8 = 13 이다.
        int[] dir = {1, 2, 4, 8};

        int count = 0;
        int maxSize = 0;
        boolean[][] v = new boolean[m][n];
        Queue<int[]> queue =new ArrayDeque<>();
        int color = 0;
        cMap = new int[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if (v[i][j]) continue;
                v[i][j] = true;
                ArrayList<int[]> pos = new ArrayList<>();
                count++;
                queue.add(new int[]{i, j});
                int sz = bfs(queue, v, dir, pos, color);
                sizes.add(sz);
                poses.add(pos);
                maxSize = Math.max(maxSize, sz);
                color++;
            }
        }
        int max = maxSize;
        // 각 영역별로 보유하고있는 칸의 좌표를 넣어둔다.
        // 모든 좌표를 큐에 넣고 BFS 를 돌리면서 4방향중 벽이 있는 칸을 제거했을 때, 옆의 영역이 다른 영역이라면 그 사이즈를 더하여 최대값을 갱신한다.

        System.out.println(count);
        System.out.println(maxSize);
        max = Math.max(max, getMax(dir, max));
        System.out.print(max);
    }
    int getMax(int[] dirs, int max) {
        for(ArrayList<int[]> posArray: poses) {
            for(int[] pos: posArray) {
                for(int dir = 0; dir < 4; dir++) {
                    int ny = pos[0] + dy[dir];
                    int nx = pos[1] + dx[dir];
                    if (ny >= m || ny < 0 || nx >= n || nx < 0 || cMap[ny][nx] == cMap[pos[0]][pos[1]]) continue;
                    if ((map[pos[0]][pos[1]] & dirs[dir]) == 0) continue;
                    max = Math.max(max, sizes.get(cMap[ny][nx]) + sizes.get(cMap[pos[0]][pos[1]]));
                }
            }
        }
        return max;
    }

    int bfs(Queue<int[]> queue, boolean[][] v, int[] dirs, ArrayList<int[]> pos, int c) {
        int cnt = 0;
        while(!queue.isEmpty()) {
            int[] now = queue.poll();
            cMap[now[0]][now[1]] = c;
            pos.add(new int[]{now[0], now[1]});
            cnt++;
            for(int dir = 0; dir < 4; dir++) {
                int ny = now[0] + dy[dir];
                int nx = now[1] + dx[dir];
                if (ny >= m || ny < 0 || nx >= n || nx < 0 || v[ny][nx]) continue;
                if ((map[now[0]][now[1]] & dirs[dir]) != 0) continue;
                v[ny][nx] = true;
                queue.add(new int[]{ny, nx});
            }
        }
        return cnt;
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
        map = new int[m][n];
        for(int i = 0; i < m; i++) {
            stk = new StringTokenizer(bf.readLine());
            for(int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(stk.nextToken());
            }
        }
    }
}