import java.util.*;
import java.io.*;

public class Main{
    static int n;
    static int m;
    static int k;
    // dfs + dp 라고 하니까 메모이제이션으로 접근해본다.
    // 조심해야 하는건 같은 칸을 반복해서 접근 할 수 있다는 점
    // 하지만 같은칸을 접근하되, 그 칸을 몇번째 알파벳으로 추가한적 있는지에 따라 달라짐
    // 100 * 100 의 그래프이론 문제 + 백트래킹 생각이 들때, 이미 요소가 많다는것을 느꼇다면 메모이제이션을 떠올릴 수 있어야함
    static int [][][] dp;
    static char [][] map;
    static char [] find;
    static StringTokenizer stk;
    static int [] dy = {-1,1,0,0};
    static int [] dx = {0,0,-1,1};
    public static void main(String [] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        stk = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        k = Integer.parseInt(stk.nextToken());
        map = new char[n][m];
        for(int i = 0;i<n;i++){
            map[i] = bf.readLine().toCharArray();
        }
        find = bf.readLine().toCharArray();
        dp = new int[n][m][find.length];
        for(int i = 0;i<n;i++){
            for(int j= 0;j<m;j++){
                Arrays.fill(dp[i][j], -1);
            }
        }
        int cnt = 0;
        for(int i= 0;i<n;i++){
            for(int j = 0;j<m;j++){
                if(find[0] == map[i][j]){
                    char [] make = new char[find.length];
                    int d = 0;
                    make[d] = map[i][j];
                    cnt += dfs(i, j, ++d, make);
                }
            }
        }
        System.out.println(cnt);
    }
    static int dfs(int y, int x, int depth, char [] present){
        //System.out.println("y : "+ y + " x : "+x);
        if(depth == find.length){
            //System.out.println("찍고");
            return 1;
        }
        if(dp[y][x][depth] != -1){
            return dp[y][x][depth];
        }
        dp[y][x][depth] = 0;
        for(int off = 1;off<=k;off++){
            for(int dir = 0;dir<4;dir++){
                int ny = y + dy[dir] * off;
                int nx = x + dx[dir] * off;
                if(OOB(ny, nx) || find[depth] != map[ny][nx]) continue;
                dp[y][x][depth] += dfs(ny, nx, depth+1, present);
                
            }
        }
        return dp[y][x][depth];
    }

    static boolean OOB(int y, int x){
        return y >= n || y < 0 || x >= m || x < 0;
    }

}