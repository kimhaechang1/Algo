import java.util.*;
import java.io.*;

public class Main{
    static int n;
    static int k;
    static int [] coins;
    static StringTokenizer stk;
    public static void main(String [] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        stk = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(stk.nextToken());
        k = Integer.parseInt(stk.nextToken());
        coins = new int[n];
        for(int i = 0;i<n;i++){
            coins[i] = Integer.parseInt(bf.readLine());
        }
        int [] dp = new int[k+1];
        dp[0] = 1;
        for(int i= 0;i<n;i++){
            for(int j= coins[i];j<=k;j++){
                dp[j] += dp[j-coins[i]];
            }
        }
        System.out.print(dp[k]);
    }
}