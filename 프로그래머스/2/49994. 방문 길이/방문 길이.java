import java.util.*;

class Solution {
    static int [][] map;
    static HashMap<Character, Integer> dirMap;
    static int [] dy = {-1,1,0,0};
    static int [] dx = {0,0,-1,1};
    static int sy, sx;
    public int solution(String dirs) {
        int answer = 0;
        map = new int[11][11];
        sy = 5;
        sx = 5;
        dirMap = new HashMap<>();
        dirMap.put('U', 0);
        dirMap.put('D', 1);
        dirMap.put('R', 3);
        dirMap.put('L', 2);
        // 캐릭터가 처음 걸어본 길의 길이
        // 좌표평면의 경계를 벗어난 명령어는 무시
        // 방향성 검사로 체크하면 8번 테케에서 완전히 당함
        // 따라서 해당 길에 대해서 반대쪽에서 오는 케이스도 완벽하게 처리해야함
        boolean [][][] v = new boolean[11][11][4];
        char [] drr = dirs.toCharArray();
        for(char dir: drr){
            int d = dirMap.get(dir);
            int ny = sy + dy[d];
            int nx = sx + dx[d];
            if(OOB(ny, nx)) continue;
            if(!v[ny][nx][d]){
                answer++;
                v[ny][nx][d] = true;
                if(d == 0) v[sy][sx][1] = true;
                if(d == 1) v[sy][sx][0] = true;
                if(d == 2) v[sy][sx][3] = true;
                if(d == 3) v[sy][sx][2] = true;
            }
            sy = ny;
            sx = nx;
        }
        return answer;
    }
    static boolean OOB(int y, int x){
        return y >= 11 || y < 0 || x >= 11 || x < 0;
    }
}