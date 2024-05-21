import java.util.*;
import java.io.*;

public class Main{
    static int n;
    static long [][] dp;
    public static void main(String [] args) throws Exception{
        BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        // 각 자리수마다 올 수 있는 수는 0~9
        // 하지만 첫째자리수는 1~9이다
        // 생각해보면 마지막 자리수에 따라 올 수 있는 수가 거의 정해져있다
        // 2의 경우 이전 자리수가 1이거나 3이어야 한다.
        // 3의 경우 이전 자리수가 2이거나 4이어야 한다.
        // 따라서 이전 값을 가지고 쌓아올라가므로 dp 점화식이 가능하다.
        // 그런데 0의 경우 항상 이전자리가 1일수 밖에 없고, 자리수에 9가 오려면 이전자리수가 8일수 밖에 없다. 이 경우만 예외처리한다.
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

        
    }
}