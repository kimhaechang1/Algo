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
        int start = 0;
        while(true) {
        	if(di < 0 && pi < 0) break;
        	if((di > -1 && d[di] > 0) || (pi > -1 && p[pi] > 0)) {
        		// 수거 or 배달을 해야할 지점을 찾았을 때
        		// 적어도 그곳에서부터 왕복해야함
        		start = Math.max(di, pi); 
        		// 최소한 트럭이 가야할 지점을 찾았다면 
        		// 내려가면서 몇개를 수거하고 몇개를 배달할지를 처리
        		while(di > -1) {
        			if(dsum == cap) break;
        			if(dsum + d[di] > cap) {
        				d[di] -= (cap - dsum);
        				dsum += (cap - dsum);
        				break;
        			}else {
        				dsum+=d[di];d[di] = 0;di--;
        			}
        		}
        		while(pi > -1) {
        			if(psum == cap) break;
        			if(psum + p[pi] > cap) {
        				p[pi] -= (cap - psum);
        				psum += (cap - psum);
        				break;
        			}else {
        				psum+=p[pi];p[pi] = 0;pi--;
        			}
        		}
        		//System.out.println(Arrays.toString(d));
        		
        		if(psum > 0 || dsum > 0) {
        			//System.out.println((start + 1) * 2);
        			answer += ((start + 1) * 2);
        			psum = 0;
        			dsum = 0;
        		}
        	}else {
        		di--;
            	pi--;
        	}
        }
        
        return answer;
    }
}