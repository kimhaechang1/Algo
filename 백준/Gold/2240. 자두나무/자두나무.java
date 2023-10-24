import java.io.*;
import java.util.*;

public class Main{
	static int [] arr;
	static int T;
	static int W;
	static StringTokenizer stk;
	public static void main(String [] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		stk = new StringTokenizer(bf.readLine());
		T = Integer.parseInt(stk.nextToken());
		W = Integer.parseInt(stk.nextToken());
		arr = new int[T+1];
		
		for(int i= 1;i<T+1;i++) {
			arr[i] = Integer.parseInt(bf.readLine());
		}
		
		// W번 움직임
		// T 초 동안 자두가 떨어짐
		// 자두가 최종적으로 떨어지는 시간은 마지막 T초를 더한 2T에 모두가 떨어짐
		/*  T 0 1 2 3 4 5 6 7 8  9  10  11  12  13  14
		 *1     0 1 1 0 0 1 1  
		 *2     1 0 0 1 1 0 0
 		 *J     1 1 1 2 2 1 1
 		 *
 		 *T 0 6 7 8 9 10 11 12 13 14 // 한번도 이동안하면
		 *0   0 0 0 1 2  2  2  3  4  // 한번 이동
		 *1   0 0 1 1 2    				 // 두번 이동
 		 *2   0 0     
 		 *          
 		 * */
		int max = 0;
		int [][] dp = new int[T+1][W+1];
		for(int t = 1;t<T+1;t++) {
			for(int w = 0;w<=W;w++) {
				if(w == 0) {
					if(arr[t] == 1) {
						dp[t][w] = dp[t-1][w] + 1;
					}else if(arr[t] == 2) {
						dp[t][w] = dp[t-1][w];
					}
				}else if(w % 2 == 1) {
					// 홀수 번 움직였는 경우 2번나무
					if(arr[t] == 1) {
						dp[t][w] = Math.max(dp[t-1][w-1]+1, dp[t-1][w]);
					}else if(arr[t] == 2) {
						dp[t][w] = Math.max(dp[t-1][w]+1, dp[t-1][w-1]);
					}
				}else if(w % 2 == 0) {
					// 짝수번 움직였을 경우 1번 나무
					if(arr[t] == 1) {
						dp[t][w] = Math.max(dp[t-1][w]+1, dp[t-1][w-1]);
					}else if(arr[t] == 2) {
						dp[t][w] = Math.max(dp[t-1][w-1]+1, dp[t-1][w]);
					}
				}
				max = Math.max(max, dp[t][w]);
				
			}
		}
		System.out.println(max);
		
		
		
	}
}
