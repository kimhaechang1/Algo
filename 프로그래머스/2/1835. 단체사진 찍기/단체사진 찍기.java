// start 15:39
// end
import java.util.*;


class Solution {
    static boolean [] v;
    static int [] res;
    static int cnt;
    static int p;
    static char [] crr = {'A','C','F','J','M','N','R','T'};
    static HashMap<Character, Integer> map;
    public int solution(int n, String[] data) {
        cnt = 0;
        map = new HashMap<>();
        res = new int[crr.length];
        v = new boolean[crr.length];
        dfs(0, n, data);
        int answer = cnt;
        return answer;
    }
    static void dfs(int depth, int len, String [] condition){
        if(depth == crr.length){
            for(int i = 0;i<crr.length;i++){
                map.put(crr[i], res[i]);
            }
            boolean canGo = true;
            for(int i = 0;i<len;i++){
                char condi = condition[i].charAt(3);
                int term = condition[i].charAt(4) - '0';
                String charCondi = condition[i].substring(0, 3);
                String [] twoAlpa = charCondi.split("~");
                char left = twoAlpa[0].charAt(0);
                char right = twoAlpa[1].charAt(0);
                int realTerm = Math.abs(map.get(left) - map.get(right))-1;
                if(condi == '='){
                    if(term != realTerm){
                        canGo = false;
                        break;
                    }
                }else if(condi == '<'){
                    if(realTerm >= term){
                        canGo = false;
                        break;
                    }
                }else{
                    if(realTerm <= term){
                        canGo = false;
                        break;
                    }
                }
            }
            if(canGo) cnt++;
            return;
        }
        for(int i= 0;i<crr.length;i++){
            if(v[i]) continue;
            v[i] = true;
            int temp = res[depth];
            res[depth] = i;
            dfs(depth+1, len, condition);
            res[depth] = temp;
            v[i] = false;
        }
    }    
}