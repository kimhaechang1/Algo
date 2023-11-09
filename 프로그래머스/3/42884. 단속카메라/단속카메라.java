import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        Arrays.sort(routes, (o1, o2)->{
            if(o1[1] == o2[1]){
                return o2[0] - o1[0];
            }
            return o1[1] - o2[1];
        });
        int cnt = 1;
        int end = routes[0][1];
        for(int i = 1;i<routes.length;i++){
            if(routes[i][0] > end ){
                cnt++;
                end = routes[i][1];
            }
        }
        return cnt;
    }
}