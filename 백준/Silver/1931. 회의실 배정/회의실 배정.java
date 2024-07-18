import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int [][] arr;
	static StringTokenizer stk;
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		arr = new int[N][2];
		for(int i = 0;i<N;i++) {
			stk = new StringTokenizer(bf.readLine());
			arr[i][0] = Integer.parseInt(stk.nextToken());
			arr[i][1] = Integer.parseInt(stk.nextToken());
		}
		// 회의 시작~ 종료 일때 
		// 서로 중첩된 요소들이 엄청많은 순간에서 최대 활용 개수를 셈한다.
		// 그러면 최대한 활용하려면 서로다른 회의의 각 종료시간과 시작시간 사이의 텀이 적어야한다.
		// 그리고 종료시간이 빨리 끝나는것부터 회의를 처리하면 최대가 될 수 있다.
		Arrays.sort(arr, (o1, o2)->{
			if(o1[1]  == o2[1]) { // 만약 종료시간이 서로 같다면 시작시간이 빠른것을 먼저 고려
				return o1[0] - o2[0];
			}
			return o1[1]-o2[1];
		});
		
		int curEt = -1; // 가장 종료시간이 이른 시간을 먼저 선택한다.
		int count = 0;
		for(int i= 0;i<N;i++) {
			if(curEt <=arr[i][0]) { // 이 때 시작과 동시에 종료되는 회의도 존재하므로 고려해야 한다.
				count++;
				curEt = arr[i][1];
			}
		}
		System.out.println(count);
		
	}
}