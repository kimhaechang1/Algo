// start 16:15
// end 17:35
import java.util.*;

class Solution {
    static String [] tenToHex = new String[17];
    public String solution(int n, int t, int m, int p) {
        // 첫번째 게임 p, 두번째 게임 q
        // m = 2이고 p = 1 이면서 n =2 이고 구해야 하는 개수가 4개라면
        // 게임은 
        // [0]p -> [1]q -> [1p -> 0q] -> [1p -> 1q] -> [1p -> 0q -> 0p]
        for(int i = 0;i<7;i++){
            tenToHex[i+10] = String.valueOf((char)('A'+i));
        }
        String answer = "";
        int cnt = 0;
        int current = 0;
        int lenStart = 1;
        StringBuilder sb = new StringBuilder();
        while(cnt < t){
            String result = converter(current, n);
            int totalLen = lenStart + result.length();
            for(int s = lenStart;s < totalLen;s++){
                if((s % m) == (p % m)){
                    sb.append(result.charAt(s-lenStart));
                    cnt++;
                    if(cnt == t) break;
                }
            }
            lenStart = totalLen;
            current++;
        }
        answer = sb.toString();
        return answer;
    }
    static String converter(int number, int o){
        if(number == 0){
            return "0";
        }else{
            Stack<String> stack = new Stack<>();
            StringBuilder result = new StringBuilder();
            while(number >= o ){
                int take = number % o;
                if(take >= 10){
                    stack.push(tenToHex[take]);        
                }else{
                    stack.push(String.valueOf(take));
                }
                number /= o;
            }
            if(number >= 10) {
            	stack.push(tenToHex[number]);
            }else {
            	stack.push(String.valueOf(number));
            }
            while(!stack.isEmpty()){
                result.append(stack.pop());
            }
            return result.toString();
        }
    }
}