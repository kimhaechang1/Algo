import java.util.*;
import java.io.*;

public class Main{
	static int N;
	static long [] len;
	static StringTokenizer stk;
	static long [] cost;
	public static void main(String [] args) throws Exception{
		BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		stk = new StringTokenizer(bf.readLine());
		len = new long[N-1];
		for(int i=0;i<len.length;i++) {
			len[i] = Integer.parseInt(stk.nextToken());
		}
		stk = new StringTokenizer(bf.readLine());
		cost = new long[N];
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
		
		// 결국 총 비용을 최소화 하려면 리터당 가격이 싼 기름을 넣어야 한다.
		// 이전에 주유한 리터당 비용보다 싼 주유소만을 거쳐가면 가장 최소가 된다.
		// 즉 만약 위의 그림에서 1번째 주유소(리터당 가격2)인 곳에서 한번에 도착하는게 나은지는 조금씩 이동해보면서 알 수 있다.
		// 처음에 최소 리터당 가격은 5이고 가려하는 다음 지역까지는 첫 지역에서 주유한 만큼 가야한다.
		// 다음은 1번 지역이고 1번지역 주유소가 더 가격이 저렴하다. 2번 주유소 까지 1번 주유소의 가격으로 이동한다.
		// 2번 주유소는 가격을 보니 최소 리터당 가격보다 더비싸다
		// 따라서 2번 주유소에서 충전을 더 했다고 가정하고 3번 주유소로 이동한다.
		// 3번은 N-1이므로 도착으로 종료한다.
		long sum = 0;
		long min = cost[0];
		for(int i = 0;i<N-1;i+=1) {
			if(cost[i] < min) {
				min = cost[i];
			}
			
			sum += (min * len[i]);
		}
		System.out.print(sum);
	}
}