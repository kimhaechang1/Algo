// start 19:49
// end 20:10
import java.util.*;

class Solution {
    static int [] answer;
    public int[] solution(int[][] arr) {
        // 분할 정복
        answer = new int[2];
        dfs(0,0,arr.length,arr);
        return answer;
    }
    static void dfs(int sy, int sx, int length, int [][] map){
        
        if(length == 1){
            answer[map[sy][sx]]++;
            return;
        }
        int sum = check(sy, sx, length, map);
        if(sum == (length * length) || sum == 0){
            answer[map[sy][sx]]++;
            return;
        }else{
            int nextLen = length / 2;
            dfs(sy, sx, nextLen, map);
            dfs(sy+nextLen,sx,nextLen, map);
            dfs(sy,sx+nextLen,nextLen, map);
            dfs(sy+nextLen,sx+nextLen,nextLen, map);
        }
        

    }
    static int check(int sy, int sx, int length, int [][] map){
        int sum = 0;
        for(int i = sy;i<sy+length;i++){
            for(int j = sx;j<sx+length;j++){
                sum += map[i][j];
            }
        }
        return sum;
    }
}