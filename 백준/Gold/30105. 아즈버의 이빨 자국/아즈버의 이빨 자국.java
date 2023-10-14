import java.util.*;
import java.io.*;

public class Main{
	static int [] arr;
	static int N;
	static StringTokenizer stk;
	public static void main(String [] args) throws Exception{
		BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		arr = new int[N];
		Map<Integer, Integer> map = new HashMap<>();
		stk = new StringTokenizer(bf.readLine());
		for(int i = 0 ;i<N;i++) {
			arr[i] = Integer.parseInt(stk.nextToken());
			map.put(arr[i], i);
		}
        // 기존에 좌표를 기준으로 브루트포스를 돌리기 보다는
        // 시간초과가 터지니까 간격을 기준으로 브루트포스를 돌릴 생각으로
        // 구할 수 있는 값에 대해서 어떤 값을 기준으로 브루트포스를 진행할 것인지 찾아나가자
		Set<Integer> set = new HashSet<>();
		StringBuilder sb = new StringBuilder();
		int cnt = 0;
		ArrayList<Integer> list = new ArrayList<>();
		for(int i = 1;i<N;i++) {
			// 이빨자국의 가능성이 있는 간격만을 본다.
			// 즉 기준점은 누가되도 상관이 없으나, arr[0]기준으로 이빨을 찍으면 무조건 변위에따른 수직선상에 다른 좌표가 생긴다.
			// 해당 간격이 다른 시작점에서도 생길 수 있는 것이다.
			// 그러므로 전체 변위를 살펴볼 필요는 없고, 0번을 기준으로 오름차순된 좌표들사이의 차가 가능성 있는 간격이 된다.
			set.add(arr[i]-arr[0]);
		}
		for(int interval : set) {
			boolean flg = true;
			for(int i = 0;i<N;i++) {
				int min = arr[i] - interval;
				if(map.containsKey(min)) {
					continue;
				}
				int max = arr[i] + interval;
				if(map.containsKey(max)) {
					continue;
				}
				flg = false;
				break;
			}
			if(flg) {
				cnt++;
				list.add(interval);
			}
		}
		Collections.sort(list);
		sb.append(cnt).append("\n");
		for(int n : list) sb.append(n).append(" ");
		System.out.print(sb);
	}
}