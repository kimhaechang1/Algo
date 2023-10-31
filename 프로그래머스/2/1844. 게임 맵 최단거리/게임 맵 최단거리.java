import java.util.*;
class Solution {
    public int solution(int[][] maps) {
        int answer = 0;
        // BFS
        int n = maps.length;
        int m = maps[0].length;
        boolean [][] v = new boolean[n][m];
        Queue<int []> q = new ArrayDeque<>();
        q.add(new int[]{0,0,1});
        v[0][0] = true;
        int [] dy= {-1,1,0,0};
        int [] dx= {0,0,-1,1};
        int res = -1;
        q : while(!q.isEmpty()){
            int [] now = q.poll();
            for(int i= 0;i<4;i++){
                int ny = now[0] + dy[i];
                int nx = now[1] + dx[i];
                if(ny >= n || ny < 0 || nx >= m || nx < 0 || v[ny][nx] ) continue;
                if(ny == n-1 && nx == m-1){
                    res = now[2]+1;
                    break q;        
                }
                if(maps[ny][nx] == 1){
                    v[ny][nx] = true;
                    q.add(new int[]{ny, nx, now[2]+1});    
                }
            }
        }
        return res;
    }
}