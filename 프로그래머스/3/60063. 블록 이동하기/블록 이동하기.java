// bfs, 구현이 아닐까 싶은?

// 1은 벽이고, 지도밖으로 이동할 수 없음
// 처음에 가로인 상태로 시작함
// 앞뒤 구분없음
// 오른쪽으로 한 칸 이동한다면 놓여있는것 그대로 이동하지만
// 90도 회전이 가능하다.
// 90도 회전하는데 걸리는 시간은 정확히 1초이다.

// 회전의 축이되는 칸으로부터 대각선에 있는 칸은 벽이 없어야 한다.
import java.util.*;

class Solution {
    static int n;
    static boolean[][][] v;
    static int[] dy = {-1,1,0,0,-1,1,-1,1};
    static int[] dx = {0,0,-1,1,-1,1,1,-1};
    public int solution(int[][] board) {
        n = board.length;
        // 가로로 누운 경우: 0, 세로로 일어나있는 경우: 1
        v = new boolean[n][n][2];
        int answer = bfs(board);
        return answer;
    }
    static int bfs(int[][] map) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{0, 0, 0, 0});
        v[0][0][0] = true;
        int time = 0;
        outter:
        while(!queue.isEmpty()) {
            int size = queue.size();
            
            for(int i = 0; i < size; i++) {
                int[] now = queue.poll();
                
                if ((now[0] == n - 2 && now[1] == n - 1 && now[2] == 1) ||
                   (now[0] == n - 1 && now[1] == n - 2 && now[2] == 0)) {
                    break outter;
                }
                
                
                int nly = 0; int nlx = 0; int nry = 0; int nrx = 0;
                for(int dir = 0; dir < 4; dir++) {
                    nly = now[0] + dy[dir];
                    nlx = now[1] + dx[dir];
                    nry = now[0] + (now[2] == 0 ? dy[3] : dy[1]) + dy[dir];
                    nrx = now[1] + (now[2] == 0 ? dx[3] : dx[1]) + dx[dir];
                    if (OOB(nly, nlx) || OOB(nry, nrx) || map[nly][nlx] == 1 || map[nry][nrx] == 1 || v[nly][nlx][now[2]]) {
                        continue;
                    }
                    v[nly][nlx][now[2]] = true;
                    queue.add(new int[]{nly, nlx, now[2], now[3] + 1});
                }
                // 오른쪽을 축으로 위로 세우거나 아래로 세우는 방식
                // 생각해보면 360도 회전은 아니고, 180도 회전만 가능하다.
                // 그러면 가로에서 세우는 경우와 세웠다가 눕는경우로 구분하자.
                if (now[2] == 0) {
                    nry = now[0] + dy[3];
                    nrx = now[1] + dx[3];
                    // 기준축을 축으로 오른쪽칸을 회전
                    if (!OOB(nry + dy[1], nrx + dx[1]) && map[nry + dy[1]][nrx + dx[1]] == 0 &&
                        !OOB(now[0] + dy[1], now[1] + dx[1]) && map[now[0] + dy[1]][now[1] + dx[1]] == 0 &&
                        !v[now[0]][now[1]][1]
                    ) {
                        v[now[0]][now[1]][1] = true;
                        queue.add(new int[]{now[0], now[1], 1, now[3] + 1});
                    }
                    if (!OOB(nry + dy[0], nrx + dx[0]) && map[nry + dy[0]][nrx + dx[0]] == 0 &&
                        !OOB(now[0] + dy[0], now[1] + dx[0]) && map[now[0] + dy[0]][now[1] + dx[0]] == 0 &&
                        !v[now[0] + dy[0]][now[1] + dx[0]][1]
                    ) {
                        v[now[0] + dy[0]][now[1] + dx[0]][1] = true;
                        queue.add(new int[]{now[0] + dy[0], now[1] + dx[0], 1, now[3] + 1});
                    }
                    // 기준축 오른쪽 칸을 기준으로 기준축 회전
                    if (!OOB(now[0] + dy[1], now[1] + dx[1]) && map[now[0] + dy[1]][now[1] + dx[1]] == 0 &&
                        !OOB(nry + dy[1], nrx + dx[1]) && map[nry + dy[1]][nrx + dx[1]] == 0 &&
                        !v[nry][nrx][1]
                    ) {
                        v[nry][nrx][1] = true;
                        queue.add(new int[]{nry, nrx, 1, now[3] + 1});
                    }
                    if (!OOB(now[0] + dy[0], now[1] + dx[0]) && map[now[0] + dy[0]][now[1] + dx[0]] == 0 &&
                        !OOB(nry + dy[0], nrx + dx[0]) && map[nry + dy[0]][nrx + dx[0]] == 0 &&
                        !v[nry + dy[0]][nrx + dx[0]][1]
                    ) {
                        v[nry + dy[0]][nrx + dx[0]][1] = true;
                        queue.add(new int[]{nry + dy[0], nrx + dx[0], 1, now[3] + 1});
                    }

                } else {
                    nry = now[0] + dy[1];
                    nrx = now[1] + dx[1];
                    // 기준축을 축으로 아래칸을 회전
                    if (!OOB(nry + dy[3], nrx + dx[3]) && map[nry + dy[3]][nrx + dx[3]] == 0 &&
                        !OOB(now[0] + dy[3], now[1] + dx[3]) && map[now[0] + dy[3]][now[1] + dx[3]] == 0 &&
                        !v[now[0]][now[1]][0]
                    ) {
                        v[now[0]][now[1]][0] = true;
                        queue.add(new int[]{now[0], now[1], 0, now[3] + 1});
                    }
                    if (!OOB(nry + dy[2], nrx + dx[2]) && map[nry + dy[2]][nrx + dx[2]] == 0 &&
                        !OOB(now[0] + dy[2], now[1] + dx[2]) && map[now[0] + dy[2]][now[1] + dx[2]] == 0 &&
                        !v[now[0] + dy[2]][now[1] + dx[2]][0]
                    ) {
                        v[now[0] + dy[2]][now[1] + dx[2]][0] = true;
                        queue.add(new int[]{now[0] + dy[2], now[1] + dx[2], 0, now[3] + 1});
                    }
                    // 기준축 아래칸을 기준으로 기준축 회전
                    if (!OOB(now[0] + dy[3], now[1] + dx[3]) && map[now[0] + dy[3]][now[1] + dx[3]] == 0 &&
                        !OOB(nry + dy[3], nrx + dx[3]) && map[nry + dy[3]][nrx + dx[3]] == 0 &&
                        !v[nry][nrx][0]
                    ) {
                        v[nry][nrx][0] = true;
                        queue.add(new int[]{nry, nrx, 0, now[3] + 1});
                    }
                    if (!OOB(now[0] + dy[2], now[1] + dx[2]) && map[now[0] + dy[2]][now[1] + dx[2]] == 0 &&
                        !OOB(nry + dy[2], nrx + dx[2]) && map[nry + dy[2]][nrx + dx[2]] == 0 &&
                        !v[nry + dy[2]][nrx + dx[2]][0]
                    ) {
                        v[nry + dy[2]][nrx + dx[2]][0] = true;
                        queue.add(new int[]{nry + dy[2], nrx + dx[2], 0, now[3] + 1});
                    }    
                }
            }
            ++time;
        }
        return time;
    }
    static boolean OOB(int y1, int x1) {
        return y1 >= n || y1 < 0 || x1 >=n || x1 < 0;
    }
}