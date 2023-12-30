import java.util.*;
import java.io.*;

class Solution {
     // start : 2023 12 30 8:31
    // end  : 2023 12 30
	public static long solution(int cap, int n, int[] d, int[] p) {
        long answer = 0;
        int dsum = 0;
        int psum = 0;
        int di = n-1;
        int pi = n-1;
        int start = -1;
        while(true) {
        	
        	start = -1;
        	for(int i = di;i>-1;i--) {
                if(dsum == cap){
                    di = i;break;  
                } 
        		if(d[i] > 0) {
        			start = Math.max(i, start);
                    
        			if(dsum + d[i] <= cap) {
        				dsum += d[i];
        				d[i] = 0;
        			}else {
        				d[i] -= (cap - dsum);
        				dsum += (cap - dsum);
        				di = i;
        				break;
        			}
        		}
        	}
        	for(int i= pi;i>-1;i--) {
                if(psum == cap) {
                    pi = i;break;
                }
        		if(p[i] > 0) {
        			start = Math.max(i, start);
        			if(psum + p[i] <= cap) {
        				psum += p[i];
        				p[i] = 0;
        			}else {
        				p[i] -= (cap - psum);
        				psum += (cap - psum);
        				pi = i;
        				break;
        			}
        		}
        	}
        	if(dsum > 0 ||  psum > 0) {
        		answer += (start + 1) * 2;
                dsum = 0;
        	    psum = 0;
        	}
        	if(start == -1) {
        		break;
        	}
        }
        
        return answer;
    }
}