import java.util.*;
import java.io.*;

public class Main {
    static StringTokenizer stk;
    static char [][] map;
    static int w;
    static int h;
    static int [][] start;
    public static void main(String [] args) throws Exception{
        BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
        stk = new StringTokenizer(bf.readLine());
        w = Integer.parseInt(stk.nextToken());
        h = Integer.parseInt(stk.nextToken());
        map = new char[h][w];
        start = new int[2][2];
        int idx = 0;
        for(int i = 0;i<h;i++){
            char [] frags = bf.readLine().toCharArray();
            for(int j = 0;j<w;j++){
                map[i][j] = frags[j];
                if(map[i][j] == 'C'){
                    start[idx][0] = i;
                    start[idx++][1] = j;
                }
            }
        }
        System.out.print(bfs());
    }
    static int bfs(){
        int [][][] v = new int[h][w][4];
        int [] dy = {-1,1,0,0};
        int [] dx = {0,0,-1,1};
        Queue<int []> pq = new ArrayDeque<>();
        int sy = start[0][0];
        int sx = start[0][1];
        for(int dir = 0;dir<4;dir++){
            int ny = sy + dy[dir];
            int nx = sx + dx[dir];
            if(OOB(ny, nx) || map[ny][nx] == '*') continue;
            v[ny][nx][dir] = 0;
            pq.add(new int[]{ny, nx, dir, 0});
        }
        int res = 987654321;
        for(int k = 0;k<4;k++){
            for(int i = 0;i<h;i++){
                for(int j = 0;j<w;j++){
                    v[i][j][k] = 987654321;
                }
            }
        }
        while(!pq.isEmpty()){
            int [] now = pq.poll();
            if(now[0] == start[1][0] && now[1] == start[1][1]){
                res = Math.min(res, now[3]);
                continue;
            }
            int dir = now[2];
            int ny;
            int nx;
            //  / 방향으로 반사 시키는 경우
            int diffDir = getRightDir(dir);
            ny = now[0] + dy[diffDir];
            nx = now[1] + dx[diffDir];
            if(!OOB(ny, nx) && map[ny][nx] !='*' && v[ny][nx][diffDir] > now[3]+1){
                v[ny][nx][diffDir] = now[3]+1;
                pq.add(new int[]{ny, nx, diffDir, now[3] + 1});
            }
            // \ 방향으로 반사시키는 경우
            diffDir = getRevDir(dir);
            ny = now[0] + dy[diffDir];
            nx = now[1] + dx[diffDir];
            if(!OOB(ny, nx) && map[ny][nx] !='*' && v[ny][nx][diffDir] > now[3] + 1) {
                v[ny][nx][diffDir] = now[3]+1;
                pq.add(new int[]{ny, nx, diffDir, now[3] + 1});
            }
            // 원래 방향대로 나아가는 경우
            ny = now[0] + dy[dir];
            nx = now[1] + dx[dir];
            if(OOB(ny, nx) || map[ny][nx] =='*' || v[ny][nx][dir] <= now[3]) continue;
            v[ny][nx][dir] = now[3];
            pq.add(new int[]{ny, nx, dir, now[3]});
        }
        return res;
    }
    static boolean OOB(int y,int x){
        return y >= h || y < 0 || x >=w || x < 0;
    }
    static int getRightDir(int dir){
        //  "/"
        switch(dir){
            case 0 :
                return 3;
            case 1:
                return 2;
            case 2:
                return 1;
            default:
                return 0;
        }
    }
    static int getRevDir(int dir){
        //  "\"
        switch(dir){
            case 0 :
                return 2;
            case 1:
                return 3;
            case 2:
                return 0;
            default:
                return 1;
        }
    }
}
