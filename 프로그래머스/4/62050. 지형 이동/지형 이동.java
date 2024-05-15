import java.util.*;
import java.io.*;
// start 13:51
// end

class Solution {
    static int [] dy = {-1,1,0,0};
    static int [] dx = {0,0,-1,1};
    static int n;
    static int m;
    public int solution(int[][] land, int height) {
        int answer = 0;
        n = land.length;
        m = land[0].length;
        // 다익스트라?
        // 중요한건 사다리를 설치하는것이 중요
        // 사다리를 설치 안하고 넘어갈 수 있다면 그 방법이 우선이 되도록 해야함
        PriorityQueue<int []> pq = new PriorityQueue<>((a, b)->{
            return a[2] - b[2];
        });
        boolean [][] v = new boolean[n][m];
        pq.add(new int[]{0,0, 0});
        int cost = 0;
        while(!pq.isEmpty()){
            int [] now = pq.poll(); 
            if(v[now[0]][now[1]]) continue;
            v[now[0]][now[1]] = true;
            cost += now[2];
            for(int dir = 0; dir<4;dir++){
                int ny = now[0] + dy[dir];
                int nx = now[1] + dx[dir];
            
                if(OOB(ny, nx)) continue;
                
                int cha = Math.abs(land[ny][nx] - land[now[0]][now[1]]);
                if(cha > height){
                    pq.add(new int[]{ny, nx, cha});
                }else if(cha <= height){
                    pq.add(new int[]{ny, nx , 0});
                }
            }
            // if(!canGo){
            //     answer = now[2];
            //     break;
            // }
        }
        answer = cost;
        
        
        return answer;
    }
    static boolean OOB(int y, int x){
        return y >= n || y < 0 || x >= m || x < 0;
    }
}