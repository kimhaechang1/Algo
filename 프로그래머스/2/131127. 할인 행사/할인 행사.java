import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        // 할인 제품은 하루에 하나씩
        // 해당 기간동안의 원소 개수가 일치하는지 검사해야함
        HashMap<String, Integer> map = new HashMap<>();
        int s = 0;
        int e = 9;
        int offset = 10;
        for(String w: want){
            map.put(w, 0);
        }
        for(int i = 0;i<10;i++){
            if(map.containsKey(discount[i])){
                int cnt = map.get(discount[i]);
                map.put(discount[i], ++cnt);
            }
        }
        while(e < discount.length){
            if(check(map, want, number)){
                answer++;
            }   
            if(map.containsKey(discount[s])){
                int cnt = map.get(discount[s]);
                if(cnt != 0){
                    map.put(discount[s], --cnt);
                }
            }
            if(e+1 >= discount.length){
                break;
            }else{
                if(map.containsKey(discount[e+1])){
                    int cnt = map.get(discount[e+1]);
                    map.put(discount[e+1], ++cnt);
                }
            }
            s++;
            e++;
        }
        return answer;
    }
    public boolean check(HashMap<String, Integer> map, String [] w, int[] number){
        for(int i= 0;i<w.length;i++){
            if(map.get(w[i]) != number[i]){
                return false;
            }
        }
        return true;
    }
    public void print(HashMap<String, Integer> map){
        for(Map.Entry<String, Integer> entry: map.entrySet()){
            System.out.println("key: "+entry.getKey()+" value: "+entry.getValue());
        }
        System.out.println("###########3");
    }
}