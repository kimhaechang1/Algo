import java.io.*;
import java.util.*;

public class Main{

    static int[][] map;
    static int n;
    static int[] dy = {-1,1,0,0};
    static int[] dx = {0,0,-1,1};
    static StringTokenizer stk;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = 5;
        map = new int[n][n];
        for(int i = 0;i<n;i++) {
            stk = new StringTokenizer(bf.readLine());
            for(int j= 0;j<n;j++) {
                map[i][j] = Integer.parseInt(stk.nextToken());
            }
        }
        stk = new StringTokenizer(bf.readLine());
        int sy = Integer.parseInt(stk.nextToken());
        int sx = Integer.parseInt(stk.nextToken());

        System.out.print(bfs(sy, sx));
    }
    static int bfs(int sy, int sx) {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] v = new boolean[n][n];
        v[sy][sx] = true;
        queue.add(new int[]{sy, sx, 0});
        while(!queue.isEmpty()) {
            int[] now = queue.poll();
            if (map[now[0]][now[1]] == 1) {
                return now[2];
            }
            for(int dir = 0;dir<4;dir++) {
                int ny= now[0] + dy[dir];
                int nx= now[1] + dx[dir];
                if (OOB(ny, nx) || v[ny][nx] || map[ny][nx] == -1) continue;
                v[ny][nx] = true;
                queue.add(new int[]{ny, nx, now[2] + 1});
            }
        }
        return -1;
    }
    static boolean OOB(int y, int x) {
        return y >= n || y < 0 || x >= n || x <0;
    }
}