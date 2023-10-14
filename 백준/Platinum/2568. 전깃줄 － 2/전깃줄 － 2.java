import java.util.*;
import java.io.*;

public class Main{
	static int N;
	static int []arr;
	static int []brr;
	static StringTokenizer stk;
	public static void main(String [] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(bf.readLine());
		arr = new int[500001];
		brr = new int[500001];
		int minAidx = 500000;
		for(int i= 1;i<=N;i++) {
			stk = new StringTokenizer(bf.readLine());
			int a = Integer.parseInt(stk.nextToken());
			int b = Integer.parseInt(stk.nextToken());
			arr[a] = b;
			if(a < minAidx) minAidx = a;
			brr[b] = a;
			// 없애야 하는 최소개수의 전깃줄이어야 한다.
			// 물론 교차만 안하는 조건이라면 얼마든지 짤라도되지만
			// 최소 개수의 전깃줄을 잘라서 교차하지 않아야하는 조건이라면
			// 역으로 생각했을 때 최대한 많이 연결해야함을 의미한다.
			// 교차하지 않으면서 최대한 많이 연결하려면 
			// 기준 전봇대의 각 층에 해당하는 인덱스보다 크거나 같은 기준으로 연결하게 만든다.
			// A를 기준으로 연결된 선들을 보았을 때,
			// 1  2  3  4  5  6  7  8  9  10
			// 8  2  9  1  X  4  6  X  7  10
			// 여기서 크거나 같은 선들만 살린다면
			// 그냥 LIS 인것 같은데
			// 2 4 6 10 살리고 
			// B 전봇대를 기준으로 생각해도 동일할 것이다.
			// 이 문제에서는 결국 LIS의 길이를 원하는 것이고, 뭐를 짤라야 하는지는
			// LIS 결과값을 B 전봇대기준으로 돌려서 A전봇대의 인덱스를 구하면 된다.
		}
		int LIS = 0;
		int [] dp = new int[N];
		int [] rank = new int[500001];
		Arrays.fill(rank, -1);
//		System.out.println(minAidx);
		dp[0] = arr[minAidx];
		rank[0] = 0;
		for(int i= 1;i<500001;i++) {
			if(arr[i] == 0) continue;
			if(dp[LIS] < arr[i]) {
				dp[++LIS] = arr[i];
				rank[i] = LIS;
			}else {
				int idx = lower(dp, 0, LIS, arr[i]);
				dp[idx] = arr[i];
				rank[i] = idx;
			}
			
		}
//		System.out.println(LIS);
//		System.out.println(Arrays.toString(rank));
		Stack<Integer> temp =new Stack<>();
		int k = LIS;
		while(k > -1) {
			for(int i= 500000;i>-1;i--) {
				if(rank[i] != -1 && rank[i] == k) {
					temp.push(arr[i]);
					k--;
				}
			}
		}
		boolean[] v = new boolean[500001];
		while(!temp.isEmpty()) {
			int t = temp.pop();
			v[brr[t]] = true;
		}
		PriorityQueue<Integer> queue = new PriorityQueue<>();
		
		for(int i=0;i<500001;i++) {
			if(arr[i] == 0 || v[i]) continue;
			queue.add(i);
		}
		if(LIS+1 == N) {
			sb.append(0);
		}else {
			sb.append(N-(LIS+1)).append("\n");
			while(!queue.isEmpty()) {
				sb.append(queue.poll()).append("\n");
			}
		}
		System.out.print(sb);
		
	}
	static int lower(int [] arr, int s, int e, int f) {
		while(s < e) {
			int m = (s+e)/2;
			if(arr[m] < f) {
				s = m+1;
			}else {
				e = m;
			}
		}
		return e;
	}
}