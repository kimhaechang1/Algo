import java.util.*;
import java.io.*;

public class Main{
	static int a1;
	static int a2;
	static int min;
	static int [] arr;
	static int N;
	static StringTokenizer stk;
	public static void main(String[] args) throws Exception{
		// 산성 용액은 1~ 10억
		// 알칼리성 용액은 -1 ~ -10억
		// 같은 양의 두 용액을 혼합한 용액의 특성값은 혼합에 사용된 각 용액의 특성값의 합으로 정의
		// 같은 양의 두 용액을 혼합하여 특성값이 0에 가장 가까운 용액
		// 두 용액 -> 투포인터??
		// 정수가 오름차순으로 주어진 -> 이분탐색도 가능한가봄
		
		// 두 용액의 특성값의 합이 0에 가장 가까우면 됨
		// 이분탐색은 잘 모르겠고
		// 산성이거나 알칼리성 용액으로만 주어지는 경우도 있다고 함
		// 그렇게 된다면 산성의 경우에는 가장 0에 가까우려면 0,1을 더해야 하고
		// 어쨌던 두 포인터로 돌면서 두 용액의 합을 측정하고
		// 합과 0과의 오프셋 차의 최솟값인 두 용액을 찾는다.
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		arr = new int[N];
		stk = new StringTokenizer(bf.readLine());
		for(int i = 0;i<N;i++) {
			arr[i] = Integer.parseInt(stk.nextToken());
		}
		Arrays.sort(arr);
		min = Integer.MAX_VALUE;
		a1 = -1;
		a2 = -1;
		int start = 0;
		int end = N-1;
		while(start < end) {
			// 오름차순으로 용액들의 특성이 주어지는데 있어서
			// 두 용액의 합이 최대한 0에 가까워야 한다.
			// 0이거나 0보다 크다면 최대범위를 줄여주고
			// 두수의 합이 음수라면 최소범위를 늘려준다.
			int off = arr[start]+arr[end];
			if(min > Math.abs(off)) {
				min = Math.abs(off);
				a1 = start;
				a2 = end;
			}
			if(off >=0) {
				end--;
			}else {
				start++;
			}
		}
		
		System.out.println(arr[a1]+" "+arr[a2]);	
	}
}