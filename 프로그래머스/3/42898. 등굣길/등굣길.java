import java.util.*;

class Solution {
    static int[][] map;
    static int[][] cost;
    static int N;
    static int M;
    static int[] dy = {1,0};
    static int[] dx = {0,1};
    static int[][] memo;
    static final int MOD = 1_000_000_007;
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        N = m;
        M = n;
        map = new int[N][M];
        memo = new int[N][M];
        cost = new int[N][M];
        for(int i= 0;i<N;i++) {
            Arrays.fill(memo[i], -1);
        }
        for(int i= 0;i<N;i++) {
            Arrays.fill(cost[i], 987654321);
        }
        for(int[] puddle: puddles) {
            int locy = puddle[0]-1;
            int locx = puddle[1]-1;
            map[locy][locx] = -1;
        }
        cost[0][0] = 0;
        dfs(0, 0, 0);
        return memo[0][0];
    }
    static int dfs(int y, int x, int pDis) {
        
        if ( y == N-1 && x == M - 1) {
            return 1;
        }
        
        if (memo[y][x] != -1) {
            return memo[y][x];
        }
        
        memo[y][x] = 0;
        for(int dir = 0;dir<2;dir++) {
            int ny = y + dy[dir];
            int nx = x + dx[dir];
            if(OOB(ny, nx) || map[ny][nx] == -1) continue;
            if(cost[ny][nx] < pDis + 1) continue;
            cost[ny][nx] = pDis + 1;
            memo[y][x] = (memo[y][x] + dfs(ny, nx, pDis + 1)) % MOD;
        }
        return memo[y][x];
    }
    static boolean OOB(int y, int x) {
        return y >= N || y < 0 || x >= M || x < 0;
    }
}