// start 09:04
// end
import java.util.*;

class Solution {
    static String [] res;
    static int n;
    static String [] users;
    static String [] bans;
    static int cnt;
    static HashSet<HashSet<String>> set;
    public int solution(String[] user_id, String[] banned_id) {
        int answer = 0;
        res = new String[banned_id.length];
        users = user_id;
        bans = banned_id;
        n = users.length;
        cnt = 0;
        set = new HashSet<>();
        boolean [] v = new boolean[n];
        boolean [] useBan = new boolean[bans.length];
        // 순열로 재시도
        // 만들어진 문자열 셋의 개수가 곧 정답이다.
        dfs(0, new HashSet<>());
        return set.size();
    }
    static void dfs(int depth, HashSet<String> tempSet){
        if(depth == bans.length){
            set.add(tempSet);
            return;
        }
        for(int i = 0;i<users.length;i++){
            if(tempSet.contains(users[i])) continue;
            if(!isCheck(users[i], bans[depth]))continue;
            tempSet.add(users[i]);
            dfs(depth+1, new HashSet<>(tempSet));
            tempSet.remove(users[i]);
        }
    }
    static boolean isCheck(String u, String v){
        if(u.length() != v.length()) return false;
        char [] me = u.toCharArray();
        char [] b = v.toCharArray();
        for(int i = 0;i<me.length;i++){
            if(b[i] == '*') continue;
            if(me[i] != b[i]) return false;
        }
        return true;
    }
}