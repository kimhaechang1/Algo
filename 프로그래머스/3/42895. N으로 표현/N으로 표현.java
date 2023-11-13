import java.util.*;
class Solution {
    static int min;
    public int solution(int N, int number) {
        int answer = 0;
        min = Integer.MAX_VALUE;
        dfs(N, number, 0, 0);
        if(min == Integer.MAX_VALUE){
            return -1;
        }else{
            return min;
        }
    }
    static void dfs(int N, int target, int depth, int current){
        if(depth > 8){
            return;
        }        
        if(current == target){
            min = Math.min(min, depth);
            return;
        }
        int temp = 0;
        for(int i= 0;i<8;i++){
            if(depth + i < 8){
                temp = temp * 10 + N;
                dfs(N, target, depth+i+1, current+temp);
                dfs(N, target, depth+i+1, current-temp);
                dfs(N, target, depth+i+1, current*temp);
                dfs(N, target, depth+i+1, current/temp);
            }
        }
    }
}