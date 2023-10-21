import java.util.*;
import java.io.*;

public class Main{
	static int [] arr;
	static int N;
	static int B; // 예산
	static int A; // 반값 할인을 받을 수 있는 최대 선물 수
	static StringTokenizer stk;
	public static void main(String [] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		stk = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(stk.nextToken());
		B = Integer.parseInt(stk.nextToken());
		A = Integer.parseInt(stk.nextToken());
		arr = new int[N];
		stk = new StringTokenizer(bf.readLine());
		for(int i= 0;i<N;i++) {
			arr[i] = Integer.parseInt(stk.nextToken());
		}
		// 26
		// 그리디
		// 구매할 수 있는 만큼 구매하다가
		// 만약에 현재 물건을 정가로 살 수 없는경우, 현재 물건부터 이전물건까지 할인을 걸다가
		// 현재물건을 살 수 있는 상태가된다면 산다.
		// 그렇게 해서 라도 살 수 없다면 현재 첨자가 가리키기는 포인터 +1이 정답이 된다.
		Arrays.sort(arr);
		long sum = 0;
		int ans = 0;
		boolean [] v = new boolean[N];
		
		for(int i= 0;i<arr.length;i++) {
			if(sum + arr[i] <= B) {
				sum += arr[i];
				ans = i+1;
			}else {
				if(A == 0) break;
				// 지금것을 못넣는데 남은 할인권 조차 없다면 물건의 수 를 늘릴 수 없다.
				boolean canGo = false; // 살 수 없는지 있는지 검사
				// 일단 장바구니에 담기
				sum += arr[i];
				
				for(int j= i;j>-1;j--) {
					// 장바구니에 담긴것 들 중에 큰것부터 고려하여 할인을 했을때, 예산을 초과하는지 검사한다.
					if(v[j]) continue; // 이미 할인 한 것은 할인할 수 없다.
					if(A == 0) break; // 할인권을 다썻으면 할인을 더이상 할 수 없다.
					sum -= (arr[j]/2);
					A--;
					v[j] = true;
					if(sum <= B) {
						canGo = true;
						ans = i+1;
						break;
					}
				}
				if(!canGo) break; 
			}
		}
		
		
		System.out.println(ans);
		
		// 3 6 3
		
		//   0 1 2
		// a 2 4 6
		// s 0 2 6 12
		
		// 3 3 3
		//   0 1 2 3
		//   3 3 3
		// a 2 2 2
		// s 0 2 4 6
		// 
	}
}