import java.util.*;

class Solution {
   public int[] solution(String[] queries) {
        TreeMap<Integer, Integer> tmap = new TreeMap<>();
        for(String query : queries){
	            String [] q = query.split(" ");
	            String order = q[0];
	            char where = (q[1].toCharArray())[0];
	            if(order.equals("I")){
	            	int key = Integer.parseInt(q[1]);
	            	Integer cnt = tmap.get(key);
	                if(cnt == null) {
	                	tmap.put(key, 1);
	                }else {
	                	tmap.put(key, ++cnt);
	                }
	                
	            }else if(order.equals("D")){
	                
	                if(tmap.isEmpty()) continue;
	                if(where == '-'){
	                    Integer lower = tmap.firstKey();
	                    Integer cnt = tmap.get(lower);
	                    if(--cnt == 0) {
	                    	tmap.remove(lower);
	                    }else {
	                    	tmap.put(lower, cnt);
	                    }
	                }else{
	                	Integer higher = tmap.lastKey();
	                    Integer cnt = tmap.get(higher);
	                    if(--cnt == 0) {
	                    	tmap.remove(higher);
	                    }else {
	                    	tmap.put(higher, cnt);
	                    }
	                }
	            }
	        }
        
        int[] answer = new int[2];
        if(tmap.isEmpty()){
            answer[0] = 0;
            answer[1] = 0;
        }else{
            
            answer[0] = tmap.lastKey();
            answer[1] = tmap.firstKey();
        }
        return answer;
    }
}