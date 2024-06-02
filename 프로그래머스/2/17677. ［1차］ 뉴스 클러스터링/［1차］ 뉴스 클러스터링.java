import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        int answer = 0;
        HashMap<String, Integer> map1 = new HashMap<>();
        HashMap<String, Integer> map2 = new HashMap<>();
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        char [] strChar1 = str1.toCharArray();
        char [] strChar2 = str2.toCharArray();
        Set<String> hapSet = new HashSet<>();
        for(int i= 0;i<strChar1.length-1;i++){
            if(!isAlpha(strChar1[i]) || !isAlpha(strChar1[i+1])){
                continue;
            }
            String sub = str1.substring(i, i+2);
            hapSet.add(sub);
            if(map1.containsKey(sub)){
                int cnt = map1.get(sub);
                map1.put(sub, ++cnt);
            }else{
                map1.put(sub, 1);
            }
        }
        for(int i= 0;i<strChar2.length-1;i++){
            if(!isAlpha(strChar2[i]) || !isAlpha(strChar2[i+1])){
                continue;
            }
            String sub = str2.substring(i, i+2);
            hapSet.add(sub);
            if(map2.containsKey(sub)){
                int cnt = map2.get(sub);
                map2.put(sub, ++cnt);
            }else{
                map2.put(sub, 1);
            }
        }
        int gyo = 0;
        // 교집합
        // 같은 문자열의 개수가 각 다중집합별로 여러개 존재한다면 더 작은쪽을 선택
        for(Map.Entry<String, Integer> entry: map1.entrySet()){
            String element = entry.getKey();
            int cnt = entry.getValue();
            int opperside = 0;
            if(map2.containsKey(element)){
                opperside = map2.get(element);
            }
            if(opperside != 0) gyo += Math.min(cnt, opperside);
        }
        int hap = 0;
        // 합집합
        Iterator<String> iter = hapSet.iterator();
        while(iter.hasNext()){
            String element = iter.next();
            int left = 0;
            if(map1.containsKey(element)){
                left = map1.get(element);
            }
            int right = 0;
            if(map2.containsKey(element)){
                right = map2.get(element);
            }
            hap += Math.max(left, right);
        }
        if(hap == 0 && gyo == 0){
            return 1 * 65536;
        }
        double t = (double) gyo / hap;
        answer = (int)(t * 65536);
        return answer;
    }
    public boolean isAlpha(char frag){
        return frag - 'a' >= 0 && frag - 'a' <= 26;
    }
}