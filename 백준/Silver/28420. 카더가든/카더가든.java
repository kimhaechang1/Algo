import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    static StringTokenizer stk;
    static int n;
    static int m;
    static int[][] map;
    static int a, b, c;
    static int ans;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        stk = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        map = new int[n][m];
        stk = new StringTokenizer(bf.readLine());
        a = Integer.parseInt(stk.nextToken());
        b = Integer.parseInt(stk.nextToken());
        c = Integer.parseInt(stk.nextToken());
        for(int i = 0;i<n;i++) {
            stk = new StringTokenizer(bf.readLine());
            for(int j =0;j<m;j++) {
                map[i][j] = Integer.parseInt(stk.nextToken());
            }
        }
        ans = 987654321;
        // shape 에 따라 다르게 설정해야함
        for(int i = 0;i<n;i++) {
            for(int j = 0;j<m;j++) {
                check(i, j);
            }
        }
        System.out.print(ans);
    }
    static void check(int y, int x) {
        //shape 1
        int sum = 0;
        if (!OOB(y + (a - 1), x + (b + c - 1))) {
            for(int i = y;i<y + a;i ++) {
                for(int j = x;j<x + b + c;j++) {
                    sum += map[i][j];
                }
            }
            ans = Math.min(ans, sum);
        }
        if (!OOB(y + ( a + b - 1), x + (c + a - 1))) {
            sum = 0;
            for(int i = y;i<y + a;i ++) {
                for(int j = x;j<x + c;j++) {
                    sum += map[i][j];
                }
            }

            for(int i = y + a;i<y + a + b;i++) {
                for(int j = x + c;j<x + c + a;j++) {
                    sum += map[i][j];
                }
            }
            ans = Math.min(ans, sum);
        }
        if (!OOB(y + ( a + c - 1), x + (b + a - 1))) {
            sum = 0;
            for(int i = y;i<y + a;i ++) {
                for(int j = x;j<x + b;j++) {
                    sum += map[i][j];
                }
            }

            for(int i = y + a;i<y + a + c;i++) {
                for(int j = x + b;j<x + b + a;j++) {
                    sum += map[i][j];
                }
            }
            ans = Math.min(ans, sum);
        }

    }
    static boolean OOB(int y, int x ){
        return y >=n || y < 0 || x >= m || x < 0;
    }


}