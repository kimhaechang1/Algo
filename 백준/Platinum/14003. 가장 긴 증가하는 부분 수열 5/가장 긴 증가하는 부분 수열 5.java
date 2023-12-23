import java.util.*;
import java.io.*;

public class Main {
	static int [] arr;
	static int [] v;
	static int N;
	static StringTokenizer stk;
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(bf.readLine());
		arr = new int[N];
		int []  dp = new int[N+1];
		stk = new StringTokenizer(bf.readLine());
		for(int i= 0;i<N;i++) {
			arr[i] = Integer.parseInt(stk.nextToken());
		}
		dp[0] = arr[0];
		
		v = new int[N];
		v[0] = 0;
		int LIS = 0;
		for(int i= 1;i<N;i++) {
			if(dp[LIS] < arr[i]) {
				dp[++LIS] = arr[i];
				v[i] = LIS;
			}
			else {
				int idx = lower(dp, 0, LIS, arr[i]);
				v[i] = idx;
				dp[idx] = arr[i];
			}
			
		}
		Stack<Integer> res = new Stack<>();
		int t = LIS;
		for(int i= arr.length-1 ;i>-1;i--) {
			if(t == v[i]) {
				res.add(arr[i]);
				--t;
			}
		}
        
        
		sb.append(LIS+1).append("\n");
		while(!res.isEmpty()) {
			sb.append(res.pop()+" ");
		}
        System.out.print(sb);
		
		
	}
	static int lower(int [] arr, int start, int end, int find) {
		while(start < end) {
			int mid = (start+end)/2;
			if(arr[mid] < find) {
				start = mid+1;
			}else {
				end = mid;
			}
		}
		return end;
	}

}
