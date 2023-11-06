import java.util.*;
import java.io.*;

public class Main{
	static int N;
	static long [] arr;
	static StringTokenizer stk;
	public static void main(String [] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		arr = new long[N];
		stk = new StringTokenizer(bf.readLine());
		for(int i= 0;i<N;i++) {
			arr[i] = Integer.parseInt(stk.nextToken());
		}
		// 세 정수의 값의 합이 0에 가까우면 된다.
		// 값이 더 중요함
		Arrays.sort(arr);
		// -97 -6 -2 6 98
		// 가장 쉽게 떠오르는 방법은
		// 두 용액의 합이 0에 가까운 걸 구하고
		// 그 두 용액을 제외한 나머지 중에서 가장 0에 가까운 용액을 고르면 답이 아닐까?
		// 바로 반례터진다.
		// 용액 하나(최소)를 고정시켜놓고 투포인터
		// 그리고 최소보다 하나 큰 것에서 끝 사이를 투포인터로 돌게 된다.
		// 여기서 중요한 점은 0에 가까워야 하므로, 0보다 크다면 큰 범위를 죽이고
		// 0보다 작으면 작은범위를 높인다.
		long [] res = new long[3];
		long min = Long.MAX_VALUE;
		for(int i = 0;i<N-2;i++) {
			int s = i+1;
			int e = N-1;
			while(s < e) {
				long sum = arr[i] + arr[s] + arr[e];
				if(min > Math.abs(sum)) {
					res[0] = arr[i];
					res[1] = arr[s];
					res[2] = arr[e];
					min = Math.abs(sum);
				}
				if(sum > 0) {
					e--;
				
				}else {
					s++;
				}
				
			}
		}
		Arrays.sort(res);
		//System.out.println(Arrays.toString(res));
		for(long a : res) System.out.print(a+" ");
		
		
	}
}