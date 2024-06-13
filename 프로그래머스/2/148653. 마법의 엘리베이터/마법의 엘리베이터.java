import java.util.*;

class Solution {
    public int solution(int storey) {
        int answer = 0;
        char [] nums = String.valueOf(storey).toCharArray();
        int bae = 0;
        
        while(bae < nums.length){
            int current = 0;
            if(bae == 0){
                current = (storey)%10;    
            }else{
                current = (storey/((int)Math.pow(10, bae))) % 10;
            }
            if(current > 5){
                answer += (10 - current);
                storey += ((10 - current) * ((int)Math.pow(10, bae)));
            }else if(current == 5){
                if(bae + 1 < nums.length){
                    int next = (storey/((int)Math.pow(10, bae+1))) % 10;
                    if(next + 1 > 5){
                        answer += 5;
                        storey += ((10 - current) * ((int)Math.pow(10, bae)));
                    }else{
                        answer += 5;
                        storey -= ((current) * ((int)Math.pow(10, bae)));    
                    }
                }else{
                    answer += (current);
                    storey -= ((current) * ((int)Math.pow(10, bae)));
                }
            }else{
                answer += (current);
                storey -= ((current) * ((int)Math.pow(10, bae)));
            }
            bae++;
        }
        System.out.println(storey);
        if(storey > 0){
            answer++;
        }
        return answer;
    }
}