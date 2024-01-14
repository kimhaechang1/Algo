// start 2024 01 14 10:30
// end 2024 01 14 11:41
import java.util.*;
import java.io.*;

class Solution {
    public static int solution(int[] info, int[][] edges) {
        // 내가 양을 몇마리 가지고 있고 늑대를 몇마리 가지고 있는 채로 노드에 방문했는지가
        // 달라져야 한다.
        // 0 양, 1 늑대
        int max = 1;
        int sc = 0;
        int wc = 0;
        int n = info.length;
        for(int i = 0;i<n;i++){
            if(info[i] == 0) sc++;
            else wc++;
        }
        int visit = 1;
        int sum = 0;
        for(int i= 0;i<n;i++) {
        	sum += (1 << i);
        }
        boolean [][][][] v = new boolean[n][sc+1][wc+1][sum+1];
        
        Queue<int []> queue = new ArrayDeque<>();
        queue.add(new int[]{0,1,0, visit}); 
        // 현재 노드, 현재 양 개수, 늑대 수, 지나온 노드
        v[0][1][0][visit] = true;
        while(!queue.isEmpty()){
            int [] now = queue.poll();
            max = Math.max(max, now[1]);
            for(int i = 0;i<n-1;i++){
                int v1 = edges[i][0];
                int v2 = edges[i][1];
                if(v1 == now[0]){
                    if(((now[3] & (1 << v2)) != 0) &&  !v[v2][now[1]][now[2]][now[3]]){
                        v[v2][now[1]][now[2]][now[3]] = true;
                        queue.add(new int[]{v2, now[1], now[2], now[3]});
                    }
                    if(((now[3] & (1 << v2)) == 0) && info[v2] == 0 && !v[v2][now[1]+1][now[2]][((now[3] | (1 << v2)))] ){
                        v[v2][now[1]+1][now[2]][((now[3] | (1 << v2)))] = true;
                        queue.add(new int[]{v2, now[1]+1, now[2], (now[3] | (1 << v2))});
                    }
                    if(((now[3] & (1 << v2)) == 0) && info[v2] == 1 && now[2]+1 < now[1] && !v[v2][now[1]][now[2]+1][((now[3] | (1 << v2)))]){
                        v[v2][now[1]][now[2]][((now[3] | (1 << v2)))] = true;
                        queue.add(new int[]{v2, now[1], now[2]+1, (now[3] | (1 << v2))});
                    }
                }else if(v2 == now[0]){
                    if(((now[3] & (1 << v1)) != 0) &&  !v[v1][now[1]][now[2]][((now[3] | (1 << v1)))]){
                        v[v1][now[1]][now[2]][((now[3] | (1 << v1)))] = true;
                        queue.add(new int[]{v1, now[1], now[2], now[3]});
                    }
                    if(((now[3] & (1 << v1)) == 0) && info[v1] == 0 && !v[v1][now[1]+1][now[2]][((now[3] | (1 << v1)))]){
                        v[v1][now[1]+1][now[2]][((now[3] | (1 << v1)))] = true;
                        queue.add(new int[]{v1, now[1]+1, now[2], (now[3] | (1 << v1))});
                    }
                    if(((now[3] & (1 << v1)) == 0) && info[v1] == 1 && now[2]+1 < now[1] && !v[v1][now[1]][now[2]+1][((now[3] | (1 << v1)))]){
                        v[v1][now[1]][now[2]][((now[3] | (1 << v1)))] = true;
                        queue.add(new int[]{v1, now[1], now[2]+1, (now[3] | (1 << v1))});
                    }
                }
            }
            
        }
        int answer = max;
        return answer;
    }
}