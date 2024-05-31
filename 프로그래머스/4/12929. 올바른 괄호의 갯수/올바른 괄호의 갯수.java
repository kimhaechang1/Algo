import java.util.*;

class Solution {
    static int result;
    static void dfs(int depth, int sum, int limit){
        if(sum < 0) return;
        if(depth == limit){
            if(sum == 0) result++;
            return;
        }
        dfs(depth+1, sum+1, limit);
        dfs(depth+1, sum-1, limit);
    }
    public int solution(int n) {
        int answer = 0;
        result = 0;
        dfs(0, 0, n*2);
        answer = result;
        return answer;
    }
}