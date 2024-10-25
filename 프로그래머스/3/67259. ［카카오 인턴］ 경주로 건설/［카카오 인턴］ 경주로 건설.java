import java.util.*;

class Solution {
    // 언제나 좌상에서 우하로 가야함
    // 경주로는 두 칸을 연결하는 도로이고, 그것이 일직선이면 직선도로, 꺾이면 코너라고 한다.
    // 직선도로는 100원, 코너는 500원
    // 0은 비어있고, 1은 벽
    static int[] dy = {-1,1,0,0};
    static int[] dx = {0,0,-1,1};
    static int n;
    public int solution(int[][] board) {
        int answer = 0;
        n = board.length;
        // 최소비용 경주로 건설
        // 다익스트라로 추정됨
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[3] - b[3]);
        int[][][] cost = new int[n][n][4];
        for(int i = 0;i<n;i++) {
            for(int j = 0;j<n;j++) {
                for(int dir = 0;dir<4;dir++) {
                    cost[i][j][dir] = 987654321;
                }
            }
        }
        cost[0][0][0] = 0;
        for(int dir = 0;dir<4;dir++ ) {
            int ny = 0 + dy[dir];
            int nx = 0 + dx[dir];
            if (OOB(ny, nx) || board[ny][nx] == 1) continue;
            cost[ny][nx][dir] = 100;
            pq.add(new int[]{ny, nx, dir, cost[ny][nx][dir]});
        }
        // 특정칸을 도달하는데 경우의수가 2가지 이상임 -> 즉 코너를 써서 도달한 경우와 코너를 쓰지않고 도달한 경우로 나뉨
        // 즉, 특정 칸에 도달하는데 있어서 이전에 사용된 방향의 축과 동일한지 동일하지 않는지가 중요함
        while(!pq.isEmpty()) {
            int[] now = pq.poll();
            if (cost[now[0]][now[1]][now[2]] < now[3]) continue;
            for(int dir = 0;dir < 4;dir++) {
                int ny = now[0] + dy[dir];
                int nx = now[1] + dx[dir];
                if (OOB(ny, nx) || board[ny][nx] == 1) continue;
                if (isCorner(now[2], dir)) {
                    if(cost[ny][nx][dir] > now[3] + 500 + 100) {
                        cost[ny][nx][dir] = now[3] + 500 + 100;
                        pq.add(new int[]{ny, nx, dir, cost[ny][nx][dir]});
                    }
                } else {
                    if (cost[ny][nx][dir] > now[3] + 100) {
                        cost[ny][nx][dir] = now[3] + 100;
                        pq.add(new int[]{ny, nx, dir, cost[ny][nx][dir]});
                    }   
                }
            }
        }
        Arrays.sort(cost[n-1][n-1]);
        return cost[n-1][n-1][0];
    }
    static boolean isCorner(int prevDir, int dir) {
        if ((prevDir == 2 || prevDir == 3) && (dir == 0 || dir == 1)) {
            return true;
        } else if ((dir == 2 || dir == 3) && (prevDir == 0 || prevDir == 1)) {
            return true;
        }
        return false;
    }
    static boolean OOB(int y, int x) {
        return y >= n || y < 0  || x >= n || x < 0;
    }
}