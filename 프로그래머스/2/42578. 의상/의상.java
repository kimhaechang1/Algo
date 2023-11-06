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
        // 옷을 고르지 않는 경우를 +1 해주고 전체 경우의 수 에서 공집합 경우를 빼주면 정답
        // 왜냐하면 각 카테고리 별로 의상의 이름이 전혀 겹치지 않는다는것이 보장되기 때문
        answer = 1;
        while(iter.hasNext()){
            answer *= (iter.next() +1);
        }
        return answer-1;
    } 
}
