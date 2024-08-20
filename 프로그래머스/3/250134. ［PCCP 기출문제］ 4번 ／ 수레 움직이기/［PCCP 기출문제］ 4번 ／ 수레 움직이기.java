import java.util.*;

class Solution {
    static int n;
    static int m;
    static int[] dy = {-1,1,0,0};
    static int[] dx = {0,0,-1,1};
    static int rsy, rsx, rty, rtx, bsy, bsx, bty, btx;
    static int min;
    static int[][] map;
    static boolean[][] rv, bv;
    public int solution(int[][] maze) {
        n = maze.length;
        m = maze[0].length;
        map = maze;
        // 빨강 수레 하나, 파란 수레 하나.
        // 각 턴마다 모든 수레를 상하좌우 인접한 칸으로 이동시켜야함
        // dfs 로 접근
        // 실수한점1, 시작점 방문체크 안할래?
        // 실수한점2, 두 좌표를 동시에 이동시켜야 할때, 모든 16가지 경우의수 중에서 스위치 된 경우를 최종 두 좌표를 결정지은 곳에서 검사해야 하는데 앞좌표만으로 설레발 쳤음
        rv = new boolean[n][m];
        bv = new boolean[n][m];
        for(int i= 0;i<maze.length;i++) {
            for(int j = 0;j<maze[0].length;j++) {
                if (maze[i][j] == 1) {
                    rsy = i;
                    rsx = j;
                    rv[i][j] = true;
                } else if (maze[i][j] == 2) {
                    bsy = i;
                    bsx = j;
                    bv[i][j] = true;
                } else if (maze[i][j] == 3) {
                    rty = i;
                    rtx = j;
                } else if (maze[i][j] == 4) {
                    bty = i;
                    btx = j;
                }
            }
        }

        min = Integer.MAX_VALUE;
        dfs(rsy, rsx, bsy, bsx, 0);
        return min == Integer.MAX_VALUE ? 0 : min;
    }
    static void dfs(int ry, int rx, int by, int bx, int turn) {

        if(ry == rty && rx == rtx && by == bty && bx == btx) {
            min = Math.min(min, turn);
            return;
        }
        int nry, nrx, nby, nbx;
        if(map[ry][rx] == 3) {
            for (int d = 0; d < 4; d++) {
                nby = by + dy[d];
                nbx = bx + dx[d];
                if (OOB(nby, nbx)) continue;
                if (bv[nby][nbx] || map[nby][nbx] == 5) continue;
                if (nby == ry && nbx == rx) continue;
                bv[nby][nbx] = true;
                dfs(ry, rx, nby, nbx, turn + 1);
                bv[nby][nbx] = false;

            }
        } else if(map[by][bx] == 4) {
            for(int dir = 0;dir<4;dir++) {
                nry = ry + dy[dir];
                nrx = rx + dx[dir];
                if(OOB(nry, nrx)) continue;
                if(rv[nry][nrx] || map[nry][nrx] == 5) continue;
                if(nry == by && nrx == bx) continue; // 멈춘곳에 들이받았는지
                rv[nry][nrx] = true;
                dfs(nry, nrx, by, bx, turn + 1);
                rv[nry][nrx] = false;
            }
        } else {
            for(int dir = 0;dir<4;dir++) {
                nry = ry + dy[dir];
                nrx = rx + dx[dir];
                if(OOB(nry, nrx)) continue;
                if(rv[nry][nrx] || map[nry][nrx] == 5) continue;
                for (int d = 0; d < 4; d++) {
                    nby = by + dy[d];
                    nbx = bx + dx[d];
                    if (OOB(nby, nbx)) continue;
                    if (bv[nby][nbx] || map[nby][nbx] == 5) continue;
                    if (nby == ry && nbx == rx && nry == by && nrx == bx) continue; // 서로 위치교환 했는지
                    if (nby == nry && nbx == nrx) continue; // 서로 목적지가 같은지
                    rv[nry][nrx] = true;
                    bv[nby][nbx] = true;
                    dfs(nry, nrx, nby, nbx, turn + 1);
                    bv[nby][nbx] = false;
                    rv[nry][nrx] = false;
                }
            }
        }
    }
    static boolean OOB(int y, int x) {
        return y>=n || y < 0 || x>=m || x < 0;
    }
}