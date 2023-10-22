import java.util.*;
import java.io.*;

public class Main{
	static int N;
	static int [] len;
	static StringTokenizer stk;
	static int [] cost;
	public static void main(String [] args) throws Exception{
		BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		stk = new StringTokenizer(bf.readLine());
		len = new int[N-1];
		for(int i=0;i<len.length;i++) {
			len[i] = Integer.parseInt(stk.nextToken());
		}
		stk = new StringTokenizer(bf.readLine());
		cost = new int[N];
		for(int i=0;i<N;i++) {
			cost[i] = Integer.parseInt(stk.nextToken());
		}
		
		/* 각 정점은 리터당 가격을 의미함
		 *   2   3   1
		 * 5 - 2 - 4 - 1
		 * 
		 * 일반적으로 총 소모해야 할 비용이 6리터니까 첫 주유소에서 30원으로 6리터를 충전해서 가면 끝까지 갈 순있다.
		 * 하지만 최적의 해가 아니므로
		 * 제일 왼쪽 도시에서 다음 도시로 가기위해 2리터(10)만을 충전하고, 다음 도시에서 3리터(6)의 비용을 그리고 다음 정점도시에서 1리터(4)을 충전하여 도착지로 가면 20원이고
		 * 이것도 최적의 해는 아니다.
		 * 최적의 해는
		 * 제일 왼쪽에서 2(10)리터의 기름을 넣고, 2번 도시에서 4리터(8)의 기름을 넣어서 한번에 도착지점으로 가는게 가장 최적의 해다. 
		 * */
		
		long min = 0;
		// 각 정점별로 한번에 도착지 까지 도달하는데 걸리는 비용을 계산한다.
		// 도착지는 계산 할 필요 없으므로 N-1개이다.
		long [] dCost = new long[N-1];
		// 거리의 누적합
		int [] Slen = new int[N];
		for(int i = 1;i<N;i++) {
			Slen[i] = Slen[i-1] + len[i-1];
		}
		// 각 도시별 한번에 도착지까지 가는데 걸리는 비용
		for(int i = 0;i<N-1;i++) {
			dCost[i] = (cost[i] * (Slen[N-1] - Slen[i]));
		}
		
//		System.out.println(Arrays.toString(len));
//		System.out.println(Arrays.toString(Slen));
//		System.out.println(Arrays.toString(dCost));
		int idx = 0;
		while(idx != N-1) {
			if(idx == N-2) {
				min+=(len[idx] * cost[idx]);
				idx++;
				continue;
			}
			long gc = 0; // 주유소마다 최소로 채운경우의 비용
			long tc = 0; // 현재 위치 주유소에서 해당 정점까지 한번에 가기위한 비용
			for(int j = idx+1;j<N;j++) {
				tc = ((Slen[j] - Slen[idx]) * cost[idx]);
				long tgc = (Slen[j] - Slen[j-1]) * cost[j-1];
//				System.out.println("idx : "+idx+"tc : "+(tc)+" gc : "+(gc+tgc));
				if(j == N-1) {
					min += tc;
					idx = N-1;
					break;
				}
				if(tc > gc+tgc) {
					min += (gc);
					idx= j-1;
					break;
				}
				gc += tgc;
			}
//			System.out.println("idx : "+idx +" min : "+min);
		}
		System.out.print(min);
	}
}