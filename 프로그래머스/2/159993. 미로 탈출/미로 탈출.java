import java.util.*;

class Solution {
    static int sy, sx;
    static int ty, tx;
    static int ly, lx;
    static boolean [][] v;
    static char [][] map;
    static int [] dy = {-1,1,0,0};
    static int [] dx = {0,0,-1,1};
    public int solution(String[] maps) {
        int answer = 0;
        // 먼저 탈출구 까지의 최단거리를 계산
        // 출구는 레버가 댕겨지지않아도 지나갈 수 있다.
        // 즉, 레버까지의 최단거리를 계산하고, 탈출구까지의 최단거리를 계산하면 됨
        map = new char[maps.length][maps[0].length()];
        for(int i= 0;i<maps.length;i++){
            char [] frags = maps[i].toCharArray();
            for(int j = 0;j<frags.length;j++){
                if(frags[j] == 'S'){
                    sy = i;
                    sx = j;
                }else if(frags[j] == 'E'){
                    ty = i;
                    tx = j;
                }else if(frags[j] == 'L'){
                    ly = i;
                    lx = j;
                }
                map[i][j] = frags[j];
            }
        }
        int sToL = distance(sy, sx, ly, lx);
        if(sToL == -1){
            return -1;
        }
        int lToE = distance(ly, lx, ty, tx);
        if(lToE == -1){
            return -1;
        }
        
        return lToE + sToL;
    }
    static int distance(int sy, int sx, int ty, int tx){
        Queue<int []> queue = new ArrayDeque<>();
        v = new boolean[map.length][map[0].length];
        v[sy][sx] = true;
        queue.add(new int[]{sy, sx, 0});
        while(!queue.isEmpty()){
            int [] now = queue.poll();
            if(now[0] == ty && now[1] == tx){
                return now[2];
            }
            for(int dir = 0;dir<4;dir++){
                int ny = now[0] + dy[dir];
                int nx = now[1] + dx[dir];
                if(OOB(ny, nx) || v[ny][nx] || map[ny][nx] == 'X') continue;
                v[ny][nx] = true;
                queue.add(new int[]{ny, nx, now[2]+1});
            }
        }
        
        return -1;
    }
    static boolean OOB(int y, int x){
        return y >= map.length || y < 0 || x >= map[0].length || x < 0;
    }
}