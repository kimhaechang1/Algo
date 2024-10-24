import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        // 진열된 모든 종류의 보석을 적어도 1개 이상 포함하는 가장 짧은 구간
        HashMap<String, Integer> countMap = new HashMap<>();
        // 어떤 연속된 구간을 찾는것
        int s = 0;
        int e = 0;
        HashSet<String> kind = new HashSet<>();
        for(String gem: gems) kind.add(gem);
        HashSet<String> present = new HashSet<>();
        int minLen = 987654321;
        int minS = 987654321;
        while(e < gems.length) {
            present.add(gems[e]);
            countMap.put(gems[e], countMap.getOrDefault(gems[e], 0) + 1);
            if (present.size() == kind.size()) {
                while(s <= e) {
                    if (minLen > e - s + 1) {
                        minLen = e - s + 1;
                        minS = s;
                        answer[0] = s + 1;
                        answer[1] = answer[0] + (e - s);
                    } else if (minLen == e - s + 1 && minS > s) {
                        minS = s;
                        answer[0] = s + 1;
                        answer[1] = answer[0] + (e - s + 1);
                    }
                    if (countMap.get(gems[s]) - 1 == 0) {
                        break;
                    } else {
                        countMap.put(gems[s], countMap.get(gems[s]) - 1);
                        
                    }
                    s++;
                }
            }
            e++;
        }
        
        return answer;
    }
}