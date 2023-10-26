import java.util.*;

class Solution {
    static TreeSet<String> tset;
    static String [] srr = {"","A","E","I","O","U"};
    public int solution(String word) {
        int answer = 1;
        tset = new TreeSet<>();
        dfs(0, "");
        Iterator<String>iter = tset.iterator();
        while(iter.hasNext()){
            String p = iter.next();
            if(word.equals(p)) break;
            answer++;
        }
        return answer;
    }
    static void dfs(int depth, String str){
        if(depth == 5){
            if(str.equals("")) return;
            tset.add(str);
            return;
        }
        for(int i = 0;i<srr.length;i++){
           dfs(depth+1, str + srr[i]);
        }
    }
    
}