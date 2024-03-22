import java.util.*;
import java.io.*;

public class Main{
    static StringTokenizer stk;
    static int n;
    static long [][] map;
    static int m;

    public static void main(String [] args) throws Exception{
        BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
        stk = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        map =new long[n][n];
        for(int i = 0;i<n;i++){
            stk = new StringTokenizer(bf.readLine());
            for(int j= 0;j<n;j++){
                map[i][j] = Integer.parseInt(stk.nextToken());
            }
        }
        // floyd - washall

        for(int k = 0;k<n;k++){
            for(int i = 0;i<n;i++){
                if(i == k) continue;
                for(int j = 0;j<n;j++){
                    if(i == j) continue;
                    if(k == j) continue;
                    map[i][j] = Math.min(map[i][j], map[i][k]+map[k][j]);
                }
            }
        }
        StringBuilder sb =new StringBuilder();
        for(int i = 0;i<m;i++){
            stk = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());
            int c = Integer.parseInt(stk.nextToken());
            if(map[a-1][b-1] <= c){
                sb.append("Enjoy other party").append("\n");
            }else{
                sb.append("Stay here").append("\n");
            }
        }
        System.out.print(sb);
    }
}