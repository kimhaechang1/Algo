
import java.util.*;

class Solution {
    public String[] solution(String[] orders, int[] course) {
        // 전체 알파벳을 통해 조합을 만들어내려 하면 최대 26C10 * 20까지 계산해야한다. << 이건 무리일듯
        // 손님이 주문한 메뉴들 사이에서 조합을 만들어 낸다.
        // 어짜피 숫자는 2 ~ 10 이므로 크지않음
        // 단품 메뉴 조합결과 중 course개수와 일치하면서 가장 많이 선발된 조합을 선택해야 한다.
        // 따라서 결과마다의 course개수만큼 골랐을때의 각각 최대 메뉴개수를 찾아야하고
        // 해당 최대개수와 일치하는 모든 메뉴조합을 결과 리스트에 담는다.
        HashMap<String, Integer> map = new HashMap<>();
        int [] max = new int[course.length];
        for(int i= 0;i<orders.length;i++){
            char [] alphas = orders[i].toCharArray();
            for(int j = course.length-1;j>-1;j--){
                Arrays.sort(alphas);
                if(alphas.length < course[j]){
                    continue;
                }
                boolean [] v = new boolean[alphas.length];
                dfs(0, 0, v, j, course[j], alphas, map, max);
            }
        }
        ArrayList<String> list = new ArrayList<>();
        for(String menu: map.keySet()){
            // 결과 조합을 하나씩 순회하면서
            for(int i = 0;i<course.length;i++){
                // course에 일치하는 메뉴조합수이면서
                // 해당 조합수 중 가장 손님들에게 많이 포함된 조합이면서
                // 그 max[j] 와 일치하는 
                if(max[i] == map.get(menu) && map.get(menu) > 1 && menu.length() == course[i]){
                    list.add(menu);
                }
            }   
        }
        Collections.sort(list);
        
        return list.stream().map(s -> s).toArray(String[]::new);
    }
    static void dfs(int depth, int start, boolean [] v, int select, int n, char [] a, HashMap<String,Integer> map, int [] max){
        if(depth == n){
            StringBuilder sb = new StringBuilder();
            for(int i = 0;i<v.length;i++){
                if(!v[i]) continue;
                sb.append(a[i]);
            }
            
            String result = sb.toString();
            int cnt = 0;
            if(map.containsKey(result)){
                cnt = map.get(result);
            }
            map.put(result, cnt+1);
            max[select] = Math.max(map.get(result), max[select]);
            return;
        }
        for(int i = start;i<a.length;i++){
            if(v[i]) continue;
            v[i] = true;
            dfs(depth+1, i, v, select, n, a, map, max);
            v[i] = false;
        }
        
    }
}