import java.util.*;
import java.io.*;

public class Main{
    static int [][] map;
    static int m;
    static int n;
    static int [] dy = {-1,1,0,0,-1,1,-1,1};
    static int [] dx = {0,0,-1,1,1,-1,-1,1};
    static boolean [][] v;
    static StringTokenizer stk;
    public static void main(String [] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        stk = new StringTokenizer(bf.readLine());
        m = Integer.parseInt(stk.nextToken());
        n = Integer.parseInt(stk.nextToken());
        map = new int[m][n];
        for(int i=0;i<m;i++){
            stk = new StringTokenizer(bf.readLine());
            for(int j = 0;j<n;j++){
                map[i][j] = Integer.parseInt(stk.nextToken());
            }
        }
        v = new boolean[m][n];
        int cnt = 0;
        for(int i = 0;i<m;i++){
            for(int j = 0;j<n;j++){
                if(v[i][j]) continue;
                if(map[i][j] == 0) continue;
                v[i][j] = true;
                cnt++;
                bfs(i, j);
            }
        }
        System.out.print(cnt);
    }
    static void bfs(int y, int x){
        Queue<int []> queue = new ArrayDeque<>();
        queue.add(new int[]{y, x});
        while(!queue.isEmpty()){
            int [] now = queue.poll();
            for(int dir = 0;dir<8;dir++){
                int ny = now[0] + dy[dir];
                int nx = now[1] + dx[dir];
                if(OOB(ny, nx) || v[ny][nx] || map[ny][nx] != 1) continue;
                v[ny][nx] = true;
                queue.add(new int[]{ny, nx});
            }
        }
    }
    static boolean OOB(int y, int x){
        return y >= m || y < 0 || x >= n || x < 0;
    }
}