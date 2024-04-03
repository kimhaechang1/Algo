// start 12:41
// end
import java.util.*;

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        // floyd washall
        long [][] dp = new long[n+1][n+1];
        for(int i= 1;i<n+1;i++){
            Arrays.fill(dp[i], 987654321);
        }
        for(int i =0;i<fares.length;i++){
            int start = fares[i][0];
            int end = fares[i][1];
            int weight = fares[i][2];
            dp[start][end] = weight;
            dp[end][start] = weight;
        }
        for(int k = 1;k<n+1;k++){
            for(int i = 1;i<n+1;i++){
                if(k == i) continue;
                for(int j = 1;j<n+1;j++){
                    if(k == j) continue;
                    if(i == j) continue;
                    dp[i][j] = Math.min(dp[i][k]+dp[k][j], dp[i][j]);
                }
            }
        }
        // A 따로 가는경우
        // B 따로 가는경우
        // A-B가 함께 가는경우
        // 모든 경우를 다 봐보면 되지않을까
        long depart = dp[s][a] + dp[s][b];
        long min = 987654321;
        for(int i = 1;i<=n;i++){
            if(i == s) continue;
            long commonSum = dp[s][i];
            long aSum = dp[i][a];
            if(i == a){
                aSum = 0;
            }
            long bSum = dp[i][b];
            if(i == b){
                bSum = 0;
            }
            min = Math.min(commonSum + aSum + bSum, min);
        }
        System.out.println("min : "+min);
        // System.out.println("s -> a = "+dp[s][a]);
        // System.out.println("s -> b = "+dp[s][b]);
        // for(int i= 1;i<n+1;i++){
        //     for(int j= 1;j<n+1;j++){
        //         if(dp[i][j] == 987654321) System.out.print("0 ");
        //         else System.out.print(dp[i][j]+" ");
        //     }
        //     System.out.println();
        // }
        
        int answer = (int)Math.min(min, depart);
        return answer;
    }
}