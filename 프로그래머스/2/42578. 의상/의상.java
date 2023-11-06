import java.util.*;

class Solution {
    static HashMap<String, Integer> map1;
    static ArrayList<String> list;
    static boolean [] sel;
    static int answer;
    static HashSet<String> set;
    static int N;
    public int solution(String[][] clothes) {
        map1 = new HashMap<>();
        list = new ArrayList<>();
        for(String []c : clothes){
            if(map1.get(c[1]) == null)
                map1.put(c[1], 1);
            else {
                int t = map1.get(c[1]);
                map1.put(c[1],++t);
            }
        }
        Iterator<Integer> iter = map1.values().iterator();
        
        answer = 1;
        while(iter.hasNext()){
            answer *= (iter.next() +1);
        }
        return answer-1;
    } 
}