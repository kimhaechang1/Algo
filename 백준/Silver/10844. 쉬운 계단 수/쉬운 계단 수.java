import java.util.*;
import java.io.*;

public class Main{
    static int n;
    static long [][] dp;
    public static void main(String [] args) throws Exception{
        BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        // 1씩 차이가 나야한다.
        // 56565
        // 각 자리수마다 올 수 있는 수는 0~9
        // 하지만 첫째자리수는 1~9이다
        dp = new long[10][n+1];

        for(int i = 1;i<=9;i++){
            // 한자리수는 모두 계단수가 될 수 있다.
            dp[i][1] = 1;
        }
        for(int i = 2;i<n+1;i++){
            for(int j = 0;j<=9;j++){
                if(j == 0){
                    dp[j][i] = ((dp[j][i] % 1_000_000_000) + (dp[1][i-1] % 1_000_000_000)) % 1_000_000_000;
                }else if(j == 9){
                    dp[j][i] = ((dp[j][i] % 1_000_000_000) + (dp[8][i-1] % 1_000_000_000)) % 1_000_000_000;
                }else{
                    dp[j][i] = ((dp[j][i] % 1_000_000_000) + (dp[j-1][i-1] % 1_000_000_000)) % 1_000_000_000;
                    dp[j][i] = ((dp[j][i] % 1_000_000_000) + (dp[j+1][i-1] % 1_000_000_000)) % 1_000_000_000;
                }
            }
        }
        long sum = 0;
        for(int i= 0;i<=9;i++) sum = ((sum % 1_000_000_000) + (dp[i][n] % 1_000_000_000)) % 1_000_000_000;
        System.out.print(sum);

        // 2 -> 1 또는 3
        // 3 -> 2 또는 4
        // 길이가 1인 계단수: 1, 2, 3, ..., 9 -> 9개
        // 길이가 2인 계단수: 12, 21, 23, ..., 98 -> 17개
        // 길이가 3인 계단수: 121, 123, 232, 234, 343, 345, 321, 323, 431 232, 234,
    }
}