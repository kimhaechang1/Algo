import java.io.*;
import java.util.*;

public class Main{

    static StringTokenizer stk;
    static int n;
    static int m;
    static char[][] map;
    static boolean[][] v;

    static boolean flag;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        stk = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        map = new char[n][m];
        for(int i =0;i<n;i++) {
            String maps = bf.readLine();
            for(int j =0;j<m;j++) {
                map[i][j] = maps.charAt(j);
            }
        }
        // 사이클 찾기로서 한 사이클은 무조건 4개이상의 점으로 이루어져 있고, 같은 색이어야 한다.
        v = new boolean[n][m];
        for(int i = 0;i<n;i++) {
            for(int j = 0;j<m;j++) {
                char color = map[i][j];
                for(int dir = 0;dir<4;dir++) {
                    flag = false;
                    int ny = i + dy[dir];
                    int nx = j + dx[dir];
                    if (OOB(ny, nx) || color != map[ny][nx]) continue;
                    v[ny][nx] = true;
                    dfs(ny, nx, i, j, v, 1, dir);
                    if (flag) {
                        System.out.println("Yes");
                        return;
                    }
                    v[ny][nx] = false;
                }


            }
        }
        System.out.println("No");
    }
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0,0, -1, 1};
    static void dfs(int y, int x, int sy, int sx, boolean[][] v, int cnt, int pDir) {
        if (flag) return;
        if (y == sy && x == sx && cnt >= 4) {
            flag = true;
            return;
        }
        for(int dir = 0;dir<4;dir++ ){
            if (isReverse(pDir, dir)) continue;
            int ny = y + dy[dir];
            int nx = x + dx[dir];
            if (OOB(ny, nx) || map[sy][sx] != map[ny][nx] || v[ny][nx]) continue;
            v[ny][nx] = true;
            dfs(ny, nx, sy, sx , v, cnt + 1, dir);
            if (flag) {
                return;
            }
            v[ny][nx] = false;
        }
    }
    static boolean OOB(int y, int x){
        return y >= n|| y < 0 || x >= m || x <0;
    }

    static boolean isReverse(int pDir, int dir) {
        if (pDir == 0 && dir == 1) {
            return true;
        } else if (pDir == 1 && dir == 0) {
            return true;
        } else if (pDir == 2 && dir == 3) {
            return true;
        } else if (pDir == 3 && dir == 2) {
            return true;
        }
        return false;
    }
}