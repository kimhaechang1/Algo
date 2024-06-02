import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        String [] caches = new String[cacheSize];
        // LRU 가장 오래동안 사용되지 않은 페이지를 교체
        // cache hit : 1
        // cache miss : 5
        int sizePointer = 0;
        for(String city: cities){
            city = city.toLowerCase();
            boolean hit = false;
            int hitIdx = -1;
            for(int i = 0;i<sizePointer;i++){
                if(caches[i] == null){ 
                    break;
                }
                if(caches[i].equals(city)){
                    hit = true;
                    hitIdx = i;
                    break;
                }
            }
            if(hit){
                answer += 1;
                caches[hitIdx] = null;
                for(int i = hitIdx;i>0;i--){
                    caches[i] = caches[i-1];
                }
                caches[0] = city;
            }else{
                answer += 5;
                if(sizePointer < cacheSize){
                    for(int i = sizePointer;i>0;i--){
                        caches[i] = caches[i-1];
                    }    
                    caches[0] = city;
                    sizePointer++;
                }else{
                    if(cacheSize == 0) continue;
                    caches[cacheSize-1] = null;
                    for(int i= cacheSize-1;i>0;i--){
                        caches[i] = caches[i-1];
                    }
                    caches[0] = city;
                }
                
            }
        }
        return answer;
    }
}