import java.util.*;
import java.io.*;

public class Main{
    static int n;
    static int m;
    static StringTokenizer stk;
    static char [][] map;
    static int [] dy = {-1,1,0,0};
    static int [] dx = {0,0,-1,1};
    static int sy;
    static int sx;
    static int [][] c;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        stk = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        map= new char[n][m];
        int idx = 1;
        c = new int[3][2];
        for(int i = 0;i<n;i++){
            char [] t = bf.readLine().toCharArray();
            for(int j = 0;j<m;j++){
                map[i][j] = t[j];
                if(map[i][j] == 'S'){
                    sy = i;
                    sx = j;
                }else if(map[i][j] == 'C'){
                    c[idx][0] = i;
                    c[idx++][1] = j;
                }
            }
        }
        bfs();
    }
    static void bfs(){
        Queue<int []> queue = new ArrayDeque<>();
        queue.add(new int[]{ sy, sx, 0, -1, 0, 0});
        boolean [][][][] v = new boolean[n][m][4][3];
        int min = Integer.MAX_VALUE;
        t : while(!queue.isEmpty()) {
            int[] now = queue.poll();
            int y = now[0];
            int x = now[1];
            int ccnt = now[2];
            int dir = now[3];
            int time = now[4];
            int ccate = now[5];
            for (int d = 0; d < 4; d++) {
                //if (isRev(dir, d)) continue;
                if (d == dir) continue;
                int ny = y + dy[d];
                int nx = x + dx[d];
                if (OOB(ny, nx) || map[ny][nx] == '#' || v[ny][nx][d][ccate]) continue;
                if (map[ny][nx] == 'C') {


                    int nxt = isSame(ny, nx, ccate);

                    if(nxt == -1){

                        queue.add(new int[]{ny, nx, ccnt, d, time+1, ccate});
                    }else{
                        if(ccnt+1 == 2){
                            min = Math.min(min, time+1);
                            break t;
                        }
                        v[ny][nx][d][ccate] = true;
                        queue.add(new int[]{ny, nx, ccnt+1, d, time+1, nxt});
                    }
                } else {
                    if(ny == 2 && nx == 0 && ccnt == 0 &&  ccate == 2){
                        System.out.println(Arrays.toString(now));
                    }
                    v[ny][nx][d][ccate] = true;
                    queue.add(new int[]{ny, nx, ccnt, d, time+1, ccate});
                }
            }
        }
        System.out.print(min == Integer.MAX_VALUE ? -1 : min);

    }
//    static boolean isRev(int dir, int nd){
//        if(dir == 0 && nd == 1) return true;
//        if(dir == 1 && nd == 0) return true;
//        if(dir == 2 && nd == 3) return true;
//        if(dir == 3 && nd == 2) return true;
//        return false;
//    }
    static boolean OOB(int y, int x){
        return y >= n || y < 0 || x >= m || x < 0;
    }
    static int isSame(int y, int x, int ccate){
        int res = 0;
        for(int i = 1;i<3;i++){
            if(c[i][0] == y && c[i][1] == x){
                res = i;
                break;
            }
        }
        if(res == ccate) return -1;
        return res;
    }
}
