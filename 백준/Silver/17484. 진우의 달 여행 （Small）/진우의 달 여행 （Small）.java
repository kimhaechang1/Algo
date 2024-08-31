import java.io.*;
import java.util.*;

public class Main{
    static int n, m;
    static int[][] map;
    static StringTokenizer stk;
    static int[] dy = {1,1,1};
    static int MAX = 987654321;
    static int[] dx = {-1,0,1};
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        stk = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());

        map = new int[n][m];
        for(int i = 0;i<n;i++) {
            stk = new StringTokenizer(bf.readLine());
            for(int j =0;j<m;j++) {
                map[i][j] = Integer.parseInt(stk.nextToken());
            }
        }

        int[][][] dp = new int[n][m][3];

        for(int j = 0;j<m;j++ ){
            for(int k = 0;k<3;k++) {
                dp[0][j][k] = map[0][j];
            }
        }
        for(int i = 1;i<n;i++) {
            for(int j= 0;j<m;j++) {
                for(int k = 0;k<3;k++) {
                    dp[i][j][k] = MAX;
                }
            }
        }

        for(int i = 1;i<n;i++) {
            for(int j= 0;j<m;j++) {
                // 왼쪽 위에서 내려오는 경우
                if(j - 1 >= 0) {
                    dp[i][j][0] = map[i][j] + Math.min(dp[i][j][0], Math.min(dp[i-1][j-1][1], dp[i-1][j-1][2]));
                }
                // 위에서 아래로 내려오는 경우

                dp[i][j][1] = map[i][j] + Math.min(dp[i][j][1], Math.min(dp[i-1][j][0], dp[i-1][j][2]));

                // 오른쪽 위에서 내려오는 경우
                if(j + 1 < m) {
                    dp[i][j][2] = map[i][j] + Math.min(dp[i][j][2], Math.min(dp[i-1][j+1][0], dp[i-1][j+1][1]));
                }
            }
        }
        // 마지막 해 도출
        int min = MAX;
        for(int i= 0;i<m;i++) {
            min = Math.min(min, Math.min(dp[n-1][i][0], Math.min(dp[n-1][i][1], dp[n-1][i][2])));
        }
        System.out.println(min);
    }
}