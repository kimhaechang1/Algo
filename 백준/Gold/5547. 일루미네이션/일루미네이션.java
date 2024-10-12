import java.util.*;
import java.io.*;

public class Main {
    static StringTokenizer stk;
    static int n;
    static int m;
    static int[][] map;
    static int[] d1y = {0,0,-1,-1,1,1};
    static int[] d1x = {1,-1,0,1,0,1};
    static int[] d2y = {0,0,-1,-1,1,1};
    static int[] d2x = {1,-1,-1,0,-1,0};
    static boolean[][] v;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        stk = new StringTokenizer(bf.readLine());  
        m = Integer.parseInt(stk.nextToken());
        n = Integer.parseInt(stk.nextToken());
        map = new int[n+2][m+2];
        
        for(int i = 1;i<n+1;i++) {
            stk = new StringTokenizer(bf.readLine());
            for(int j= 1;j<m+1;j++) {
                map[i][j] = Integer.parseInt(stk.nextToken());
            }
        }
        // bfs 돌려서 6방검사 했을 때, 1이 적혀있는 방면의 수의 합
        // 왼쪽 오른쪽은 x좌표 +1, -1
        // y가 짝수인
        // 왼쪽위는 y, x 둘다 감소, 오른쪽 위는 y만 감소
        // 왼쪽아래는 y증가 x감소, 오른쪽 아래는 y만 증가
        // y가 홀수인
        // 왼쪽 위 y만 감소, 오른쪽 위는 y감소 x증가
        // 왼쪽 아래는 y만 증가, 오른쪽 아래는 y증가 x증가
        int ans= 0;
        v = new boolean[n+2][m+2];
        for(int i = 0;i<n+2;i++) {
            for(int j = 0;j<m+2;j++) {
                if(v[i][j] || map[i][j] == 1) continue;
                v[i][j] = true;
                ans += bfs(i, j);
                System.out.print(ans);
                return;
            }
        }
        
    }
    static int bfs(int sy, int sx){
        Queue<int[]> queue = new ArrayDeque<>();
        int result = 0;
        queue.add(new int[]{sy, sx});
        while(!queue.isEmpty()) {
            int[] now = queue.poll();
            if ((now[0] & 1) == 1) {
                // 홀수
                
                for(int dir = 0;dir<6;dir++) {
                    int ny= now[0] + d1y[dir];
                    int nx= now[1] + d1x[dir];
                    if(OOB(ny, nx) || v[ny][nx]) continue;
                    if(map[ny][nx] == 1) {
                        result++;
                    } else {
                        v[ny][nx] = true;
                        queue.add(new int[]{ny, nx});
                    }
                }
            } else {
                
                for(int dir = 0;dir<6;dir++) {
                    int ny= now[0] + d2y[dir];
                    int nx= now[1] + d2x[dir];
                    if(OOB(ny, nx) || v[ny][nx]) continue;
                    if(map[ny][nx] == 1) {
                        result++;
                    } else {
                        v[ny][nx] = true;
                        queue.add(new int[]{ny, nx});
                    }
                }
            }
        }
        return result;
    }
    static boolean OOB(int y, int x) {
        return y > n+1 || y < 0 || x > m +1 || x < 0;
    }
}