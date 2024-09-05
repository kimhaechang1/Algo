import java.util.*;

class Solution {
    static HashMap<String, ArrayList<Integer>> map;
    public int[] solution(String[] info, String[] query) {
        map = new HashMap<>();
        for(int i =0;i<info.length;i++) {
            // 여기서 미리 모든 키를 만들 수 없는데는 이유가 있다.
            // 왜냐하면 모든키를 미리 만들어놓고서 현재 알고있는 정보를 기입하려 하면 어떤 경우까지 가능한지 
            // key에 대한 완전탐색을 해야하기 때문이다.
            // 대신 여기와 같이 알고있는 키 조합에 대해서 -의 경우를 섞어서 모든키로 조합을 만드는건 쉽다
            // 그게 무슨말이냐? 현재 해당하는 score를 가져갈 수 있는 모든 key 조합을 만든다는 의미이다.
            dfs(0, "",info[i].split(" "));
        }
        
        for(String key: map.keySet()) {
            ArrayList<Integer> list = map.get(key);
            Collections.sort(list);
            map.put(key, list);
        }
        int[] answer = new int[query.length];
        for(int i = 0;i<query.length;i++) {
            String[] frags = query[i].split(" and ");
            String[] withScore = frags[3].split(" ");
            String key = frags[0]+frags[1]+frags[2]+withScore[0];
            if(!map.containsKey(key)) {
                answer[i] = 0;
                continue;
            }
            int score = Integer.parseInt(withScore[1]);
            int s = 0;
            ArrayList<Integer> list = map.get(key);
            // 주어진 쿼리가 항상 있는건 아님
            
            int e = list.size() - 1;
            int ans = list.size();
            while(s <= e) {
                int mid = (s + e) / 2;
                if(list.get(mid) >= score) {
                    e = mid - 1;
                    ans = Math.min(mid, ans);
                } else {
                    s = mid + 1;
                }
            }
            answer[i] = list.size() - ans;
        }
        return answer;
    }
    static void dfs(int depth, String key, String[] info) {
        if(depth == 4) {
            if(map.containsKey(key)) {
                ArrayList<Integer> list = map.get(key);
                list.add(Integer.parseInt(info[depth]));
                map.put(key, list);
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(Integer.parseInt(info[depth]));
                map.put(key, list);    
            }
            
            return;
        }
        
        dfs(depth+1, key + "-", info);
        dfs(depth+1, key + info[depth], info);
    }
}