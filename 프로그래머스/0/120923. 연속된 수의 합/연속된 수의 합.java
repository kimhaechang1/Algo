// start 21:23
// end 
import java.util.*;

class Solution {
    public int[] solution(int num, int total) {
        int[] answer = {};
         // -1000 ~ 1000
        int sum = 0;
        int idx=0; 
        int [] window = new int[num];
        for(int i = -1000;i<-1000+num;i++){
            sum += i;
            window[idx++] = i;
        }
        
        for(int i = -1000+num;i<1001;i++){
            sum -= window[0];
            for(int j = 0;j<window.length-1;j++){
                window[j] = window[j+1];
            }
            sum += i;
            window[num-1] = i;
            if(sum == total){
                break;
            }
        }
       
        return window;
    }
}