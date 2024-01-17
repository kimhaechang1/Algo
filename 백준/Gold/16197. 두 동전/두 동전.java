import java.util.*;
import java.io.*;

public class Main{
    static StringTokenizer stk;
    static int n;
    static int m;
    static int min;
    static char [][] map;
    static int [] dy = {-1,1,0,0};
    static int [] dx = {0,0,-1,1};
    public static void main(String[] args) throws Exception{
        BufferedReader bf =new BufferedReader(new InputStreamReader(System.in));
        stk = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        map = new char[n][m];
        min = Integer.MAX_VALUE;
        int idx = 0;
        int [][] poses = new int[2][2];
        for(int i = 0;i<n;i++){
            char [] ch = bf.readLine().toCharArray();
            for(int j = 0;j<m;j++){
                map[i][j] = ch[j];
                if(ch[j] == 'o'){
                    poses[idx][0] = i;
                    poses[idx++][1] = j;
                }
            }
        }
        boolean [][][][] v= new boolean[n][m][n][m];
        v[poses[0][0]][poses[0][1]][poses[1][0]][poses[1][1]] = true;
        dfs(0, poses[0][0], poses[0][1], poses[1][0], poses[1][1], v);
        System.out.print(min == Integer.MAX_VALUE ? -1 : min);
    }

    static void dfs(int depth, int c1y, int c1x, int c2y, int c2x, boolean [][][][] v){

        if(min < depth) {
            return;
        }
        if(depth == 10){
            return;
        }
        boolean [][][][] cp = copy(v);
        for(int dir = 0;dir<4;dir++){
            int n1y = c1y + dy[dir];
            int n1x = c1x + dx[dir];
            if(!OOB(n1y, n1x) && map[n1y][n1x] == '#'){
                n1y = c1y;
                n1x = c1x;
            }
            int n2y = c2y + dy[dir];
            int n2x = c2x + dx[dir];
            if(!OOB(n2y, n2x) && map[n2y][n2x] == '#'){
                n2y = c2y;
                n2x = c2x;
            }
            if(OOB(n1y, n1x) && OOB(n2y, n2x)) continue;

            if((OOB(n1y, n1x) && !OOB(n2y, n2x)) || (!OOB(n1y, n1x) && OOB(n2y, n2x))){
                min = Math.min(depth+1, min);
                continue;
            }

            if(cp[n2y][n2x][n1y][n1x]) continue;
            cp[n2y][n2x][n1y][n1x] = true;
            dfs(depth+1, n2y, n2x, n1y, n1x, cp);
        }
    }
    static boolean OOB(int y, int x){
        return y >= n|| y < 0 || x >= m || x < 0;
    }
    static boolean [][][][] copy(boolean [][][][] pres){
        boolean [][][][] cp = new boolean[n][m][n][m];
        for(int i= 0;i<n;i++){
            for(int j = 0;j<m;j++){
                for(int k = 0;k<n;k++){
                    for(int p=0;p<m;p++){
                        cp[i][j][k][p] = pres[i][j][k][p];
                    }
                }
            }
        }
        return cp;
    }

}