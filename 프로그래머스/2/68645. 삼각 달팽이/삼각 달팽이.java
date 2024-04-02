// start 20:17
// end
import java.util.*;

class Solution {
    static int [] dy = {1,0,-1};
    static int [] dx = {0,1,-1};
    public int[] solution(int n) {
        int[] answer = new int[(n*(n+1))/2];
        int [][] map = new int[n][n];
        int cur = 1;
        int dir = 0;
        int y = 0;
        int x = 0;
        int limit = (n * (n+1))/2;
        while(cur <= limit){
            map[y][x] = cur;
            int ny = y + dy[dir];
            int nx = x + dx[dir];
            if(ny >= n || nx < 0 || nx >= n || map[ny][nx] > 0){
                dir +=1;
                dir %=3;
                ny = y + dy[dir];
                nx = x + dx[dir];
            }
            y = ny;
            x = nx;
            cur++;
        }
        int offset = 1;
        int idx = 0;
        for(int i = 0;i<n;i++){
            for(int j = 0;j<offset;j++){
                answer[idx++] = map[i][j];
            }
            offset++;
        }
        /*
        []
        [][]
        [][][]
        [][][][]*/
        return answer;
    }
}