// 격자 바깥으로 나갈 수 없음
// 총 거리가 k여야 하며, 같은격자를 두번이상 방문해도 됨 -> k번째일 때 r,c에 도착해있어야 하는듯
// 상하 좌우 이동임
// 문자열이 사전 순으로 빠른 경로로 탈출해야함
// d < l < r < u 순서임


// 아래로 갈수 있으면 제일 좋고, 다음 왼쪽, 다음 오른쪽, 다음 위 순서임
// 최단거리로 내려갈 수 있는 칸수를 k에서 뺏을 때, 남은 칸 수가 홀수면 갈 수 없음
// 왜냐하면 좌우 반복 혹은 상하 반복 등의 행위로 k번째일 때 딱 목적지에 도착해야 함
// 거리상 애초에 k번 만큼 갈 수 없는 경우에서는 제외해야함
import java.util.*;

class Solution {
    static int N;
    static int M;
    static int K;
    static String answer;
    static boolean find;
    static int[] dy = {0,-1,1,0};
    static int[] dx = {1,0,0,-1};
    public String solution(int n, int m, int x, int y, int r, int c, int k) {        
        int totalCnt = Math.abs(x - r) + Math.abs(y - c);
        
        if (k < totalCnt) return "impossible";
        if (((totalCnt - k) & 1) == 1) return "impossible";
        N = n;
        M = m;
        K = k;
        answer = null;
        find = false;
        dfs(x, y, r, c, k, -1, new StringBuilder());
        
        return answer;
    }
    static void dfs(int x, int y, int r, int c, int depth, int prevDir, StringBuilder sb) {
        if (depth < 0 || find) return;
        int remain = Math.abs(x - r) + Math.abs(y - c);
        if (((depth - remain) & 1) == 1) return;
        if (remain > depth) return;
        if (depth == 0 && x == r && y == c) {
            if (answer == null || sb.toString().compareTo(answer) < 0) {
                find = true;
                answer = sb.toString();
            }
            return;
        }
        
        
        for(int dir = 0; dir < 4; dir++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            if (nx > N || nx <= 0 || ny > M || ny <= 0) continue;
            sb.append(getDir(dir));
            dfs(nx, ny, r, c, depth - 1, dir, sb);
            sb.delete(sb.length() - 1, sb.length());
        }
    }
    static String getDir(int dir) {
        if (dir == 0) return "d";
        if (dir == 1) return "l";
        if (dir == 2) return "r";
        else return "u";
    }
    static String reverseDir(int dir) {
        if (dir == 0) return "u";
        if (dir == 1) return "r";
        if (dir == 2) return "l";
        else return "d";
    }
}