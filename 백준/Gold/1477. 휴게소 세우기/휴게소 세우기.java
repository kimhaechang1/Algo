import java.util.*;
import java.io.*;

public class Main{
	static StringTokenizer stk;
	static int n;
	static int m;
	static int l;
	static int [] arr;
	static boolean [] t;
	public static void main(String [] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		stk = new StringTokenizer(bf.readLine());
		n = Integer.parseInt(stk.nextToken());
		m = Integer.parseInt(stk.nextToken());
		l = Integer.parseInt(stk.nextToken());
		// 최대이자 최소
		// 파라메트릭 서치로 찾는다.
		// 이때 상한선이 자꾸 내려오다가 더이상 내려가지 않는 순간이 정답이 된다.
		// 
		stk = new StringTokenizer(bf.readLine());
		arr = new int[n+2];
		arr[0] = 0;
		arr[n+1] = l;
		for(int i = 1;i<=n;i++) {
			arr[i] = Integer.parseInt(stk.nextToken());
		}
		Arrays.sort(arr);
		System.out.print(upper(1, l));
	}
	static int upper(int s, int e) {
		int minValue = e;
		while(s <= e) {
			int mid = (s+e)/2;
			int res = chk(mid);
			if(res > m) {
				s = mid+1;
			}else {
				minValue = Math.min(mid, minValue);
				e = mid-1;
			}
		}
		return minValue;
	}
	static int chk(int value) {
		// 해당 구간이 휴게소가 있다면?
		int cnt = 0;
		// 해당거리를 최대로 만들 수 있는지 검사
		for(int i = 1;i<n+2;i++) {
			int dis = arr[i] - arr[i-1] -1;
			if(dis == 0) continue;
			if(dis >= value) {
				cnt += (dis/value);
			}
		}
		return cnt;
	
	}
}