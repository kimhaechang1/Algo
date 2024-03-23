import java.util.*;
import java.io.*;

public class Main{
	static int [] arr;
	static StringTokenizer stk;
	static int N;
	static int M;
	
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		stk = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		stk = new StringTokenizer(bf.readLine());
		int min = Integer.MAX_VALUE;
		arr = new int[N];
		for(int i= 0;i<N;i++) {
			arr[i] = Integer.parseInt(stk.nextToken());
			// 연속된 수의 최소개수가 1개라는 의미로 해석할 수 있다.
			if(min == Integer.MAX_VALUE && M <= arr[i]) {
				min = 1;
			}
		}
		long [] S = new long[N];
		S[0] = arr[0];
		for(int i= 1;i<N;i++) {
			S[i] = S[i-1] + arr[i];
		}
		if(S[N-1] >= M) {
			if(min > (arr.length)) min = arr.length;
		}
//		System.out.println(Arrays.toString(S));
		int s = 0;
		int e = 1;
		while(s < N) {
			long sum = S[e] - S[s];
//			System.out.println(s +" "+e);
			if(sum >= M) {
				if(min > e-s) {
					min = (e - s);
				}
			}
			if(sum < M) {
				if(e + 1 >= N) {
					s++;
				}else {
					e++;
				}
			}else if(sum >= M) {
				s++;
			}
		}
		if(min == Integer.MAX_VALUE) {
			System.out.println(0);
		}else {
			System.out.println(min);
		}
	}
}