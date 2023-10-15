import java.util.*;
import java.io.*;

public class Main{
	static int N;
	static StringTokenizer stk;
	public static void main(String [] args) throws Exception{
		BufferedReader bf= new BufferedReader(new InputStreamReader(System.in));
		// 모든 수가 자기 머리위보다 크다는것이 보장되어 있다면, 그냥 뒤에서부터 5번째 수를 찾으면 된다.
		N = Integer.parseInt(bf.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2)->{
			return o2-o1;
		});
		for(int i=0;i<N;i++) {
			stk = new StringTokenizer(bf.readLine());
			for(int j= 0;j<N;j++) {
				pq.add(Integer.parseInt(stk.nextToken()));
			}
		}
		while(N-- >1) {
			pq.poll();
		}
		System.out.println(pq.peek());
	}
}