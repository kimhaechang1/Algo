import java.util.*;
import java.io.*;

public class Main{
    static StringTokenizer stk;
    static int n;
    static int [] arr;
    public static void main(String [] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        stk = new StringTokenizer(bf.readLine());
        arr = new int[n+1];
        for(int i = 1;i<n+1;i++){
            arr[i] = Integer.parseInt(stk.nextToken());
        }
        int [] dp = new int[n+1];
        dp[0] = -999999999;
        int max = Integer.MIN_VALUE;
        for(int i= 1;i<n+1;i++){
            dp[i] = Math.max(dp[i-1]+arr[i], arr[i]);
            max = Math.max(max, dp[i]);
        }
        System.out.println(max);
    }
}