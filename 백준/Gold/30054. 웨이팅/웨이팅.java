import java.io.*;
import java.util.*;

public class Main{
	static int N;
	static StringTokenizer stk;
	static class Person{
		int idx;
		int reserve;
		int arrive;
		public Person(int i, int res, int arr) {
			this.idx = i;
			this.reserve = res;
			this.arrive = arr;
		}
		@Override
		public String toString() {
			return "Person [idx=" + idx + ", reserve=" + reserve + ", arrive=" + arrive + "]";
		}
		
	}
	public static void main(String [] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		boolean [] v = new boolean[N];
		int [][] map = new int[N][2];
		int [] reserve = new int[200001]; 
		// 해당 시간에 예약자가 있다면 해당 예약자 idx, 없다면 -1
		// idx를 통해 map 2차원 배열에서 해당 고객의 예약시간과 도착시간 모두 조회가능
		Arrays.fill(reserve, -1);
		Person [] ps =  new Person[N];
		// 도착시간을 기준으로 오름차순 정렬한 결과를 가질 Person 객체배열
		for(int i = 0;i<N;i++) {
			int idx = i;
			stk = new StringTokenizer(bf.readLine());
			int res = Integer.parseInt(stk.nextToken());
			int arr = Integer.parseInt(stk.nextToken());
			ps[i] = new Person(idx, res, arr);
			reserve[res] = idx;
			map[idx][0] = res;
			map[idx][1] = arr;
		}
		Arrays.sort(ps, (o1, o2)->{
			if(o1.arrive == o2.arrive) {
				return o1.reserve-o2.reserve;
			}
			return o1.arrive - o2.arrive;
		});
		int max = -1;
		int t= 1;
		int cnt = 0;
		int idx = 0;
		while(cnt < N) {
			// 우선 예약한 사람이 존재하는지 확인
			// 대기큐에 사람을 빼낼건지 예약한 사람을 사용할 수 있는지 여부를 나타내는 flg
			boolean flg = true;
			if(t <= 200000) {
				// 20 만 이후에 시간대에는 들어와있는 순서대로 식사하면됨
				if(reserve[t] != -1) {
					// 예약자가 존재한다면
					// 해당 예약자가 현재 바로 식당 출입이 가능한 상태인지 확인
					int pidx = reserve[t];
					if(map[pidx][1] <= t){
						// 해당 예약자의 도착 한 시간이 현재시간 이하라면 
						// 바로 식사가 가능함
						if(!v[pidx]) 
						{
							v[pidx] = true;
							cnt++;
							if(max < t - map[pidx][1]) {
								max = t - map[pidx][1];
							}
							flg = false;
							// 대기자 명단에서 데리고 올 이유가 없음
						}	
					}
				}
			}
			if(flg) {
				// 대기자 명단에서 뽑는것
				boolean isFind = false;
				// while문 자체가 안돌았는데 찾은걸로 취급할 순 없기 때문
				while(idx < N && ps[idx].arrive <= t) {
					// 현재 시간 이하의 도착시간을 가지는 사람들 중에 가장 먼저 도착한 사람이면서
					// 아직 식사를 안한사람을 데리고 가야함
					if(!v[ps[idx].idx]) {
						isFind = true;
						break;
					}
					idx++;
				}
				if(isFind) {
					// 대기자 명단에서 찾았을 경우
					v[ps[idx].idx] = true;
					cnt++;
					// 최대시간 갱신
					if(max < t - map[ps[idx].idx][1]) {
						max = t - map[ps[idx].idx][1];
					}
				}
			}
			t++;
		}
		System.out.println(max);
		
		// 전체적인 시나리오
		// 도착한 시간으로 1초부터 셈한다고 하면
		// 도착한 시간 순으로 정렬한다고 하면 만약 도착한 시간이 더 예약시간이 빠를경우로 정렬한다.
		// 3 1
		// 4 1
		// 1 2
		// 2 2
		// 6 3
		// 5 4
		// 1초에 도착 한 사람은 3시예약과 4시예약이신 분들이다.
		// 원래 1초의 예약시간을 가진 사람들 중에서 1초보다 작거나 같은 도착시간을 가진사람이 없으므로
		// 3시예약이신 분이 들어가게 된다. visite 처리
		// 2초에 도착한 사람은 1시 예약과 2시예약하신 분들이다.
		// 2시에 예약하신 분들 중에 2시보다 작거나 같은 도착시간을 가지신분이 한분 계시므로 저분을 올려보낸다. visit처리
		// 3초에 도착 한 사람은 6시 예약하신 분들이다.
		// 3초에 예약한 사람중에서 자신보다 작거나 같은 도착시간을 가졌지만 이미 처리된 분이므로 없다고 한다.
		// 아직 본인 시간이 되지 않았으므로 밀려난다. -> 큐에 넣으면 될듯
		// 대기큐에 가장 앞쪽에 있는 사람은 현재 1시에 도착하신 분 이므로 이분을 3초에 보낸다.
		// 4초에 도착한 사람은 5시에 예약하신 분들이다. -> 큐에 넣으면 될듯
		// 4초 예약은 이미 처리된 사람 뿐이므로, 신경쓰지않는다 -> 큐에 젤 앞단인 분을 보낸다.
		// 현재까지 큐( 6시예약, 5시예약 )
		// 5초에 도착한 사람은 없고, 5초에 예약한 사람들 중에서 먼저 한사람이 나오면된다.
		// 6초에 도착한 사람은 없고, 6초에 예약한 사람들 중에서 먼저 한사람이 나오면 된다.
	}
}