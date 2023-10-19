import java.util.*;
class Solution {
    public int solution(int n, int[][] results) {
        // floyd 문제
        int [][] map = new int[n+1][n+1];
        for(int i= 1;i<n+1;i++){
            for(int j= 1;j<n+1;j++){
                map[i][j] = 987654321;
            }
        }
        for(int i = 0;i<results.length;i++){
            int a = results[i][0];
            int b = results[i][1];
            map[a][b] = 1;
        }
        for(int k= 1;k<n+1;k++){
            for(int i = 1;i<n+1;i++){
                if(k == i) continue;
                for(int j = 1;j<n+1;j++){
                    if(j == k) continue;
                    if(i == j) continue;
                    map[i][j] = Math.min(map[i][j], map[i][k]+map[k][j]);            
                }
            }
        }
        
        int [] cnt = new int[n+1];
        int c = 0;
        for(int i= 1;i<n+1;i++){
            for(int j= 1;j<n+1;j++){
                if(i != j && map[i][j] != 987654321) cnt[i]++;
            }
            for(int j= 1;j<n+1;j++){
                if(i != j && map[j][i] != 987654321) cnt[i]++;
            }
            if(cnt[i] == n-1) c++;
        }
        System.out.println(Arrays.toString(cnt));
        return c;
    }
}