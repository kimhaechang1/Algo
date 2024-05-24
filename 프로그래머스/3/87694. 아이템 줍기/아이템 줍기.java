import java.util.*;
class Solution {
    static int [][] map;
    static int sy, sx;
    static int ty, tx;
    static int [] dy = {-1,1,0,0,-1,1,-1,1};
    static int [] dx = {0,0,-1,1,-1,1,1,-1};
    static boolean [][] v;
    static int [][] rs;
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY){
        int answer = 0;
        map = new int[110][110];
        rs = new int[rectangle.length][4];
        rs = rectangle;
        for(int i = 0;i<rs.length;i++){
            int [] p = rs[i];
            // 두배로 늘려주면된다. 그럴려면 찍을 공간이 2배씩 있어야 한다.
            // 크기를 두배로 늘리되, 위치점도 2배로 늘려야한다. 왜냐하면 실제로 1차이나는곳 에서 시작했다면
            // 2배 늘린 좌표평면에서는 2차이나는곳에서 시작하는것과 마찬가지기 때문
            for(int y = p[0] * 2; y<= p[2] * 2;y++){
                for(int x = p[1] * 2;x<=p[3] *2;x++){
                    if(map[y][x] == 1) continue;
                    // 이미 겹쳐져 있는 좌표는 무시한다.
                    map[y][x] = 1;
                    if(y == p[0] * 2 || y == p[2] * 2 || x == p[1] * 2 || x == p[3] * 2){
                        map[y][x] = 2;
                    }
                }
            }
        }
        sy = characterX * 2;
        sx = characterY * 2;
        ty = itemX * 2;
        tx = itemY * 2;
        return bfs() / 2;
    }
    
    static int bfs(){
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{sy, sx, 0});
        v = new boolean[110][110];
        v[sy][sx] = true;
        int result = 0;
        while(!queue.isEmpty()){
            int [] now = queue.poll();
            if(now[0] == ty && now[1] == tx){
                result = now[2];
            }
            for(int dir = 0;dir< 4;dir++){
                int ny = now[0] + dy[dir];
                int nx = now[1] + dx[dir];
                if(OOB(ny, nx) || map[ny][nx] != 2 || v[ny][nx]) continue;
                v[ny][nx] = true;
                queue.add(new int[]{ny, nx, now[2]+1});
            }
        }
        return result;
    }
    static boolean OOB(int y, int x){
        return y >= 110 || y < 0 || x >=110 || x < 0;
    }
}