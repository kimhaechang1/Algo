import java.util.*;
import java.io.*;
public class Main {
    static int max;
    static StringTokenizer stk;
    static char [][] map;
    static int [] dy = {-1,0,1,0};
    static int [] dx = {0,1,0,-1};
    static int N;
    static int M;

    static void dfs(int nowi, int nowj, boolean [] nowused, int cnt){
        for(int i= 0;i<4;i++){
            int ny = nowi+dy[i];
            int nx = nowj+dx[i];
            if(ny>=N || ny<0 || nx>=M || nx<0 || nowused[map[ny][nx]-'A']){
                if(max < cnt){
                    max = cnt;
                }
                continue;
            }
            nowused[map[ny][nx]-'A'] = true;
            dfs(nowi+dy[i], nowj+dx[i], nowused, cnt+1);
            nowused[map[ny][nx]-'A'] = false;
        }
    }


    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        // ny, nx에 적힌 칸은 이전에 밟은 칸과는 알파벳이 달라야한다.
        // BFS로 영역탐색을 하며 최대한 나아갈수 있는 칸의 개수를 셈하고 최대값을 갱신한다.
        // 생각해보니 각 상태공간에서 방문체크는 서로 독립적이어야 한다. 따라서 BFS로 하기힘듬
        // 중요한 점은 시작칸인 0,0도 카운트에 포함하게 된다.
        // 방문체크가 필요하다.
        stk = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());
        map = new char[N][M];
        for(int i= 0;i<N;i++){
            String t = bf.readLine();
            for(int j=0;j<M;j++){
                map[i][j] = t.charAt(j);
            }
        }
        boolean[]isused = new boolean[27];
        max = Integer.MIN_VALUE;
        isused[map[0][0]-'A'] = true;
        dfs(0, 0,isused,1);
        System.out.println(max);
    }
}