// start 19:49
// end
import java.util.*;
class Solution {
    static char [][] map;
    static int [] dy = {-1,1,0,0};
    static int [] dx = {0,0,-1,1};
    static int n;
    static int m;
    static int sy, sx;
    static int ty, tx;
    public int solution(String[] board) {
        int answer = 0;
        n = board.length;
        m = board[0].length();
        map = new char[n][m];
        for(int i = 0;i<n;i++){
            for(int j = 0;j<m;j++){
                char frag = board[i].charAt(j);
                if(frag == 'R'){
                    sy = i;
                    sx = j;
                    map[i][j] = '.';
                }else if(frag == 'G'){
                    ty = i;
                    tx = j;
                    map[i][j] = '.';
                }else{
                    map[i][j] = frag;
                }
            }
        }
        // for(int i= 0;i<n;i++){
        //     for(int j= 0;j<m;j++){
        //         System.out.print(map[i][j]+" ");
        //     }
        //     System.out.println();
        // }
        System.out.println(bfs());

        return bfs();
    }
    static int bfs(){
        int min = -1;
        Queue<int []> queue = new ArrayDeque<>();
        boolean[][][] v = new boolean[n][m][4];
        queue.add(new int[]{sy, sx, 0});
        while(!queue.isEmpty()){
            int [] now = queue.poll();
            if(now[0] == ty && now[1] == tx){
                min = now[2];
                break;
            }
            for(int dir = 0;dir<4;dir++){
                int ny = now[0] + dy[dir];
                int nx = now[1] + dx[dir];
                while(!OOB(ny, nx) && map[ny][nx] == '.' && !v[ny][nx][dir]){
                    v[ny][nx][dir] = true;
                    ny += dy[dir];
                    nx += dx[dir];
                }
                if(OOB(ny-dy[dir], nx-dx[dir])){
                    continue;
                }
                if(now[0] == ny-dy[dir] && now[1] == nx-dx[dir]){
                    continue;
                }
                queue.add(new int[]{ny-dy[dir], nx-dx[dir], now[2]+1});
            }
        }
        return min;
    }
    static boolean OOB(int y, int x){
        return y >= n || y < 0 || x >= m || x < 0;
    }
}