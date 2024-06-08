import java.util.*;

class Solution {
    static int [] v;
    public int solution(int x, int y, int n) {
        int answer = 0;
        return get(x, y, n);
    }
    static int bfs(int x, int y, int n){
        PriorityQueue<int []> pq =new PriorityQueue<>((a, b)->{
            return a[1] - b[1];
        });
        boolean [] v = new boolean[1000001];
        pq.add(new int[]{x, 0});
        while(!pq.isEmpty()){
            int [] now = pq.poll();
            if(now[0] == y){
                return now[1];
            }
            if(now[0] + n < v.length && !v[now[0]+n]){
                v[now[0]+n] = true;
                pq.add(new int[]{now[0]+n, now[1]+1});
            }
            if(now[0] * 2 < v.length && !v[now[0]*2]){
                v[now[0] * 2] = true;
                pq.add(new int[]{now[0] * 2, now[1]+1});
            }
            if(now[0] * 3 < v.length && !v[now[0]*3]){
                v[now[0]*3] = true;
                pq.add(new int[]{now[0] * 3, now[1]+1});
            }
        }
        return -1;
    }
    static int get(int x, int y, int n){
        int [] dp = new int[1_000_001];
        Arrays.fill(dp, 987654321);
        dp[x] = 0;
        for(int i = x;i<=y;i++){
            if(i % 2 == 0){
                dp[i] = Math.min(dp[i], dp[i/2]+1);
            }
            if(i % 3 == 0){
                dp[i] = Math.min(dp[i], dp[i/3]+1);
            }
            if(i-n > -1 && dp[i-n]+1 < dp[i]){
                dp[i] = dp[i-n]+1;
            }
        }
        return dp[y] == 987654321 ? -1 : dp[y];
    }
}