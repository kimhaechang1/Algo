import java.util.*;

class Solution {
    static int n;
    static int m;
    static int[] dy = {-1,1,0,0};
    static int[] dx = {0,0,-1,1};
    static int[] r, b;
    static int min;
    static int[][] map;
    static boolean[][] rv;
    static boolean[][] bv;
    public int solution(int[][] maze) {
        int answer = 0;
        n = maze.length;
        m = maze[0].length;
        map = maze;
        // 빨강 수레 하나, 파란 수레 하나.
        // 각 턴마다 모든 수레를 상하좌우 인접한 칸으로 이동시켜야함
        // dfs 로 접근
        r = new int[2];
        b = new int[2];
        rv = new boolean[n][m];
        bv = new boolean[n][m];
        for(int i= 0;i<maze.length;i++) {
            for(int j = 0;j<maze[0].length;j++) {
                if (maze[i][j] == 1) {
                    r[0] = i;
                    r[1] = j;
                    rv[i][j] = true;
                } else if (maze[i][j] == 2) {
                    b[0] = i;
                    b[1] = j;
                    bv[i][j] = true;
                }
            }
        }
        min = Integer.MAX_VALUE;
        dfs(0, r, b);
        return min == Integer.MAX_VALUE ? 0 : min;
    }
    static void dfs(int depth, int[] r, int[] b) {

        if(map[b[0]][b[1]] == 4 && map[r[0]][r[1]] == 3) {
            min = Math.min(min, depth);
            return;
        }

        int ry, rx, by, bx;

        if (map[r[0]][r[1]] == 3) {
            for(int dir = 0;dir < 4;dir++) {
                by = b[0] + dy[dir];
                bx = b[1] + dx[dir];
                if(OOB(by, bx)) continue;
                if(bv[by][bx] || map[by][bx] == 5) continue;
                if(!(by == r[0] && bx == r[1])) {
                    bv[by][bx] = true;
                    dfs(depth+1,  r, new int[]{by, bx});
                    bv[by][bx] = false;
                }
            }
        } else if (map[b[0]][b[1]] == 4) {
            for(int dir = 0;dir < 4;dir++) {
                ry = r[0] + dy[dir];
                rx = r[1] + dx[dir];
                if(OOB(ry, rx)) continue;
                if(rv[ry][rx] || map[ry][rx] == 5) continue;
                if(!(ry == b[0] && rx == b[1])) {
                    rv[ry][rx] = true;
                    dfs(depth+1, new int[]{ry, rx}, b);
                    rv[ry][rx] = false;
                }
            }
        } else {
            for(int dir = 0;dir < 4;dir++) {
                ry = r[0] + dy[dir];
                rx = r[1] + dx[dir];
                if(OOB(ry, rx))continue;
                if(rv[ry][rx] || map[ry][rx] == 5) continue;
                for(int dir2 = 0;dir2 < 4;dir2++) {
                    by = b[0] + dy[dir2];
                    bx = b[1] + dx[dir2];
                    if(OOB(by, bx)) continue;
                    if(bv[by][bx] || map[by][bx] == 5) continue;
                    if(by == r[0] && bx == r[1] && ry == b[0] && rx == b[1]) continue;
                    if(by == ry && bx == rx) continue;
                    rv[ry][rx] = true;
                    bv[by][bx] = true;
                    dfs(depth+1, new int[]{ry, rx}, new int[]{by, bx});
                    bv[by][bx] = false;
                    rv[ry][rx] = false;
                }
            }
        }
    }
    static boolean OOB(int y, int x) {
        return y>=n || y < 0 || x>=m || x < 0;
    }
}