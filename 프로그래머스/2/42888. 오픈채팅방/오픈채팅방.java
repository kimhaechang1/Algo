import java.util.*;
import java.io.*;
// 2024 01 13 13:30 start
//
class Solution {
    public String[] solution(String[] record) {
        // 채팅방을 나간 후 새로운 닉네임으로 들어온 경오
        // [prev] 님이 나갔습니다. [new] 님이 들어왔습니다.
        // 채팅방안에서 닉네임을 변경한 경우
        HashMap<String, String> parsingTable = new HashMap<>();
        
        int len = 0;
        for(String rec : record){
            String [] frags = rec.split(" ");
            
            if("Change".equals(frags[0])){    
                parsingTable.put(frags[1], frags[2]);
            }else if("Enter".equals(frags[0])){
                if(parsingTable.containsKey(frags[0])){
                    continue;
                }else{
                    parsingTable.put(frags[1] ,frags[2]);
                }
            }
        }
        ArrayList<String> resultList = new ArrayList<>();
        for(String rec : record){
            String []frags = rec.split(" ");
            StringBuilder sb = new StringBuilder();
            switch(frags[0]){
                case "Enter":
                    sb.append(parsingTable.get(frags[1])).append("님이 들어왔습니다.");
                    resultList.add(sb.toString());
                    break;
                case "Leave":
                    sb.append(parsingTable.get(frags[1])).append("님이").append(" ").append("나갔습니다.");
                    resultList.add(sb.toString());
                    break;
            }
        }
        String [] answer = new String[resultList.size()];
        for(int i = 0;i<answer.length;i++){
            answer[i] = resultList.get(i);
        }
        
        
        return answer;
    }
}