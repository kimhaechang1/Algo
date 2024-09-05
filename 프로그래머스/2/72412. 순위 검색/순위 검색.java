import java.util.*;

class Solution {
    static HashMap<String, ArrayList<Integer>> map;
    public int[] solution(String[] info, String[] query) {
        map = new HashMap<>();
        for(int i =0;i<info.length;i++) {
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