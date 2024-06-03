import java.util.*;

class Solution {
    static HashMap<String, Integer> map; 
    public int[] solution(String msg) {
        int[] answer = {};
        map = new HashMap<>();
        for(int i = 0;i<26;i++){
            map.put(String.valueOf((char)('A'+i)), i+1);
        }
        ArrayList<Integer> ansList = new ArrayList<>();
        // 해당 문자열이 등록되어있지 않을때 까지 뽑고
        // 않는 순간 그전까지 합쳐진 문자열의 index를 출력하고 새로운 문자열을 붙여서 insert
        int p = 0;
        int index = 27;
        while(p < msg.length()){
            StringBuilder sb = new StringBuilder();
            sb.append(msg.charAt(p));
            int result = map.get(sb.toString());
            int offset = p;
            while(true){
                offset++;
                if(offset >= msg.length()) break;
                sb.append(msg.charAt(offset));
                if(map.containsKey(sb.toString())){
                    result = map.get(sb.toString());
                }else{
                    break;
                }
            }
            p+=(offset-p);
            ansList.add(result);
            if(!map.containsKey(sb.toString())){
                map.put(sb.toString(), index);    
                index++;
            }
            
            
        }
        answer = new int[ansList.size()];
        for(int i= 0;i<answer.length;i++){
            answer[i] = ansList.get(i);
        }
        return answer;
    }
}