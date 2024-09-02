import java.util.*;

class Solution {
    static char[][] map;
    static int[] dy = {-1,1,0,0,-1,-1,1,1};
    static int[] dx = {0,0,-1,1,1,-1,1,-1};
    public int solution(String[] board) {
        int answer = -1;
        // 혼자서 틱택토를 하는데 선공이 'O', 후공이 'X'
        // 9칸이 모두 찬 경우 무승부로 게임종료
        // 경우의수로 전체 O의 개수가 X보다 한개 더 많거나 같을때
            // 같을때 나올수 있는 경우는 X가 승리조건을 이루었을 때와, O와 X 모두 승리조건을 이루지 못했을 때이다.
        map = new char[3][3];
        for(int i = 0;i<board.length;i++) {
            for(int j= 0;j<3;j++) {
                map[i][j] = board[i].charAt(j);
            }
        }
        int Ocnt = 0;
        int Xcnt = 0;
        
        for(int i = 0;i<3;i++) {
            for(int j = 0;j<3;j++) {
                if(map[i][j] == 'O') Ocnt++;
                if(map[i][j] == 'X') Xcnt++;
            }
        }
        
        if(Xcnt > Ocnt) return 0;
        if(Xcnt == Ocnt) {
            if(isOWin()) {
                return 0;
            }
        }
        if(Xcnt < Ocnt) {
            if (Ocnt - Xcnt != 1) return 0;
            if (isXWin()) {
                return 0;
            }
            
        }
        return 1;
    }
    static boolean isOWin() {
        for(int i= 0;i<3;i++) {
            for(int j = 0;j<3;j++) {
                if(map[i][j] != 'O') continue;
                boolean[][] v = new boolean[3][3];
                if(bfs('O', i, j, v)) {
                    return true;
                }
            }
        }
        return false;
    }
    static boolean isXWin() {

        for(int i=0;i<3;i++) {
            for(int j = 0;j<3;j++) {
                if(map[i][j] != 'X') continue;
                boolean[][] v = new boolean[3][3];
                if(bfs('X', i, j, v)) {
                    return true;
                }
            }
        }
        return false;
    }
    static boolean bfs(char m, int y, int x, boolean[][] v) {
        Queue<int[]> queue = new ArrayDeque<>();
        v[y][x] = true;
        for(int dir = 0;dir<8;dir++) {
            int ny = y + dy[dir];
            int nx = x + dx[dir];
            if(OOB(ny, nx) || v[ny][nx] || map[ny][nx] != m) continue;
            v[ny][nx] = true;
            queue.add(new int[]{ny, nx, 2, dir});
        }
        while(!queue.isEmpty()) {
            int[] now = queue.poll();
            if(now[2] == 3) {
                return true;
            }
            int ny = now[0] + dy[now[3]];
            int nx = now[1] + dx[now[3]];
            if(OOB(ny, nx) || v[ny][nx] || map[ny][nx] != m) continue;
            v[ny][nx] = true;
            queue.add(new int[] {ny, nx, now[2] + 1, now[3]});
        }        
        return false;
    }
    static boolean OOB(int y, int x) {
        return y >= 3 || y < 0 || x >= 3 || x < 0;
    }
}