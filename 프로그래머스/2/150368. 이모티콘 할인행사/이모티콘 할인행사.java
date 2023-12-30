import java.util.*;
import java.io.*;
//  2023 12 31 00:48
//  2023 12 31 
class Solution {
    static int maxJ;
    static int maxV;
    static int [] disc;
    static int [] res;
    static int n,m;
    static void dfs(int depth, int[][] u, int [] e){
        if(depth == m){
            int total = 0;
            int cnt = 0;
            for(int [] user : u){
                int sum = 0;
                int dp = user[0];
                int l = user[1];
                //System.out.println(Arrays.toString(user));
                for(int i = 0;i<m;i++){
                    if(res[i] >= dp){
                        sum += (e[i] - (int)(e[i] * ((double)res[i] / 100)));
                    }
                    if(sum >= l){
                        sum = 0;
                        cnt++;
                        break;
                    }
                }
                
                total += sum;
            }
            if(maxJ < cnt){
                maxJ = cnt;
                maxV = total;
            }else if(maxJ == cnt){
                maxV = Math.max(maxV, total);
            }
            //System.out.println("==================");
            return;
        }
        
        for(int i = 0;i<4;i++){
            res[depth] = disc[i];
            dfs(depth+1, u, e);
        }
    }
    public int[] solution(int[][] u, int[] e) {
        // 가입자 최대로 늘리고, 판매액을 그 다음 순서
        // 각 이모티콘 별로 어떻게 할인을 넣을지에 대한 최대값 구하는 문제
        // 사용자는 자기 자신이 갖고있는 퍼센트별로 합리적인 이모티콘을 구매하다가 
        // 자기자신의 기준 금액을 넘어서면 이모티콘 플러스에 가입하게 됨
        // dfs 돌려도 될듯? 각 이모티콘별로 경우의수가 최대 4개뿐임, 이모티콘도 1~7개밖에안됨
        // 이모티콘 플러스 가입자는 모든 이모티콘 구매를 취소 즉, 0원이 됨
        int[] answer = new int[2];
        n = u.length;
        m = e.length;
        maxJ = 0;
        maxV = 0;
        disc = new int[]{10,20,30,40};
        res = new int[m];
        dfs(0, u, e);
        answer[0] = maxJ;
        answer[1] = maxV;
        return answer;
    }
}