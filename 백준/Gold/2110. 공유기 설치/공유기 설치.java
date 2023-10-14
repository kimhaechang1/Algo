import java.util.*;
import java.io.*;

public class Main {
	static StringTokenizer stk;
	static int N;
	static int C;
	static int [] arr;
	static int max;
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		stk = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(stk.nextToken());
		C = Integer.parseInt(stk.nextToken());
		arr = new int[N];
		for(int i = 0 ;i<N;i++) {
			arr[i] = Integer.parseInt(bf.readLine());
		}
		max = Integer.MIN_VALUE;
		Arrays.sort(arr);
		int start = 1;
		int end = arr[arr.length-1] - arr[0]+1;
		// 공유기를 설치하는 데 있어서 
		// 최소거리의 최대치는 한계가 무조건 있게 되고 그 거리보다 더 큰 거리를 기준으로
		// 탐색을 하였어도 다른 공유기 사이 인접한 거리가 더 짧아지게 된다
		// 공유기 사이의 거리를 1로 두었을 때 부터 최대 공유기 사이 거리를 두었을 때 
		// 공유기를 실제로 해당 거리보다 크거나 같게 목표 개수만큼 설치할 수 있는 경우 최대값 갱신
		// 근데 사이거리를 만들 수 있는 방법은 여러가지 이고
		// 여기서 중요한 것은 최소거리를 매번 start end로 범위를 좁혀가며 찾아가는데
		// 해당 최소거리보다 같거나 큰 지점에 공유기를 설치해보며, 직전 공유기 설치위치에서부터 계속 찾아야 한다는 것이 포인트이다.
		// 또한 그렇게 찾은 설치가능한 공유기 수가 설치해야하는 공유기 수와 비교하며 범위를 좁혀 나가야 한다.
		// 왜냐하면 최소거리에 따라 누가 바뀌냐 -> 설치 가능한 공유기 수가 바뀐다.!!!
		// 최대 가능한 사이거리를 계산 할 때, 그냥 최대길이에서 최소를 빼주게 된다면 실제 거리를 구할 수 없게 된다.
		// 따라서 빼주고 +1을 해주어야한다.
		while(start < end) {
			int mid = (start + end)/2;
			int res = check(mid);
			
			if(res < C) {
				// 현재 정한 최소간격으로는 공유기를 모두 설치하기 힘들다.
				// 따라서 최소간격으로 선택할 범위를 더 작은쪽으로 줄여야 한다.
				end = mid;
			}else {
				// 현재 정한 최소간격으로 공유기를 모두 설치 가능하다.
				// 따라서 계속해서 간격을 더 넓은 범위에서 찾아가며 
				// 공유기 설치 개수가 뚝 떨어지는 순간을 찾아야 한다.
				start = mid+1;
			}
			
		}
		System.out.println(end-1);
	}
	static int check(int dis) {
		int c = 1;
		// 첫번째 집은 무조건 설치 할 수 있음
		// 첫번째 집에서 부터 시작해서 해당 dis값보다 크거나 같을 때 설치 할 수 있게 됨
		// 왜냐하면 주어진 거리가 최소 인 경우 이므로 해당 거리보다 더 큰곳이면 당연히 설치 할 수 있게 된다.
		int prev = arr[0];
		for(int i= 1;i<N;i++) {
			int target = arr[i];
			
			if(target - prev >=dis){
				c++;
				prev = target;
			}
		}
		return c;
	}
}
